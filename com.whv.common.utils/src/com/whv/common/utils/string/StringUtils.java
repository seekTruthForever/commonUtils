package com.whv.common.utils.string;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * String工具类：匹配
 * @author huawei
 *
 */
public class StringUtils {
	/**
	  * 判断是否为整数 
	  * @param str 传入的字符串 
	  * @return 是整数返回true,否则返回false 
	*/
	  public static boolean isInteger(String str) {  
	        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
	        return pattern.matcher(str).matches();  
	  }
	  
	  /**
	     * 匹配是否包含数字
	     * @param str 可能为中文，也可能是-19162431.1254，不使用BigDecimal的话，变成-1.91624311254E7
	     * @return
	     */
	    public static boolean isNumeric(String str) {
	        // 该正则表达式可以匹配所有的数字 包括负数
	        Pattern pattern = Pattern.compile("^[-\\\\+]?[0-9]+\\.?[0-9]*$");
	        String bigStr;
	        try {
	            bigStr = new BigDecimal(str).toString();
	        } catch (Exception e) {
	            return false;//异常 说明包含非数字。
	        }

	        Matcher isNum = pattern.matcher(bigStr); // matcher是全匹配
	        if (!isNum.matches()) {
	            return false;
	        }
	        return true;
	    } 
	    public static final char UNDERLINE = '_';

	    /**
	     * 驼峰格式字符串转换为下划线格式字符串
	     * 
	     * @param param
	     * @return
	     */
	    public static String camel2ul(String param) {
	        if (param == null || "".equals(param.trim())) {
	            return "";
	        }
	        int len = param.length();
	        StringBuilder sb = new StringBuilder(len);
	        for (int i = 0; i < len; i++) {
	            char c = param.charAt(i);
	            if (Character.isUpperCase(c)) {
	                sb.append(UNDERLINE);
	                sb.append(Character.toLowerCase(c));
	            } else {
	                sb.append(c);
	            }
	        }
	        return sb.toString();
	    }

	    /**
	     * 下划线格式字符串转换为驼峰格式字符串
	     * 
	     * @param param
	     * @return
	     */
	    public static String ul2camel(String param) {
	        if (param == null || "".equals(param.trim())) {
	            return "";
	        }
	        int len = param.length();
	        StringBuilder sb = new StringBuilder(len);
	        for (int i = 0; i < len; i++) {
	            char c = param.charAt(i);
	            if (c == UNDERLINE) {
	                if (++i < len) {
	                    sb.append(Character.toUpperCase(param.charAt(i)));
	                }
	            } else {
	                sb.append(c);
	            }
	        }
	        return sb.toString();
	    }

	    /**
	     * 下划线格式字符串转换为驼峰格式字符串2
	     * 
	     * @param param
	     * @return
	     */
	    public static String ul2camel2(String param) {
	        if (param == null || "".equals(param.trim())) {
	            return "";
	        }
	        StringBuilder sb = new StringBuilder(param);
	        Matcher mc = Pattern.compile("_").matcher(param);
	        int i = 0;
	        while (mc.find()) {
	            int position = mc.end() - (i++);
	            sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
	        }
	        return sb.toString();
	    }

	    public static void main(String[] args) {
	        String aaa = "app_version_fld";
	        System.out.println(ul2camel(aaa));
	        System.out.println(ul2camel2(aaa));
	        aaa = "appVersionFld";
	        System.out.println(camel2ul(aaa));
	    
	    }
}
