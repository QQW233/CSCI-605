package battleship;

import exception.OutOfBoundsException;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class represents the grid of cells (squares) and a collection of ships is also kept so the Board
 * can be asked if the game is over. The class is Serializable so that its instance can be saved to a
 * file in binary form using an {@link java.io.ObjectOutputStream} and restored with an
 * {@link java.io.ObjectInputStream}. Because the object holds references to all other objects in the
 * system, no other objects need to be separately saved.
 *
 * @author Steve Gao, sg2369@rit.edu
 * @author Qiwen Quan, qq5575@g.rit.edu
 */
public class Board implements Serializable {

    /** Collection of all the cells in the game board in the form of a 2D array. */
    private Cell[][] board;
    /** Collection of all the ships on the game board. */
    private ArrayList<Ship> ships;
    /** Number of rows of the game board. */
    private int nRow;
    /** Number of columns of the game board. */
    private int nCol;

    /**
     * Constructor of the Board class. Initializes the fields.
     *
     * @param nRow Number of rows of the game board.
     * @param nCol Number of columns of the game board.
     */
    public Board(int nRow, int nCol){
        this.board = new Cell[nRow][nCol];
        for(int i=0; i < nRow; i++)
            for(int j=0; j < nCol; j++)
                this.board[i][j] = new Cell(i, j);
        this.ships = new ArrayList<>();
        this.nRow = nRow;
        this.nCol = nCol;
    }

    /**
     * Fetch the Cell object at the given location.
     *
     * @param row row number (0-based)
     * @param column column number (0-based)
     * @return the Cell created for this position on the board
     * @throws OutOfBoundsException if either coordinate is negative or too high
     */
    public Cell getCell(int row, int column) throws OutOfBoundsException {
        try{
            Cell cell = this.board[row][column];
            return cell;
        } catch (Exception e){
            throw new OutOfBoundsException(row, column);
        }
    }


    /**
     * Add a ship to the board. The only current reason that the
     * board needs direct access to the ships is to poll them
     * to see if they are all sunk and the game is over.
     *
     * @see Cell#putShip(Ship)
     * @param ship the as-yet un-added ship
     * @rit.pre This ship has already informed the Cells of the board
     *    that are involved.
     */
    public void addShip(Ship ship){
        this.ships.add(ship);
    }

    /**
     * Checks if all ships have been sunk.
     *
     * @return True if all ships have been sunk.
     */
    public boolean allSunk(){
        for(Ship s : this.ships){
            if(!s.isSunk()){
                return false;
            }
        }
        return true;
    }

    /**
     * Display the locations of the ships.
     *
     * @param ps PrintStream used to print the output.
     */
    public void fullDisplay(PrintStream ps){
        // Uncomment the two commented lines for a greater distancing between lines.
        ps.print("\t");
        for(int i=0; i < this.nCol-1; i++) ps.print((i) + "\t");
        ps.print((this.nCol-1) + "\n");
        //ps.println();
        for(int i=0; i < this.nRow; i++){
            ps.print((i) + "\t");
            for(int j=0; j < this.nCol; j++){
                ps.print(this.board[i][j].displayChar());
                if(j < this.nCol-1) ps.print("\t");
                else ps.print("\n");
            }
            //ps.println();
        }
    }

    /**
     * Display the current game board.
     *
     * @param ps PrintStream used to print the output.
     */
    public void display(PrintStream ps){
        // Uncomment the two commented lines for a greater distancing between lines.
        ps.print('\t');
        for(int i=0; i < this.nCol-1; i++)
            ps.print((i) + "\t");
        ps.print((this.nCol-1) + "\n");
        //ps.println();
        for(int i=0; i < this.nRow; i++){
            ps.print((i) + "\t");
            for(int j=0; j < this.nCol; j++){
                ps.print(this.board[i][j].displayHitStatus());
                if(j < this.nCol-1) ps.print("\t");
                else ps.print('\n');
            }
            //ps.println();
        }
    }

}
