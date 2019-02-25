import CipherDriver.CipherDriver;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CipherDriver c = new CipherDriver(args);
        c.runCipher();
    }
}