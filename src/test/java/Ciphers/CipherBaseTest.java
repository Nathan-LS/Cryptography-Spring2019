package Ciphers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class CipherBaseTest {
    private CipherAbstractBase cipher;

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

    // this is the plaintext test case string to be encrypted.
    protected abstract String string_to_encrypt();

    // this is what the encrypted string should look like after being encrypted
    protected abstract String assert_string_encrypted();

    @Test
    void encrypt() {
        assertEquals(assert_string_encrypted(), cipher.encrypt(string_to_encrypt()));
    }

    @Test
    void decrypt() {
        String encrypted_text = cipher.encrypt(string_to_encrypt());
        assertEquals(string_to_encrypt(), cipher.decrypt(encrypted_text));
    }
}

