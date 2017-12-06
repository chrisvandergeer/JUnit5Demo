package nl.cge.service;

import nl.cge.domein.Bankrekening;

import java.math.BigDecimal;

public class TransaktieService {

    public boolean maakOver(Bankrekening van, Bankrekening naar, BigDecimal bedrag) {
        if (van.neemOp(bedrag)) {
            naar.boekBij(bedrag);
            return true;
        }
        return false;
    }
}
