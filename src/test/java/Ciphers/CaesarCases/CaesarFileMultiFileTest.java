package Ciphers.CaesarCases;

import Ciphers.Caesar;
import Ciphers.CipherAbstractMultiFileTest;

public class CaesarFileMultiFileTest extends CipherAbstractMultiFileTest {
    @Override
    protected Class cipher_class() {
        return Caesar.class;
    }

    @Override
    protected String key() {
        return "9";
    }
}
