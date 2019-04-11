package Ciphers.AESCases;

import Ciphers.AES;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AESPadder {
    private AES cipher;

    @BeforeEach
    void setUp() {
        cipher = new AES();
    }

    @Test
    void pad_less() {
        byte[] a = {1, 2, 3};
        byte[] p = cipher.padder(a, 128);
        assertEquals(128, p.length);
    }

    @Test
    void pad_equal() {
        byte[] a = new byte[128];
        Arrays.fill(a, (byte) 1);
        byte[] p = cipher.padder(a, 128);
        assertEquals(128, p.length);
        assertArrayEquals(a, p);
    }

    @Test
    void pad_1more() {
        byte[] a = new byte[129];
        Arrays.fill(a, (byte) 1);
        byte[] p = cipher.padder(a, 128);
        assertEquals(256, p.length);
    }

//    @Test
//    void setKeyInvalid() {
//        for (String s : Arrays.asList("test", "t", "5.5", "100", "-5", "0123456789abcde")) {
//            assertFalse(cipher.setKey(s));
//        }
//    }
}