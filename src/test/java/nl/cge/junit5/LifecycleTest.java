package nl.cge.junit5;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class LifecycleTest {

    public LifecycleTest() {
        System.out.println("Constructor");
    }

    @BeforeAll
    static public void beforeAll() {
        System.out.println("beforeAll");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("beforeEach");
    }

    @Test
    public void test1() {
        System.out.println("==> test 1");
    }

    @Test
    public void test2() {
        System.out.println("==> test 2");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("afterEach");
    }

    @AfterAll
    static public void afterAll() {
        System.out.println("afterAll");
    }


}
