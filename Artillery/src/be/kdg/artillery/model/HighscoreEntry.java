package be.kdg.artillery.model;

/**
 * Robby Dirix & Laurens de Voogd
 * 10/03/2016.
 */

/**
 * Deze klasse houdt de scores bij op basis van naam en aantalbeurten het neemt om de vijandige tank te vernietigen.
 */
public class HighscoreEntry {
    String naam;
    short aantalBeurten;

    public HighscoreEntry(String naam, short aantalBeurten) {
        this.naam = naam;
        this.aantalBeurten = aantalBeurten;
    }

    public HighscoreEntry(String name) {
        this.naam = name;
    }

    public void resetBeurten() {
        this.aantalBeurten = 0;
    }

    public String getNaam() {
        return naam;
    }

    public void addBeurt() {
        this.aantalBeurten++;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    @Override
    public String toString() {
        return String.format("%s\t%s", naam, aantalBeurten);
    }
}
