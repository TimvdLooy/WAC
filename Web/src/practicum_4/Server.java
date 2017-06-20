package practicum_4;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.InputStream;
 
public class Server {
  public static void main(String[] arg) throws Exception {
    ServerSocket s = new ServerSocket(4711);
    while (true) {
        new MyServlet(s.accept()).start();
    }
  }
}
