import java.io.*;
import java.net.*;
import java.util.*;

public class protocol {

    private Socket socket;
    private int identify;

    public String runCommand(String cmd) throws IOException {

        String results = null;

        System.out.println("Please hold tight, I am sending results for" + "-" + cmd + "...");

        StringBuilder sb = new StringBuilder();

        if (cmd != null) { //check for an empty input
            Process p = Runtime.getRuntime().exec(cmd); //execute the command
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            PrintWriter output = new PrintWriter(p.getOutputStream(), true);

            while ((results = input.readLine()) != null) {
                sb.append(results + "\n"); //put the output into the string format that can be displayed to the user
            }
            String s2 = sb.toString();
            System.out.println(s2); //print out results
        }//end if()
        sb.append("!!!!"); //delimeter to end while loop on ClientSide
        return sb.toString(); //return results to Server class 
    }//end runCommand()
}//end protocol
