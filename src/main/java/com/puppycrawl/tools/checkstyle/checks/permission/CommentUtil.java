package com.puppycrawl.tools.checkstyle.checks.permission;

import java.util.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.permission.PermissionCollector;

public class CommentUtil {

//    private static final String COMMENT_PATTEN_1_PRE = "you must declare the";
//    private static final String COMMENT_PATTEN_1_SUF = "permission in your Android Manifest";
//
//    private static final String COMMENT_PATTEN_2_PRE = "the application must have the";
//    private static final String COMMENT_PATTEN_2_SUF = "permission to use this class";
//
//    private static final String COMMENT_PATTEN_3_PRE = "you must declare the service in your manifest file with the";
//
//    private static final String COMMENT_PATTEN_4_PRE = "the application must have";
//    private static final String COMMENT_PATTEN_4_SUF = "permission to use this class";

    private static final String COMMENT_LINK_PERM_ID = "{@link android.Manifest.permission#";
    private static final String COMMENT_LINK_PERM_PREF = "android.Manifest.permission#";
    private static final String COMMENT_LINK_PERM_SUF = "}";

    private static final String COMMENT_CODE_PERM_ID = "{@code android.permission.";
    private static final String COMMENT_CODE_PERM_PREF = "android.permission.";
    private static final String COMMENT_CODE_PERM_SUF = "}";

    private static final String COMMENT_GAP_PREF = " ";

    public static String makeCommentInOneLine(String comment) {
        if (comment == null) {
            return "";
        }
        return comment.replace("\n *", "");
    }

    public static Set<String> getPermissionInClassComment(String comment) {
        if (comment == null) {
            return null;
        }
        String oneLineComment = makeCommentInOneLine(comment);
//        if (!(oneLineComment.contains(COMMENT_PATTEN_1_PRE) && oneLineComment.contains(COMMENT_PATTEN_1_SUF))
//                && !(oneLineComment.contains(COMMENT_PATTEN_2_PRE) && oneLineComment.contains(COMMENT_PATTEN_2_SUF))
//                && !oneLineComment.contains(COMMENT_PATTEN_3_PRE)
//                && !(oneLineComment.contains(COMMENT_PATTEN_4_PRE) && oneLineComment.contains(COMMENT_PATTEN_4_SUF))) {
//            return null;
//        }
        Set<String> permissionsFound = Collections.synchronizedSet(new HashSet<>());

//        String[] bigComments1 = oneLineComment.split(COMMENT_PATTEN_1_PRE);
//        String[] bigComments2 = oneLineComment.split(COMMENT_PATTEN_2_PRE);
//        String[] bigComments3 = oneLineComment.split(COMMENT_PATTEN_3_PRE);
//        String[] bigComments4 = oneLineComment.split(COMMENT_PATTEN_4_PRE);
//
//        collectPermissions(bigComments1, permissionsFound);
//        collectPermissions(bigComments2, permissionsFound);
//        collectPermissions(bigComments3, permissionsFound);
//        collectPermissions(bigComments4, permissionsFound);

        getPotentialPermissions(oneLineComment, permissionsFound, "android.Manifest.permission#", null);
        getPotentialPermissions(oneLineComment, permissionsFound, "android.permission.", null);
//        getPotentialPermissions(oneLineComment, permissionsFound, " ", " permission");

        if (permissionsFound.size() > 0) {
            return permissionsFound;
        }
        return null;
    }

    private static void collectPermissions(String[] commentLines, Set<String> permissionsFound) {
        for (String bigComment : commentLines) {
            bigComment = bigComment.trim();
            if (bigComment.startsWith(COMMENT_LINK_PERM_ID)) {
                String permission = bigComment.replace(COMMENT_LINK_PERM_ID, "");
                int sufIndex = permission.indexOf(COMMENT_LINK_PERM_SUF);
                if (sufIndex >= 0) {
                    permission = permission.substring(0, sufIndex);
                }
                permissionsFound.add(permission);
            }
            if (bigComment.startsWith(COMMENT_CODE_PERM_ID)) {
                String permission = bigComment.replace(COMMENT_CODE_PERM_ID, "");
                int sufIndex = permission.indexOf(COMMENT_CODE_PERM_SUF);
                if (sufIndex >= 0) {
                    permission = permission.substring(0, sufIndex);
                }
                permissionsFound.add(permission);
            }
        }
    }

