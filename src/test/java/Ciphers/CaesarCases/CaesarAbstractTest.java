package Ciphers.CaesarCases;

import Ciphers.Caesar;
import Ciphers.CipherAbstractIndividualTest;

public abstract class CaesarAbstractTest extends CipherAbstractIndividualTest {
    @Override
    protected Class cipher_class() {
        return Caesar.class;
    }
}