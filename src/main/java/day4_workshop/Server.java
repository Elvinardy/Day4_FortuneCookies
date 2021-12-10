package day4_workshop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    public static void main(String[] args) throws IOException {
        
        ServerSocket ss = new ServerSocket(3066);
        Socket s = ss.accept();
        String inputFile = "Cookies.txt";
        
        if(args != null && args.length >=1)
        inputFile = args[0];
        else {
            System.out.println("You didn't provide the file to "+ "read the cookies. Will try to read from default file");
        }
        
        System.out.println("Connecting Client.. listening port 3066..");

        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter pr = new PrintWriter(s.getOutputStream(), true); //if true, no need to include .flush() for the writer anymore.
        String response = br.readLine();
        
        while(!"exit".equals(response) && null != response) {
        
            System.out.println("Client: " + response);

        try {
        
        if("get-cookie".equals(response)) {
            System.out.println("Sending a cookie.. ");
            pr.println("cookie-text " + new Cookie().getCookie());
            pr.flush();
            response = br.readLine();
        }
        else {
            pr.println("Server: you said " + response);
            pr.flush();
            response = br.readLine();
        }
     }  catch(IOException e) {
            e.printStackTrace();
            break;
    }
        
    }
    s.close();
    ss.close();

    }

}
