package battleship;

import exception.OutOfBoundsException;
import exception.OverlapException;
import java.io.Serializable;

/**
 * A single ship in a Battleship game
 *
 * @author Steve Gao, sg2369@rit.edu
 * @author Qiwen Quan, qq5575@g.rit.edu
 */
public class Ship implements Serializable {

    /** String to display when a ship that has been entirely sunk. */
    public static final String SUNK_MESSAGE = "A battleship has been sunk!";
    /** Upmost row of this ship. */
    private int uRow;
    /** Leftmost column of this ship. */
    private int lCol;
    /** Orientation of this ship. */
    private Orientation orientation;
    private int length;
    /** Number of hits this ship has taken. */
    private int hit;
    /** Board where this ship is placed. */
    private Board board;


    /**
     * Orientation is a property of a ship.
     * The names of the enum values were chosen because they
     * are descriptive and match the words put in the configuration
     * files.
     *
     * @see Orientation#valueOf(String)
     */
    public enum Orientation {
        HORIZONTAL( 0, 1 ), VERTICAL( 1, 0 );

        /**
         * Use it to loop through all of the cell locations a ship
         * is on, based on the upper left end of the ship.
         */
        public int rDelta, cDelta;

        /**
         * Associate a direction vector with the orientation.
         * @param rDelta how much the row number changes in this direction
         * @param cDelta how much the column number changes
         *               in this direction
         */
        Orientation( int rDelta, int cDelta ) {
            this.rDelta = rDelta;
            this.cDelta = cDelta;
        }
    }

    /**
     * Initialize this new ship's state. Tell the Board object
     * and each involved Cell object about the existence of this
     * ship by trying to put the ship at each applicable Cell.
     * @param board holds a collection of ships
     * @param uRow the uppermost row that the ship is on
     * @param lCol the leftmost column that the ship is on
     * @param ort the ship's orientation
     * @param length how many cells the ship is on
     * @throws OverlapException if this ship would overlap another one
     *              that already exists
     * @throws OutOfBoundsException if this ship would extend beyond
     *              the board
     */
    public Ship(Board board, int uRow, int lCol, Orientation ort, int length) throws OverlapException, OutOfBoundsException{
        this.uRow = uRow;
        this.lCol = lCol;
        this.orientation = ort;
        this.length = length;
        this.hit = 0;
        this.board = board;
        int currentRow = uRow;
        int currentCol = lCol;
        for(int i=0; i < length; i++){
            board.getCell(currentRow, currentCol).putShip(this);
            currentRow += this.orientation.rDelta;
            currentCol += this.orientation.cDelta;
        }
    }

    /** Update the cells that contain this ship to the corresponding status when this ship is sunk. */
    public void updateSunkShip(){
        int currentRow = uRow;
        int currentCol = lCol;
        // this try-catch block is for Board.getCell method. However, since the ship is already placed,
        // the coordinates must be valid. Hence, we do nothing in the catch block.
        try {
            for (int i = 0; i < length; i++) {
                if (this.orientation == Orientation.HORIZONTAL) {
                    board.getCell(currentRow, currentCol).updateSunkShip();
                    currentCol++;
                } else {
                    board.getCell(currentRow, currentCol).updateSunkShip();
                    currentRow++;
                }
            }
        } catch (OutOfBoundsException e){}
    }

    /** Record a hit of this ship. */
    public void hit(){
        this.hit++;
    }

    /**
     * Checks if this ship has been sunk.
     *
     * @return True if this ship has been sunk. False otherwise.
     */
    public boolean isSunk(){
        if(this.hit >= this.length) return true;
        return false;
    }

}
