package hw4.heroes;

import hw4.game.HeroParty.Team;
import hw4.heroes.Heroes.Role;

/**
 * Abstract class to store the information and methods that are shared by all the heroes.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public abstract class Hero {

    /** Hit point upper bound. */
    private int hp;
    /** Current hit point. */
    private int current_hp;
    /** Name of the hero. */
    private String name;
    /** Damage this hero inflicts. */
    private int attack_damage;
    /** Role of this hero. */
    private Role role;

    /**
     * Constructor of the Hero class that initializes the fields.
     *
     * @param name Name of this hero.
     * @param hp Hit point upper bound.
     * @param attack_damage Damage this hero inflicts.
     * @param role Role of this hero.
     */
    protected Hero(String name, int hp, int attack_damage, Role role){
        this.name = name;
        this.hp = hp;
        this.current_hp = hp;
        this.attack_damage = attack_damage;
        this.role = role;
    }

    /**
     * Retrieve the name of this hero.
     *
     * @return A string that is the name of this hero.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Attacks an enemy hero.
     *
     * @param h1 the enemy hero to attack.
     */
    public void attack(Hero h1){
        h1.takeDamage(this.attack_damage);
    }

    /**
     * Checks if this hero has fallen. If so, a message will be displayed to the system output.
     *
     * @return Whether or not this hero has fallen. If so, return true.
     */
    public boolean hasFallen(){
        if(this.current_hp <= 0) {
            System.out.println(this.name + " has fallen!");
            return true;
        }
        return false;
    }

    /**
     * Retrieve the role of this hero.
     *
     * @return the role of this hero.
     */
    public Role getRole(){
        return this.role;
    }

    /**
     * Heals this hero. A hero cannot has a hp that is bigger than the hp upper bound after the healing.
     *
     * @param heal amount of hp to heal.
     */
    public void heal(int heal){
        this.current_hp += heal;
        // Checks if there is overhealing.
        if(this.current_hp > this.hp) {
            //System.out.println(this.name + " heals " + (heal - (this.current_hp - this.hp)) + " points");
            this.current_hp = this.hp;
        }
        System.out.println(this.name + " heals " + heal + " points");
    }

    /**
     * Takes the damage inflicted by another hero. A hero cannot have a negative hp after taking the damage.
     *
     * @param damage the amount of damage to take.
     */
    public void takeDamage(int damage){
        this.current_hp -= damage;
        // checks if hp is below 0.
        if(this.current_hp < 0)
            this.current_hp = 0;
        System.out.println(this.name + " takes " + damage + " damage");
    }

    /**
     * Create a new hero.
     *
     * @param role role of the new hero
     * @param team team of the new hero
     * @param party party of the new hero
     * @return the newly created hero.
     */
    public static Hero create(Heroes.Role role, Team team, Party party){
        switch (role){
            case HEALER: return new Healer(team, party);
            case BERSERKER: return new Berserker(team);
            default: return new Tank(team);
        }
    }

    /**
     * Returns a String that summarizes the hero status.
     *
     * @return the hero status.
     */
    public String toString(){
        return this.name + ", " + this.role + ", " + this.current_hp + "/" + this.hp;
    }

}
