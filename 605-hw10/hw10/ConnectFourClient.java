package hw10;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * ConnectFour game client application entry point. This client will try to connect to the server according to the
 * given hostname and port. After successful connection, the program will interact with user to play the game.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class ConnectFourClient {

    private Socket socket;
    private PrintStream printer;
    private Scanner scanner;
    private Scanner prompt;
    /** Records the status of the current game. */
    private ConnectFour game;

    /**
     * Constructor for the ConnectFourClient class. Initializes the fields and prepares for the game.
     *
     * @param hostname hostname of the server.
     * @param port port number on which the server runs.
     * @throws IOException if an I/O error occurs when opening the socket or creating the Input/OutputStream.
     */
    public ConnectFourClient(String hostname, int port) throws IOException {
        this.socket = new Socket(hostname, port);
        OutputStream output = socket.getOutputStream();
        this.printer = new PrintStream(output);
        InputStream input = socket.getInputStream();
        this.scanner = new Scanner(input);
        this.prompt = new Scanner(System.in);

        this.game = new ConnectFour();
    }

    /**
     * Shutdown the program properly.
     *
     * @throws IOException if an I/O error occurs when shutting down this socket.
     */
    public void exit() throws IOException {
        this.socket.shutdownOutput();
        this.socket.shutdownInput();
    }

    /**
     * Run the game. The program receives request from server to update the status of the game and display
     * it on the console, as well as interacting with user for moves and send them back to the server.
     *
     * @throws IOException if an I/O error occurs when shutting down this socket. From exit() method.
     */
    public void runGame() throws IOException {
        // Keep running the game until a signal is received or an error occurred.
        mainloop:
        while (true) {
            try{
                // Receive request from the server.
                String response = scanner.nextLine();
                String[] command = response.split(" ");
                // Perform the relevant actions according to the request from server.
                switch (command[0]){
                    // Handshake.
                    case ConnectFourProtocol.CONNECT:
                        System.out.println("Connected!");
                        break;
                    // It's user's turn to make a move.
                    case ConnectFourProtocol.MAKE_MOVE:
                        System.out.print("Your turn! Enter a column: ");
                        boolean validInput = false;
                        while(!validInput){
                            try{
                                int column = Integer.parseInt(prompt.nextLine());
                                game.makeMove(column);
                                printer.println(ConnectFourProtocol.MOVE + " " + column);
                                validInput = true;
                            } catch (Exception e){
                                System.out.println("Invalid input. Please enter a column: ");
                            }
                        }
                        break;
                    // Other player just made a move.
                    case ConnectFourProtocol.MOVE_MADE:
                        try{
                            game.makeMove(Integer.parseInt(command[1]));
                        } catch(Exception e){
                            throw new ConnectFourException("Error when receiving request.", e);
                        }
                        break;
                    // User has won the game.
                    case ConnectFourProtocol.GAME_WON:
                        System.out.println("You have won the game!");
                        break mainloop;
                    // The game is tied.
                    case ConnectFourProtocol.GAME_TIED:
                        System.out.println("The game is tied!");
                        break mainloop;
                    // User has lost the game.
                    case ConnectFourProtocol.GAME_LOST:
                        System.out.println("You lost the game!");
                        break mainloop;
                    // Error occurred.
                    case ConnectFourProtocol.ERROR:
                        System.out.println("An error has occurred in the server. Game is terminated.");
                        break mainloop;
                    default:
                        System.out.println("Unknown request from server. Game is terminated.");
                        break mainloop;
                }
            } catch (Exception e) {
                // An error occurred when running the game. The game will be terminated and the error will be displayed.
                System.out.println("Error. Game is terminated.");
                System.out.println(e);
                break mainloop;
            }
        }
        exit();
    }

    /**
     * Entrypoint of the ConnectFourClient class. It will takes a hostname and a port number as the commandline
     * arguments. Number of arguments provided is checked and the arguments are validated. After validation, the client
     * will connect to the server. Error message will be displayed if validation fails.
     *
     * @param args Commandline arguments. Should be 1 String that represents the host name and
     *             1 int that represents the port number.
     * @throws IOException if error in opening socket or Input/Outputstream when running the client.
     */
    public static void main(String[] args) throws IOException {
        // Check the number of commandline arguments and validate the input.
        if (args.length != 2){
            System.out.println("Usage: java ConnectFourClient <hostname> <port>");
            return;
        }
        String hostname;
        int port;
        try{
            hostname = args[0];
            port = Integer.parseInt(args[1]);
        } catch (Exception e){
            System.out.println("Illegal commandline input.");
            System.out.println(e);
            return;
        }
        // Constructed the object and start the client after input validation.
        ConnectFourClient main = new ConnectFourClient(hostname, port);
        main.runGame();
    }


}
