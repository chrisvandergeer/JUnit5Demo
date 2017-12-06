package nl.cge.automatischeincasso;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TijdvakBepaler {

    public List<Tijdvak> bepaalTijdvakken(Vordering vordering) {
        List<Tijdvak> tijdvakken = createTijdvakkenVoorVolledigTijdvak(vordering);

        Tijdvak tijdvak1 = tijdvakken.get(0);
//        if (vordering.getVerkortTijdvak().getBegindatum().isAfter(tijdvak1.getPeriode().getBegindatum())) {
//            tijdvak1.setBegindatum(vordering.getVerkortTijdvak().getBegindatum());
//
//        }

        aanpassenTijdvak3AanVerkortTijdvak(vordering, tijdvakken);

//        LocalDate einddatumVolledigTijdvak = vordering.getVolledigTijdvak().getEinddatum();
//        LocalDate einddatumVerkortTijdvak = vordering.getVerkortTijdvak().getEinddatum();
//        if ()

        return tijdvakken;
    }

    private void aanpassenTijdvak3AanVerkortTijdvak(Vordering vordering, List<Tijdvak> tijdvakken) {
        Tijdvak tijdvak1 = tijdvakken.get(0);
        Tijdvak tijdvak2 = tijdvakken.get(1);
        Tijdvak tijdvak3 = tijdvakken.get(2);
        if (vordering.getVerkortTijdvak().getBegindatum().isAfter(tijdvak3.getPeriode().getBegindatum())) {
            tijdvak3.setBegindatum(vordering.getVerkortTijdvak().getBegindatum());
        }
        if (vordering.getVerkortTijdvak().getEinddatum().isAfter(tijdvak3.getPeriode().getBegindatum())) {
            tijdvak3.setEinddatum(vordering.getVerkortTijdvak().getEinddatum());
        }
        tijdvak3.setBedrag(vordering.getBedrag().subtract(tijdvak1.getBedrag()).subtract(tijdvak2.getBedrag()));
    }

    private List<Tijdvak> createTijdvakkenVoorVolledigTijdvak(Vordering vordering) {
        LocalDate begindatum = vordering.getVolledigTijdvak().getBegindatum();
        Tijdvak tijdvak1 = new Tijdvak(begindatum);
        Tijdvak tijdvak2 = new Tijdvak(begindatum.plusMonths(1));
        Tijdvak tijdvak3 = new Tijdvak(begindatum.plusMonths(2));
        tijdvak3.setEinddatum(vordering.getVolledigTijdvak().getEinddatum());
        BigDecimal vorderingBedragVolledigTijdvak = bedragVolledigTijdvak(vordering);
        BigDecimal remainder = vorderingBedragVolledigTijdvak.remainder(BigDecimal.valueOf(3));
        BigDecimal maandbedrag = vorderingBedragVolledigTijdvak.subtract(remainder).divide(BigDecimal.valueOf(3));
        tijdvak1.setBedrag(maandbedrag);
        tijdvak2.setBedrag(maandbedrag);
        tijdvak3.setBedrag(maandbedrag.add(remainder));
        return Arrays.asList(tijdvak1, tijdvak2, tijdvak3);
    }

    private BigDecimal bedragVolledigTijdvak(Vordering vordering) {
        BigDecimal vorderingBedrag = vordering.getBedrag();
        BigDecimal aantalDagenInVolledigTijdvak = BigDecimal.valueOf(vordering.getVolledigTijdvak().aantalDagenInPeriode());
        BigDecimal aantalDagenInVerkortTijdvak = BigDecimal.valueOf(vordering.getVerkortTijdvak().aantalDagenInPeriode());
        return vorderingBedrag
                .divide(aantalDagenInVerkortTijdvak, 9, RoundingMode.FLOOR)
                .multiply(aantalDagenInVolledigTijdvak)
                .setScale(0, RoundingMode.HALF_EVEN);
    }

}
