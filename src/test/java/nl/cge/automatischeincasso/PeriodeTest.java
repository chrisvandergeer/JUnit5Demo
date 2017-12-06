package nl.cge.automatischeincasso;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;

import static java.math.BigDecimal.*;
import static java.time.LocalDate.of;
import static org.junit.jupiter.api.Assertions.*;

class PeriodeTest {

    @Test
    public void aantalDagenInPeriode() {
        Periode periode = new Periode(
                LocalDate.of(2016, 1, 12),
                LocalDate.of(2016, 2, 11));
        assertEquals(30, periode.aantalDagenInPeriode());
    }

    @Test
    public void remainder() {
        assertEquals(ONE, valueOf(31).remainder(valueOf(3)));
        assertEquals(ZERO, valueOf(30).remainder(valueOf(3)));
    }

    @Test
    public void bepaalVorderingbedrag() {
        Periode volledigTijdvak = new Periode(of(2016, 2, 2), of(2016, 5, 1));
        Periode verkortTijdvak = new Periode(of(2016, 2, 26), of(2016, 5, 1));

        System.out.println("Volledig tijdvak dagen " + volledigTijdvak.aantalDagenInPeriode());
        System.out.println("Verkort tijdvak dagen " + verkortTijdvak.aantalDagenInPeriode());

        BigDecimal bedrag = BigDecimal.valueOf(100);
        BigDecimal dagenVolledigTijdvak = BigDecimal.valueOf(volledigTijdvak.aantalDagenInPeriode());
        BigDecimal dagenVerkortTijdvak = BigDecimal.valueOf(verkortTijdvak.aantalDagenInPeriode());
        BigInteger verkortBedrag = bedrag
                .divide(dagenVolledigTijdvak, 9, RoundingMode.HALF_EVEN)
                .multiply(dagenVerkortTijdvak)
                .toBigInteger();
        System.out.println(verkortBedrag);


//        BigDecimal bedrag = BigDecimal.valueOf(100).divide(BigDecimal.valueOf(volledigTijdvak.aantalDagenInPeriode())).multiply(BigDecimal.valueOf(verkortTijdvak.aantalDagenInPeriode()));
//        System.out.println(bedrag);

    }

}