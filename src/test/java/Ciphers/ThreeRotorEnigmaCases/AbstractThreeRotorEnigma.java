package Ciphers.ThreeRotorEnigmaCases;

import Ciphers.AbstractCipherMultiFile;
import Ciphers.ThreeRotorEnigmaEC;


abstract class AbstractThreeRotorEnigma extends AbstractCipherMultiFile {
    @Override
    protected Class cipher_class() {
        return ThreeRotorEnigmaEC.class;
    }
}