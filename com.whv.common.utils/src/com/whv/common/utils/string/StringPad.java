package com.whv.common.utils.string;
/**
 * ��ַ������������������Ҳ���������
 * @author huawei
 *
 */
public class StringPad {
	
	/**
	 * �����ַ���
	 * @param content ԭ�ַ���
	 * @param padChar ����ַ�
	 * @param len �����ַ�������
	 * @return
	 */
	public static String lpad(String content ,char padChar,int len) {
		if(content==null) return null;
		if(len < content.length() ) return content;
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<len-content.length();i++) {
			sb.append(padChar);
		}
		sb.append(content);
		return sb.toString();
	}
	/**
	 * �Ҳ���ַ���
	 * @param content ԭ�ַ���
	 * @param padChar ����ַ�
	 * @param len �����ַ�������
	 * @return
	 */
	public static String rpad(String content ,char padChar,int len) {
		if(content==null) return null;
		if(len < content.length() ) return content;
		StringBuffer sb = new StringBuffer();
		sb.append(content);
		for(int i=0;i<len-content.length();i++) {
			sb.append(padChar);
		}
		return sb.toString();
	}
	/**
	 * �������
	 * @param content ԭ�ַ���
	 * @param padChar ����ַ�
	 * @param len �����ַ�������
	 * @return
	 */
	public static String bothpad(String content ,char padChar,int len) {
		if(content==null) return null;
		//ԭ�ַ�������
		int sourceLength = content.length();
		if(len < sourceLength ) return content;
		//�����ӵĳ���
		int lpadLength = (len-sourceLength)/2;
		//�Ҳ���ӵĳ���
		int rpadLength = len-sourceLength - lpadLength;
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<lpadLength;i++) {
			sb.append(padChar);
		}
		sb.append(content);
		for(int i=0;i<rpadLength;i++) {
			sb.append(padChar);
		}
		return sb.toString();
	}
	/**
	 * �����ַ���
	 * @param content ԭ�ַ���
	 * @param padStr ����ַ���
	 * @param len �����ַ�������
	 * @return
	 */
	public static String lpad(String content ,String padStr,int len) {
		if(content==null) return null;
		if(padStr == null || "".equals(padStr)) return content;
		if(len - content.length() < padStr.length() ) return content;
		int padCount = (len-content.length())/padStr.length();
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<padCount;i++) {
			sb.append(padStr);
		}
		sb.append(content);
		return sb.toString();
	}
	/**
	 * �Ҳ���ַ���
	 * @param content ԭ�ַ���
	 * @param padStr ����ַ���
	 * @param len �����ַ�������
	 * @return
	 */
	public static String rpad(String content ,String padStr,int len) {
		if(content==null) return null;
		if(padStr == null || "".equals(padStr)) return content;
		if(len - content.length() < padStr.length() ) return content;
		int padCount = (len-content.length())/padStr.length();
		StringBuffer sb = new StringBuffer();
		sb.append(content);
		for(int i=0;i<padCount;i++) {
			sb.append(padStr);
		}
		return sb.toString();
	}
	/**
	 * ������ַ���
	 * @param content ԭ�ַ���
	 * @param padStr ����ַ���
	 * @param len �����ַ�������
	 * @return
	 */
	public static String bothpad(String content ,String padStr,int len) {
		if(content==null) return null;
		if(padStr == null || "".equals(padStr)) return content;
		if(len - content.length() < padStr.length() ) return content;
		int padCount = (len-content.length())/padStr.length();
		//�����ӵĳ���
		int lpadLength = padCount/2;
		//�Ҳ���ӵĳ���
		int rpadLength = padCount - lpadLength;
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<lpadLength;i++) {
			sb.append(padStr);
		}
		sb.append(content);
		for(int i=0;i<rpadLength;i++) {
			sb.append(padStr);
		}
		return sb.toString();
	}
	/**
	 * �����ַ���
	 * @param content ԭ�ַ���
	 * @param padStr ����ַ���
	 * @param len �����ַ�������
	 * @param flag �Ƿ������һ�Σ�true:�ǣ�false:��
	 * @return
	 */
	public static String lpad(String content ,String padStr,int len,boolean flag) {
		if(content==null) return null;
		if(padStr == null || "".equals(padStr)) return content;
		if(len - content.length() < padStr.length() ) return flag?padStr+content:content;
		int padCount = (len-content.length())/padStr.length();
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<padCount;i++) {
			sb.append(padStr);
		}
		sb.append(content);
		return sb.toString();
	}
	/**
	 * �Ҳ���ַ���
	 * @param content ԭ�ַ���
	 * @param padStr ����ַ���
	 * @param len �����ַ�������
	 * @param flag �Ƿ������һ�Σ�true:�ǣ�false:��
	 * @return
	 */
	public static String rpad(String content ,String padStr,int len,boolean flag) {
		if(content==null) return null;
		if(padStr == null || "".equals(padStr)) return content;
		if(len - content.length() < padStr.length() ) return flag?content+padStr:content;
		int padCount = (len-content.length())/padStr.length();
		StringBuffer sb = new StringBuffer();
		sb.append(content);
		for(int i=0;i<padCount;i++) {
			sb.append(padStr);
		}
		return sb.toString();
	}
	/**
	 * ������ַ���
	 * @param content ԭ�ַ���
	 * @param padStr ����ַ���
	 * @param len �����ַ�������
	 * @param flag �Ƿ������һ�Σ�true:�ǣ�false:��
	 * @return
	 */
	public static String bothpad(String content ,String padStr,int len,boolean flag) {
		if(content==null) return null;
		if(padStr == null || "".equals(padStr)) return content;
		if(len - content.length() < padStr.length() ) return flag?padStr+content+padStr:content;
		int padCount = (len-content.length())/padStr.length();
		//�����ӵĳ���
		int lpadLength = padCount/2;
		//�Ҳ���ӵĳ���
		int rpadLength = padCount - lpadLength;
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<lpadLength;i++) {
			sb.append(padStr);
		}
		sb.append(content);
		for(int i=0;i<rpadLength;i++) {
			sb.append(padStr);
		}
		return sb.toString();
	}
}
