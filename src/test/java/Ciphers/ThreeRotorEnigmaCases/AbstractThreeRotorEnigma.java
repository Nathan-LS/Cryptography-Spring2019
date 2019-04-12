package Ciphers.ThreeRotorEnigmaCases;

import Ciphers.AbstractCipherMultiTextFile;
import Ciphers.ThreeRotorEnigmaEC;


abstract class AbstractThreeRotorEnigma extends AbstractCipherMultiTextFile {
    @Override
    protected Class cipher_class() {
        return ThreeRotorEnigmaEC.class;
    }
}