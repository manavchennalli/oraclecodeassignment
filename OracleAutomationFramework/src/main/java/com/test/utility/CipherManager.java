package com.test.utility;

import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author Manav C K Date: 08 November 2020
 *
 */
public class CipherManager {
	public final static String ALGORITHM = "DES";
	public final static String secretKey = "01234567";
	private static final String HEX = "0123456789ABCDEF";

	public static String encode(String stringToEncode) {

		SecretKey myDesKey = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);

		String encryptResult = null;

		Cipher desCipher;
		try {
			desCipher = Cipher.getInstance(ALGORITHM);
			desCipher.init(1, myDesKey);
			byte[] text = stringToEncode.getBytes();
			byte[] textEncrypted = desCipher.doFinal(text);
			encryptResult = toHex(textEncrypted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptResult;
	}

	public static String toHex(byte[] stringBytes) {
		StringBuilder result = new StringBuilder(2 * stringBytes.length);

		for (int i = 0; i < stringBytes.length; i++) {
			result.append(HEX.charAt(stringBytes[i] >> 4 & 0xF)).append(HEX.charAt(stringBytes[i] & 0xF));
		}

		return result.toString();
	}

	public static String decode(String decode) {

		byte[] decryResult = null;
		try {
			SecretKey key = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			decryResult = cipher.doFinal(toByte(decode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(decryResult);
	}

	private static byte[] toByte(String hexString) {
		int len = hexString.length() / 2;
		byte[] result = new byte[len];

		for (int i = 0; i < len; i++) {
			result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();

		}
		return result;
	}

	public static void main(String[] args) {
		String password;
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the password: ");
		password = in.nextLine();
		System.out.println("Encrypted Code: " + encode(password));
		in.close();
	}

}