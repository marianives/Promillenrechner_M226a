package ch.bbbaden.m226a;

import java.util.Date;

/**
 *
 * @author M-N
 */
public class AlkoholischesGetraenk {

    public final double BIER_ALKOHOLGEHALT = 0.05;
    public final double WEIN_ALKOHOLGEHALT = 0.10;
    public final double SCHNAPS_ALKOHOLGEHALT = 0.40;
    private final double DICHTE_ALKOHOL = 0.8;

    private int volumenInMilliLiter;
    private double alkoholgehalt;
    private java.util.Date getrunkenAm;

    public AlkoholischesGetraenk(int volumenInMilliLiter, double alkoholgehalt, Date getrunkenAm) {
        this.volumenInMilliLiter = volumenInMilliLiter;
        this.alkoholgehalt = alkoholgehalt;
        this.getrunkenAm = getrunkenAm;
    }

    public double getStundenSeitEinnahme(java.util.Date jetzt) {
        long timeDifference = getrunkenAm.getTime() - jetzt.getTime();
        return timeDifference;
    }

    public double getAlkoholMasselnGramm() {
        double alkMasseInGramm = (volumenInMilliLiter * alkoholgehalt * DICHTE_ALKOHOL);
        return alkMasseInGramm;
    }
}
