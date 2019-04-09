package com.puppycrawl.tools.checkstyle.permission;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class PermissionCollector {

    private static final Logger LOG = Logger.getLogger(PermissionCollector.class.getName());

    private static PermissionCollector sInstance;

    public static PermissionCollector getInstance() {
        if (sInstance == null) {
            synchronized (PermissionCollector.class) {
                if (sInstance == null) {
                    sInstance = new PermissionCollector();
                }
            }
        }
        return sInstance;
    }

    private final Map<String, String> mPermissionMap = new ConcurrentHashMap<>();
    private final Map<String, String> mExtraPermissionMap = new ConcurrentHashMap<>();
    private final Map<String, String> mPermissionGroupMap = new ConcurrentHashMap<>();
    private final Map<String, String> mExtraPermissionGroupMap = new ConcurrentHashMap<>();

    private final Map<String, Set<String>> mMethodPermissions = new ConcurrentHashMap<>();
    private final Map<String, Set<String>> mMethodPermissionGroups = new ConcurrentHashMap<>();

    private final Map<String, Set<String>> mFieldPermissions = new ConcurrentHashMap<>();
    private final Map<String, Set<String>> mFieldPermissionGroups = new ConcurrentHashMap<>();

    public void init() {
        // init permission map
        try {
            Field[] permission_fields = Manifest.permission.class.getDeclaredFields();
            for (Field permission_field : permission_fields) {
                String fieldName = permission_field.getName();
                permission_field.setAccessible(true);
                String value = (String) permission_field.get(null);

                mPermissionMap.put(fieldName, value);
            }
        } catch (Throwable tr) {
            LOG.warning(tr.toString());
        }
        // init permission group map
        try {
            Field[] permission_group_fields = Manifest.permission_group.class.getDeclaredFields();
            for (Field permission_group_field : permission_group_fields) {
                String fieldName = permission_group_field.getName();
                permission_group_field.setAccessible(true);
                String value = (String) permission_group_field.get(null);

                mPermissionGroupMap.put(fieldName, value);
            }
        } catch (Throwable tr) {
            LOG.warning(tr.toString());
        }
    }

    public void addMethodPermission(String permission, String caller) {
        if (!mPermissionMap.containsKey(permission)) {
            mExtraPermissionMap.put(permission, translatePermission(permission));
            LOG.info("addMethodPermission but permission not found in Manifest, permission: " + permission + ", caller: " + caller);
        }
        Set<String> callerSet = mMethodPermissions.get(permission);
        if (callerSet == null) {
            synchronized (mMethodPermissions) {
                callerSet = mMethodPermissions.get(permission);
                if (callerSet == null) {
                    callerSet = Collections.synchronizedSet(new HashSet<>());
                    mMethodPermissions.put(permission, callerSet);
                }
            }
        }
        callerSet.add(caller);
    }

    public void addMethodPermissionGroup(String permission_group, String caller) {
        if (!mPermissionGroupMap.containsKey(permission_group)) {
            mExtraPermissionGroupMap.put(permission_group, translatePermissionGroup(permission_group));
            LOG.info("addCommentPermissionGroup but permission_group not found in Manifest, permission_group: " + permission_group + ", caller: " + caller);
        }
        Set<String> callerSet = mMethodPermissionGroups.get(permission_group);
        if (callerSet == null) {
            synchronized (mMethodPermissionGroups) {
                callerSet = mMethodPermissionGroups.get(permission_group);
                if (callerSet == null) {
                    callerSet = Collections.synchronizedSet(new HashSet<>());
                    mMethodPermissionGroups.put(permission_group, callerSet);
                }
            }
        }
        callerSet.add(caller);
    }

    public void addFieldPermission(String permission, String desc) {
        if (!mPermissionMap.containsKey(permission)) {
            mExtraPermissionMap.put(permission, translatePermission(permission));
            LOG.info("addFieldPermission but permission not found in Manifest, permission: " + permission + ", desc: " + desc);
        }
        if (desc.contains("android.os.Build$VERSION_CODES")) {
            LOG.info("addFieldPermission but only a api level desc, ignore.");
            return;
        }
        Set<String> descSet = mFieldPermissions.get(permission);
        if (descSet == null) {
            synchronized (mFieldPermissions) {
                descSet = mFieldPermissions.get(permission);
                if (descSet == null) {
                    descSet = Collections.synchronizedSet(new HashSet<>());
                    mFieldPermissions.put(permission, descSet);
                }
            }
        }
        descSet.add(desc);
    }

    public void addFieldPermissionGroup(String permission_group, String desc) {
        if (!mPermissionGroupMap.containsKey(permission_group)) {
            mExtraPermissionGroupMap.put(permission_group, translatePermission(permission_group));
            LOG.info("addFieldPermissionGroup but permission not found in Manifest, permission_group: " + permission_group + ", desc: " + desc);
        }
        Set<String> descSet = mFieldPermissionGroups.get(permission_group);
        if (descSet == null) {
            synchronized (mFieldPermissionGroups) {
                descSet = mFieldPermissionGroups.get(permission_group);
                if (descSet == null) {
                    descSet = Collections.synchronizedSet(new HashSet<>());
                    mFieldPermissionGroups.put(permission_group, descSet);
                }
            }
        }
        descSet.add(desc);
    }

    private String translatePermission(String permission) {
        return "android.permission." + permission;
    }

    private String translatePermissionGroup(String permission_group) {
        return "android.permission-group." + permission_group;
    }

    public void dumpPermissionScanResult() {
        LOG.info("dumpPermissionScanResult start");
    }

}
