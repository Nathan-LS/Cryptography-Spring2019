package Ciphers.AESCases;

import Ciphers.AES;
import Ciphers.AbstractCipherMultiByteFile;


abstract class AbstractAES extends AbstractCipherMultiByteFile {
    @Override
    protected Class cipher_class() {
        return AES.class;
    }
}