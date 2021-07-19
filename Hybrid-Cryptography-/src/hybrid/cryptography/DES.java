/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hybrid.cryptography;

/**
 *
 * @author rakesh
 */

/**
 * @param args the command line arguments
 */
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Random;

class DES {
    byte[] skey = new byte[1000];
    String skeyString;
    // static byte[] raw;
    String encryptedData, decryptedMessage;

    public DES() {
    }

    public void aencrypt(byte[] raw, byte[] ibyte) {
        try {
            // generateSymmetricKey();

            // inputMessage=JOptionPane.showInputDialog(null,"Enter message to encrypt");

            // byte[] ibyte = inputMessage.getBytes();
            System.out.println("in aencrypt method");

            // generateSymmetricKey(raw);
            byte[] ebyte = encrypt(raw, ibyte);
            System.out.println("in aencrypt method");
            String encryptedData = new String(ebyte);
            System.out.println("Encrypted message " + encryptedData);
            // JOptionPane.showMessageDialog(null,"Encrypted Data "+"\n"+encryptedData);
            BufferedWriter bufferedWriter = null;
            try {
                // String strContent = "This example shows how to write string content to a
                // file";
                File myFile = new File("output.txt");
                // check if file exist, otherwise create the file before writing
                if (!myFile.exists()) {
                    myFile.createNewFile();
                }
                Writer writer = new FileWriter(myFile, true);
                bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write(encryptedData);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bufferedWriter != null)
                        bufferedWriter.close();
                } catch (Exception ex) {

                }
            }
            // inputMessage = new String(Files.readAllBytes(Paths.get("hello.txt")));
            // ebyte= inputMessage.getBytes();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void adecrypt(byte[] ebyte, byte[] raw1) {
        System.out.println("in adecrypt method");
        // System.out.println("this is raw:"+raw);
        // System.out.println(Arrays.equals(raw, raw1));
        // System.out.println("in adecrypt method");
        generateSymmetricKey(raw1);
        try {
            byte[] dbyte = decrypt(raw1, ebyte);
            System.out.println("in adecrypt method");
            String decryptedMessage = new String(dbyte);
            System.out.println("Decrypted message " + decryptedMessage);

            JOptionPane.showMessageDialog(null, "Decrypted Data " + "\n" + decryptedMessage);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    void generateSymmetricKey(byte[] raw) {
        try {
            /*
             * Random r = new Random(); int num = r.nextInt(10000); String knum =
             * String.valueOf(num); byte[] knumb = knum.getBytes();
             */
            skey = getRawKey(raw);
            System.out.println("this is raw:" + skey);
            skeyString = new String(skey);
            System.out.println("DES Symmetric key = " + skeyString);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("DES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(seed);
        kgen.init(56, sr);
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        return raw;
    }

    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        System.out.println(raw);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "DES");

        System.out.println(skeySpec);
        System.out.println(raw);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(clear);
        System.out.println(raw);
        return encrypted;
    }

    private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        System.out.println("at the last step");
        byte[] decrypted = cipher.doFinal(encrypted);
        System.out.println("at the last step");
        return decrypted;
    }

}
