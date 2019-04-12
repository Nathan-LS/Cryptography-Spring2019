package Ciphers.HillCipherCases;

import Ciphers.AbstractCipherMultiTextFile;
import Ciphers.HillCipherEC;


abstract class AbstractHillCipher extends AbstractCipherMultiTextFile {
    @Override
    protected Class cipher_class() {
        return HillCipherEC.class;
    }
}