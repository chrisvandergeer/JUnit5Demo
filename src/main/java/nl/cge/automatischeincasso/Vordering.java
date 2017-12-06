package nl.cge.automatischeincasso;

import java.math.BigDecimal;

public class Vordering {

    private BigDecimal bedrag;
    private Periode volledigTijdvak;
    private Periode verkortTijdvak;

    public Vordering(BigDecimal bedrag, Periode volledigTijdvak, Periode verkortTijdvak) {
        this.bedrag = bedrag;
        this.volledigTijdvak = volledigTijdvak;
        this.verkortTijdvak = verkortTijdvak;
    }

    public BigDecimal getBedrag() {
        return bedrag;
    }

    public Periode getVolledigTijdvak() {
        return volledigTijdvak;
    }

    public Periode getVerkortTijdvak() {
        return verkortTijdvak;
    }
}
