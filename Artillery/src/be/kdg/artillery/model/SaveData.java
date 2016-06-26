package be.kdg.artillery.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Robby Dirix & Laurens de Voogd
 * 18-Feb-16
 */

/**
 * Hier worden alle bestanden ingelezen en worden ook de bestanden weggeschreven.
 */
public class SaveData {

    public static final String FILE_TERRAIN = "Artillery/src/resources/terrain.dat";
    public static final String FILE_HIGHSCORE = "Artillery/src/resources/highscore.dat";
    public static final String FILE_ABOUT = "Artillery/src/resources/about.txt";

    public static void saveTerrain(double[] x, double[] y) {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(FILE_TERRAIN))) {

            for (int i = 0; i < 54; i++) {
                out.writeShort((short) x[i]);
                out.writeShort((short) y[i]);
            }
        } catch (IOException ex) {
            throw new TankException(ex.getMessage());
        }
    }

    public static Terrain loadTerrain() {
        double[] x = new double[54];
        double[] y = new double[54];

        try (DataInputStream in = new DataInputStream(new FileInputStream(FILE_TERRAIN))) {
            for (int i = 0; i < 54; i++) {
                x[i] = in.readShort();
                y[i] = in.readShort();
            }
        } catch (IOException ex) {
            throw new TankException(ex.getMessage());
        }
        return new Terrain(x, y);

    }

    public static void saveHiscore(List<HighscoreEntry> hiscoreEntries) {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(FILE_HIGHSCORE))) {
            out.writeInt(hiscoreEntries.size());
            for (int i = 0; i < hiscoreEntries.size(); i++) {
                out.writeUTF(hiscoreEntries.get(i).naam);
                out.writeShort(hiscoreEntries.get(i).aantalBeurten);
            }
        } catch (IOException ex) {
            throw new TankException(ex.getMessage());
        }
    }

    public static List<HighscoreEntry> loadHighscoreEntries() {
        List<HighscoreEntry> res = new ArrayList<>();
        try (DataInputStream in = new DataInputStream(new FileInputStream(FILE_HIGHSCORE))) {
            int len = in.readInt();

            for (int i = 0; i < len; i++) {
                String name = in.readUTF();
                short turns = in.readShort();
                res.add(new HighscoreEntry(name, turns));
            }
        } catch (IOException ex) {
        }
        return res;
    }

    public static String highscoreToString(List<HighscoreEntry> entries) {
        StringBuilder sb = new StringBuilder(String.format("%-40s  %s\n", "Naam", "Beurten"));
        for (int i = 0; i < entries.size(); i++) {
            sb.append(String.format("%-40s\t%s", entries.get(i).naam, entries.get(i).aantalBeurten));
            if (i < entries.size())
                sb.append("\n");
        }
        return sb.toString();
    }

    public static String loadAbout() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_ABOUT))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }

        } catch (IOException ex) {
            throw new TankException(ex.getMessage());
        }
        return sb.toString();
    }
}
