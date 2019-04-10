package Ciphers.DESCases;

import Ciphers.AbstractCipherMultiByteFile;
import Ciphers.DES;


abstract class AbstractDES extends AbstractCipherMultiByteFile {
    @Override
    protected Class cipher_class() {
        return DES.class;
    }
}