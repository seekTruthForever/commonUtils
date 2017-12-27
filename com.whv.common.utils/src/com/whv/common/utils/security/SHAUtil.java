package com.whv.common.utils.security;

/**
 * 
 *
 * @author chengjian.he
 * @version  3.4, 2016��8��5�� ����10:05:37 
 * @since   
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAUtil
{

  /**
   * �����ı����ݣ����� SHA-256 ��
   * 
   * @param strText
   * @return
   */
  public static String SHA256(final String strText)
  {
    return SHA(strText, "SHA-256");
  }

  /**
   * �����ı����ݣ����� SHA-512 ��
   * 
   * @param strText
   * @return
   */
  public static String SHA512(final String strText)
  {
    return SHA(strText, "SHA-512");
  }
  /**
   * �����ı����ݣ����� SHA-512 ��
   * 
   * @param strText
   * @return
   */
  public static String MD5(final String strText)
  {
    return SHA(strText, "MD5");
  }
  /**
   * �ַ��� SHA ����
   * 
   * @param strSourceText
   * @return
   */
  private static String SHA(final String strText, final String strType)
  {
    // ����ֵ
    String strResult = null;

    // �Ƿ�����Ч�ַ���
    if (strText != null && strText.length() > 0)
    {
      try
      {
        // SHA ���ܿ�ʼ
        // �������ܶ��� ������������
        MessageDigest messageDigest = MessageDigest.getInstance(strType);
        // ����Ҫ���ܵ��ַ���
        messageDigest.update(strText.getBytes());
        // �õ� byte ��ͽ��
        byte byteBuffer[] = messageDigest.digest();

        // �� byte �D�Q�� string
        StringBuffer strHexString = new StringBuffer();
        // ��v byte buffer
        for (int i = 0; i < byteBuffer.length; i++)
        {
          String hex = Integer.toHexString(0xff & byteBuffer[i]);
          if (hex.length() == 1)
          {
            strHexString.append('0');
          }
          strHexString.append(hex);
        }
        // �õ����ؽY��
        strResult = strHexString.toString();
      }
      catch (NoSuchAlgorithmException e)
      {
        e.printStackTrace();
      }
    }

    return strResult;
  }
//����ļ����㷨     
public static String KL(String inStr) {     
 char[] a = inStr.toCharArray();     
 for (int i = 0; i < a.length; i++) {     
  a[i] = (char) (a[i] ^ 't');     
 }     
 String s = new String(a);     
 return s;     
}   
  public static void main(String args[]){
	  System.out.println(MD5("ILoveYou"));//62accaf23ac9a73c0b28765b7dfaf75a
  }
}
