package nl.cge.junit5;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class AssumptionsTest {

    @Test
    public void testOSX() {
        assumeTrue("Mac OS X".equals(System.getProperty("os.name")));
        assertTrue(Files.isDirectory(Paths.get("/tmp")));
    }

    @Test
    public void testWindows() {
        assumeTrue("Windows".equals(System.getProperty("os.name")));
        assertTrue(Files.isDirectory(Paths.get("C:/Windows")));
    }

}
