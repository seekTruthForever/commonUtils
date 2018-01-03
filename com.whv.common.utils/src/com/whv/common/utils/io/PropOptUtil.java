package com.whv.common.utils.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Properties;

/**
 * �����ļ�����������
 * @author huawei
 *
 */
public class PropOptUtil {
	/**
	 * �������ļ��л�ȡֵ
	 * @param filePath ����������·��
	 * @return Properties
	 */
	public static Properties getProperties(String filePath) {
		Properties properties = new Properties();
		InputStream in = PropOptUtil.class.getClassLoader()
				.getResourceAsStream(filePath);
		try {
			properties.load(new InputStreamReader(in,"UTF-8"));
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
	 * @param filePath ����·��
	 * @return Properties
	 */
	public static Properties getPropertiesAP(String filePath) {
		Properties properties = new Properties();
		InputStream in = null;
		try {
			in = new FileInputStream(new File(filePath));
			properties.load(new InputStreamReader(in,"UTF-8"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}catch (IOException e) {
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
	 * @param filePath ����·��
	 * @param key ��
	 * @return ֵ
	 */
	public static String getPropertiesValueAP(String filePath,String key) {
		Properties properties = getPropertiesAP(filePath);
		String value = properties.getProperty(key).trim();
		return value;
	}
	/**
	 * �������ļ��л�ȡֵ
	 * @param filePath ����·��
	 * @param key ��
	 * @param defaultValue Ĭ��ֵ
	 * @return ֵ
	 */
	public static String getPropertiesValueAP(String filePath,String key,String defaultValue) {
		Properties properties = getPropertiesAP(filePath);
		String value = properties.getProperty(key,defaultValue).trim();
		return value;
	}
	/**
	 * �������ļ��л�ȡֵ
	 * @param filePath ����������·��
	 * @param key ��
	 * @return ֵ
	 */
	public static String getPropertiesValue(String filePath,String key) {
		Properties properties = getProperties(filePath);
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
	public static String getPropertiesValue(String filePath,String key,String defaultValue) {
		Properties properties = getProperties(filePath);
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
		Properties properties = new Properties();
		String url = PropOptUtil.class.getClassLoader().getResource(filePath).getPath();
		InputStream in = null;
		try {
			File file = new File(url);
			if(!file.exists()){
				file.createNewFile();
			}
			in = new FileInputStream(file);
			properties.load(new InputStreamReader(in,"UTF-8"));
			properties.setProperty(key, value);
			FileOutputStream out = new FileOutputStream(file);
			properties.store(new OutputStreamWriter(out,"UTF-8"), "�޸������ļ�"+url);
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
	 * д������ֵ
	 * @param filePath �����ļ�·��,����·��
	 * @param key ��������
	 * @param value ����ֵ
	 */
	public static void createPropertiesAp(String filePath,String key,String value){
		Properties properties = new Properties();
		InputStream in = null;
		try {
			File file = new File(filePath);
			if(!file.exists()){
				file.createNewFile();
			}
			in = new FileInputStream(file);
			properties.load(new InputStreamReader(in,"UTF-8"));
			properties.setProperty(key, value);
			FileOutputStream out = new FileOutputStream(file);
			properties.store(new OutputStreamWriter(out,"UTF-8"), "�޸������ļ�"+filePath);
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
}
