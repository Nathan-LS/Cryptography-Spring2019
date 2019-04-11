package Ciphers;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES extends CipherAbstractByteBase {

    private static SecretKeySpec secretKey;
    private static byte[] skey;

    /**
     * Sets the key to use
     * @param key - the first byte of this represents whether
     * to encrypt or to decrypt. 00 means encrypt and any other
     * value to decrypt. Then come the bytes of the 128-bit key
     * (should be 16 of them).
     * @return - True if key is valid anf False otherwise
     */
    @Override
    public boolean setKey(final String key) {
        if (key.length() != 16) {
            System.out.println("Key length must be 16 characters.");
            return false;
        } else {
            MessageDigest sha = null;
            try {
                skey = key.getBytes("UTF-8");
                sha = MessageDigest.getInstance("SHA-1");
                skey = sha.digest(skey);
                skey = Arrays.copyOf(skey, 16);
                secretKey = new SecretKeySpec(skey, "AES");
                return true;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return false;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return false;
            }
        }
    }


    /**
     * Encrypts a plaintext string
     * @param plaintext - the plaintext string
     * @return - the encrypted ciphertext string
     */
    @Override
    public byte[] encrypt(final byte[] plaintext) {
        byte[] plain = this.padder(plaintext, 128);
        byte[] encrypted = new byte[plain.length * 2];
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            for(int i=0; i < plain.length; i+=16){
                byte[] block = this.get_block(plain, 16, i);
                byte[] encryptedBlock = cipher.doFinal(block);
                System.arraycopy(encryptedBlock, 0, encrypted, i*2, 32);
            }
            return encrypted;
        } catch (Exception e ) {
            System.out.println("Error while encrypting: " + e.toString());
            return null;
        }
    }

    /**
     * Decrypts a string of ciphertext
     * @param cipherText - the ciphertext byte array
     * @return - the plaintext byte array
     */
    @Override
    public byte[] decrypt(final byte[] cipherText) {
        byte[] encrypted = this.padder(cipherText, 128);
        byte[] decrypted = new byte[encrypted.length];
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            for(int i=0; i < encrypted.length; i+=32){
                byte[] block = this.get_block(encrypted, 32, i);
                byte[] decryptedBlock = cipher.doFinal(block);
                System.arraycopy(decryptedBlock, 0, decrypted, i/2, 16);
            }
            return decrypted;
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
            return null;
        }
    }
}
