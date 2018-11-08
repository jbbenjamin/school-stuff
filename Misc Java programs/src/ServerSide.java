import java.net.*;
import java.io.*;

public class ServerSide {

    public static void main(String[] args) throws IOException {

        System.out.println("\nServer running, waiting on connection from Client...\n");
        ServerSocket serverSocket = null;
        boolean alive = true;
        try {
            serverSocket = new ServerSocket(Integer.parseInt(args[0])); //create new socket			
        } catch (IOException e) {
            System.err.println("Could not listen on port: " +serverSocket.getLocalPort());
            System.exit(1);
        }//end try, catch

        int threadCount = 0;
        while (alive) { //continue to run as long as the server is active
            new ServerThread(serverSocket.accept()).start(); //accept each connection coming and execute thread
            System.out.println("Ready to run thread number" + "..." + ++threadCount);

        }
        serverSocket.close(); //close Server Socket 
    }
}
