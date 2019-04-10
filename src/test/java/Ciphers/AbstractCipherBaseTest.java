package Ciphers;

import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractCipherBaseTest<T extends CipherInterface> {
    T cipher;

    @BeforeEach
    void setUp() throws Exception {
        cipher = reflection_make();
        cipher.setKey(key());
    }

    private T reflection_make() throws Exception {
        return cipher_class().getConstructor().newInstance();
    }

    // a class definition for reflection
    protected abstract Class<T> cipher_class();

    // this is the key used for the test case
    protected abstract String key();
}
