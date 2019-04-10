package CipherDriver;

import Ciphers.*;

public class CipherFactory {
    static CipherAbstractTextBase getCipher(String cipher){
        switch (cipher) {
            case "PLF":
                return new PlayFair();
            case "RTS":
                return new RowTransposition();
            case "RFC":
                return new RailFence();
            case "RFCI":
                return new RailFenceInverted();
            case "VIG":
                return new Vigenre();
            case "CES":
                return new Caesar();
            case "HIL":
                return new HillCipherEC();
            case "3RE":
                return new ThreeRotorEnigmaEC();
            default:
                System.out.println(String.format("Unrecognized cipher type: %s", cipher));
                return null;
        }
    }
}
