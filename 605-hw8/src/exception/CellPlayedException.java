package exception;

/**
 * A customized Exception. CellPlayedException handles the abnormal situation when
 * a cell that has been hit before is hit again.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class CellPlayedException extends BattleshipException {

    /** Error message of CellPlayedException. */
    public static final String ALREADY_HIT = "This cell has already been hit";

    /**
     * Constructor of CellPlayedException.
     * It calls the constructor of the superclass.
     *
     * @param row x coordinate.
     * @param column y coordinate.
     */
    public CellPlayedException(int row, int column) {
        super(row, column, ALREADY_HIT);
    }

}
