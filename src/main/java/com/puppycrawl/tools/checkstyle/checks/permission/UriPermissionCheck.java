package com.puppycrawl.tools.checkstyle.checks.permission;

import java.util.*;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FullIdent;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.permission.PermissionCollector;

public class UriPermissionCheck extends AbstractCheck {

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
        return new int[0];
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

    private void checkPermission(DetailAST variableAST) {
        // 检测是否为AUTHORITY字段
        String fieldName = variableAST.findFirstToken(TokenTypes.IDENT).getText();
        // 获取类型
        DetailAST typeAST = variableAST.findFirstToken(TokenTypes.TYPE);
        FullIdent fullIdent = FullIdent.createFullIdent(typeAST.getFirstChild());
        String fieldType = fullIdent.getText();
        if (!(fieldName.equals("AUTHORITY") && fieldType.equals("java.lang.String"))) {
            return;
        }
        String desc = getDesc(variableAST);
        PermissionCollector.getInstance().addUri(desc);
        Set<String> permissions = getNearestPermissions(variableAST);
        if (permissions != null && permissions.size() > 0) {
            for (String permission : permissions) {
                PermissionCollector.getInstance().addUriPermission(permission, desc);
            }
        }
    }

    private Set<String> getNearestPermissions(DetailAST variableAST) {
        DetailAST variableComment = CommentUtil.getCommentDetailAST(variableAST);
        // 向上找到最近的Class
        DetailAST classComment = null;
        DetailAST tempClassAST = variableAST;
        while (tempClassAST != null) {
            if (tempClassAST.getType() == TokenTypes.CLASS_DEF
                    || tempClassAST.getType() == TokenTypes.INTERFACE_DEF) {
                classComment = CommentUtil.getCommentDetailAST(tempClassAST);
                break;
            }
            tempClassAST = tempClassAST.getParent();
        }
        // 找到所有潜在的权限
        Set<String> permissionsSet = Collections.synchronizedSet(new HashSet<>());
        collectPermissions(variableComment, permissionsSet);
        if (classComment != null) {
            collectPermissions(classComment, permissionsSet);
        }

        return permissionsSet;
    }

    private void collectPermissions(DetailAST commentAst, Set<String> permissionsSet) {
        if (commentAst == null) {
            return;
        }
        String commentLine = commentAst.getText();

        Set<String> permissions = CommentUtil.getPermissions(commentLine);

        if (permissionsSet == null) {
            permissionsSet = new HashSet<>();
        }
        if (permissions != null && permissions.size() > 0) {
            for (String permission : permissions) {
                permissionsSet.add(permission);
            }
        }

        CommentUtil.getPotentialPermissions(commentLine, permissionsSet, "android.Manifest.permission#", null);
        CommentUtil.getPotentialPermissions(commentLine, permissionsSet, "android.permission.", null);
        CommentUtil.getPotentialPermissions(commentLine, permissionsSet, " ", " permission");
    }

    private String getDesc(DetailAST fieldAST) {
        // 获取相关属性
        String fieldName = fieldAST.findFirstToken(TokenTypes.IDENT).getText();
        // 获取返回值
        DetailAST typeAST = fieldAST.findFirstToken(TokenTypes.TYPE);
        FullIdent fullIdent = FullIdent.createFullIdent(typeAST.getFirstChild());
        String fieldType = fullIdent.getText();
        // 获取赋值信息
        String assignInfo = null;
        DetailAST assignAST = fieldAST.findFirstToken(TokenTypes.ASSIGN);
        if (assignAST != null) {
            DetailAST exprAST = assignAST.findFirstToken(TokenTypes.EXPR);
            DetailAST valueAST = exprAST.getFirstChild();
            assignInfo = valueAST.getText();
        }
        // 向上找到所有的Class, Interface
        List<String> classNameList = new ArrayList<>();
        DetailAST tempFieldAST = fieldAST;
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

    private void recordPackageName(DetailAST packageAST) {
        DetailAST packageNameAst = packageAST.getLastChild().getPreviousSibling();
        FullIdent fullIdent = FullIdent.createFullIdent(packageNameAst);
        packageName = fullIdent.getText();
    }

    @Override
    public boolean isCommentNodesRequired() {
        return true;
    }

}
