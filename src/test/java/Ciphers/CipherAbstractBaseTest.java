package Ciphers;

import org.junit.jupiter.api.BeforeEach;

public abstract class CipherAbstractBaseTest {
    CipherAbstractBase cipher;

    @BeforeEach
    void setUp() throws Exception {
        cipher = reflection_make();
        cipher.setKey(key());
    }

    private CipherAbstractBase reflection_make() throws Exception {
        return cipher_class().getConstructor().newInstance();
    }

    // a class definition for reflection
    protected abstract Class<CipherAbstractBase> cipher_class();

    // this is the key used for the test case
    protected abstract String key();
}
