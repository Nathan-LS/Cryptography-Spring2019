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
        byte[] p = cipher.padder(a, 16);
        assertEquals(16, p.length);
    }

    @Test
    void pad_equal() {
        byte[] a = new byte[16];
        Arrays.fill(a, (byte) 1);
        byte[] p = cipher.padder(a, 16);
        assertEquals(16, p.length);
        assertArrayEquals(a, p);
    }

    @Test
    void pad_1more() {
        byte[] a = new byte[17];
        Arrays.fill(a, (byte) 1);
        byte[] p = cipher.padder(a, 16);
        assertEquals(32, p.length);
    }

    @Test
    void pad_more() {
        byte[] a = new byte[15459];
        Arrays.fill(a, (byte) 1);
        byte[] p = cipher.padder(a, 16);
        assertEquals(15472, p.length);
    }

    @Test
    void pad_strip(){
        byte[] a = {1, 2, 3};
        assertArrayEquals(a, cipher.padderStrip(a));
    }

    @Test
    void pad_strip2(){
        byte[] a = {1, 2, 3, 0};
        byte[] b = {1, 2, 3};
        assertArrayEquals(b, cipher.padderStrip(a));
    }

    @Test
    void pad_strip3(){
        byte[] a = {0, 0, 0, 0};
        byte[] b = {};
        assertArrayEquals(b, cipher.padderStrip(a));
    }

    @Test
    void pad_strip4(){
        byte[] a = {0, 1, 0, 0};
        byte[] b = {0, 1};
        assertArrayEquals(b, cipher.padderStrip(a));
    }
}