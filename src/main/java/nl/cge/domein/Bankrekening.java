package nl.cge.domein;

import java.math.BigDecimal;

import static nl.cge.utils.Preconditions.checkArgument;

public class Bankrekening {

    private String rekeningnummer;
    private BigDecimal saldo;
    private BigDecimal limiet;

    public Bankrekening(String rekeningnummer) {
        this.rekeningnummer = rekeningnummer;
        this.saldo = BigDecimal.ZERO;
        this.limiet = BigDecimal.ZERO;
    }

    public void wijzigLimit(BigDecimal limiet) {
        checkArgument(limiet.signum() >= 0);
        this.limiet = limiet;
    }

    public boolean neemOp(BigDecimal gewenstOpTeNemenBedrag) {
        checkArgument(gewenstOpTeNemenBedrag.signum() > 0);
        BigDecimal maxOpTeNemenBedrag = saldo.add(limiet);
        if (maxOpTeNemenBedrag.compareTo(gewenstOpTeNemenBedrag) >= 0) {
            saldo = saldo.subtract(gewenstOpTeNemenBedrag);
            return true;
        }
        return false;
    }

    public void boekBij(BigDecimal bedrag) {
        checkArgument(bedrag.signum() > 0);
        saldo = saldo.add(bedrag);
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public BigDecimal getLimiet() {
        return limiet;
    }

    @Override
    public String toString() {
        return "Bankrekening{" +
                "rekeningnummer='" + rekeningnummer + '\'' +
                ", saldo=" + saldo +
                ", limiet=" + limiet +
                '}';
    }
}
