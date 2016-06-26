package be.kdg.artillery.model;

/**
 * Robby Dirix & Laurens de Voogd
 * 18-Feb-16
 */

/**
 * Dit is een enum van alle beschikbare projectielen in het spel. Elk projectiel dient een ander aantal van schadepunten toe aan de vijandige tank.
 */
public enum Projectile {

    ROCKET(20), HELLFIRE_MISSILE(25), RED_BOMB(25), GRENADE(20), ATOM_BOMB(50), RUBBER_CHICKEN(100);
    int damage;

    Projectile(int dmg) {
        this.damage = dmg;
    }
}


