import java.net.*;
import java.io.*;

public class Server implements Runnable {
	   
	  private Socket          socket    = null;
	  private ServerSocket    server    = null;
	  private DataInputStream streamIn  = null;
	  private String 		  TimeStamp = null;
	  private Thread		  thread	= null; 
	  public Server(int port, int numOfClients){  
		   try {  
			 System.out.println("Binding to port " + port + ", please wait  ...");
	         server = new ServerSocket(port, numOfClients);			//creates a ServerSocker object  
	         System.out.println("Server started: " + server);
	         System.out.println(server.getInetAddress());
	         start();
		   }
		   
		   catch(IOException ioe) {
		     System.out.println(ioe); 
		   }
	  }
	  
//-----------------------------------------------------------------------------------------
	  public void run(){
		  while (thread != null) {							//should run indefinitely because thread.stop is not called (so there will always be a thread).
			 try {
				 String inputLine, returnCode;				//"inputLine" is the command the client sends (and server receives) 
															//"returnCode" is what the server sends (and the client receives)
				
				 System.out.println("Waiting for a client ...");
				 socket = server.accept();					//creates a (client)socket object when server accepts a connection request from client
				 
				 TimeStamp = new java.util.Date().toString(); //not necessarily needed, I'm putting this here for testing purposes.
				 
				 System.out.println("Client accepted: " + socket);
				 open();
	         
				 BufferedOutputStream os = new BufferedOutputStream(socket.getOutputStream());
				 OutputStreamWriter osw = new OutputStreamWriter(os, "US-ASCII");	/*I'm using OutputStreamWriter because it's what one of the examples I looked at used.
	         																		The java tutorial used PrinterWriter, though I'm assuming either should work.*/
	         
				 returnCode = "SingleSocketServer accepted client at "+ TimeStamp;	//again, this and the next two lines are for testing purposes, they are not necessarily needed.
				 osw.write(returnCode);			//1st instance of "osw.write" tells client the time that the server accepted its request
				 osw.flush();
	         	         
				 while ((inputLine = streamIn.readUTF()) != null) {		//reads the input command from the client, as long as it doesn't send an empty string or the string "Quit"
					 //returnCode = responseHandling.processInput(inputLine);		//this section will be the interface that decides what response to give the client. I just put "responseHandling.processInput()" for now					
					 /*
	             	 ...
	             	 ...
	             	 ...
	             	 ...
	             	 */
					 osw.write(returnCode);	//writes back response to client
					 if (returnCode.equalsIgnoreCase("Quit")) //quits when server receives the string "Quit"				
	                 break;
				 }
	         
				 	close();	// closes current socket connection, (goes back up to 'while' loop) and listens for another client request
			 }
			 catch(IOException ioe){
				 System.out.println("Acceptance Error: " + ioe); 
			 }
		  }
	  } 
	  
//-----------------------------------------------------------------------	   
	  public void start(){  				//called by Server object
		  if(thread == null){  
			 thread = new Thread(this); 	//creates thread from Server object
			 thread.start();				//thread.start() calls Server.run()
	      }
	  } 	  

//-----------------------------------------------------------------------
	  public void stop(){  
		  if(thread != null){  
			 thread.stop(); 
	         thread = null;
	      }
	  }
	  	  
//-----------------------------------------------------------------------	  
	  public void open() throws IOException{
		   streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
	   }
	   
//----------------------------------------------------	   
	   public void close() throws IOException{
		  if (socket != null)    socket.close();
	      if (streamIn != null)  streamIn.close();
	   }
//----------------------------------------------------	   
	   public static void main(String args[]){
		  Server server = null;
	      if (args.length != 2)
	         System.out.println("Usage: java Server port");
	      else
	         server = new Server(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
	   }
}


