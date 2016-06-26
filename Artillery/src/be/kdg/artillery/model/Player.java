package be.kdg.artillery.model;


/**
 * Robby Dirix & Laurens de Voogd
 * 18-Feb-16
 */

/**
 * Dit is de klasse die de speler een tank, een naam, een kleur geeft.
 */
public class Player {
    public Tank getTank() {
        return tank;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public boolean isDoge() {
        return isDoge;
    }

    Tank tank;
    String name;
    String color;
    boolean isDoge;

    public Player(String name, String color) {
        this();
        this.name = name;
        this.color = color;
        if (name.toLowerCase().equals("doge")) {
            isDoge = true;
        }
    }

    public Player() {
        tank = new Tank();
    }
}
