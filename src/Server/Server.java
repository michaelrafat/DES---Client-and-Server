package Server;

import DES.DES;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author miche
 */

class Server extends DES{

    private static String encryptedMessage;
    private static String decryptedMessage;

    public static void main(String[] args) throws Exception {

        
        Server ser = new Server();

        try { 
            ServerSocket s1 = new ServerSocket(8080);

            Socket s = s1.accept();

            Scanner sc = new Scanner(s.getInputStream());

            encryptedMessage = sc.nextLine();

            ser.generateKey();

            decryptedMessage = ser.decryptMessage(encryptedMessage);

            PrintWriter p = new PrintWriter(s.getOutputStream());

            p.println("Decrypted Message : " + decryptedMessage);

            System.out.println("Decrypted Message : " + decryptedMessage); 

            s1.close();
            
         } catch (Exception e) {
            e.getStackTrace();
         }
    }
}