package com.puppycrawl.tools.checkstyle.checks.permission;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FullIdent;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.permission.PermissionCollector;

public class ClassPermissionCheck extends AbstractCheck {

    private static final Logger LOG = Logger.getLogger(MethodPermissionCheck.class.getName());

    private String packageName = "";

    @Override
    public int[] getDefaultTokens() {
        return new int[]{TokenTypes.CLASS_DEF, TokenTypes.PACKAGE_DEF};
    }

    @Override
    public int[] getAcceptableTokens() {
        return new int[]{TokenTypes.CLASS_DEF, TokenTypes.PACKAGE_DEF};
    }

    @Override
    public int[] getRequiredTokens() {
        return new int[0];
    }

    @Override
    public void visitToken(DetailAST ast) {
        switch (ast.getType()) {
            case TokenTypes.PACKAGE_DEF:
                recordPackageName(ast);
                break;
            case TokenTypes.CLASS_DEF:
                checkPermission(ast);
                break;
            default:
                final String exceptionMsg = "Unexpected token type: " + ast.getText();
                throw new IllegalArgumentException(exceptionMsg);
        }
    }

    private void recordPackageName(DetailAST packageAST) {
        DetailAST packageNameAst = packageAST.getLastChild().getPreviousSibling();
        FullIdent fullIdent = FullIdent.createFullIdent(packageNameAst);
        packageName = fullIdent.getText();
    }

    private void checkPermission(DetailAST methodAst) {
        DetailAST modifiers = methodAst.findFirstToken(TokenTypes.MODIFIERS);
        if (modifiers != null) {
            DetailAST child = modifiers.getFirstChild();
            while (child.getNextSibling() != null) {
                switch (child.getType()) {
                    case TokenTypes.SINGLE_LINE_COMMENT:
                    case TokenTypes.BLOCK_COMMENT_BEGIN:
                        DetailAST commentContent = child.findFirstToken(TokenTypes.COMMENT_CONTENT);
                        checkHasPermission(commentContent);
                        break;
                    // 其中有部分是在Annotation里面，所以也可能需要找到TokenTypes.ANNOTATION
                    case TokenTypes.ANNOTATION:
                        DetailAST annotationChild = child.getFirstChild();
                        while (annotationChild.getNextSibling() != null) {
                            switch (annotationChild.getType()) {
                                case TokenTypes.SINGLE_LINE_COMMENT:
                                case TokenTypes.BLOCK_COMMENT_BEGIN:
                                    DetailAST commentContent2 = annotationChild.findFirstToken(TokenTypes.COMMENT_CONTENT);
                                    checkHasPermission(commentContent2);
                                    break;
                                default:
                                    break;
                            }
                            annotationChild = annotationChild.getNextSibling();
                        }
                        break;
                    default:
                        break;
                }
                child = child.getNextSibling();
            }
        }
    }

    private void checkHasPermission(DetailAST commentAst) {
        String commentLine = commentAst.getText();

        Set<String> permissions = CommentUtil.getPermissionInClassComment(commentLine);

        if (permissions != null && permissions.size() > 0) {
            List<String> callers = getAllCallers(commentAst);
            if (callers != null && callers.size() > 0) {
                for (String permission : permissions) {
                    for (String caller : callers) {
                        PermissionCollector.getInstance().addClassPermission(permission, caller);
                    }
                }
            }
        }
    }

    private List<String> getAllCallers(DetailAST commentAst) {
        // 找到外层第一个CLASS_DEF
        DetailAST targetClassDef = null;
        DetailAST tempTargetClassDef = commentAst;
        while (tempTargetClassDef != null) {
            // 找到最近的一层Method
            if (tempTargetClassDef.getType() == TokenTypes.CLASS_DEF) {
                targetClassDef = tempTargetClassDef;
                break;
            }
            tempTargetClassDef = tempTargetClassDef.getParent();
        }
        if (targetClassDef == null) {
            return null;
        }
        // 获取所有的函数列表，并进行存储
        DetailAST objBlock = targetClassDef.findFirstToken(TokenTypes.OBJBLOCK);
        if (objBlock == null) {
            return null;
        }
        List<String> callers = new ArrayList<>();
        DetailAST child = objBlock.getFirstChild();
        while (child != null) {
            if (child.getType() == TokenTypes.METHOD_DEF) {
                String caller = getCaller(child);
                callers.add(caller);
            }
            child = child.getNextSibling();
        }

        if (callers.size() > 0) {
            return callers;
        }
        return null;
    }

    private String getCaller(DetailAST methodAST) {
        String methodName = "";
        String returnType = "";
        List<String> params = new ArrayList<>();
        methodName = methodAST.findFirstToken(TokenTypes.IDENT).getText();
        // 获取返回值
        DetailAST typeAST = methodAST.findFirstToken(TokenTypes.TYPE);
        FullIdent fullIdent = FullIdent.createFullIdent(typeAST.getFirstChild());
        returnType = fullIdent.getText();
        // 获取参数列表
        DetailAST parameters = methodAST.findFirstToken(TokenTypes.PARAMETERS);
        DetailAST child = parameters.getFirstChild();
        while (child != null) {
            // children are PARAMETER_DEF and COMMA
            if (child.getType() == TokenTypes.PARAMETER_DEF) {
                DetailAST paramName = child.findFirstToken(TokenTypes.IDENT);
                DetailAST paramTypeAST = child.findFirstToken(TokenTypes.TYPE);
                FullIdent paramFullIdent = FullIdent.createFullIdent(paramTypeAST.getFirstChild());
                params.add(paramFullIdent.getText() + " " + paramName.getText());
            }
            child = child.getNextSibling();
        }
        // 向上找到所有的Class, Interface
        List<String> classNameList = new ArrayList<>();
        DetailAST tempClassAST = methodAST;
        while (tempClassAST != null) {
            if (tempClassAST.getType() == TokenTypes.CLASS_DEF
                    || tempClassAST.getType() == TokenTypes.INTERFACE_DEF) {
                final String className = tempClassAST.findFirstToken(TokenTypes.IDENT).getText();
                classNameList.add(0, className);
            }
            tempClassAST = tempClassAST.getParent();
        }
        // 与package用规则串联到一起
        String caller = packageName;
        for (int i = 0; i < classNameList.size(); i++) {
            if (i == 0) {
                caller += "." + classNameList.get(i);
            } else {
                caller+= "$" + classNameList.get(i);
            }
        }
        String paramsString = "";
        for (String paramString : params) {
            if (paramsString.length() > 0) {
                paramsString += ", ";
            }
            paramsString += paramString;
        }
        caller = returnType + " " + caller + "." + methodName + "(" + paramsString + ")";
        return caller;
    }

    @Override
    public boolean isCommentNodesRequired() {
        return true;
    }

}
