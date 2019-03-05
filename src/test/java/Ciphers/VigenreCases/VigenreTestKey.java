package Ciphers.VigenreCases;

import Ciphers.Vigenre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VigenreTestKey {
    private Vigenre cipher;

    @BeforeEach 
    void setUp() {
        cipher = new Vigenre();
    }

    @Test
    void setKeyValid() {
        for (String s : Arrays.asList("VIGENRE", "DECEPTIVE", "COMPUTER")) {
            assertTrue(cipher.setKey(s), s);
        }
    }

    @Test 
    void setKeyInvalid() {
        for (String s : Arrays.asList("1", "-1", "5.5")) {
            assertFalse(cipher.setKey(s), s);
        }
    }
}