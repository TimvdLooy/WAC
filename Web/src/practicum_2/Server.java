package practicum_2;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.InputStream;

class Server {
  public static void main(String[] arg) throws Exception {
    ServerSocket ss = new ServerSocket(4711);
    Socket s = ss.accept();
    InputStream is = s.getInputStream();
    Scanner sc = new Scanner(is);

    System.out.println(sc.nextLine());
   
    s.close();
    ss.close();
  }
}