package Ciphers.RailFenceInvertedCases;

import Ciphers.RailFenceInverted;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RailFenceTestKey {
    private RailFenceInverted cipher;

    @BeforeEach
    void setUp() {
        cipher = new RailFenceInverted();
    }

    @Test
    void setKeyValid() {
        for (String s : Arrays.asList("1", "2", "3", "4", "23")) {
            assertTrue(cipher.setKey(s), s);
        }
    }

    @Test
    void setKeyInvalid() {
        for (String s : Arrays.asList("test", "t", "5.5", "-5", "0", "-1")) {
            assertFalse(cipher.setKey(s), s);
        }
    }
}