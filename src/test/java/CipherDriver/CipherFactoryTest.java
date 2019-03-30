package CipherDriver;

import Ciphers.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CipherFactoryTest {

    @Test
    void getCipher_PLF() {
        assertTrue(CipherFactory.getCipher("PLF") instanceof PlayFair);
    }
    @Test
    void getCipher_RTS() {
        assertTrue(CipherFactory.getCipher("RTS") instanceof RowTransposition);
    }
    @Test
    void getCipher_RFC() {
        assertTrue(CipherFactory.getCipher("RFC") instanceof RailFence);
    }
    @Test
    void getCipher_RFCI() {
        assertTrue(CipherFactory.getCipher("RFCI") instanceof RailFenceInverted);
    }
    @Test
    void getCipher_VIG() {
        assertTrue(CipherFactory.getCipher("VIG") instanceof Vigenre);
    }
    @Test
    void getCipher_CES() {
        assertTrue(CipherFactory.getCipher("CES") instanceof Caesar);
    }
    @Test
    void getCipher_HIL() {
        assertTrue(CipherFactory.getCipher("HIL") instanceof HillCipherEC);
    }
    @Test
    void getCipher_3RE() {
        assertTrue(CipherFactory.getCipher("3RE") instanceof ThreeRotorEnigmaEC);
    }

    @Test
    void getCipher_unknown() {
        assertNull(CipherFactory.getCipher("3ghRE"));
    }
}