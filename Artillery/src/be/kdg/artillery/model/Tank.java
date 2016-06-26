package be.kdg.artillery.model;

import com.sun.javafx.geom.Vec2d;


/**
 * Robby Dirix & Laurens de Voogd
 * 18-Feb-16
 */

/**
 * Dit is de tank klasse.
 * Hier worden de levenspunten van de tank bijgehouden en als de tank geraakt wordt gaan er hier ook levenspunten af.
 * Er wordt ook gekeken of een tank dood gaat.
 */
public class Tank {
    public int getHealth() {
        return health;
    }

    int health;

    public boolean isDead() {
        return health <= 0;
    }

    public Tank() {
        health = 100;
    }

    public void hit(int damage) {
        health = Math.max(health - damage, 0);
    }

}
