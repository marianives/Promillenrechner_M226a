package ch.bbbaden.m226a;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 *
 * @author M-N
 */
public class Person {

    public final int MAENNLICH = 0;
    public final int WEIBLICH = 1;
    private final double ABBAU_WARTEZEIT_STUNDEN = 1.0;
    private final double ABBAU_PRO_STUNDE = 0.1;
    private final double ANTEIL_WASSER_IM_BLUT = 0.8;
    private final double DICHTE_BLUT_GRAMM_PRO_CCM = 1.055;

    private double koerpermasse;
    private double koerpergroesseInCm;
    private java.util.Date geburtsdatum;
    private int geschlecht;
    private double alkoholPromille = 0.0;

    public Person(double koerpermasse, double koerpergroesseInCm, Date geburtsdatum, int geschlecht) {
        this.koerpermasse = koerpermasse;
        this.koerpergroesseInCm = koerpergroesseInCm;
        this.geburtsdatum = geburtsdatum;
        this.geschlecht = geschlecht;
    }

    private double getAlterInJahre() {
        final LocalDate jetzt = LocalDate.now();
        final LocalDate localGeburtsdatum = geburtsdatum.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        //final Period period = Period.between(localGeburtsdatum, jetzt);
        final long days = ChronoUnit.DAYS.between(localGeburtsdatum, jetzt);
        return days / 365.0;

    }

    public void trinke(AlkoholischesGetraenk alkoholischesGetraenk) {

    }

    public double getAlkoholPromille() {

        return 0;

    }

    public double getGKW() {
        double gkw;
        if (geschlecht == 0) {
            gkw = (2.447 - 0.0956 * getAlterInJahre() + 0.1074 * koerpergroesseInCm + 0.3362 * koerpermasse);
        } else {
            gkw = (0.203 - 0.07 * getAlterInJahre() + 0.1069 * koerpergroesseInCm + 0.2466 * koerpermasse);
        }
        return gkw;
    }

}
