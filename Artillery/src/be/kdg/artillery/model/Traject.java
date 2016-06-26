package be.kdg.artillery.model;

import com.sun.javafx.geom.Vec2d;

import java.util.ArrayList;
import java.util.List;

/**
 * Robby Dirix & Laurens De Voogd
 * 25-Feb-16
 */

/**
 * Hier wordt de formule van het pad berekend op basis van wind,zwaartekracht,richting en sterkte die wordt ingegeven.
 */
public class Traject {
    final double gravity = 9.81;
    double angle;
    double speed;

    public Traject() {
    }

    //dir staat voor direction.
    public Vec2d[] getTraject(double angle, double speed, double wind, double x, double y) {
        List<Vec2d> points = new ArrayList<>();
        this.angle = angle;
        this.speed = speed;
        double startX = x + 40 * Math.cos(angle * Math.PI / 180);
        double startY = y + 40 * Math.sin(angle * Math.PI / 180);

        points.add(new Vec2d(startX, startY));
        x = startX;
        y = startY;


        double dirX = Math.cos(angle * Math.PI / 180) * speed;
        double dirY = Math.sin(angle * Math.PI / 180) * speed;

        for (int i = 0; i < 400; i++) {
            dirY += gravity;
            dirX -= wind;
            x += dirX / 50;
            y += dirY / 50;
            points.add(new Vec2d(x, y));
        }
        Vec2d[] res = new Vec2d[points.size()];
        res = points.toArray(res);
        return res;
    }
}
