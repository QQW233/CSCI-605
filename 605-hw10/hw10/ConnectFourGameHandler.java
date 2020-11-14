package hw10;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * A specific ConnectFour game session for two connected players. This class will act as the middleman and the referee
 * of the game. Two players communicate through this class. This class also tracks the status of the game and end the
 * game when the game is finished.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class ConnectFourGameHandler extends Thread {

    /** Records the status of the current game. */
    private ConnectFour game;
    private Socket client1;
    private Socket client2;
    private PrintStream printer1;
    private Scanner scanner1;
    private PrintStream printer2;
    private Scanner scanner2;

    /**
     * Constructor for the ConnectFourHandler class. Initializes the fields and prepares for the game.
     *
     * @param client1 Player 1.
     * @param client2 Player 2.
     * @throws IOException if an I/O error occurs when opening the socket or creating the Input/OutputStream.
     */
    public ConnectFourGameHandler(Socket client1, Socket client2) throws IOException {
        this.game = new ConnectFour();
        this.client1 = client1;
        this.client2 = client2;

        OutputStream output1 = client1.getOutputStream();
        this.printer1 = new PrintStream(output1);
        InputStream input1 = client1.getInputStream();
        this.scanner1 = new Scanner(input1);

        OutputStream output2 = client2.getOutputStream();
        this.printer2 = new PrintStream(output2);
        InputStream input2 = client2.getInputStream();
        this.scanner2 = new Scanner(input2);
    }

    /**
     * Shutdown the program properly.
     *
     * @throws IOException if an I/O error occurs when shutting down the sockets.
     */
    public void exit() throws IOException {
        client1.shutdownOutput();
        client2.shutdownOutput();
    }

    /**
     * Run the game until the game is finished.
     * This method interact with both players to provide/receive update of the current game.
     */
    @Override
    public void run() {
        // Terminate the game if error occurred during the game.
        try {
            // Track which player played the last game.
            int lastPlayed = 1;
            // Keep running the game until the game is finished.
            mainloop:
            while (!game.hasWonGame() && !game.hasTiedGame()) {
                // Track if one player has disconnected.
                boolean responding = false;
                // Player 2's turn.
                if (lastPlayed == 0) {
                    printer2.println(ConnectFourProtocol.MAKE_MOVE);
                    while(scanner2.hasNextLine()){
                        String[] commands = scanner2.nextLine().split(" ");
                        responding = true;
                        // Update the game in this class as well as the other player after a move is made.
                        if (commands[0].equals(ConnectFourProtocol.MOVE)){
                            game.makeMove(Integer.parseInt(commands[1]));
                            printer1.println(ConnectFourProtocol.MOVE_MADE + " " + Integer.parseInt(commands[1]));
                        }
                        else{
                            throw new ConnectFourException("Invalid response from client.");
                        }
                        break;
                    }
                }
                // Player 1's turn.
                else {
                    printer1.println(ConnectFourProtocol.MAKE_MOVE);
                    while(scanner1.hasNextLine()){
                        responding = true;
                        String[] commands = scanner1.nextLine().split(" ");
                        // Update the game in this class as well as the other player after a move is made.
                        if (commands[0].equals(ConnectFourProtocol.MOVE)){
                            game.makeMove(Integer.parseInt(commands[1]));
                            printer2.println(ConnectFourProtocol.MOVE_MADE + " " + Integer.parseInt(commands[1]));
                        }
                        else{
                            throw new ConnectFourException("Invalid response from client.");
                        }
                        break;
                    }
                }
                // Report error and terminate the game if a player has disconnected.
                if (!responding){
                    printer1.println(ConnectFourProtocol.ERROR);
                    printer2.println(ConnectFourProtocol.ERROR);
                    throw new ConnectFourException("Player has disconnected.");
                }
                // Switch the play.
                lastPlayed = 1 - lastPlayed;
            }
            // One player wins the game.
            if(game.hasWonGame()){
                if (lastPlayed == 0){
                    printer1.println(ConnectFourProtocol.GAME_WON);
                    printer2.println(ConnectFourProtocol.GAME_LOST);
                }
                else{
                    printer2.println(ConnectFourProtocol.GAME_WON);
                    printer1.println(ConnectFourProtocol.GAME_LOST);
                }
            }
            // The game is tied.
            else if (game.hasTiedGame()){
                printer1.println(ConnectFourProtocol.GAME_TIED);
                printer2.println(ConnectFourProtocol.GAME_TIED);
            }
            // Other situations (Irregular program flow).
            else{
                throw new ConnectFourException("Game finished abnormally.");
            }
            exit();
        } catch (ConnectFourException e){
            System.out.println("Error occurred during game session. Game is terminated.");
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println("Error occurred in IO.");
            System.out.println(e);
        }

    }

}
