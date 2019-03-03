package Ciphers.HillCipherCases;

import Ciphers.AbstractCipherMultiFile;
import Ciphers.HillCipherEC;


abstract class AbstractHillCipher extends AbstractCipherMultiFile {
    @Override
    protected Class cipher_class() {
        return HillCipherEC.class;
    }
}