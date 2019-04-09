package com.puppycrawl.tools.checkstyle.permission;

import org.junit.Test;

public class MainTest {

    @Test
    public void testPermissionCheck()
            throws Exception {
        Main.main("-c", "/Users/fengdi/Downloads/Permissions/config.xml",
                "/Users/fengdi/Downloads/Permissions/src-to-check");
    }

}
