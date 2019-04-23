package com.puppycrawl.tools.checkstyle.checks.permission;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FullIdent;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.permission.PermissionCollector;

public class ClassDumper extends AbstractCheck {

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
                collect(ast);
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

    private void collect(DetailAST classAst) {
        List<String> allCallers = getAllCallers(classAst);
        if (allCallers != null) {
            for (String caller : allCallers) {

                String relevantClass = PermissionCollector.getInstance().getRelevantClass(caller);
                if (relevantClass != null && relevantClass.length() > 0) {
                    PermissionCollector.getInstance().addClassDump(relevantClass, caller);
                }
            }
        }
    }

    private List<String> getAllCallers(DetailAST classDef) {
        // 找到外层第一个CLASS_DEF
        if (classDef == null) {
            return null;
        }
        // 获取所有的函数列表，并进行存储
        DetailAST objBlock = classDef.findFirstToken(TokenTypes.OBJBLOCK);
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
