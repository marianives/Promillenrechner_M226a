package ch.bbbaden.m226a;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author M-N
 */
public class GUI {

    public Person askPersonData() {
        Person p = new Person(eingabeGewichtGUI(), eingabeHoeheGUI(), eingabeGeburtsdatumGUI(), eingabeGeschlaechtGUI());
        return p;

    }

    public int eingabeGeschlaechtGUI() {

        String[] optionsGeschlaecht = {"Männlich", "Weiblich"};
        return JOptionPane.showOptionDialog(null, "Geschlächt", "Bitte wählen Sie Ihr Geschlächt", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsGeschlaecht, optionsGeschlaecht[0]);

    }

    public Date eingabeGeburtsdatumGUI() {
        Date geburtsdatum = null;
        final DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        final Date jetzt = new Date();
        do {
            final String eingabe = JOptionPane.showInputDialog(null, "Geben Sie Ihr Geburtsdatum ein.",
                    "Geburtsdatum",
                    JOptionPane.QUESTION_MESSAGE);

            if (eingabe == null) { // Cancel
                System.exit(0);
            }
            try {
                geburtsdatum = formatter.parse(eingabe);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, eingabe + " ist kein gültiges Datum.");
            }
        } while (geburtsdatum == null || !jetzt.after(geburtsdatum));
        return geburtsdatum;
    }

    public double eingabeHoeheGUI() {
        String eingabeHoehe = JOptionPane.showInputDialog(null,
                "Geben Sie Ihre Höhe ein",
                "Höhe",
                JOptionPane.PLAIN_MESSAGE);
        return Double.parseDouble(eingabeHoehe);
    }

    public Date eingabeTrinkzeitGUI() {
        Date trinkDatumUndZeit = null;
        final DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy kk:mm");
        final Date jetzt = new Date();
        do {
            final String eingabe = JOptionPane.showInputDialog(null, "Geben Sie Trinkdatum und - zeit ein.",
                    "Trinkzeit",
                    JOptionPane.QUESTION_MESSAGE);
            if (eingabe == null) { // Cancel
                System.exit(0);
            }
            try {
                trinkDatumUndZeit = formatter.parse(eingabe);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, eingabe + " ist keine gültige Trinkzeit.");
            }
        } while (trinkDatumUndZeit == null || !jetzt.after(trinkDatumUndZeit));
        return trinkDatumUndZeit;

    }

    public double eingabeGewichtGUI() {
        double gewichtGUI = 0;
        do {
            String eingabeGewicht = JOptionPane.showInputDialog(null,
                    "Geben Sie Ihr Gewicht ein",
                    "Gewicht",
                    JOptionPane.PLAIN_MESSAGE);
            if (eingabeGewicht == null) { // Cancel
                System.exit(0);
            }
            try {
                gewichtGUI = Double.parseDouble(eingabeGewicht);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, eingabeGewicht + " ist kein gültiges Gewicht.");
            }

        } while (gewichtGUI == 0);
        return gewichtGUI;

    }

    public int eingabeGetraenkGUI() {
        String[] optionsGetraenk = {"Bier", "Wein", "Schnaps"};
        return JOptionPane.showOptionDialog(null, "Getränk", "Bitte wählen Sie Ihr Getränk",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, optionsGetraenk, optionsGetraenk[0]);

    }

    public int eingabeMengeGUI() {
        int mengeGUI = 0;
        do {
            String eingabeMenge = JOptionPane.showInputDialog(null,
                    "Geben Sie die Menge des Getränks in milliliter ein",
                    "Menge getränk",
                    JOptionPane.PLAIN_MESSAGE);
            if (eingabeMenge == null) { // Cancel
                System.exit(0);
            }
            try {
                mengeGUI = Integer.valueOf(eingabeMenge);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, eingabeMenge + " ist keine gültige Menge.");
            }

        } while (mengeGUI == 0);
        return mengeGUI;

    }

    public AlkoholischesGetraenk askAlkoholischeGetraenkeData(Date trinkDatum) {
        double alkoholgehalt = 0;
        switch (eingabeGetraenkGUI()) {
            case 0:
                alkoholgehalt = 0.05;
                break;
            case 1:
                alkoholgehalt = 0.10;
                break;
            case 2:
                alkoholgehalt = 0.40;
                break;
        }
        AlkoholischesGetraenk aG = new AlkoholischesGetraenk(eingabeMengeGUI(), alkoholgehalt, eingabeTrinkzeitGUI());
        return aG;

    }

    public void promilleRechner() {
        Person i = new Person(eingabeGewichtGUI(), eingabeHoeheGUI(), eingabeGeburtsdatumGUI(), eingabeGeschlaechtGUI());
        double alkoholgehalt = 0;
        switch (eingabeGetraenkGUI()) {
            case 0:
                alkoholgehalt = 0.05;
                break;
            case 1:
                alkoholgehalt = 0.10;
                break;
            case 2:
                alkoholgehalt = 0.40;
                break;
        }

        AlkoholischesGetraenk a = new AlkoholischesGetraenk(eingabeMengeGUI(), alkoholgehalt, eingabeTrinkzeitGUI());
        double promilleAlkohol = (0.8 * a.getAlkoholMasselnGramm()) / (1.055 * i.getGKW());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm");


        Date trinkzeit = eingabeTrinkzeitGUI();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date jetzt = new Date();
        dateFormat.format(jetzt);

        long timeDifference = jetzt.getTime() - trinkzeit.getTime();

        for (int z = 3600000; z < timeDifference; z+=3600000){
            promilleAlkohol -= 0.1;
        }

        JOptionPane.showMessageDialog(null, "Ihre Promilleghealt: " + promilleAlkohol);
    }

}
