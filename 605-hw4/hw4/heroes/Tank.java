package hw4.heroes;

import hw4.game.HeroParty.Team;

/**
 * Concrete class to store the information and relevant actions of Role Tank. This class inherits abstract
 * class Hero. It contains fields that is special to role Tank and overrides method takeDamage in the super
 * class to adopt the special behavior of role Tank.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class Tank extends Hero{

    /** Percent of damage reduced because of the defense. */
    private double defense;

    /**
     * Constructor for the class Tank that initializes the fields.
     *
     * @param team the team that this Tank is in. Used to retrieve the character name of this Tank.
     */
    protected Tank(Team team) {
        super(Heroes.getName(team, Heroes.Role.TANK), 40, 15, Heroes.Role.TANK);
        this.defense = 0.1;
    }

    /**
     * Takes damage from an enemy hero. This method overrides takeDamage method in the super class to add
     * the damage reduction feature that Tank has.
     *
     * @param damage the amount of damage initially inflicted by the enemy hero.
     */
    @Override
    public void takeDamage(int damage){
        super.takeDamage((int) (damage * (1 - defense)));
    }

}
