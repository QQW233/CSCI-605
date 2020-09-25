package Passenger;

/**
 * A class implements comparable interface.
 * Store the information of a Passenger that will be used in a Priority Queue.
 *
 * @author Steve Gao, sg2369@rit.edu
 * @author Qiwen Quan, qq5575@g.rit.edu
 */
public class Passenger implements Comparable<Passenger> {
    /**
     * Constructor for passenger
     * @param name the name of the passenger
     * @param brd_grp the boarding group of the passenger, should be 'A','B',or'C'. Used to determine comparable_group
     * @param sequence sequence of the passenger, used in comparison
     */
    public Passenger(String name, char brd_grp, int sequence) {
        this.name = name;
        this.brd_grp = brd_grp;
        this.sequence = sequence;
        //determine the value of comparable_group based on boarding group of the passenger
        switch (brd_grp) {
            case ('A'):
                comparable_group = 0;
                break;
            case ('B'):
                comparable_group = 1;
                break;
            case ('C'):
                comparable_group = 2;
                break;
            default:
                comparable_group = -1;
        }
    }

    /**
     * Compare this passenger to the other one. Return positive,zero and negative value to indicate larger, equals, and smaller
     * @param other A passenger object
     * @return an int used to indicate which passenger is larger.
     */
    @Override
    public int compareTo(Passenger other) {
        return this.comparable_group
                == other.comparable_group ?
                other.sequence - this.sequence :
                other.comparable_group - this.comparable_group;
    }

    /**
     * A toString method
     * @return A string representation of passenger
     */
    @Override
    public String toString() {
        return name + " in seat " + brd_grp + sequence;
    }

    /* passenger's name */
    private final String name;
    /* passenger's boarding group */
    private final char brd_grp;
    /* passenger's sequence */
    private final int sequence;
    /* passenger's boarding group used in comparison */
    private final int comparable_group;
}
