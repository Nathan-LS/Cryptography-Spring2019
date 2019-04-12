package CipherDriver;

import Ciphers.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class CipherDriver {
    private String[] args;
    private CipherInterface cipher;

    public CipherDriver(final String[] cli_args) {
        args = cli_args;
        if (args.length != 5) {
            System.out.println(String.format("This program requires 5 arguments, but %s were given." +
                    "\nUsage: <CIPHER NAME> <KEY> <ENC/DEC> <INPUT FILE> <OUTPUT FILE>" +
                    "\nEx: AES 0123456789abcdef ENC input.png output.png", args.length));
            System.exit(1);
        }
    }

    protected void setKey(final String key) {
        if (!cipher.setKey(key)) {
            System.out.println("Something went wrong when setting the key.");
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
                if (cipher instanceof CipherAbstractByteBase){
                    writeFileByte(new File(args[4]), ((CipherAbstractByteBase)cipher).encrypt(readFileByte(new File(args[3]))));
                }
                else{
                    writeFileString(new File(args[4]), ((CipherAbstractTextBase)cipher).encrypt(readFileString(new File(args[3]))));
                }
                System.out.println(String.format("Successfully encrypted the contents of %s to: %s", args[3], args[4]));
                break;
            case "DEC":
                if (cipher instanceof CipherAbstractByteBase){
                    writeFileByte(new File(args[4]), ((CipherAbstractByteBase)cipher).decrypt(readFileByte(new File(args[3]))));
                }
                else{
                    writeFileString(new File(args[4]), ((CipherAbstractTextBase)cipher).decrypt(readFileString(new File(args[3]))));
                }
                System.out.println(String.format("Successfully decrypted the contents of %s to: %s", args[3], args[4]));
                break;
            default:
                System.out.println(String.format("Unrecognized ENC/DEC type: %s", args[2]));
                System.exit(1);
        }
    }

    protected String readFileString(final File inputFile) throws IOException {
        return FileUtils.readFileToString(inputFile, "utf-8");
    }

    protected void writeFileString(final File outputFile, final String content) throws IOException {
        FileUtils.writeStringToFile(outputFile, content, "utf-8");
    }

    protected byte[] readFileByte(final File inputFile) throws IOException {
        return FileUtils.readFileToByteArray(inputFile);
    }

    protected void writeFileByte(final File outputFile, final byte[] content) throws IOException {
        FileUtils.writeByteArrayToFile(outputFile, content);
    }


}
