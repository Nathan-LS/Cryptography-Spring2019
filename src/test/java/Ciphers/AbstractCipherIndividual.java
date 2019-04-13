package Ciphers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractCipherIndividual extends AbstractCipherBaseTest<CipherAbstractTextBase> {
    /**
     * Extend this class to test single, simple cases with a cipher. You must override:
     * string_to_encrypt        Return a string to be encrypted.
     * assert_string_encrypted  Return an assert string that the string_to_encrypt must match after being run through the encrypt function.
     * key                      The key to use for the encryption and decryption.
     * cipher_class             The class you wish to run the test on. Example Caesar.class
     */
    // this abstract defines a single test case for a single string. Overwrite all 3 methods for the test case.

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
