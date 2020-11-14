package hw10;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ConnectFour game server application entry point. This server will wait for two players to connect before starting
 * a game. This server uses multithreading to allow multiple games to run simultaneously.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class ConnectFourServer {

    /** Record the number of players that connect/connected to this server for monitor purpose. */
    private int playerCount;
    private ServerSocket server;

    /**
     * Constructor of ConnectFourServer class. It takes an port number as parameter and starts the server
     * on that port.
     *
     * @param port Port number on which this server will run.
     * @throws IOException if an I/O error occurs when opening the socket.
     */
    public ConnectFourServer(int port) throws IOException {
        this.server = new ServerSocket(port);
        this.playerCount = 0;
        System.out.println("ConnectFour game server up and running...");
    }

    /**
     * Run the server and perform relevant actions. Server will keep waiting endlessly for users to connect.
     * Once two players connect to the server, a new Thread will be started to process the game for these two
     * players while the server will continue to wait for other players to join.
     *
     * @throws IOException if an I/O error occurs when opening the socket or creating the Input/OutputStream.
     */
    public void run() throws IOException {
        // Keep waiting for players to join the game.
        while(true){
            // Connect the first player.
            Socket client1 = server.accept();
            OutputStream output1 = client1.getOutputStream();
            PrintStream printer1 = new PrintStream(output1);
            printer1.println(ConnectFourProtocol.CONNECT);
            this.playerCount++;
            System.out.println("Current player count:" + this.playerCount);

            // Connect the second player.
            Socket client2 = server.accept();
            this.playerCount++;
            OutputStream output2 = client2.getOutputStream();
            PrintStream printer2 = new PrintStream(output2);
            printer2.println(ConnectFourProtocol.CONNECT);
            System.out.println("Current player count:" + this.playerCount);

            // Start a game session once two players are connected.
            ConnectFourGameHandler gameSession = new ConnectFourGameHandler(client1, client2);
            gameSession.start();
        }

    }

    /**
     * Entrypoint of the ConnectFourServer class. It will takes a port number as the commandline argument.
     * Number of arguments provided is checked and the argument is validated. After validation, the server will be
     * started on the given port number. Error message will be displayed if validation fails.
     *
     * @param args Commandline arguments. Should be 1 int that represents the port number.
     * @throws IOException if error in opening socket or Input/Outputstream when running the server.
     */
    public static void main(String[] args) throws IOException {
        // Check the number of commandline arguments and validate the input.
        if (args.length != 1){
            System.out.println("Usage: java ConnectFourServer <port number>");
            return;
        }
        int port;
        try {
            port = Integer.parseInt(args[0]);
        } catch (NumberFormatException e){
            System.out.println("Invalid commandline input.");
            return;
        }
        // Constructed the object and start the server after input validation.
        ConnectFourServer main = new ConnectFourServer(port);
        main.run();

    }

}
