package nl.cge.automatischeincasso;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static java.time.LocalDate.*;
import static org.junit.jupiter.api.Assertions.*;

class TijdvakBepalerTest {

    @Test
    public void testVolledigTijdvak() {
        // GIVEN
        Periode volledigTijdvak = new Periode(of(2016, 2, 2), of(2016, 5, 1));
        Periode verkortTijdvak = new Periode(of(2016, 2, 2), of(2016, 5, 1));
        Vordering vordering = new Vordering(BigDecimal.valueOf(100), volledigTijdvak, verkortTijdvak);

        // WHEN
        List<Tijdvak> tijdvakken = new TijdvakBepaler().bepaalTijdvakken(vordering);

        // THEN
        Tijdvak tijdvak1 = tijdvakken.get(0);
        assertEquals(LocalDate.of(2016, 2, 2), tijdvak1.getPeriode().getBegindatum());
        assertEquals(LocalDate.of(2016,3, 1), tijdvak1.getPeriode().getEinddatum());
        assertEquals(BigDecimal.valueOf(33), tijdvak1.getBedrag());

        Tijdvak tijdvak2 = tijdvakken.get(1);
        assertEquals(LocalDate.of(2016, 3, 2), tijdvak2.getPeriode().getBegindatum());
        assertEquals(LocalDate.of(2016,4, 1), tijdvak2.getPeriode().getEinddatum());
        assertEquals(BigDecimal.valueOf(33), tijdvak2.getBedrag());

        Tijdvak tijdvak3 = tijdvakken.get(2);
        assertEquals(LocalDate.of(2016, 4, 2), tijdvak3.getPeriode().getBegindatum());
        assertEquals(LocalDate.of(2016,5, 1), tijdvak3.getPeriode().getEinddatum());
        assertEquals(BigDecimal.valueOf(34), tijdvak3.getBedrag());
    }

    @Test
    public void testVerkortTijdvakBegin() {
        // GIVEN
        Periode volledigTijdvak = new Periode(of(2016, 2, 2), of(2016, 5, 1));
        Periode verkortTijdvak = new Periode(of(2016, 2, 26), of(2016, 5, 1));
        Vordering vordering = new Vordering(BigDecimal.valueOf(73), volledigTijdvak, verkortTijdvak);

        // WHEN
        List<Tijdvak> tijdvakken = new TijdvakBepaler().bepaalTijdvakken(vordering);

        // THEN
        Tijdvak tijdvak1 = tijdvakken.get(0);
        assertEquals(LocalDate.of(2016, 2, 26), tijdvak1.getPeriode().getBegindatum());
        assertEquals(LocalDate.of(2016,3, 1), tijdvak1.getPeriode().getEinddatum());
        assertEquals(BigDecimal.valueOf(33), tijdvak1.getBedrag());

        Tijdvak tijdvak2 = tijdvakken.get(1);
        assertEquals(LocalDate.of(2016, 3, 2), tijdvak2.getPeriode().getBegindatum());
        assertEquals(LocalDate.of(2016,4, 1), tijdvak2.getPeriode().getEinddatum());
        assertEquals(BigDecimal.valueOf(33), tijdvak2.getBedrag());

        Tijdvak tijdvak3 = tijdvakken.get(2);
        assertEquals(LocalDate.of(2016, 4, 2), tijdvak3.getPeriode().getBegindatum());
        assertEquals(LocalDate.of(2016,5, 1), tijdvak3.getPeriode().getEinddatum());
        assertEquals(BigDecimal.valueOf(34), tijdvak3.getBedrag());
    }

    @Test
    public void testVerkortTijdvakEind() {
        // GIVEN
        Periode volledigTijdvak = new Periode(of(2016, 2, 2), of(2016, 5, 1));
        Periode verkortTijdvak = new Periode(of(2016, 2, 2), of(2016, 4, 8));

        Vordering vordering = new Vordering(BigDecimal.valueOf(74), volledigTijdvak, verkortTijdvak);

        // WHEN
        List<Tijdvak> tijdvakken = new TijdvakBepaler().bepaalTijdvakken(vordering);

        // THEN
        Tijdvak tijdvak1 = tijdvakken.get(0);
        assertEquals(LocalDate.of(2016, 2, 2), tijdvak1.getPeriode().getBegindatum());
        assertEquals(LocalDate.of(2016,3, 1), tijdvak1.getPeriode().getEinddatum());
        assertEquals(BigDecimal.valueOf(33), tijdvak1.getBedrag());

        Tijdvak tijdvak2 = tijdvakken.get(1);
        assertEquals(LocalDate.of(2016, 3, 2), tijdvak2.getPeriode().getBegindatum());
        assertEquals(LocalDate.of(2016,4, 1), tijdvak2.getPeriode().getEinddatum());
        assertEquals(BigDecimal.valueOf(33), tijdvak2.getBedrag());

        Tijdvak tijdvak3 = tijdvakken.get(2);
        assertEquals(LocalDate.of(2016, 4, 2), tijdvak3.getPeriode().getBegindatum());
        assertEquals(LocalDate.of(2016,4, 8), tijdvak3.getPeriode().getEinddatum());
        assertEquals(BigDecimal.valueOf(8), tijdvak3.getBedrag());
    }

}