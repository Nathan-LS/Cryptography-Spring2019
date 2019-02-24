package Ciphers.CaesarCases;

import Ciphers.Caesar;
import Ciphers.CipherBaseTest;

public abstract class CaesarAbstractTest extends CipherBaseTest {
    @Override
    protected Class cipher_class() {
        return Caesar.class;
    }
}