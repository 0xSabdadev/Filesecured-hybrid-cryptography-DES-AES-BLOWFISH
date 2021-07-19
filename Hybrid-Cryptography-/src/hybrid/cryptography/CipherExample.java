/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hybrid.cryptography;

import java.io.FileInputStream;
import java.security.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import java.util.Random;
import javax.swing.*;
import java.util.Random;

public class CipherExample {

	CipherExample() {

	}

	// needs to be at least 8 characters for DES
	public void aencrypt(String key, String path) {

		try {
			FileInputStream fis = new FileInputStream(path);
			FileOutputStream fos = new FileOutputStream("cipher_DES.txt");
			encrypt(key, fis, fos);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	void adecrypt(String key, String path) {
		try {
			FileInputStream fis2 = new FileInputStream(path);
			FileOutputStream fos2 = new FileOutputStream("decrypt_final.txt");
			decrypt_des(key, fis2, fos2);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void encrypt_AES(String key, String path) {

		try {
			FileInputStream fis = new FileInputStream(path);
			FileOutputStream fos = new FileOutputStream("cipher_AES.txt");
			encrypt_AES(key, fis, fos);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void encrypt_blowfish(String key, String path) {

		try {
			FileInputStream fis = new FileInputStream(path);
			FileOutputStream fos = new FileOutputStream("cipher_Blow.txt");
			encrypt_AES(key, fis, fos);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	void decrypt_AES(String key, String path) {
		try {
			FileInputStream fis2 = new FileInputStream(path);
			FileOutputStream fos2 = new FileOutputStream("decrypt_DES.txt");
			decrypt_AES(key, fis2, fos2);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	void decrypt_blowfish(String key, String path) {
		try {
			FileInputStream fis2 = new FileInputStream(path);
			FileOutputStream fos2 = new FileOutputStream("decrypt_AES.txt");
			decrypt_blowfish(key, fis2, fos2);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	void encrypt(String key, InputStream is, OutputStream os) {
		try {
			encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, is, os);
		}

		catch (Throwable e) {
			e.printStackTrace();
		}
	}

	void encrypt_AES(String key, InputStream is, OutputStream os) {
		try {
			encryptordecrpyt_AES1(key, Cipher.ENCRYPT_MODE, is, os);
		}

		catch (Throwable e) {
			e.printStackTrace();
		}
	}

	void encrypt_blowfish(String key, InputStream is, OutputStream os) {
		try {
			encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, is, os);
		}

		catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void decrypt_des(String key, InputStream is, OutputStream os) {
		try {
			encryptOrDecrypt(key, Cipher.DECRYPT_MODE, is, os);
			// System.out.println("AES encrptyion");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void decrypt_AES(String key, InputStream is, OutputStream os) {
		try {
			encryptordecrpyt_AES1(key, Cipher.DECRYPT_MODE, is, os);
			// System.out.println("AES decrption");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void decrypt_blowfish(String key, InputStream is, OutputStream os) {
		try {
			encryptordecrpyt_AES1(key, Cipher.DECRYPT_MODE, is, os);
			// System.out.println("AES decrption");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable {
		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey desKey = skf.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES"); // DES/ECB/PKCS5Padding for SunJCE

		if (mode == Cipher.ENCRYPT_MODE) {
			cipher.init(Cipher.ENCRYPT_MODE, desKey);
			CipherInputStream cis = new CipherInputStream(is, cipher);
			doCopy(cis, os);
		} else if (mode == Cipher.DECRYPT_MODE) {
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			CipherOutputStream cos = new CipherOutputStream(os, cipher);
			doCopy(is, cos);
		}
	}

	public static void encryptordecrpyt_AES1(String key, int mode, InputStream is, OutputStream os) throws Throwable {

		DESedeKeySpec aes1 = new DESedeKeySpec(key.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
		SecretKey desKey = skf.generateSecret(aes1);
		Cipher cipher = Cipher.getInstance("DESede"); // DES/ECB/PKCS5Padding for SunJCE

		if (mode == Cipher.ENCRYPT_MODE) {
			cipher.init(Cipher.ENCRYPT_MODE, desKey);
			CipherInputStream cis = new CipherInputStream(is, cipher);
			doCopy(cis, os);
		} else if (mode == Cipher.DECRYPT_MODE) {
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			CipherOutputStream cos = new CipherOutputStream(os, cipher);
			doCopy(is, cos);
		}
	}

	public static void encryptordecrpyt_blowfish(String key, int mode, InputStream is, OutputStream os)
			throws Throwable {

		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "blowfish");
		SecretKeyFactory skf = SecretKeyFactory.getInstance("blowfish");
		SecretKey bkey = skf.generateSecret(skeySpec);
		Cipher cipher = Cipher.getInstance("Blowfish");

		if (mode == Cipher.ENCRYPT_MODE) {
			cipher.init(Cipher.ENCRYPT_MODE, bkey);
			CipherInputStream cis = new CipherInputStream(is, cipher);
			doCopy(cis, os);
		} else if (mode == Cipher.DECRYPT_MODE) {
			cipher.init(Cipher.DECRYPT_MODE, bkey);
			CipherOutputStream cos = new CipherOutputStream(os, cipher);
			doCopy(is, cos);
		}
	}

	public static void doCopy(InputStream is, OutputStream os) throws IOException {
		byte[] bytes = new byte[64];
		int numBytes;
		while ((numBytes = is.read(bytes)) != -1) {
			os.write(bytes, 0, numBytes);
		}
		os.flush();
		os.close();
		is.close();
	}

}
