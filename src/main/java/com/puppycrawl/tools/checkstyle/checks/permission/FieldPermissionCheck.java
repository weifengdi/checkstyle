package com.puppycrawl.tools.checkstyle.checks.permission;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FullIdent;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.permission.PermissionCollector;
import com.puppycrawl.tools.checkstyle.utils.CommonUtil;

public class FieldPermissionCheck extends AbstractCheck {

    private static final Logger LOG = Logger.getLogger(FieldPermissionCheck.class.getName());

    private String packageName = "";

    @Override
    public int[] getDefaultTokens() {
        return new int[]{TokenTypes.VARIABLE_DEF, TokenTypes.PACKAGE_DEF};
    }

    @Override
    public int[] getAcceptableTokens() {
        return new int[]{TokenTypes.VARIABLE_DEF, TokenTypes.PACKAGE_DEF};
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
            case TokenTypes.VARIABLE_DEF:
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

    private void checkPermission(DetailAST variableAST) {
        DetailAST modifiers = variableAST.findFirstToken(TokenTypes.MODIFIERS);
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

        Set<String> permissions = CommentUtil.getPermissions(commentLine);

        if (permissions != null && permissions.size() > 0) {
            for (String permission : permissions) {
                String desc = getDesc(commentAst);
                PermissionCollector.getInstance().addFieldPermission(permission, desc);
            }
        }

        Set<String> potentialPermissions = new HashSet<>();
        CommentUtil.getPotentialPermissions(commentLine, potentialPermissions, "android.Manifest.permission#", null);
        CommentUtil.getPotentialPermissions(commentLine, potentialPermissions, "android.permission.", null);
        CommentUtil.getPotentialPermissions(commentLine, potentialPermissions, " ", " permission");

        if (potentialPermissions != null && potentialPermissions.size() > 0) {
            for (String permission : potentialPermissions) {
                String desc = getDesc(commentAst);
                PermissionCollector.getInstance().addFieldPermission(permission, desc);
            }
        }
    }

    private String getDesc(DetailAST commentAst) {
        DetailAST variableAST = null;
        DetailAST tempFieldAST = commentAst;
        while (tempFieldAST != null) {
            // 找到最近的一层Method
            if (tempFieldAST.getType() == TokenTypes.VARIABLE_DEF) {
                variableAST = tempFieldAST;
                break;
            }
            tempFieldAST = tempFieldAST.getParent();
        }
        if (variableAST == null) {
            LOG.info("getDesc but no variableAST got!");
            return "";
        }
        // 获取相关属性
        String fieldName = variableAST.findFirstToken(TokenTypes.IDENT).getText();
        // 获取返回值
        DetailAST typeAST = variableAST.findFirstToken(TokenTypes.TYPE);
        FullIdent fullIdent = FullIdent.createFullIdent(typeAST.getFirstChild());
        String fieldType = fullIdent.getText();
        // 获取赋值信息
        String assignInfo = null;
        DetailAST assignAST = variableAST.findFirstToken(TokenTypes.ASSIGN);
        if (assignAST != null) {
            DetailAST exprAST = assignAST.findFirstToken(TokenTypes.EXPR);
            DetailAST valueAST = exprAST.getFirstChild();
            assignInfo = valueAST.getText();
        }
        // 向上找到所有的Class, Interface
        List<String> classNameList = new ArrayList<>();
        tempFieldAST = commentAst;
        while (tempFieldAST != null) {
            if (tempFieldAST.getType() == TokenTypes.CLASS_DEF
                    || tempFieldAST.getType() == TokenTypes.INTERFACE_DEF) {
                final String className = tempFieldAST.findFirstToken(TokenTypes.IDENT).getText();
                classNameList.add(0, className);
            }
            tempFieldAST = tempFieldAST.getParent();
        }
        // 与package用规则串联到一起
        String desc = packageName;
        for (int i = 0; i < classNameList.size(); i++) {
            if (i == 0) {
                desc += "." + classNameList.get(i);
            } else {
                desc+= "$" + classNameList.get(i);
            }
        }
        desc = fieldType + " " + desc + "." + fieldName;
        if (assignInfo != null) {
            desc = desc + "=" + assignInfo;
        }

        return desc;
    }

    @Override
    public boolean isCommentNodesRequired() {
        return true;
    }

}
