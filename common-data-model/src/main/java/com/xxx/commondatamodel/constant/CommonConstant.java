package com.xxx.commondatamodel.constant;

import java.util.List;
import java.util.Map;

public class CommonConstant {

    public static final class SexType {
        public static final  String MAN = "男人";
        public static final  String WOMAN = "女人";
        private SexType() {
        }
    }

    public static class UserType {
        public static Map<String,String> USER_TYPE_NAME_MAP = null;
        public static List<String> USER_TYPE_LIST = null;
        private UserType() {
        }
    }
}
