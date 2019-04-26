package com.puppycrawl.tools.checkstyle.permission;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import com.puppycrawl.tools.checkstyle.checks.permission.FileUtil;

public class ManifestHelper {

    private static final Logger LOG = Logger.getLogger(ManifestHelper.class.getName());

    private static ManifestHelper sInstance;

    public static ManifestHelper getInstance() {
        if (sInstance == null) {
            synchronized (ManifestHelper.class) {
                if (sInstance == null) {
                    sInstance = new ManifestHelper();
                }
            }
        }
        return sInstance;
    }

    private static final Set<String> sensitive_perms_backup = Collections.synchronizedSet(new HashSet<>());
    private static final Set<String> black_perms_backup = Collections.synchronizedSet(new HashSet<>());

    private static final Set<String> sensitive_perms_group_backup = Collections.synchronizedSet(new HashSet<>());

    private static final Set<String> black_perms = Collections.synchronizedSet(new HashSet<>());
    private static final Set<String> sensitive_perms = Collections.synchronizedSet(new HashSet<>());
    private static final Set<String> normal_perms = Collections.synchronizedSet(new HashSet<>());

    private static final Set<String> black_perm_groups = Collections.synchronizedSet(new HashSet<>());
    private static final Set<String> sensitive_perm_groups = Collections.synchronizedSet(new HashSet<>());
    private static final Set<String> normal_perm_groups = Collections.synchronizedSet(new HashSet<>());

    public void init() {
        Collections.addAll(sensitive_perms_backup, "android.permission.ACCESS_LOCATION_EXTRA_COMMANDS", "android.permission.ACCESS_MEDIA_LOCATION"
                , "android.permission.ACCESS_WIFI_STATE", "android.permission.BATTERY_STATS"
                , "android.permission.BIND_ACCESSIBILITY_SERVICE", "android.permission.BIND_AUTOFILL_SERVICE"
                , "android.permission.BIND_CALL_REDIRECTION_SERVICE", "android.permission.BLUETOOTH"
                , "android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH_PRIVILEGED"
                , "android.permission.CHANGE_CONFIGURATION", "android.permission.CHANGE_NETWORK_STATE"
                , "android.permission.CHANGE_WIFI_STATE", "android.permission.DISABLE_KEYGUARD"
                , "android.permission.EXPAND_STATUS_BAR", "android.permission.FOREGROUND_SERVICE"
                , "android.permission.GET_ACCOUNTS_PRIVILEGED", "android.permission.GET_PACKAGE_SIZE"
                , "android.permission.GET_TASKS", "android.permission.KILL_BACKGROUND_PROCESSES"
                , "android.permission.NFC", "android.permission.NFC_TRANSACTION_EVENT"
                , "android.permission.READ_LOGS", "android.permission.READ_MEDIA_AUDIO"
                , "android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_VIDEO"
                , "android.permission.REORDER_TASKS", "android.permission.SMS_FINANCIAL_TRANSACTIONS"
                , "android.permission.USE_BIOMETRIC", "android.permission.USE_FINGERPRINT"
                , "android.permission.USE_FULL_SCREEN_INTENT", "android.permission.VIBRATE"
                , "android.permission.MANAGE_FINGERPRINT", "android.permission.FLASHLIGHT"
                , "android.permission.MANAGE_ACCOUNTS");
        Collections.addAll(sensitive_perms_backup, "android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR"
                , "android.permission.READ_CALL_LOG", "android.permission.WRITE_CALL_LOG"
                , "android.permission.PROCESS_OUTGOING_CALLS", "android.permission.CAMERA"
                , "android.permission.READ_CONTACTS", "android.permission.WRITE_CONTACTS"
                , "android.permission.GET_ACCOUNTS", "android.permission.ACCESS_FINE_LOCATION"
                , "android.permission.ACCESS_COARSE_LOCATION", "android.permission.RECORD_AUDIO"
                , "android.permission.READ_PHONE_STATE", "android.permission.READ_PHONE_NUMBERS"
                , "android.permission.CALL_PHONE", "android.permission.ANSWER_PHONE_CALLS"
                , "android.permission.ADD_VOICEMAIL", "android.permission.USE_SIP"
                , "android.permission.BODY_SENSORS", "android.permission.SEND_SMS"
                , "android.permission.RECEIVE_SMS", "android.permission.READ_SMS"
                , "android.permission.RECEIVE_WAP_PUSH", "android.permission.RECEIVE_MMS"
                , "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE");
        Collections.addAll(black_perms_backup, "android.permission.CALL_COMPANION_APP", "android.permission.CHANGE_WIFI_MULTICAST_STATE"
                , "android.permission.INSTANT_APP_FOREGROUND_SERVICE", "android.permission.MANAGE_OWN_CALLS"
                , "android.permission.PACKAGE_USAGE_STATS", "android.permission.REQUEST_COMPANION_RUN_IN_BACKGROUND"
                , "android.permission.REQUEST_COMPANION_USE_DATA_IN_BACKGROUND", "android.permission.REQUEST_DELETE_PACKAGES"
                , "android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS", "com.android.alarm.permission.SET_ALARM"
                , "android.permission.SET_WALLPAPER", "android.permission.SET_WALLPAPER_HINTS"
                , "android.permission.TRANSMIT_IR");

        Collections.addAll(sensitive_perms_group_backup, "android.permission-group.CALENDAR", "android.permission-group.CALL_LOG"
                , "android.permission-group.CAMERA", "android.permission-group.CONTACTS"
                , "android.permission-group.LOCATION", "android.permission-group.MICROPHONE"
                , "android.permission-group.PHONE", "android.permission-group.SENSORS"
                , "android.permission-group.SMS", "android.permission-group.STORAGE"
                , "android.permission-group.ACTIVITY_RECOGNITION", "android.permission-group.MEDIA_AURAL"
                , "android.permission-group.MEDIA_VISUAL");
    }

