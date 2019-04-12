package Ciphers;

public interface CipherInterface<T> {
    boolean setKey(final String key);

    T encrypt(final T plaintext);

    T decrypt(final T cipherText);
}
