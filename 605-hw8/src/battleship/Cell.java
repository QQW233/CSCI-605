package battleship;

import exception.CellPlayedException;
import exception.OverlapException;
import java.io.Serializable;

/**
 * A single spot on the Battleship game board.
 * A cell knows if there is a ship on it, and it remember
 * if it has been hit.
 *
 * @author Steve Gao, sg2369@rit.edu
 * @author Qiwen Quan, qq5575@g.rit.edu
 */
public class Cell implements Serializable {

    /** Character to display for a ship that has been entirely sunk */
    public static final char SUNK_SHIP_SECTION = '*';

    /** Character to display for a ship that has been hit but not sunk */
    public static final char HIT_SHIP_SECTION = '☐';

    /** Character to display for a water cell that has been hit */
    public static final char HIT_WATER = '.';

    /**
     * Character to display for a water cell that has not been hit.
     * This character is also used for an unhit ship segment.
     */
    public static final char PRISTINE_WATER = '_';

    /**
     * Character to display for a ship section that has not been
     * sunk, when revealing the hidden locations of ships
     */
    public static final char HIDDEN_SHIP_SECTION = 'S';

    /** Ship that sits on this cell. */
    private Ship ship;
    /** x coordinate. */
    private final int row;
    /** y coordinate. */
    private final int col;
    /** Current status of this cell. */
    private char status;

    /**
     * Constructor of the Cell class. It will set the status of the newly created cell to PRISTINE_WATER.
     *
     * @param row x coordinate.
     * @param col y coordinate.
     */
    public Cell(int row, int col){
        this.row = row;
        this.col = col;
        this.status = PRISTINE_WATER;
    }

    /**
     * Place a ship on this cell. Of course, ships typically cover
     * more than one Cell, so the same ship will usually be passed
     * to more than one Cell's putShip method.
     *
     * @param s the ship that is to be on this Cell
     * @throws OverlapException if there is already a ship here.
     */
     public void putShip(Ship s) throws OverlapException{
         if(this.ship == null){
             this.ship = s;
             this.status = this.HIDDEN_SHIP_SECTION;
         }
         else throw new OverlapException(this.row, this.col);
     }

    /**
     * Hit this cell. The status of this cell will be changed accordingly.
     * Notice: If this cell has been hit before, an Exception will be thrown.
     *
     * @throws CellPlayedException If this cell has been hit before.
     */
     public void hit() throws CellPlayedException {
        if(this.displayHitStatus() != PRISTINE_WATER){
            throw new CellPlayedException(this.row, this.col);
        }
        else if(this.ship == null){
            this.status = HIT_WATER;
        }
        else{
            this.status = HIT_SHIP_SECTION;
            this.ship.hit();
            if(this.ship.isSunk()){
                System.out.println(Ship.SUNK_MESSAGE);
                this.ship.updateSunkShip();
            }

        }
     }

    /**
     * Update the status of this cell from HIT_SHIP_SECTION to SUNK_SHIP_SECTION.
     */
    public void updateSunkShip(){
         if(this.ship.isSunk()){
             this.status = SUNK_SHIP_SECTION;
         }
     }

    /**
     * Display whether this cell has been hit before.
     *
     * @return a char. PRISTINE_WATER if this cell hasn't been hit. Otherwise it will be the
     * current status of this cell.
     */
     public char displayHitStatus(){
         if(this.status == this.HIDDEN_SHIP_SECTION){
             return this.PRISTINE_WATER;
         }
         else{
             return this.status;
         }
     }

    /**
     * Display the current status of this cell.
     *
     * @return the current status of this cell.
     */
     public char displayChar(){
         return this.status;
     }

}
