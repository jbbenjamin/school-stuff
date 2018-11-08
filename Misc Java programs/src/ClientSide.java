import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

public class ClientSide extends Thread{

    private static Socket mySocket;
    private static String hostName;
    private static String sendCmd;
    private static long timeStart;
    private static long timeStop;
    private static long elapsedTime;
    private CyclicBarrier barrier;
    private Thread	thread	= null;
    private int threadCount;
    private static int port;
    private static int clientCount = 1;
    public static ArrayList<ClientSide> clientList = new ArrayList<ClientSide>();
    public static ArrayList<Integer> timeList = new ArrayList<Integer>();
    public ClientSide(String cmd) {
        ClientSide.sendCmd = cmd;
    }//end constructor

    public static void main(String args[]) throws IOException, IOException, InterruptedException, BrokenBarrierException {
        System.out.println("Welcome to the Network Management Application");

        //attempt to connect to host
        System.out.println("attempting to connect to host");
        hostName = args[0];
        port = Integer.parseInt(args[1]);
        while (true) {
            MainMenu();	//pass the control to SwitchMenu()
        }
    }//end main()

    public void run() {
	System.out.println("Pass 1, port " + port);
	//int i = 0;
	//while (clientList.get(i).isAlive == true){
        try {
        	//thread = threadList.get(i);
        	if(clientCount > 1){
        	barrier.await();
        	}
            mySocket = new Socket(hostName, port); //create new socket
            System.out.println("Pass 2");

            //create output and input streams
            PrintWriter output = new PrintWriter(
                    mySocket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    mySocket.getInputStream()));

            System.out.println("\nRequesting the '" + sendCmd + "' from " + hostName);
            timerStart();		//start the timer
            output.println(sendCmd);	//send the command to the server side
            output.flush();		//clear the stream
            String results;

            while (!(results = input.readLine()).equals("!!!!") && results != null) //check for valid
            {
            	timerStop(); //stop the timer
            	System.out.println(this.getName()+ ": " + results); //server output
            }
            
            elapsedTime(); //and display the elapsed time			
            timeList.add((int)this.elapsedTime()); //add the time to the list of times (to be averaged later)
            
            input.close();
            output.close();
            mySocket.close();
	    

        /*try {
            this.join();
        } 
        catch (InterruptedException ex) {
            System.out.println(ex.toString());
        }*/
              
         
        System.out.println("The request took a total time of " + elapsedTime() + "ms to complete.");
    	}	// end try  
         
        catch (UnknownHostException e) {
            System.err.println("Don't know about host");
            System.exit(1);
        } 
        catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection");      
            System.exit(1); //  if (port > 9000) (if port 'is over 9000!!!!') System.exit(1);
        }
        catch (InterruptedException | BrokenBarrierException e) {
        	System.err.println("Interrupted.");
        	System.exit(1);
        }//end try, catch
       // i++;
	//}// end while
	//destroy();
    }//end run()

    //JB start() no longer needed, handled in userGo()
	  /*public void start(int count){  				//called by Client object  
		  int numberOfThreads = count;
		  for (int i = 0; i < numberOfThreads; i++){	 
			  Thread t = new Thread(this); 	//creates thread from Client object
			  threadList.add(t);
		  }
		  
		  Thread t = new Thread(this); 	//creates thread from Client object
		  t = null;
		  threadList.add(t);	//appends a null thread to the end of threadList
		  
		  //for (int i = 0; i < numberOfThreads; i++){	
			  threadList.get(0).run();				//thread.start() calls ClientSide.run()
		  //} 
			  float totalTime = 0;
			  for (int i = 0; i < count; i++){
				  totalTime = totalTime + timeList.get(i);
			  }
			  float meanTime = totalTime/count;
			  System.out.println("The total time for requests was " + totalTime + "ms.");
			  System.out.println("The average time for requests was " + meanTime + "ms.");
			  timeList.clear();
	  }*/
