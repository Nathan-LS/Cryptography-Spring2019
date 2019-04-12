package Ciphers.RailFenceCases;

import Ciphers.AbstractCipherMultiTextFile;
import Ciphers.RailFence;


abstract class AbstractRailFence extends AbstractCipherMultiTextFile {
    @Override
    protected Class cipher_class() {
        return RailFence.class;
    }

}