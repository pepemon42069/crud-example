package com.test.doofus.constants;

public class GeneralConstants {
    public static final String ASCENDING = "asc";
    public static final String DESCENDING = "desc";
    public static final String SORT_ORDER_REGEX = "^(" + ASCENDING + "|" + DESCENDING + ")$";
    public static final String SORTABLE_FIELDS_DEFAULT = "^(id|email)$";
}
