package hw4.game;

import hw4.heroes.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Class to store the information and the actions of a party which contains heroes.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class HeroParty implements Party {

    /** the team that this party belongs to. */
    private Team team;
    /** the heroes contained in this party in the form of a list */
    private List<Hero> heros;


    /**
     * Constructor of the HeroParty class. It initializes the fields, adds heroes into
     * a queue and shuffle the queue.
     *
     * @param team the team that this party belongs to.
     * @param seed the seed used to shuffle the queue.
     */
    public HeroParty(Team team, int seed){
        this.heros = new ArrayList<>();
        this.team = team;
        addHero(Hero.create(Heroes.Role.BERSERKER, team, this));
        addHero(Hero.create(Heroes.Role.HEALER, team, this));
        addHero(Hero.create(Heroes.Role.TANK, team, this));
        Collections.shuffle(this.heros, new Random(seed));
    }

    /**
     * Enum to record the teams.
     */
    public enum Team {
        DRAGON,
        LION
    }

    /**
     * adds a hero to the party.
     *
     * @param hero the hero to be added.
     */
    @Override
    public void addHero(Hero hero) {
        this.heros.add(hero);
    }

    /**
     * Remove a hero from the party.
     *
     * @return the hero that has been removed.
     */
    @Override
    public Hero removeHero() {
        Hero hero_fallen = this.heros.remove(0);
        return hero_fallen;
    }

    /**
     * Count the number of heroes in this party.
     *
     * @return the number of heroes in this party
     */
    @Override
    public int numHeroes() {
        return this.heros.size();
    }

    /**
     * Get the team that this party belongs to.
     *
     * @return the team that this party belongs to.
     */
    @Override
    public Team getTeam() {
        return this.team;
    }

    /**
     * Get all the heroes contained in this party.
     *
     * @return all the heroes contained in this party, in the form a list.
     */
    @Override
    public List<Hero> getHeroes() {
        return this.heros;
    }
}