    public void recordPerm(String comment, String value, String desc) {
        if (comment == null) {
            comment = "";
        }
        if (value == null) {
            value = "";
        }
        if (desc == null) {
            desc = "";
        }
        value = value.replace("\"", "");
        if (value.contains("permission-group")) {
            if (sensitive_perms_group_backup.contains(value)) {
                sensitive_perm_groups.add(desc);
            } else {
                normal_perm_groups.add(desc);
            }
        } else {
            if (black_perms_backup.contains(value)) {
                black_perms.add(desc);
            } else if (sensitive_perms_backup.contains(value)) {
                sensitive_perms.add(desc);
            } else {
                if (comment.contains("Protection level: signature")
                        || comment.contains("This is not available to third party applications")
                        || comment.contains("This permission cannot be granted to third-party apps")
                        || comment.contains("Not for use by third-party applications")) {
                    black_perms.add(desc);
                } else if (comment.contains("Protection level: dangerous")) {
                    sensitive_perms.add(desc);
                } else {
                    normal_perms.add(desc);
                }
            }
        }
    }

    public void dumpManifestScanResult() {
        StringBuilder manifestCollection = new StringBuilder();
        manifestCollection.append("black_perms");
        for (String desc : black_perms) {
            manifestCollection.append("\n");
            manifestCollection.append("    ");
            manifestCollection.append(desc);
        }
        manifestCollection.append("\n\n\n").append("sensitive_perms");
        for (String desc : sensitive_perms) {
            manifestCollection.append("\n");
            manifestCollection.append("    ");
            manifestCollection.append(desc);
        }
        manifestCollection.append("\n\n\n").append("normal_perms");
        for (String desc : normal_perms) {
            manifestCollection.append("\n");
            manifestCollection.append("    ");
            manifestCollection.append(desc);
        }
        manifestCollection.append("\n\n\n").append("black_perm_groups");
        for (String desc : black_perm_groups) {
            manifestCollection.append("\n");
            manifestCollection.append("    ");
            manifestCollection.append(desc);
        }
        manifestCollection.append("\n\n\n").append("sensitive_perm_groups");
        for (String desc : sensitive_perm_groups) {
            manifestCollection.append("\n");
            manifestCollection.append("    ");
            manifestCollection.append(desc);
        }
        manifestCollection.append("\n\n\n").append("normal_perm_groups");
        for (String desc : normal_perm_groups) {
            manifestCollection.append("\n");
            manifestCollection.append("    ");
            manifestCollection.append(desc);
        }
        FileUtil.saveStringToFile(manifestCollection.toString(), new File("/Users/fengdi/Desktop/Permissions/Output/PermissionGroupAlipay.txt"));
    }


}
