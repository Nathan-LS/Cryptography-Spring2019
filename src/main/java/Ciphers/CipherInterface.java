package Ciphers;

public interface CipherInterface {
    boolean setKey(final String key);

    String encrypt(final String plaintext);

    String decrypt(final String cipherText);
}
