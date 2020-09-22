package hw4.heroes;

import hw4.game.HeroParty.Team;

/**
 * Concrete class to store the information and relevant actions of Role Berserker. This class inherits abstract
 * class Hero. This class does not override any method in the super class and does not have any additional field
 * since Berserker has no additional Role feature.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class Berserker extends Hero {


    /**
     * Constructor for the class Berserker that initializes the fields.
     *
     * @param team the team that this Berserker is in. Used to retrieve the character name of this Berserker.
     */
    protected Berserker(Team team) {
        super(Heroes.getName(team, Heroes.Role.BERSERKER), 30, 20, Heroes.Role.BERSERKER);
    }

}
