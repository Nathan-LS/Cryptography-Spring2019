package Ciphers.CaesarCases;

import Ciphers.AbstractCipherMultiFile;
import Ciphers.Caesar;

abstract class AbstractCaesarMultiFile extends AbstractCipherMultiFile {
    @Override
    protected Class cipher_class() {
        return Caesar.class;
    }
}
