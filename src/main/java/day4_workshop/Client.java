package day4_workshop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("Creating client socket.. ");
        Socket s = new Socket("localhost",3066);
        
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter pr = new PrintWriter(s.getOutputStream(), true);
        System.out.println("Connected to localhost: 3066");
        System.out.println("You want a cookie..?");

        Scanner scanner = new Scanner(System.in);
        String str;
        String response = scanner.nextLine();

        while(!"exit".equals(response)) {
            pr.println(response);
            pr.flush();
            str = br.readLine();
            System.out.println(str);
            response = scanner.nextLine();

            /*pr.println(str);
            if(!str.equals("exit")) {
                response = br.readLine();
                System.out.println(response); */
            }
            s.close();
            scanner.close();
        }
}
