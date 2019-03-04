package Ciphers.RailFenceCases;

import Ciphers.AbstractCipherMultiFile;
import Ciphers.RailFence;


abstract class AbstractRailFence extends AbstractCipherMultiFile {
    @Override
    protected Class cipher_class() {
        return RailFence.class;
    }

}