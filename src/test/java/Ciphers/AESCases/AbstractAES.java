package Ciphers.AESCases;

import Ciphers.AES;
import Ciphers.AbstractCipherMultiFile;


abstract class AbstractAES extends AbstractCipherMultiFile {
    @Override
    protected Class cipher_class() {
        return AES.class;
    }
}