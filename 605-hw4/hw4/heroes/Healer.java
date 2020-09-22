package hw4.heroes;

import hw4.game.HeroParty.Team;
import hw4.heroes.Heroes.Role;

/**
 * Concrete class to store the information and relative actions of role Healer. This class inherits abstract
 * class Hero. It contains fields that are special to role Healer and overrides method attack in the super
 * class to adopt the special behavior of role Healer.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class Healer extends Hero {

    /** Amount of hp that this Healer heals. */
    private int heal_amount;
    /** Records which party this Healer is in. Used to determine which heroes to heal during battle. */
    private Party party;

    /**
     * Constructor for the class Healer that initializes the fields.
     *
     * @param team the team that this Healer is in. Used to retrieve the character name of the Healer.
     * @param party the party that this Healer is in.
     */
    protected Healer(Team team, Party party) {
        super(Heroes.getName(team, Heroes.Role.HEALER), 35, 10, Role.HEALER);
        this.heal_amount = 10;
        this.party = party;
    }

    /**
     * Attacks an enemy hero. This method overrides attack method in the super class to add the healing
     * action before attacking the enemy hero.
     *
     * @param h1 the enemy hero to attack.
     */
    @Override
    public void attack(Hero h1) {
        for (Hero hero : this.party.getHeroes()){
            hero.heal(this.heal_amount);
        }
        super.attack(h1);
    }
}
