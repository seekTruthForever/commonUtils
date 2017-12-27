package com.whv.common.utils.convert;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * �ַ����������໥ת���Ĺ�����
 * @author huawei
 *
 */
public class DateConvert {
	 /**
     * �ַ���ת��Ϊ���ڣ���ʽΪyyyy-MM-dd HH:mm:ss
     * @param dateStr
     * @return
     */
    public static Timestamp str2Ts(String dateStr,String fmtStr){
        if(dateStr==null || "".equals(dateStr)){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(fmtStr==null || "".equals(fmtStr)){
            fmtStr = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdfDay = new SimpleDateFormat(fmtStr);
        Date dateObj = null;
        try {
        	dateObj = sdfDay.parse(dateStr);
        } catch (ParseException e) {
        }
        String time = sdf.format(dateObj);
        return Timestamp.valueOf(time);
    }
    /**
     * �ַ���ת��Ϊ���ڣ���ʽΪyyyy-MM-dd HH:mm:ss
     * @param dateStr
     * @return
     */
    public static Date str2Date(String dateStr,String fmtStr){
        if(dateStr==null || "".equals(dateStr)){
            return null;
        }
        if(fmtStr==null || "".equals(fmtStr)){
            fmtStr = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(fmtStr);
        Date dateObj = null;
        try {
            dateObj = sdf.parse(dateStr);
        } catch (ParseException e) {
        }
        return dateObj;
    }
    /**
     * ����ת��Ϊ�ַ�������ʽΪyyyy-MM-dd HH:mm:ss
     * @param ts
     * @return
     */
    public static String ts2Str(Timestamp ts,String fmtStr){
        if(ts==null){
            return "";
        }
        if(fmtStr==null || "".equals(fmtStr)){
            fmtStr = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(fmtStr);
        return sdf.format(ts);
    }
    /**
     * ����ת��Ϊ�ַ�������ʽΪyyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String date2Str(Date date,String fmtStr){
        if(date==null){
            return "";
        }
        if(fmtStr==null || "".equals(fmtStr)){
            fmtStr = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(fmtStr);
        return sdf.format(date);
    }
}
