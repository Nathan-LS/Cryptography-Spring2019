package Ciphers.PlayFairCases;

import Ciphers.AbstractCipherMultiFile;
import Ciphers.PlayFair;


abstract class AbstractPlayFair extends AbstractCipherMultiFile {
    @Override
    protected Class cipher_class() {
        return PlayFair.class;
    }

}