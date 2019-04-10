package CipherDriver;

import Ciphers.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class CipherDriver {
    private String[] args;
    private CipherAbstractTextBase cipher;

    public CipherDriver(final String[] cli_args) {
        args = cli_args;
        if (args.length != 5) {
            System.out.println(String.format("This program requires 5 arguments, but %s were given.", args.length));
            System.exit(1);
        }
    }

    protected void setKey(final String key) {
        if (!cipher.setKey(key)) {
            System.exit(1);
        }
    }

    public void runCipher() throws IOException {
        cipher = CipherFactory.getCipher(args[0]);
        if (cipher == null){
            System.exit(1);
        }
        setKey(args[1]);
        switch (args[2]) {
            case "ENC":
                writeFile(new File(args[4]), cipher.encrypt(readFile(new File(args[3]))));
                System.out.println(String.format("Successfully encrypted the contents of %s to: %s", args[3], args[4]));
                break;
            case "DEC":
                writeFile(new File(args[4]), cipher.decrypt(readFile(new File(args[3]))));
                System.out.println(String.format("Successfully decrypted the contents of %s to: %s", args[3], args[4]));
                break;
            default:
                System.out.println(String.format("Unrecognized ENC/DEC type: %s", args[2]));
                System.exit(1);
        }
    }

    protected String readFile(final File inputFile) throws IOException {
        return FileUtils.readFileToString(inputFile, "utf-8");
    }

    protected void writeFile(final File outputFile, final String content) throws IOException {
        FileUtils.writeStringToFile(outputFile, content, "utf-8");
    }

}