    // 原始Comment信息，解析显式说明使用的Permissions
    public static Set<String> getPermissions(String commentLine) {
        String oneLineComment = makeCommentInOneLine(commentLine);
        // 检测Comment中，是否指定使用了某些权限
        Set<String> permissionsFound = Collections.synchronizedSet(new HashSet<>());
        if (oneLineComment.contains(COMMENT_LINK_PERM_ID)) {
            String[] comments = oneLineComment.split(COMMENT_GAP_PREF);
            for (String comment : comments) {
                comment = comment.trim();
                if (comment.startsWith(COMMENT_LINK_PERM_PREF)) {
                    String permission = comment.replace(COMMENT_LINK_PERM_PREF, "");
                    int sufIndex = permission.indexOf(COMMENT_LINK_PERM_SUF);
                    if (sufIndex >= 0) {
                        permission = permission.substring(0, sufIndex);
                    }
                    permissionsFound.add(permission);
                }
            }
        } else if (oneLineComment.contains(COMMENT_CODE_PERM_ID)) {
            String[] comments = oneLineComment.split(COMMENT_GAP_PREF);
            for (String comment : comments) {
                comment = comment.trim();
                if (comment.startsWith(COMMENT_CODE_PERM_PREF)) {
                    String permission = comment.replace(COMMENT_CODE_PERM_PREF, "");
                    int sufIndex = permission.indexOf(COMMENT_CODE_PERM_SUF);
                    if (sufIndex >= 0) {
                        permission = permission.substring(0, sufIndex);
                    }
                    permissionsFound.add(permission);
                }
            }
        }
        if (permissionsFound.size() > 0) {
            return permissionsFound;
        }
        return null;
    }

    // 解析潜在使用的Permissions
    public static Set<String> getPotentialPermissions(String commentLine, Set<String> permissionsFound, String prefixAddOn, String suffixAddOn) {
        String oneLineComment = makeCommentInOneLine(commentLine);
        if (permissionsFound == null) {
            permissionsFound = Collections.synchronizedSet(new HashSet<>());
        }
        if (prefixAddOn == null) {
            prefixAddOn = "";
        }
        if (suffixAddOn == null) {
            suffixAddOn = "";
        }

        List<String> allPermissions = PermissionCollector.getInstance().getAllPermissionKey();

        for (String permission : allPermissions) {
            if (oneLineComment.contains(prefixAddOn + permission + suffixAddOn)) {
                permissionsFound.add(permission);
            }
        }

        if (permissionsFound.size() > 0) {
            return permissionsFound;
        }
        return null;
    }

    public static DetailAST getCommentDetailAST(DetailAST target) {
        DetailAST commentAST = null;
        DetailAST modifiers = target.findFirstToken(TokenTypes.MODIFIERS);
        if (modifiers != null) {
            DetailAST child = modifiers.getFirstChild();
            while (child.getNextSibling() != null) {
                switch (child.getType()) {
                    case TokenTypes.SINGLE_LINE_COMMENT:
                    case TokenTypes.BLOCK_COMMENT_BEGIN:
                        commentAST = child.findFirstToken(TokenTypes.COMMENT_CONTENT);
                        break;
                    // 其中有部分是在Annotation里面，所以也可能需要找到TokenTypes.ANNOTATION
                    case TokenTypes.ANNOTATION:
                        DetailAST annotationChild = child.getFirstChild();
                        while (annotationChild.getNextSibling() != null) {
                            switch (annotationChild.getType()) {
                                case TokenTypes.SINGLE_LINE_COMMENT:
                                case TokenTypes.BLOCK_COMMENT_BEGIN:
                                    commentAST = annotationChild.findFirstToken(TokenTypes.COMMENT_CONTENT);
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

        return commentAST;
    }

}
