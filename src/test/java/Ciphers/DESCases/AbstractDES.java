package Ciphers.DESCases;

import Ciphers.DES;
import Ciphers.AbstractCipherMultiFile;


abstract class AbstractDES extends AbstractCipherMultiFile {
    @Override
    protected Class cipher_class() {
        return DES.class;
    }
}