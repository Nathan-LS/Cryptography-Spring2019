package Ciphers.RailFenceInvertedCases;

import Ciphers.AbstractCipherMultiTextFile;
import Ciphers.RailFenceInverted;


abstract class AbstractRailFence extends AbstractCipherMultiTextFile {
    @Override
    protected Class cipher_class() {
        return RailFenceInverted.class;
    }

}