package Ciphers;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES extends CipherAbstractBase {

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
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            return true;
        }

        /*if (key.length() != 16) {
            System.out.println("Key length must be 16 characters.");
            return false;
        } else {
            CipherKey = key;
            return true;
        }*/
    }

    /**
     * Encrypts a plaintext string
     * @param plaintext - the plaintext string
     * @return - the encrypted ciphertext string
     */
    @Override
    public String encrypt(final String plaintext) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            return Base64.getEncoder().encodeToString(cipher.doFinal(plaintext.getBytes("UTF-8")));
        } catch (Exception e ) {
            System.out.println("Error while encrypting: " + e.toString());
        }

        return null;
    }

    /**
     * Decrypts a string of ciphertext
     * @param cipherText - the ciphertext
     * @return - the plaintext
     */
    @Override
    public String decrypt(final String cipherText) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            
            return new String(cipher.doFinal(Base64.getDecoder().decode(cipherText)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }

        return null;
    }
}
