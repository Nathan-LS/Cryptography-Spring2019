package Ciphers;

public interface CipherInterface {
    boolean setKey(final String key);

    String encrypt(String plaintext);

    String decrypt(String cipherText);
}
