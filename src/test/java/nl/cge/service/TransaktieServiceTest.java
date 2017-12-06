package nl.cge.service;

import nl.cge.domein.Bankrekening;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.*;
import static org.junit.jupiter.api.Assertions.*;

class TransaktieServiceTest {

    @Test
    public void testMaakOver() {
        // GIVEN
        Bankrekening van = new Bankrekening("123");
        van.boekBij(valueOf(1000));

        Bankrekening naar = new Bankrekening("987");
        naar.boekBij(valueOf(500));

        // WHEN
        boolean overmakenGelukt = new TransaktieService().maakOver(van, naar, ONE);

        // THEN
        assertAll("verwachte state na overmaken",
                () -> assertTrue(overmakenGelukt),
                () -> assertEquals(van.getSaldo(), valueOf(999)),
                () -> assertEquals(naar.getSaldo(), valueOf(501))
        );
    }

    @Test
    @Disabled("Gewenst resultaat is nog onduidelijk, wordt momenteel afgestemd met de PO")
    public void testMaakOverBij1EuroTeWeinig() {
        fail("");
    }

}