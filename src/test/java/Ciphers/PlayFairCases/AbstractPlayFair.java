package Ciphers.PlayFairCases;

import Ciphers.AbstractCipherMultiTextFile;
import Ciphers.PlayFair;


abstract class AbstractPlayFair extends AbstractCipherMultiTextFile {
    @Override
    protected Class cipher_class() {
        return PlayFair.class;
    }

}