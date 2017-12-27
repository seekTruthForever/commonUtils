package com.whv.common.utils.security;
/**
 * BASE64���ܹ���
 * @author huawei
 *
 */
public class Base64Util {
    public static String encode(String s) {  
        if (s == null)  
            return null;  
        return (new sun.misc.BASE64Encoder()).encode(s.getBytes());  
    }  
    // �� BASE64 ������ַ��� s ���н���   ����
    public static String decode(String s) {  
        if (s == null)  
            return null;  
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();  
        try {  
            byte[] b = decoder.decodeBuffer(s);  
            return new String(b);  
        } catch (Exception e) {  
            return null;  
        }  
    }  
    public static String m2a(Object ming){
        return Base64Util.encode(Base64Util.encode(Base64Util.encode((String)ming)));
    }
    public static String a2m(String an){
        return Base64Util.decode(Base64Util.decode(Base64Util.decode(an)));
    }
    
}

