package exception;

/**
 * A customized Exception. OutOfBoundsException handles the abnormal situation where the coordinates
 * if out of the boundary of the game board.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class OutOfBoundsException extends BattleshipException {

    /** Error message of OutOfBoundsException. */
    public static final String PAST_EDGE = "Coordinates are past board edge";

    /**
     * Constructor of OutOfBoundsException.
     * It calls the constructor of the superclass.
     *
     * @param row x coordinate.
     * @param column y coordinate.
     */
    public OutOfBoundsException(int row, int column) {super(row, column, PAST_EDGE);}

}
