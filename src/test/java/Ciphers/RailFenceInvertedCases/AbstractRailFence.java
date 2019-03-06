package Ciphers.RailFenceInvertedCases;

import Ciphers.AbstractCipherMultiFile;
import Ciphers.RailFenceInverted;


abstract class AbstractRailFence extends AbstractCipherMultiFile {
    @Override
    protected Class cipher_class() {
        return RailFenceInverted.class;
    }

}