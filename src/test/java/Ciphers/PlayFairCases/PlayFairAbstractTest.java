package Ciphers.PlayFairCases;

import Ciphers.AbstractCipherIndividual;
import Ciphers.PlayFair;


abstract class PlayFairAbstractTest extends AbstractCipherIndividual {
    @Override
    protected Class cipher_class() {
        return PlayFair.class;
    }

}