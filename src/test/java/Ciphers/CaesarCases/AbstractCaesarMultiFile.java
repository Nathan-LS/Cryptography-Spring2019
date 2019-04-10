package Ciphers.CaesarCases;

import Ciphers.AbstractCipherMultiTextFile;
import Ciphers.Caesar;

abstract class AbstractCaesarMultiFile extends AbstractCipherMultiTextFile {
    @Override
    protected Class cipher_class() {
        return Caesar.class;
    }
}
