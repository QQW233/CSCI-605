package hw4.heroes;

import hw4.game.HeroParty.Team;
import java.util.List;

/**
 * Interface Party states the necessary methods that a party of heroes should have.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public interface Party {

    /** Add a hero to the party. */
    void addHero(Hero hero);
    /** Remove a hero from the party. */
    Hero removeHero();
    /** Get the number of heroes that are in this party */
    int numHeroes();
    /** Get the team of this party. */
    Team getTeam();
    /** Get all the heroes that are in this party. */
    List<Hero> getHeroes();

}
