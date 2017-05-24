package DES;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author miche
 */

public class DES {

    protected String message;
    protected static SecretKey key;
    protected final String publicKey;
    protected Cipher desCipher;
    
    public DES(){
        publicKey = "michaelrafat";
    }

    
    protected Key generateKey() throws Exception {
        
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        DESKeySpec dESKeySpec = new DESKeySpec(publicKey.getBytes()); 
        key = keyFactory.generateSecret(dESKeySpec);
        
        return key;
    }
    
    protected String encryptMessage(String message) throws Exception {
        
        // Create the cipher
        desCipher = Cipher.getInstance("DES");
        // Initialize the cipher for encryption        
        desCipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] messageBytes = message.getBytes("UTF8");
        
        byte[] raw = desCipher.doFinal(messageBytes);
        //encode messaage by base 64 for better encryption
        BASE64Encoder encoder = new BASE64Encoder();
        String base64 = encoder.encode(raw);
        
        return base64;
    }

    protected String decryptMessage(String encryptedMessage) throws Exception {
        
        // Create the cipher
        desCipher = Cipher.getInstance("DES");
        // Initialize the cipher for decryption        
        desCipher.init(Cipher.DECRYPT_MODE, key);
        
        //decode the Base64 coded message
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] raw = decoder.decodeBuffer(encryptedMessage);

        //decode the message
        byte[] stringBytes = desCipher.doFinal(raw);
        
        //convert the decoded message to a String
        String msg = new String(stringBytes, "UTF8");
 
        return msg;
    }
}