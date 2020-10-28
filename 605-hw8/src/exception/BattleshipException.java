package exception;

/**
 * A customized Exception. BattleshipException handles all abnormal situations and acts as
 * the root for several more specified user defined Exceptions.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class BattleshipException extends Exception {

    /** Default value for coordinates. */
    public static final int UNSET = -1;

    // Create public integer fields row and column.
    // Make them so they cannot be changed, and non-static.
    public final int row;
    public final int column;

    /**
     * Constructor of BattleshipException. It records the coordinates
     * and then call the constructor of superclass.
     *
     * @param row x coordinate.
     * @param column y coordinate.
     * @param message error message.
     */
    public BattleshipException( int row, int column, String message ) {
        super( message + ", row=" + row + ", column=" + column );
        this.row = row;
        this.column = column;
    }

    /**
     * Constructor of BattleshipException. It sets the coordinates to UNSET
     * and then call the constructor of superclass.
     *
     * @param message error message.
     */
    public BattleshipException(String message){
        super(message + ", row=" + UNSET + ", column=" + UNSET);
        this.row = UNSET;
        this.column = UNSET;
    }
}
