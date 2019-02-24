package Ciphers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CaesarTestKey {
    private Caesar cipher;

    @BeforeEach
    void setUp() {
        cipher = new Caesar();
    }

    @Test
    void setKeyValid() {
        for (String s : new ArrayList<String>(Arrays.asList("1", "2", "3", "4", "23"))) {
            assertTrue(cipher.setKey(s));
        }
    }

    @Test
    void setKeyInvalid() {
        for (String s : new ArrayList<String>(Arrays.asList("test", "t", "5.5", "100", "-5"))) {
            assertFalse(cipher.setKey(s));
        }
    }
}