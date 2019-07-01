package com.puppycrawl.tools.checkstyle.permission;

import org.junit.Test;

public class MainTest {

    @Test
    public void testPermissionCheck()
            throws Exception {
//        Main.main("-c", "/Users/fengdi/Desktop/Permissions/config.xml",
//                "/Users/fengdi/Desktop/Permissions/src-to-check");

        Main.main("-c", "/Users/fengdi/Desktop/Permissions/config-full-src.xml",
                "/Users/fengdi/Desktop/Permissions/android-28");

//        Main.main("-c", "/Users/fengdi/Desktop/Permissions/config_manifest.xml",
//                "/Users/fengdi/Desktop/Permissions/src-to-check/android/Manifest.java");
    }

}
