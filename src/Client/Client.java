package Client;

import DES.DES;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


/**
 *
 * @author miche
 */

class Client extends DES {
    
    private static String decryptedMessage;
    private static String encryptedMessage;
    
    public static void main(String[] args) throws Exception {
         
        Client c = new Client();

        try{
            Scanner scanner = new Scanner(System.in);

            Socket s = new Socket("localhost", 8080);

            System.out.println("Please Enter Message : ");

            c.message = scanner.nextLine();
            
            c.generateKey();
            
            encryptedMessage = c.encryptMessage(c.message);
            
            PrintStream pr = new PrintStream(s.getOutputStream());
            
            pr.println(encryptedMessage);
            
            System.out.println("Encrypted Message : " + encryptedMessage);
                                 
        } catch(Exception e){
            e.getStackTrace();
        }    
    }
}