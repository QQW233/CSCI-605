package battleship;

import exception.CellPlayedException;
import exception.OutOfBoundsException;
import exception.OverlapException;
import java.io.*;

/**
 * Battleship game entry point. This program is a simplified version of game Battleship. User will interact with the
 * program to perform several actions to win the game. Currently support features: Hit a cell, Save the game, Cheat and
 * Quit the program. This program takes one commandline argument which is the path of the setup file/saved game.
 *
 * @author Steve Gao, sg2369@rit.edu
 * @author Qiwen Quan, qq5575@g.rit.edu
 *
 */
public class Battleship {

    /** board records the current state of the game including the game board and user progress. */
    private Board board;
    /** Read input from the user. */
    private BufferedReader br;
    /** Print the output. */
    private PrintStream ps;

    /**
     * Constructor of the Battleship class. Initialize the fields.
     * Notice that the board field will not be initialized here but in the run method.
     *
     */
    private Battleship(){
        this.ps = new PrintStream(System.out);
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * run method checks if the provided file path is valid. If so, it will further decide whether the given file
     * is a initial setup file or saved game image. Then, it will setup/load the board and call play() method to
     * start the game.
     *
     * @param args Commandline arguments. Should provide a file path.
     */
    private void run(String[] args){
        String filename = args[0];
        // Checks if the given file is a saved game image.
        try(FileInputStream fis = new FileInputStream(filename); ObjectInputStream ois = new ObjectInputStream(fis)){
            this.board = (Board) ois.readObject();
            this.ps.println("Saved game found. Loading complete.");
            play();
            // If not a game image, check if it is a valid setup file.
        } catch (IOException | ClassNotFoundException e) {
            try(BufferedReader br = new BufferedReader(new FileReader(filename))){
                this.ps.println("Initial setup file found. Loading...");
                String temp = br.readLine();
                String[] line = temp.split("\\s+");
                int nRow = Integer.parseInt(line[0]);
                int nCol = Integer.parseInt(line[1]);
                this.board = new Board(nRow, nCol);
                temp = br.readLine();
                // Add all ships to the game board.
                while(temp != null){
                    line = temp.split("\\s+");
                    nRow = Integer.parseInt(line[0]);
                    nCol = Integer.parseInt(line[1]);
                    Ship s = new Ship(this.board, nRow, nCol, Ship.Orientation.valueOf(line[2]), Integer.parseInt(line[3]));
                    this.board.addShip(s);
                    temp = br.readLine();
                }
                this.ps.println("Loading complete.");
                play();
                // The given setup file contains invalid information.
            } catch (OverlapException | OutOfBoundsException e2) {
                this.ps.println(e2);
                this.ps.println("Initial setup file corrupted.");
            } catch (IOException e2){
                System.out.print("File cannot be read as either save or initial setup.");
            }
        // Properly close the program.
        } finally {
            if(quit()) System.out.println("Program terminated.");
            else System.out.println("Program terminated abnormally.");
        }
    }

    /**
     * Entry point of the program. It will first check if the number of commandline arguments given is correct.
     * If so, it will create the Battleship class and call the run() method to execute the program.
     *
     * @param args Commandline arguments. It should be of length one, which is the path to the setup file/saved game.
     */
    public static void main(String[] args){
        // Checks if the number of argument is equal to 1
        if(args.length != 1){
            System.out.println("Number of arguments is incorrect.\nUsage: java Battleship <filename>");
        }
        else{
            Battleship main = new Battleship();
            main.run(args);
        }
    }

    /** Print the current game board and the menu of available actions to the user. */
    private void promptPlayer(){
        this.ps.println("\nCurrent board:");
        this.board.display(this.ps);
        this.ps.println("Please select an action:\nHit a cell: h row column\nSave the game: s file\nQuit: q\nCheat: !");
        this.ps.flush();
    }

    /**
     * Play method starts the game and keep interacting with user until the user choose to quit the
     * program or win the game.
     *
     * @throws IOException If something goes wrong during the IO process.
     */
    private void play() throws IOException {
        promptPlayer();
        // Read the first line which is the length and width of the game board.
        String line = br.readLine();
        String[] commands = line.split("\\s+");
        outer:
        while(!commands[0].toLowerCase().equals("q")){
            switch (commands[0]){
                case "h":
                    if(commands.length == 3){
                        try{
                            int nRow = Integer.parseInt(commands[1]);
                            int nCol = Integer.parseInt(commands[2]);
                            if(hit(nRow, nCol)){
                                if(this.board.allSunk()){
                                    ps.println("All ships have been sunk! You win!");
                                    // Terminate the while loop to end the game.
                                    break outer;
                                }
                            }
                        } catch (Exception e){
                            ps.println("Incorrect input. Please enter int values for row and column.");
                        }
                    }
                    else this.ps.println("Number of arguments is incorrect.\nUsage: h row column");
                    break;
                case "s":
                    if(commands.length == 2){
                        if(save(commands[1])) this.ps.println("Game saved.");
                        else this.ps.println("Something went wrong when saving the game. Please try again.");
                    }
                    else this.ps.println("Number of arguments is incorrect.\nUsage: s filename");
                    break;
                case "!":
                    this.board.fullDisplay(this.ps);
                    break;
                default:
                    this.ps.println("Invalid command.");
                    break;
            }
            this.ps.flush();
            promptPlayer();
            line = br.readLine();
            commands = line.split("\\s+");
        }
    }

    /**
     * Hit a cell of the game board and change the status of the game board accordingly.
     *
     * @param nRow x coordinate of the cell to hit.
     * @param nCol y coordinate of the cell to hit.
     * @return whether or not the hit action has been performed successfully.
     */
    private boolean hit(int nRow, int nCol){
        try{
            this.board.getCell(nRow, nCol).hit();
            return true;
        } catch (OutOfBoundsException | CellPlayedException e) {
            ps.println(e);
            ps.flush();
            return false;
        }
    }

    /**
     * Save the current game to a file for future play.
     *
     * @param filename Path to the save file. It will be created and overwritten should it already exists.
     * @return whether or not the save action has been successfully performed.
     * @throws IOException If something goes wrong during the file IO process.
     */
    private boolean save(String filename){
        try{
            File save = new File("save");
            if(!save.exists())
                save.mkdirs();
            save = new File("save/" + filename);
            if(!save.exists())
                save.createNewFile();
            FileOutputStream fout = new FileOutputStream("save/" + filename);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(this.board);
            fout.close();
            oos.close();
            return true;
        } catch (Exception e){
            this.ps.println(e);
            return false;
        }
    }

    /**
     * Close the streams so the program can terminate properly to avoid any leak.
     *
     * @return whether or not the streams have been closed properly.
     */
    private boolean quit(){
        try{
            this.ps.println("Shutting down...");
            this.ps.flush();
            this.ps.close();
            this.br.close();
            return true;
        } catch(IOException e){
            this.ps.println(e);
            return false;
        }
    }

}
