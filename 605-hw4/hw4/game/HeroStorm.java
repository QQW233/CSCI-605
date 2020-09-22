package hw4.game;

import hw4.game.HeroParty.*;
import hw4.heroes.Hero;

/**
 * HeroStorm is a game that describes a battle between the two teams, Dragons and Lions.
 * Each team has 3 heroes, a Tank, a Berserker and a Healer.
 * Heroes of each team is put into a queue and heroes at the front of two queues have 1 on 1 battles.
 * After each 1 on 1 battle, the heroes in the battle, if not fallen, will be put at the back of the queue for future battles.
 * It takes two user inputs as the seeds to randomly shuffle the queue.
 * The battle process is displayed and there will be one winner team at the end.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class HeroStorm {

    /** The Dragon team represented as a HeroParty Object. */
    private HeroParty team_dragon;
    /** The Lion team represented as a HeroParty Object. */
    private HeroParty team_lion;
    /** Number of battles taken. This is also used to decide which team attacks first. */
    private int toggle;

    /**
     * Constructor of the HeroStorm class that initializes the fields.
     *
     * @param num1 random seed used to shuffle the Dragon team queue.
     * @param num2 random seed used to shuffle the Lion team queue.
     */
    public HeroStorm(int num1, int num2){
        this.team_dragon = new HeroParty(Team.DRAGON, num1);
        this.team_lion = new HeroParty(Team.LION, num2);
        this.toggle = 0;
    }

    /**
     * Start a 1 on 1 battle between the two teams. After battle fallen heroes will be removed and
     * heroes that are not fallen will be put to the back of the queue.
     *
     * @param first team to attack first.
     * @param second team to attack second.
     */
    private void battle(HeroParty first, HeroParty second){
        Hero first_hero = first.getHeroes().get(0);
        Hero second_hero = second.getHeroes().get(0);
        System.out.println("*** " + first_hero.getName() + " vs " + second_hero.getName() + "!\n");
        first_hero.attack(second_hero);
        // Check if the second hero falls
        if(second_hero.hasFallen()) {
            second.removeHero();
            first.addHero(first.removeHero());
        }
        else{
            second_hero.attack(first_hero);
            // Check if the first hero falls
            if(first_hero.hasFallen())
                first.removeHero();
            else
                first.addHero(first.removeHero());
            second.addHero(second.removeHero());
        }
        System.out.println();
    }

    /**
     * Controls the flow of the game. play method will constantly start 1 on 1 battles until
     * there is a team that wins (All heroes of the other team have fallen).
     */
    public void play(){
        // start battles if there is still heroes alive in both teams
        while(this.team_dragon.numHeroes() != 0 && this.team_lion.numHeroes() != 0){
            // Print the pre-battle summary
            System.out.println("Battle #" + (this.toggle + 1) + "\n==========");
            System.out.println(team_dragon.getTeam() + ":");
            for(Hero hero : team_dragon.getHeroes())
                System.out.println(hero.toString());
            System.out.println("");
            System.out.println(team_lion.getTeam() + ":");
            for(Hero hero : team_lion.getHeroes())
                System.out.println(hero.toString());
            System.out.println();
            // Two teams take shift to attack first.
            if(toggle % 2 == 0)
                battle(team_dragon, team_lion);
            else
                battle(team_lion, team_dragon);
            toggle++;
        }
        if(this.team_dragon.numHeroes() == 0){
            System.out.println("Team Lion wins!");
        }
        else{
            System.out.println("Team Dragon wins!");
        }
    }

    /**
     * main method to run the program. It checks if command line arguments are valid and constructs a HeroStorm
     * object and then call the play method to start the game.
     *
     * @param args command line arguments. Should be two integers which are the seed used to shuffle the heroes queue.
     */
    public static void main(String[] args){
        if(args.length != 2){
            System.out.println("Usage: java HeroStorm dragon_seed_# lion_seed_#");
        }
        else{
            HeroStorm main = new HeroStorm(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            main.play();
        }
    }

}
