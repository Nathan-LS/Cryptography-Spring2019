package Ciphers.PlayFairCases;

import Ciphers.CipherAbstractIndividualTest;
import Ciphers.PlayFair;


abstract class PlayFairAbstractTest extends CipherAbstractIndividualTest {
    @Override
    protected Class cipher_class() {
        return PlayFair.class;
    }

}