package Ciphers.DESCases;

import Ciphers.DES;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DESTestKey {
    private DES cipher;

    @BeforeEach
    void setUp() {
        cipher = new DES();
    }

    @Test
    void setKeyValid() {
        assertTrue(cipher.setKey("0123456789abcdef"));
    }

    @Test
    void setKeyInvalid() {
        for (String s : Arrays.asList("test", "t", "5.5", "100", "-5", "0123456789abcde")) {
            assertFalse(cipher.setKey(s));
        }
    }
}