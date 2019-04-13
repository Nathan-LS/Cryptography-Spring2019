package Ciphers;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractCipherBaseTest<T extends CipherInterface> {
    T cipher;

    @BeforeEach
    void setUp() throws Exception {
        cipher = reflection_make();
        assertTrue(cipher.setKey(key())); // DES fails here
    }

    private T reflection_make() throws Exception {
        return cipher_class().getConstructor().newInstance();
    }

    // a class definition for reflection
    protected abstract Class<T> cipher_class();

    // this is the key used for the test case
    protected abstract String key();
}
