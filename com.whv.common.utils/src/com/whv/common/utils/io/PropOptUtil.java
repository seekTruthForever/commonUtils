package com.whv.common.utils.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * �����ļ�����������
 * @author huawei
 *
 */
public class PropOptUtil {
	//Ĭ�ϱ���
		private final static String DEF_CHARSET="UTF-8";
		private String charset;
		private InputStream in;
		public PropOptUtil() {
			charset = DEF_CHARSET;
			in = null;
		}
		/**
		 * ͨ���ļ����������������๹����
		 * @param fis �ļ�������
		 */
		public PropOptUtil(InputStream fis) {
			this(fis,null);
		}
		/**
		 * ͨ���ļ����������������๹����
		 * @param fis �ļ�������
		 * @param encode ����
		 */
		public PropOptUtil(InputStream fis,String encode) {
			if(encode==null||"".equals(encode)) {
				charset = DEF_CHARSET;
			}else {
				charset = encode;
			}
			in = fis;
		}
		/**
		 * ͨ���ļ�·�����������๹����
		 * @param filePath �ļ����·��
		 */
		public PropOptUtil(String filePath) {
			this(filePath,null,true);
		}
		/**
		 * ͨ���ļ�·�����������๹����
		 * @param filePath �ļ�·��
		 * @param isRelative �Ƿ����·��
		 */
		public PropOptUtil(String filePath,boolean isRelative) {
			this(filePath,null,isRelative);
		}
		/**
		 * ͨ���ļ�·�����������๹����
		 * @param filePath �ļ����·��
		 * @param encode ����
		 */
		public PropOptUtil(String filePath,String encode) {
			this(filePath,encode,true);
		}
		/**
		 * ͨ���ļ�·�����������๹����
		 * @param filePath �ļ�·��
		 * @param encode ����
		 * @param isRelative �Ƿ����·��
		 */
		public PropOptUtil(String filePath,String encode,boolean isRelative) {
			if(encode==null||"".equals(encode)) {
				charset = DEF_CHARSET;
			}else {
				charset = encode;
			}
			if(isRelative) {
				 in = PropOptUtil.class.getClassLoader().getResourceAsStream(filePath);
			}else {
				try {
					in = new FileInputStream(new File(filePath));
				} catch (FileNotFoundException e) {
					in = null;
				}
			}
		}
	/**
	 * �������ļ��л�ȡֵ
	 * @param filePath ����������·��
	 * @return Properties
	 */
	public  Properties getProperties() {
		if(in==null) {
			throw new RuntimeException("�ļ�������");
		}
		Properties properties = new Properties();
		try {
			properties.load(new InputStreamReader(in,charset));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties;
	}
	/**
	 * �������ļ��л�ȡֵ
	 * @param filePath ����������·��
	 * @param key ��
	 * @return ֵ
	 */
	public String getPropertiesValue(String key) {
		Properties properties = getProperties();
		String value = properties.getProperty(key).trim();
		return value;
	}
	/**
	 * �������ļ��л�ȡֵ
	 * @param filePath ����������·��
	 * @param key ��
	 * @param defaultValue Ĭ��ֵ
	 * @return ֵ
	 */
	public String getPropertiesValue(String key,String defaultValue) {
		Properties properties = getProperties();
		String value = properties.getProperty(key,defaultValue).trim();
		return value;
	}
	/**
	 * д������ֵ
	 * @param filePath �����ļ�·��������������·��
	 * @param key ��������
	 * @param value ����ֵ
	 */
	public static void createProperties(String filePath,String key,String value){
		createProperties(filePath, key, value, DEF_CHARSET);
	}
	/**
	 * д������ֵ
	 * @param filePath �����ļ�·��������������·��
	 * @param key ��������
	 * @param value ����ֵ
	 */
	public static void createProperties(String filePath,String key,String value,String charset){
		createProperties(filePath, key, value, charset,false);
	}
	/**
	 * д������ֵ
	 * @param filePath �����ļ�·��������������·��
	 * @param key ��������
	 * @param value ����ֵ
	 */
	public static void createPropertiesAB(String filePath,String key,String value,String charset){
		createProperties(filePath, key, value, charset,true);
	}
	/**
	 * д������ֵ
	 * @param filePath �����ļ�·��������������·��
	 * @param key ��������
	 * @param value ����ֵ
	 */
	public static void createPropertiesAB(String filePath,String key,String value){
		createPropertiesAB(filePath, key, value, DEF_CHARSET);
	}
	/**
	 * д������ֵ
	 * @param filePath �����ļ�·��������������·��
	 * @param key ��������
	 * @param value ����ֵ
	 */
	public static void createProperties(String filePath,String key,String value,String charset,boolean isAbsolute){
		Properties properties = new Properties();
		String url = null;
		if(isAbsolute) {
			url = filePath;
		}else {
			url = PropOptUtil.class.getClassLoader().getResource(filePath).getPath();
		}
		InputStream in = null;
		try {
			File file = new File(url);
			if(!file.exists()){
				file.createNewFile();
			}
			in = new FileInputStream(file);
			properties.load(new InputStreamReader(in,charset));
			properties.setProperty(key, value);
			FileOutputStream out = new FileOutputStream(file);
			properties.store(new OutputStreamWriter(out,charset), "�޸������ļ�"+url);
		} catch (IOException e){
			e.printStackTrace();
		}finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
    /**
     * ��ȡ�����ļ��е�����ֵ
     * @return
     */
    public Map<String,String> readAllProps(){
        Map<String,String> h = new HashMap<String,String>();
        try {
        	Properties props = getProperties();
            Enumeration<String> er = (Enumeration<String>) props.propertyNames();
            while (er.hasMoreElements()) {
                String paramName = er.nextElement();
                h.put(paramName, props.getProperty(paramName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return h;
    }
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public InputStream getIn() {
		return in;
	}
	public void setIn(InputStream in) {
		this.in = in;
	}
}
