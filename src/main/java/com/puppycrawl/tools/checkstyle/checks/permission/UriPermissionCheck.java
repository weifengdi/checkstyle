package com.puppycrawl.tools.checkstyle.checks.permission;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;

public class UriPermissionCheck extends AbstractCheck {

    @Override
    public int[] getDefaultTokens() {
        return new int[0];
    }

    @Override
    public int[] getAcceptableTokens() {
        return new int[0];
    }

    @Override
    public int[] getRequiredTokens() {
        return new int[0];
    }

    @Override
    public void visitToken(DetailAST ast) {
    }

}
