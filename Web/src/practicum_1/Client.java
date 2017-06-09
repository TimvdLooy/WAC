package practicum_1;

import java.net.Socket;
import java.io.OutputStream;

class Client {
  public static void main(String[] arg) throws Exception {
    Socket s = new Socket("145.89.102.92", 4711);
    OutputStream os = s.getOutputStream();
    os.write("hello\n".getBytes());
    s.close();
  }
}
