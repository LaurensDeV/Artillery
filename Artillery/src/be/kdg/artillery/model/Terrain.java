package be.kdg.artillery.model;

import javafx.scene.shape.Polygon;

import java.util.Random;

/**
 * Robby Dirix & Laurens de Voogd
 * 18-Feb-16
 */

/**
 * Hier wordt het terrein random gegenereerd.
 * Omdat we tekenen met behulp van een polygon worden hier x en y coordinaten random gegenereerd.
 */
public class Terrain {

    double j;
    double[] x;
    double[] y;
    double[] xy = new double[108];
    Polygon poly;
    Random random = new Random();

    public Polygon getPoly() {
        return poly;
    }

    public double[] getX() {
        return x;
    }

    public double[] getY() {
        return y;
    }

    public Terrain() {
        x = new double[54];
        y = new double[54];
    }

    public Terrain(double[] x, double[] y) {
        this.x = x;
        this.y = y;
    }

    public void generate(double width, double height) {

        for (int i = 0; i < x.length; i++) {
            x[i] = j;
            j = j + width / 50;
        }

        //Eerste punt is op 70% van de hoogte van het scherm.
        y[0] = height * 0.7f;

        double maxHeight = height * 0.4f;
        double minHeight = height * 0.85;

        for (int i = 1; i < y.length; i++) {

            //Dit maakt een platform om de tank op te zetten
            if ((i > 4 && i < 10) || (i > 41 && i < 46)) {
                y[i] = y[i - 1];
            } else {
                int rand = random.nextInt(100) - 50;
                y[i] = Math.max(y[i - 1] + rand, maxHeight + random.nextInt(100));
                y[i] = Math.min(y[i], minHeight - random.nextInt(100));
            }
        }
        //Deze maaakt de polygon tot 1 geheel
        x[51] = width;
        x[52] = 0;
        x[53] = 0;
        y[51] = height;
        y[52] = height;
        y[53] = y[0];

        //Omdat de polygon klasse anders werkt dan de polygon van het canvas wordt hier de punten omgezet in 1 array.
        int k = 0;
        for (int i = 0; i < x.length; i++) {
            xy[k] = x[i];
            xy[k + 1] = y[i];
            k = k + 2;
        }
        poly = new Polygon(xy);
    }


}