//-----------------------------------------------------------------------
	  /*public void stop(){   
	         thread = null;
	  }*/
	  	  
    //check for valid input (an integer from 1 to 7)
    public static boolean isValidInput(String num) {
        try {
            Integer.parseInt(num);
        } catch (NumberFormatException nfe) {
            return false;
        }
        if ((Integer.parseInt(num) > 8) || (Integer.parseInt(num) < 1)) {
            return false;
        }
        return true;
    }

    public static void MainMenu() {

        while (true) {
            System.out.println("Please make a selection:\n"
                    + "1. Host current Date and Time\n"
                    + "2. Host uptime\n"
                    + "3. Host memory use\n"
                    + "4. Host Netstat\n"
                    + "5. Host current users\n"
                    + "6. Host running processes\n"
                    + "7. Quit\n"
                    + "8. Change number of clients\n");
            Scanner input = new Scanner(System.in);
            String choice = input.nextLine();

            //if input is invalid, request new input
            if (isValidInput(choice) == false) {
                System.out.println("\'" + choice + "\'"
                        + " is not a valid selection\n");
            } else {
                char ch = choice.charAt(0);
                switch (ch) {
                    case '1': {
                        //runtime exec date
                        System.out.println("you enetered 1\n");
                        userGo("date", clientCount);
                        break;
                    }//end case '1'
                    case '2': {
                        System.out.println("you enetered 2\n");
                        userGo("uptime", clientCount);
                        break;
                    }//end case '2
                    case '3': {
                        System.out.println("you enetered 3\n");
                        userGo("vmstat", clientCount);
                        break;
                    }//end case '3'
                    case '4': {
                        System.out.println("you enetered 4\n");
                        userGo("netstat", clientCount);
                        break;
                    }//end case '4'
                    case '5': {
                        System.out.println("you enetered 5\n");
                        userGo("users", clientCount);
                        break;
                    }//end case '5'
                    case '6': {
                        System.out.println("you enetered 6\n");
                        userGo("ps", clientCount);
                        break;
                    }//end case '6'
                    case '7': {
                        System.out.println("Goodbye\n");
                        System.exit(0);  //close program
                        break;
                    }//end case '7'
                    case '8': {
                    	clientList.clear();
                    	timeList.clear();
                    	System.out.println("How many clients?");
                    	Scanner scan = new Scanner(System.in);
                    	clientCount = scan.nextInt();
                    	break;
                    }//end case '8'
                }//end switch
            }//end else if
        }//end while
    }//end MainMenu()

    public static void userGo(String cmd, int count) {
    	clientList.clear();	//clears the current arraylist of clients
        CyclicBarrier barrier = new CyclicBarrier(count);
    	
        clientList.ensureCapacity(count);
    	for (int i = 0; i < count; i++)	//makes 'count' number of clients. This can be set with input '8' in the main menu
    	{
        ClientSide theClient = new ClientSide(cmd); //create object of ClientSide
        theClient.setName("Client"+(i+1));
        theClient.barrier = barrier;
        clientList.add(i, theClient);	// add created object to the array list
    	}
    	System.out.println("The number of clients created was: " + clientList.size());
    	for (int i = 0; i < count; i++)	//
    	{
    		clientList.get(i).start();	//no longer goes to 'start()' method, instead automatically calls theClient.run()
        
    		/*try {
    			clientList.get(i).join();	//waits for current thread to die before doing the next iteration of the for loop
    		}
    		
    		catch (InterruptedException ex) {
    			System.out.println(ex.toString());
    		}*/
        
    	}
    	
    	
    	float totalTime = 0;
		  for (int i = 0; i < count; i++){	//after all threads have ran, add up the times stored in the arraylist 'timeList'
			  totalTime = totalTime + timeList.get(i);
		  }
		  float meanTime = totalTime/count;	//finds average time
		  System.out.println("The total time for requests was " + totalTime + "ms.");
		  System.out.println("The average time for requests was " + meanTime + "ms.");
		  timeList.clear();
    	
    }//end userGo()

    /**
     * returns the current system time.
     */
    public static void timerStart() {
        timeStart = System.currentTimeMillis();
    }//end timerStart()

    /**
     * returns the current system time.
     */
    public static void timerStop() {
        timeStop = System.currentTimeMillis();
    }//end timerStop()

    /**
     * calculates and returns the elapsed time.
     */
    public static long elapsedTime() {
        elapsedTime = timeStop - timeStart;
        return elapsedTime;
    }//end elapsedTime()
}
