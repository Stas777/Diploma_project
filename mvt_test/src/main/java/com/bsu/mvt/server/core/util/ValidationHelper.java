package com.bsu.mvt.server.core.util;

import com.bsu.mvt.server.core.exception.ValidationErrorBean;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class ValidationHelper {
    // duplicate
    private static final String REGEXP_DUPLICATE_ENTRY = "Duplicate entry '(.*?)' for key '(.*?)'";
    private static final String MSG_DUPLICATE_ENTRY = "Duplicate entry '%1' for column '%2'";
    private static final Pattern PATTERN_DUPLICATE_ENTRY = Pattern.compile(REGEXP_DUPLICATE_ENTRY);

    // name
    private static final String REGEXP_NAME = "^[a-zA-Z0-9_]+$";
    private static final Pattern PATTERN_NAME = Pattern.compile(REGEXP_NAME);
    private static final String MSG_NAME = "Field '%1' = '%2' doesn't match pattern '%3'";
    private static final String MSG_MIN_MAX = "Field '%1' = '%2' doesn't belong interval ['%3'..'%4']";
    private static final String MSG_LIST_EMPTY = "List '%1' should contain at least one value";
    private static final String MSG_ID = "Field '%1' = '%2' should have valid id value";
    private static final String MSG_FIELD_EMPTY = "Field '%1' cannot be empty";
    public static final String MSG_BOTH_FIELD_EMPTY = "One of the fields '%1' or '%2' should not be empty";
    public static final String MSG_BOTH_FIELD_NOT_EMPTY = "One of the fields '%1' or '%2' should be empty";
    public static final String MSG_WRONG_VALUE = "Field '%1' has wrong value '%2'";


    public static ValidationErrorBean validateDatabaseDuplicateKey(String exceptionMessage, String primaryKeyName) {
        ValidationErrorBean r = null;
        Matcher m = PATTERN_DUPLICATE_ENTRY.matcher(exceptionMessage);
        if (m.find()) {
            r = new ValidationErrorBean();
            r.setMessage(MSG_DUPLICATE_ENTRY);
            String g1 = m.group(1);
            String g2 = m.group(2);
            r.setArguments(Arrays.asList((Object) g1, "PRIMARY".equals(g2) ? primaryKeyName : g2));
            r.setKey("PRIMARY".equals(g2) ? primaryKeyName : g2);
        }
        return r;
    }

    public static ValidationErrorBean validateName(String value, String fieldName) {
        ValidationErrorBean r = null;
        if (value != null) {
            Matcher m = PATTERN_NAME.matcher(value);
            if (!m.find()) {
                r = new ValidationErrorBean();
                r.setMessage(MSG_NAME);
                r.setArguments(Arrays.asList((Object) fieldName, value, REGEXP_NAME));
                r.setKey(fieldName);
                return r;
            } else {
                return null;
            }
        } else {
            r = new ValidationErrorBean();
            r.setMessage(MSG_NAME);
            r.setArguments(Arrays.asList((Object) fieldName, "null", REGEXP_NAME));
            r.setKey(fieldName);
            return r;
        }
    }

    public static ValidationErrorBean validateMinMax(Float value, String fieldName, Float min, Float max) {
        if (value < min || value > max) {
            return new ValidationErrorBean(fieldName, MSG_MIN_MAX, Arrays.asList((Object) fieldName, value, min, max));
        } else {
            return null;
        }
    }

    public static ValidationErrorBean validateFile(String filePath, String md5, String fieldName, String fieldName2) {
        ValidationErrorBean r = null;

        if (filePath == null) {
            return new ValidationErrorBean(fieldName, "Wrong file param '%1' = null",
                    Arrays.asList((Object) fieldName));
        }

        if (md5 == null) {
            return new ValidationErrorBean(fieldName2, "Wrong file param '%1' = null",
                    Arrays.asList((Object) fieldName2));
        }

        try {
            File file = new File(filePath);
            boolean b = file.exists();
            if (!b) {
                return new ValidationErrorBean(fieldName, "Cannot find file '%1' on server side",
                        Arrays.asList((Object) filePath));
            }
        } catch (SecurityException e) {
            return new ValidationErrorBean(fieldName, "Cannot read file '%1', read access denied",
                    Arrays.asList((Object) filePath));
        }

        try {
            String serverMD5 = FileUtil.generateMD5Fast(filePath);
            if (!md5.equalsIgnoreCase(serverMD5)) {
                return new ValidationErrorBean(fieldName2, "Wrong client '%1' for file '%2', " +
                        "client value = '%3', server value = '%4' ",
                        Arrays.asList((Object) fieldName2, filePath, md5, serverMD5));
            }
        } catch (IOException e) {
            return new ValidationErrorBean(fieldName, "Cannot find file '%1' on server side",
                    Arrays.asList((Object) filePath));
        }
        return null;
    }

    public static ValidationErrorBean validateListIsEmpty(List list, String fieldName) {
        ValidationErrorBean r = null;
        if (list == null || list.isEmpty()) {
            r = new ValidationErrorBean();
            r.setMessage(MSG_LIST_EMPTY);
            r.setArguments(Arrays.asList((Object) fieldName));
            r.setKey(fieldName);
            return r;
        } else {
            return null;
        }
    }

    public static ValidationErrorBean validateFieldIsEmpty(Object o, String fieldName) {
        ValidationErrorBean r = null;
        if (o == null) {
            r = new ValidationErrorBean();
            r.setMessage(MSG_FIELD_EMPTY);
            r.setArguments(Arrays.asList((Object) fieldName));
            r.setKey(fieldName);
            return r;
        } else {
            return null;
        }
    }

    public static ValidationErrorBean validateId(Long value, String fieldName) {
        ValidationErrorBean r = null;
        if (value == null || value < 0) {
            r = new ValidationErrorBean();
            r.setMessage(MSG_ID);
            r.setArguments(Arrays.asList((Object) fieldName, value));
            r.setKey(fieldName);
            return r;
        } else {
            return null;
        }
    }

    // is not needed because gson won't be mapped if wrong enum value comes
    public static ValidationErrorBean validateEnumValue(Object value, String fieldName) {
        ValidationErrorBean r = null;
        if (value == null) {
            r = new ValidationErrorBean();
            r.setMessage(MSG_WRONG_VALUE);
            r.setArguments(Arrays.asList((Object) fieldName, value));
            r.setKey(fieldName);
            return r;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        ValidationErrorBean bean = validateName(null, "name");
    }
}
