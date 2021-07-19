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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.*;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Random;

class AES {
    byte[] skey = new byte[1000];
    String skeyString;
    static byte[] raw;
    String inputMessage, encryptedData, decryptedMessage;

    public AES(byte[] ibyte) {
        try {
            generateSymmetricKey();

            // inputMessage=JOptionPane.showInputDialog(null,"Enter message to encrypt");
            // inputMessage = new String(Files.readAllBytes(Paths.get("hello.txt")));
            // byte[] ibyte = inputMessage.getBytes();
            byte[] ebyte = encrypt(raw, ibyte);
            String encryptedData = new String(ebyte);
            System.out.println("Encrypted message " + encryptedData);
            BufferedWriter bufferedWriter = null;
            try {
                // String strContent = "This example shows how to write string content to a
                // file";
                File myFile = new File("output1.txt");
                // check if file exist, otherwise create the file before writing
                if (!myFile.exists()) {
                    myFile.createNewFile();
                }
                Writer writer = new FileWriter(myFile);
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
            JOptionPane.showMessageDialog(null, "Encrypted Data " + "\n" + encryptedData);

            byte[] dbyte = decrypt(raw, ebyte);
            String decryptedMessage = new String(dbyte);
            System.out.println("Decrypted message " + decryptedMessage);

            JOptionPane.showMessageDialog(null, "Decrypted Data " + "\n" + decryptedMessage);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    void generateSymmetricKey() {
        try {
            Random r = new Random();
            int num = r.nextInt(10000);
            String knum = String.valueOf(num);
            byte[] knumb = knum.getBytes();
            skey = getRawKey(knumb);
            skeyString = new String(skey);
            System.out.println("AES Symmetric key = " + skeyString);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(seed);
        kgen.init(128, sr); // 192 and 256 bits may not be available
        SecretKey skey = kgen.generateKey();
        raw = skey.getEncoded();
        return raw;
    }

    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }

    private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

}
