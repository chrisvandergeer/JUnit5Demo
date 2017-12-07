package nl.cge.automatischeincasso;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Periode {

    private LocalDate begindatum;
    private LocalDate einddatum;

    public Periode(LocalDate begindatum, LocalDate einddatum) {
        this.begindatum = begindatum;
        this.einddatum = einddatum;
    }

    public LocalDate getBegindatum() {
        return begindatum;
    }

    public LocalDate getEinddatum() {
        return einddatum;
    }

    public long aantalDagenInPeriode() {
        return ChronoUnit.DAYS.between(begindatum, einddatum);
    }

    public void setBegindatum(LocalDate begindatum) {
        this.begindatum = begindatum;
    }

    public void setEinddatum(LocalDate einddatum) {
        this.einddatum = einddatum;
    }

    public Periode copy() {
        return new Periode(begindatum, einddatum);
    }

    @Override
    public String toString() {
        return "Periode{" +
                "begindatum=" + begindatum +
                ", einddatum=" + einddatum +
                '}';
    }
}
