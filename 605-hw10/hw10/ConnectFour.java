package hw10;

/**
 * A basic implementation of the Connect Four game.
 *
 * Modified and completed by:
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class ConnectFour {
    /** the number of rows */
    public final static int ROWS = 6;
    /** the number of columns */
    public final static int COLS = 7;
    /** how big a line one needs to win */
    public final static int WIN_LEN = 4;

    /**
     * Used to indicate a move that has been made on the board.
     */
    public enum Move {
        PLAYER_ONE('X'),
        PLAYER_TWO('O'),
        NONE('.');

        private char symbol;

        Move(char symbol) {
            this.symbol = symbol;
        }

        public char getSymbol() {
            return symbol;
        }

        /**
         * Compares if this Move is equal to the given Move according to their symbols.
         * @param other Other Move to compare with this Move.
         * @return whether or not two Moves are equal. True if they have the same symbol. False otherwise.
         */
        public boolean equals(Move other) {
            return this.symbol == other.getSymbol();
        }
    }

    /**
     * The number of rows in the board.
     */
    private int rows;

    /**
     * The number of columns in the board.
     */
    private int cols;

    /**
     * The board.
     */
    private Move[][] board;

    /**
     * Used to keep track of which player's turn it is; 0 for player 1, and 1
     * for player 2.
     */
    private int turn;

    /**
     * Creates a Connect Four game using a board with the standard number of
     * rows (6) and columns (7).
     */
    public ConnectFour() {
        this(ROWS, COLS);
    }

    /**
     * Creates a Connect Four game using a board with the specified number of
     * rows and columns. Assumes that player 1 is the first to move.
     *
     * @param rows The number of rows in the board.
     * @param cols The number of columns in the board.
     */
    public ConnectFour(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        board = new Move[rows][cols];
        for(int row=0; row < rows; row++) {
            for(int col=0; col<cols; col++) {
                board[row][col] = Move.NONE;
            }
        }

        turn = 0;
    }

    /**
     * Makes a move for the player whose turn it is. If the move is successful,
     * play automatically switches to the other player's turn.
     *
     * @param column The column in which the player is moving.
     *
     * @throws ConnectFourException If the move is invalid for any reason.
     */
    public void makeMove(int column) throws ConnectFourException {
        try {
            int row = 0;
            // Find the available cell.
            while (!this.board[row][column].equals(Move.NONE))
                row++;
            if (this.turn == 0)
                this.board[row][column] = Move.PLAYER_ONE;
            else
                this.board[row][column] = Move.PLAYER_TWO;
            System.out.println("A move has been made in column " + column);
            System.out.println(this + "\n");
            // Switches the play.
            turn = 1 - turn;
        } catch (Exception e){
            // This block is executed when user enters a column that is already full.
            throw new ConnectFourException("Illegal move made by user.");
        }
    }

    /**
     * Look over the entire board for any N-in-a-row situations.
     * (By N we mean {@link #WIN_LEN}.)
     * @return true if one of the players has an N-in-a-row situation.
     */
    public boolean hasWonGame() {

        // Record the current amount of symbols that are the same and are in a row.
        int lengthCur;
        // Record the last symbol read.
        Move player = Move.PLAYER_ONE;
        // Check horizontal.
        for (int row = 0; row < this.rows; row++){
            lengthCur = 0;
            for (int col = 0; col < this.cols; col++){
                if(this.board[row][col].equals(player)) lengthCur++;
                else if (this.board[row][col].equals(Move.NONE)) lengthCur = 0;
                else{
                    player = this.board[row][col];
                    lengthCur = 1;
                }
                if (lengthCur == ConnectFour.WIN_LEN) return true;
            }
        }

        // Check vertical.
        for (int col = 0; col < this.cols; col++){
            lengthCur = 0;
            for (int row = 0; row < this.rows; row++){
                if(this.board[row][col].equals(player)) lengthCur++;
                else if (this.board[row][col].equals(Move.NONE)) lengthCur = 0;
                else{
                    player = this.board[row][col];
                    lengthCur = 1;
                }
                if (lengthCur == ConnectFour.WIN_LEN) return true;
            }
        }

        // Check diagonal (direction: /)
        for (int row = 0; row < this.rows; row++){
            int curCol = 0;
            int curRow = row;
            lengthCur = 0;
            while(curCol < this.cols && curRow < this.rows){
                if(this.board[curRow][curCol].equals(player)) lengthCur++;
                else if (this.board[curRow][curCol].equals(Move.NONE)) lengthCur = 0;
                else{
                    player = this.board[curRow][curCol];
                    lengthCur = 1;
                }
                if (lengthCur == ConnectFour.WIN_LEN) return true;
                curCol++;
                curRow++;
            }
        }
        for (int col = 0; col < this.cols; col++){
            int curCol = col;
            int curRow = 0;
            lengthCur = 0;
            while(curCol < this.cols && curRow < this.rows){
                if(this.board[curRow][curCol].equals(player)) lengthCur++;
                else if (this.board[curRow][curCol].equals(Move.NONE)) lengthCur = 0;
                else{
                    player = this.board[curRow][curCol];
                    lengthCur = 1;
                }
                if (lengthCur == ConnectFour.WIN_LEN) return true;
                curCol++;
                curRow++;
            }
        }

        // Check diagonal (direction: \)
        for (int row = 0; row < this.rows; row++){
            int curCol = 0;
            int curRow = row;
            lengthCur = 0;
            while(curCol < this.cols && curRow >= 0){
                if(this.board[curRow][curCol].equals(player)) lengthCur++;
                else if (this.board[curRow][curCol].equals(Move.NONE)) lengthCur = 0;
                else{
                    player = this.board[curRow][curCol];
                    lengthCur = 1;
                }
                if (lengthCur == ConnectFour.WIN_LEN) return true;
                curCol++;
                curRow--;
            }
        }
        for (int col = 0; col < this.cols; col++){
            int curCol = col;
            int curRow = this.rows - 1;
            lengthCur = 0;
            while(curCol < this.cols && curRow >= 0){
                if(this.board[curRow][curCol].equals(player)) lengthCur++;
                else if (this.board[curRow][curCol].equals(Move.NONE)) lengthCur = 0;
                else{
                    player = this.board[curRow][curCol];
                    lengthCur = 1;
                }
                if (lengthCur == ConnectFour.WIN_LEN) return true;
                curCol++;
                curRow--;
            }
        }
        return false;
    }

    /**
     * Checks to see if the game is tied - no NONE moves left in board.  This
     * is called after hasGameWon.
     *
     * @return whether game is tied or not
     */
    public boolean hasTiedGame() {
        for(int i=0; i < this.cols; i++)
            for(int j = 0; j < this.rows; j++)
                if(this.board[i][j].equals(Move.NONE)) return false;
        return true;
    }

    /**
     * Returns a {@link String} representation of the board, suitable for
     * printing.
     *
     * @return A {@link String} representation of the board.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for(int r=rows-1; r >= 0; r--) {
            for(int c=0; c<cols; c++) {
                builder.append('[');
                builder.append(board[r][c].getSymbol());
                builder.append(']');
            }
            builder.append('\n');
        }
        return builder.toString();
    }
}
