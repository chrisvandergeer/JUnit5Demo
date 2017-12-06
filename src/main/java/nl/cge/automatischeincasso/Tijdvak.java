package nl.cge.automatischeincasso;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Tijdvak {

    private Periode periode;
    private BigDecimal bedrag;

    public Tijdvak(LocalDate begindatum) {
        LocalDate einddatum = begindatum.plusMonths(1).minusDays(1);
        Periode periode = new Periode(begindatum, einddatum);
        this.periode = periode;
    }

    public void setBegindatum(LocalDate begindatum) {
        periode.setBegindatum(begindatum);
    }

    public void setEinddatum(LocalDate einddatum) {
        periode.setEinddatum(einddatum);
    }

    public Periode getPeriode() {
        return periode;
    }

    public void setBedrag(BigDecimal bedrag) {
        this.bedrag = bedrag;
    }

    public BigDecimal getBedrag() {
        return bedrag;
    }

    public boolean isGeldigTijdvak() {
        return periode.getEinddatum().isAfter(periode.getEinddatum());
    }
}
