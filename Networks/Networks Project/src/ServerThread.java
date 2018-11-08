import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {

    Socket clientSocket = null;

    public ServerThread(Socket clientSocket) {
        super("ServerThread");
        this.clientSocket = clientSocket;

    }

    public void run() {
        System.out.println("Waiting for command...");
        int threadCount = 0;

        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);//Create new outgoing and incoming communication streams
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine; //String to capture incoming, outgoing streams

            protocol p = new protocol(); // new protocol object

            while ((inputLine = in.readLine()) != null) {		//while the is input is true 		
                System.out.println("\nGetting command from Thread: " + "...");
                outputLine = p.runCommand(inputLine);		//process the incoming command
                out.println(outputLine);					//send out the results
            }//end while()

            clientSocket.close(); //close clientSocket, in and out streams						
            out.close();
            in.close();
        } catch (IOException ex) {
            System.err.println("error is in ServerThread.java.");
            System.exit(1);
        }//end try, catch

    }//end while()
    //close serverSocket
}//end main()
//end ServerSide
