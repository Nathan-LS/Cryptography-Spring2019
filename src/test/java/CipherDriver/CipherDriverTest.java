package CipherDriver;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CipherDriverTest {
    final private String[] args = {"CES", "9", "ENC", "plaintext_1.txt", "test_outputFile.txt"};
    private CipherDriver driver;
    private File inputFile;
    private File outFile;
    private String inputString;

    @BeforeEach
    void setUp() throws Exception {
        driver = new CipherDriver(args);
        outFile = new File("test_outputFile.txt");
        inputFile = new File("plaintext_1.txt");
        inputString = "Hello this is a test string which we are testing. You are reading a test string.";
        FileUtils.writeStringToFile(inputFile, inputString, "utf-8");
    }

    @AfterEach
    void tearDown() {
        FileUtils.deleteQuietly(outFile);
        FileUtils.deleteQuietly(inputFile);
    }

    @Test
    void runCipher() throws Exception {
        driver.runCipher();
        String match = "Qnuux cqrb rb j cnbc bcarwp fqrlq fn jan cnbcrwp. Hxd jan anjmrwp j cnbc bcarwp.";
        assertEquals(match, FileUtils.readFileToString(outFile, "utf-8"));
    }

    @Test
    void readFile() throws Exception {
        assertEquals(inputString, driver.readFileString(inputFile));
    }

    @Test
    void writeFile() throws Exception {
        driver.writeFileString(outFile, inputString);
        assertEquals(inputString, FileUtils.readFileToString(outFile, "utf-8"));
    }
}