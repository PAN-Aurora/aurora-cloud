package com.aurora.common.utils;


import java.security.MessageDigest;

/**
 *  md5 加密
 * @author :PHQ
 * @date：2020/12/30
 **/
public class Md5Util {
    private static final String slat = "&%5123***&&%%$$#@";
    //加密
    public static String encrypt(String dataStr) {
        try {
            dataStr = dataStr + slat;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF-8"));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
