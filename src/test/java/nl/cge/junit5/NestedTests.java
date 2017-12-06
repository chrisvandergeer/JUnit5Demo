package nl.cge.junit5;

import nl.cge.domein.Bankrekening;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;


// org.junit.jupiter.api.

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Bankrekening Tests \uD83D\uDE04")
class NestedTests {

    private Bankrekening bankrekening;

    @BeforeEach
    public void setup() {
        bankrekening = new Bankrekening("NL60RABO123");
    }

    @DisplayName("Saldo = 0, limiet = 1000")
    @Nested
    class GeenSaldoWelLimit {

        @BeforeEach
        public void setup() {
            bankrekening.wijzigLimit(BigDecimal.valueOf(1000));
        }

        @DisplayName("1000 euro opnemen")
        @Test
        public void test1000Opnemen() {
            assertTrue(bankrekening.neemOp(BigDecimal.valueOf(1000)));
        }

        @DisplayName("1001 euro opnemen")
        @Test
        public void test1001Openemen() {
            assertFalse(bankrekening.neemOp(BigDecimal.valueOf(1001)));
        }
    }

    @DisplayName("Saldo = 1000, limit = 0")
    @Nested
    class WelSaldoGeenLimiet {

        @BeforeEach
        public void setup() {
            bankrekening.boekBij(BigDecimal.valueOf(1000));
        }

        @DisplayName("1000 euro opnemen")
        @Test
        public void test1000Opnemen() {
            assertTrue(bankrekening.neemOp(BigDecimal.valueOf(1000)));
        }

        @DisplayName("1001 euro opnemen")
        @Test
        public void test1001Opnemen() {
            assertFalse(bankrekening.neemOp(BigDecimal.valueOf(1001)));
        }
    }

    @DisplayName("Saldo = 1000, limit = 1000")
    @Nested
    class WelSaldoWelLimit {

        @BeforeEach
        public void setup() {
            bankrekening.boekBij(BigDecimal.valueOf(1000));
            bankrekening.wijzigLimit(BigDecimal.valueOf(1000));
        }

        @DisplayName("2000 euro opnemen")
        @Test
        public void test2000Openemen() {
            assertTrue(bankrekening.neemOp(BigDecimal.valueOf(2000)));
        }

        @DisplayName("2001 euro opnemen")
        @Test
        public void test2001Opnemen() {
            assertFalse(bankrekening.neemOp(BigDecimal.valueOf(2001)));
        }
    }

}
