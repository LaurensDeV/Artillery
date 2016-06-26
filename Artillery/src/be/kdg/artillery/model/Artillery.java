package be.kdg.artillery.model;

import com.sun.javafx.geom.Vec2d;

import java.util.List;


/**
 * Robby Dirix & Laurens de Voogd
 * 18-Feb-16
 */

/**
 * Dit is de hoofmodelklasse. Deze linkt het model met de verschillende presenters.
 * Hierin bevinden zich methoden die doorlinken naar de methoden van de andere modelklassen die nodig zijn voor het spel te laten werken.
 */
public class Artillery {
    Player speler1;
    Player speler2;
    HighscoreEntry[] highscoreEntry = new HighscoreEntry[2];
    Tank tank;
    Terrain terrain;
    Traject traject = new Traject();
    SaveData save;
    Projectile proj;

    public Artillery() {
        terrain = new Terrain();
    }

    public void setSpeler1(Player speler1) {
        this.speler1 = speler1;
    }

    public void setSpeler2(Player speler2) {
        this.speler2 = speler2;
    }

    public Player getSpeler1() {
        return speler1;
    }

    public Player getSpeler2() {
        return speler2;
    }

    int selectedPlayerIndex;

    public int getSelectedPlayerIndex() {
        return selectedPlayerIndex;
    }

    public Player getSelectedPlayer() {
        return selectedPlayerIndex == 1 ? speler2 : speler1;
    }

    public Player getEnemyPlayer() {
        return selectedPlayerIndex == 0 ? speler1 : speler2;
    }

    public void switchPlayer() {
        selectedPlayerIndex = selectedPlayerIndex == 0 ? 1 : 0;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void generateTerrain(double width, double height) {
        terrain.generate(width, height);
    }

    public void saveTerrain(double[] x, double[] y) {
        SaveData.saveTerrain(x, y);
    }

    public Terrain loadTerrain() {
        return SaveData.loadTerrain();
    }

    public String loadAbout() {
        return SaveData.loadAbout();
    }

    public String loadHighscore() {
        return SaveData.highscoreToString(SaveData.loadHighscoreEntries());
    }

    public void saveHighscore(List<HighscoreEntry> highscore) {
        SaveData.saveHiscore(highscore);
    }

    public HighscoreEntry[] getHighscoreEntry() {
        return highscoreEntry;
    }


    public Vec2d[] getTraject(double angle, double speed, double wind, double x, double y) {
        return traject.getTraject(angle, speed, wind, x, y);
    }

    public String getFileNameTer() {
        return SaveData.FILE_TERRAIN;
    }

    public int getProjDamage(int selected) {
        return Projectile.values()[selected].damage;
    }
}
