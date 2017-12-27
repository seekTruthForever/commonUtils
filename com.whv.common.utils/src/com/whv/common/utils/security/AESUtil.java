package com.whv.common.utils.security;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
/**
 * AES���ܹ���
 * @author huawei
 *
 */
public class AESUtil {
	
	private Key key;
	
	/**
	 * ����AES�Գ���Կ
	 * @throws NoSuchAlgorithmException
	 */
	public void generateKey() throws NoSuchAlgorithmException {
		KeyGenerator keygen = KeyGenerator.getInstance("AES");
		SecureRandom random = new SecureRandom();
		keygen.init(random);
		this.key = keygen.generateKey();
	}
	
	
	/**
	 * ����
	 * @param in
	 * @param out
	 * @throws InvalidKeyException
	 * @throws ShortBufferException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IOException
	 */
	public void encrypt(InputStream in, OutputStream out) throws InvalidKeyException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
		this.crypt(in, out, Cipher.ENCRYPT_MODE);
	}
	
	/**
	 * ����
	 * @param in
	 * @param out
	 * @throws InvalidKeyException
	 * @throws ShortBufferException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IOException
	 */
	public void decrypt(InputStream in, OutputStream out) throws InvalidKeyException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
		this.crypt(in, out, Cipher.DECRYPT_MODE);
	}

	/**
	 * ʵ�ʵļ��ܽ��ܹ���
	 * @param in
	 * @param out
	 * @param mode
	 * @throws IOException
	 * @throws ShortBufferException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 */
	public void crypt(InputStream in, OutputStream out, int mode) throws IOException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(mode, this.key);
		
		int blockSize = cipher.getBlockSize();
		int outputSize = cipher.getOutputSize(blockSize);
		byte[] inBytes = new byte[blockSize];
		byte[] outBytes = new byte[outputSize];
		
		int inLength = 0;
		boolean more = true;
		while (more) {
			inLength = in.read(inBytes);
			if (inLength == blockSize) {
				int outLength = cipher.update(inBytes, 0, blockSize, outBytes);
				out.write(outBytes, 0, outLength);
			} else {
				more = false;
			}
		}
		if (inLength > 0)
			outBytes = cipher.doFinal(inBytes, 0, inLength);
		else
			outBytes = cipher.doFinal();
		out.write(outBytes);
		out.flush();
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Key getKey() {
		return key;
	}
	
	public static void main(String[] args) throws Exception {
		AESUtil aes = new AESUtil();
        aes.generateKey();
        File file = new File("D:/aa.jpg");  
        FileInputStream in = new FileInputStream(file); 
        File file1 = new File("D:/temp/pubaes.key");  
        FileOutputStream out = new FileOutputStream(file1);  
		aes.encrypt(in, out);
		aes.decrypt(in, out);
	}

}

