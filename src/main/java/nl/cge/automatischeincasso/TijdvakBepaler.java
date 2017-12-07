package nl.cge.automatischeincasso;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TijdvakBepaler {

    public List<Tijdvak> bepaalTijdvakken(Vordering vordering) {
        List<Tijdvak> tijdvakken = createTijdvakkenVoorVolledigTijdvak(vordering);
        tijdvakken = verkortBeginTijdvak(vordering, tijdvakken);
        tijdvakken = verkortEindTijdvak(vordering, tijdvakken);

        return tijdvakken;
    }

    private List<Tijdvak> verkortEindTijdvak(Vordering vordering, List<Tijdvak> tijdvakken) {
        Tijdvak tijdvak1 = tijdvakken.get(0);
        Tijdvak tijdvak2 = tijdvakken.get(1);
        Tijdvak tijdvak3 = tijdvakken.get(2);
        Periode verkortTijdvak = vordering.getVerkortTijdvak();
        if (!tijdvak3.getPeriode().getEinddatum().equals(verkortTijdvak.getEinddatum())) {
            tijdvak3.setEinddatum(verkortTijdvak.getEinddatum());
            BigDecimal bedragTijdvak1En2 = tijdvak1.getBedrag().add(tijdvak2.getBedrag());
            BigDecimal bedragTijdvak3 = vordering.getBedrag().subtract(bedragTijdvak1En2);
            tijdvak3.setBedrag(bedragTijdvak3);
        }
        return tijdvakken;
    }

    private List<Tijdvak> verkortBeginTijdvak(Vordering vordering, List<Tijdvak> tijdvakken) {
        Tijdvak tijdvak1 = tijdvakken.get(0);
        Tijdvak tijdvak2 = tijdvakken.get(1);
        Tijdvak tijdvak3 = tijdvakken.get(2);
        Periode verkortTijdvak = vordering.getVerkortTijdvak();
        if (!tijdvak1.getPeriode().getBegindatum().equals(verkortTijdvak.getBegindatum())) {
            tijdvak1.setBegindatum(verkortTijdvak.getBegindatum());
            BigDecimal bedragTijdvak2En3 = tijdvak2.getBedrag().add(tijdvak3.getBedrag());
            BigDecimal bedragTijdvak1 = vordering.getBedrag().subtract(bedragTijdvak2En3);
            tijdvak1.setBedrag(bedragTijdvak1);
        }
        return tijdvakken;
    }

    private List<Tijdvak> createTijdvakkenVoorVolledigTijdvak(Vordering vordering) {
        LocalDate begindatum = vordering.getVolledigTijdvak().getBegindatum();
        Tijdvak tijdvak1 = new Tijdvak(begindatum);
        Tijdvak tijdvak2 = new Tijdvak(begindatum.plusMonths(1));
        Tijdvak tijdvak3 = new Tijdvak(begindatum.plusMonths(2));
        tijdvak3.setEinddatum(vordering.getVolledigTijdvak().getEinddatum());
        BigDecimal vorderingBedragVolledigTijdvak = bedragVolledigTijdvak(vordering);
        BigDecimal remainder = vorderingBedragVolledigTijdvak.remainder(BigDecimal.valueOf(3));
        BigDecimal maandbedrag = vorderingBedragVolledigTijdvak.subtract(remainder).divide(BigDecimal.valueOf(3))
                .setScale(0, RoundingMode.HALF_EVEN);
        tijdvak1.setBedrag(maandbedrag);
        tijdvak2.setBedrag(maandbedrag);
        tijdvak3.setBedrag(maandbedrag.add(remainder).setScale(0, RoundingMode.HALF_EVEN));
        return Arrays.asList(tijdvak1, tijdvak2, tijdvak3);
    }

    private BigDecimal bedragVolledigTijdvak(Vordering vordering) {
        return berekenNaarTijdvak(vordering.getBedrag(), vordering.getVerkortTijdvak(), vordering.getVolledigTijdvak());
    }

    private BigDecimal berekenNaarTijdvak(BigDecimal bedrag, Periode van, Periode naar) {
        BigDecimal aantalDagenPeriodeVan = BigDecimal.valueOf(van.aantalDagenInPeriode());
        BigDecimal aantalDagenPeriodeNaar = BigDecimal.valueOf(naar.aantalDagenInPeriode());
        return bedrag.divide(aantalDagenPeriodeVan, 9, RoundingMode.HALF_EVEN)
                .multiply(aantalDagenPeriodeNaar)
                .setScale(0, RoundingMode.HALF_EVEN);
    }

}
