package Ciphers.RailFenceCases;

import Ciphers.AbstractCipherMultiFile;
import Ciphers.Railfence;


abstract class AbstractRailFence extends AbstractCipherMultiFile {
    @Override
    protected Class cipher_class() {
        return Railfence.class;
    }

}