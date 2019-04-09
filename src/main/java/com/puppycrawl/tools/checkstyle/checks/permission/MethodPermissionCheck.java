package com.puppycrawl.tools.checkstyle.checks.permission;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.oracle.tools.packager.Log;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FullIdent;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.permission.PermissionCollector;
import com.puppycrawl.tools.checkstyle.utils.CommonUtil;

public class MethodPermissionCheck extends AbstractCheck {

    private static final Logger LOG = Logger.getLogger(MethodPermissionCheck.class.getName());

    private static final String COMMENT_LINK_PERM_ID = "{@link android.Manifest.permission#";
    private static final String COMMENT_LINK_PERM_PREF = "android.Manifest.permission#";
    private static final String COMMENT_LINK_PERM_SUF = "}";

    private static final String COMMENT_CODE_PERM_ID = "{@code android.permission.";
    private static final String COMMENT_CODE_PERM_PREF = "android.permission.";
    private static final String COMMENT_CODE_PERM_SUF = "}";

    private static final String COMMENT_GAP_PREF = " ";

    private String packageName = "";

    @Override
    public int[] getDefaultTokens() {
        return new int[]{TokenTypes.METHOD_DEF, TokenTypes.PACKAGE_DEF};
    }

    @Override
    public int[] getAcceptableTokens() {
        return new int[]{TokenTypes.METHOD_DEF, TokenTypes.PACKAGE_DEF};
    }

    @Override
    public int[] getRequiredTokens() {
        return CommonUtil.EMPTY_INT_ARRAY;
    }

    @Override
    public void visitToken(DetailAST ast) {
        switch (ast.getType()) {
            case TokenTypes.PACKAGE_DEF:
                recordPackageName(ast);
                break;
            case TokenTypes.METHOD_DEF:
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
        // 扫描目标不含有任何的函数实现，均为Stub，理论上所有的权限信息，均在MODIFIERS的Comment中寻找即可，按照定义标准进行匹配
        DetailAST modifiers = methodAst.findFirstToken(TokenTypes.MODIFIERS);
        if (modifiers != null) {
            // 找到TokenTypes.MODIFIERS中所有的TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.COMMENT_CONTENT
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
        if (commentLine.contains(COMMENT_LINK_PERM_ID)) {
            String[] comments = commentLine.split(COMMENT_GAP_PREF);
            for (String comment : comments) {
                comment = comment.trim();
                if (comment.startsWith(COMMENT_LINK_PERM_PREF)) {
                    String permission = comment.replace(COMMENT_LINK_PERM_PREF, "");
                    int sufIndex = permission.indexOf(COMMENT_LINK_PERM_SUF);
                    if (sufIndex >= 0) {
                        permission = permission.substring(0, sufIndex);
                    }
                    Log.info("checkHasPermission got:" + permission);
                    String caller = getCaller(commentAst);
                    PermissionCollector.getInstance().addCommentPermission(permission, caller);
                }
            }
        } else if (commentLine.contains(COMMENT_CODE_PERM_ID)) {
            String[] comments = commentLine.split(COMMENT_GAP_PREF);
            for (String comment : comments) {
                comment = comment.trim();
                if (comment.startsWith(COMMENT_CODE_PERM_PREF)) {
                    String permission = comment.replace(COMMENT_CODE_PERM_PREF, "");
                    int sufIndex = permission.indexOf(COMMENT_CODE_PERM_SUF);
                    if (sufIndex >= 0) {
                        permission = permission.substring(0, sufIndex);
                    }
                    Log.info("checkHasPermission got:" + permission);
                    String caller = getCaller(commentAst);
                    PermissionCollector.getInstance().addCommentPermission(permission, caller);
                }
            }
        }
    }

    private String getCaller(DetailAST commentAst) {
        String methodName = "";
        String returnType = "";
        List<String> params = new ArrayList<>();
        DetailAST tempMethodAST = commentAst;
        while (tempMethodAST != null) {
            // 找到最近的一层Method
            if (tempMethodAST.getType() == TokenTypes.METHOD_DEF) {
                methodName = tempMethodAST.findFirstToken(TokenTypes.IDENT).getText();
                // 获取返回值
                DetailAST typeAST = tempMethodAST.findFirstToken(TokenTypes.TYPE);
                FullIdent fullIdent = FullIdent.createFullIdent(typeAST.getFirstChild());
                returnType = fullIdent.getText();
                // 获取参数列表
                DetailAST parameters = tempMethodAST.findFirstToken(TokenTypes.PARAMETERS);
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
                break;
            }
            tempMethodAST = tempMethodAST.getParent();
        }
        // 向上找到所有的Class, Interface
        List<String> classNameList = new ArrayList<>();
        tempMethodAST = commentAst;
        while (tempMethodAST != null) {
            if (tempMethodAST.getType() == TokenTypes.CLASS_DEF
                    || tempMethodAST.getType() == TokenTypes.INTERFACE_DEF) {
                final String className = tempMethodAST.findFirstToken(TokenTypes.IDENT).getText();
                classNameList.add(className);
            }
            tempMethodAST = tempMethodAST.getParent();
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
