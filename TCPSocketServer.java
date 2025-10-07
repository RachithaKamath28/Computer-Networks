package tcpsocketserver;

import java.io.*;
import java.net.*;
public class TCPSocketServer {
    public static void main(String[] args)throws Exception{
         ServerSocket serverSocket=new ServerSocket(4000);
		 System.out.println("****Server Side****");
		 System.out.println("Server ready for connection");
		 Socket connSock=serverSocket.accept();
		 System.out.println("Connection is successful and ready for file transfer");
		 InputStream istream=connSock.getInputStream();
		 BufferedReader fileRead=new BufferedReader(new InputStreamReader(istream));
		 String fname=fileRead.readLine();
		 File fileName=new File(fname);
		 OutputStream ostream=connSock.getOutputStream();
		 PrintWriter pwrite=new PrintWriter(ostream,true);
		 if(fileName.exists()) {
			 BufferedReader contentRead=new BufferedReader(new FileReader(fname));
			 System.out.println("Writing file COntents to the socket");
			 String str;
			 while((str=contentRead.readLine())!=null)
			 {
				 pwrite.println(str);
			 }
			 contentRead.close();
		 }
		 else
		 {
			 System.out.println("Requested file does not exist");
			 String msg=("Requested file does not exist");
			 pwrite.println(msg);
		 }
		 connSock.close();
		 serverSocket.close();
		 fileRead.close();
                 pwrite.close();
    }
    
}
