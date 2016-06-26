package be.kdg.artillery.model;

import be.kdg.artillery.view.game.TankView;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.shape.Rectangle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Robby Driix & Laurens de Voogd
 * 11/03/2016.
 */

/**
 * Deze klasse kijkt of er een projectiel in contact okmt met enzerzijds de kanten van het venster, het terrein of de tank van de andere speler.
 */
public class Collision {
    //We hebben hier de tank verdeeld in 2 rechthoeken omdat als we dit met 1 rechthoek tekenden zorgde dit ervoor dat je soms raak schiet wanneer dit niet het geval is
    //Er is dus een rechthoek voor de banden van de tank en een andere voor de cockpit.
    public static boolean tankCollision(double x, double y, Vec2d posTank) {
        Rectangle top = new Rectangle(posTank.x + 17, posTank.y + 3, 18, 23);
        Rectangle bottom = new Rectangle(posTank.x + 6, posTank.y + 30, 38, 17);

        return ((x > top.getX() && y > top.getY() && x < top.getX() + top.getWidth() && y < top.getY() + top.getHeight())
                || (x > bottom.getX() && y > bottom.getY() && x < bottom.getX() + bottom.getWidth() && y < bottom.getY() + bottom.getHeight()));
    }

    public static boolean outOfBounds(double x, double y, double viewWidth, double viewHeight) {
        return x < 10 || x > viewWidth || y > viewHeight || y < 10;
    }

    //de t die vaak gebruikt wordt staat voor terrein
    public static boolean terrainCollision(double x, double y, double[] tX, double[] tY) {

        double minX = tX[0];
        double maxX = tX[0];
        double minY = tY[0];
        double maxY = tY[0];
        for (int i = 1; i < 54; i++) {
            minX = Math.min(tX[i], minX);
            maxX = Math.max(tX[i], maxX);
            minY = Math.min(tY[i], minY);
            maxY = Math.max(tY[i], maxY);
        }
        if (x < minX || x > maxX || y < minY || y > maxY) {
            return false;
        }
        boolean inside = false;
        for (int i = 0, j = 54 - 1; i < 54; j = i++) {
            if ((tY[i] > y) != (tY[j] > y) &&
                    x < (tX[j] - tX[i]) * (y - tY[i]) / (tY[j] - tY[i]) + tX[i]) {
                inside = !inside;
            }
        }
        return inside;
    }

    //Deze methode verkort het pad zodat de punten die in het pad die het terrein raken niet meer getekend worden en daardoor loopt de animatie ook niet verder.
    public static Vec2d[] trim(Vec2d[] path, double width, double height, double[] tX, double[] tY) {
        List<Vec2d> list = new ArrayList<>();

        for (int i = 0; i < path.length; i++) {
            if (terrainCollision(path[i].x, path[i].y, tX, tY) || outOfBounds(path[i].x, path[i].y, width, height))
                break;
            list.add(path[i]);
        }

        Vec2d[] res = new Vec2d[list.size()];
        res = list.toArray(res);
        return res;
    }
}
