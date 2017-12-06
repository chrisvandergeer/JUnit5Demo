package nl.cge.junit5;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class MyDynamicTest {

    @TestFactory
    public Collection<DynamicTest> testMe() {
        return Arrays.asList(
                dynamicTest("1st test", () -> assertEquals(1, 1)),
                dynamicTest("2nd test", () -> assertEquals(2, 2)));
    }
}
