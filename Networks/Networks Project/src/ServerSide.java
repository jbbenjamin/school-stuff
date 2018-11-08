import java.net.*;
import java.io.*;

public class ServerSide {

    public static void main(String[] args) throws IOException {
        int portNum = Integer.parseInt(args[0]);
        System.out.println("\nServer running, waiting on connection from Client...\n");
        ServerSocket serverSocket = null;
        boolean alive = true;
        try {
            serverSocket = new ServerSocket(portNum);
		 System.out.println("Port num " + portNum + " at host " +  InetAddress.getLocalHost().getHostAddress());
 //create new socket			
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + portNum);
           if (portNum > 9000)  System.exit(1);
            else portNum++;
        }//end try, catch

        int threadCount = 0;
        while (alive) { //continue to run as long as the server is active
            new ServerThread(serverSocket.accept()).start(); //accept each connection coming and execute thread
            System.out.println("Ready to run thread number" + "..." + ++threadCount);

        }
        serverSocket.close(); //close Server Socket 
    }
}
