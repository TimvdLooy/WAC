package practicum_5;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class MyServlet {
	private Socket socket;
	
	public MyServlet(Socket sock){
		socket = sock;
	}
	
	public void run() throws IOException{
		InputStream is = socket.getInputStream();
		
		 int c = is.read();
		    while (c != -1) {
		      System.out.print((char) c);
		      c = is.read();
		    }
		    frege
	}
}
