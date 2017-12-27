package com.whv.common.utils.string;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * �ַ���ƴ�Ӽ��в���
 * @author huawei
 *
 */
public class StringConcat {
	/**
	 * �� "a,b,c" �ĳ� "'a','b','c'"
	 * @param seia �ַ���
	 * @param spliter  �ַ�
	 * @param newspliter �ַ�
	 * @param concat ��ջ����� '
	 * @return ������֯�õ��ַ���
	 */
	public static String splitConcat(String seia,String spliter,String newspliter,String concat)
	{
		if(seia==null||spliter==null)
		{
			return concat+concat;
		}
		if(seia.endsWith(spliter))
		{
			seia=seia.substring(0,seia.lastIndexOf(spliter));
		}
		String[] spl=seia.trim().split(spliter);
		if(spl==null||spl.length==1)
		{
			return concat+seia+concat;
		}
		StringBuffer newString = new StringBuffer();
		for(int i=0;i<spl.length;i++)
		{
			newString.append(concat).append(spl[i]).append(concat);
			if(i!=spl.length-1)
			{
				newString.append(newspliter);
			}
		}
		return newString.toString();
	}
	/**
	 * ���е���󲿷�ȥ�� ���û�У����߲�����laster������Ӧ����β�򷵻�ԭ��
	 * @param serial
	 * @param laster
	 * @return
	 */
	public static String concatLast(String serial,String laster)
	{
		if(serial==null||laster==null)
		{
			return serial;
		}
		if(serial.endsWith(laster))
		{
			return serial.substring(0,serial.lastIndexOf(laster));
		}
		return serial;
	}
	/**
	 * ��������ǧ��λ�ָ�
	 * @param number
	 * @return
	 */
		public String longThousandsSplit(String number){
			String returnNumber = "";
			if(null != number && !"".equals(number)){
				NumberFormat numberFormat1 = NumberFormat.getNumberInstance();
				Pattern pattern = Pattern.compile("[0-9]*");
				Matcher isNum = pattern.matcher(number);
				if( isNum.matches() ){
				   Long numbers = Long.parseLong(number);
				   returnNumber = numberFormat1.format(numbers);
				}else{
				   returnNumber = number;
				}
			}
			return returnNumber;
		}
}
