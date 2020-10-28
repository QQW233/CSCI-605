package exception;

/**
 * A customized Exception. OverlapException handles the abnormal situation when
 * attempting to put a ship on a cell that already has a ship on it.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class OverlapException extends BattleshipException {

    /** Error message of OverlapException. */
    public static final String OVERLAP = "Overlaps error";

    /**
     * Constructor of OverlapException.
     * It calls the constructor of the superclass.
     *
     * @param row x coordinate.
     * @param column y coordinate.
     */
    public OverlapException(int row, int column) {
        super(row, column, OVERLAP);
    }

}
