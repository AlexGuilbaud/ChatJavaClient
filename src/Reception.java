import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Reception implements Runnable,Cypher {

    private BufferedReader in;
    private String message = null;

    public Reception(BufferedReader in){

        this.in = in;
    }

    public void run() {

        while(true){
            try {


                message = in.readLine();
                //System.out.println(message);
                //System.out.println("Key = "+Emission.KeyCrypt);

                String[] parts = message.split(":");
                String part1 = parts[0]; // pseudo
                String part2 = parts[1]; // message
                String part3 = parts[2]; // message
                System.out.println(part1+":" + this.decodeAES128(part3, part2));


            }catch (SocketException e1){
                System.out.println("Server disconnected");
                System.exit(0);
            } catch (IOException e) {

            }
        }
    }

    @Override
    public String encodeAES128(String m) {
        return null;
    }

    @Override
    public String decodeAES128(String key, String encodedmsg) {
        try {
            Key k = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE, k);
            byte[] decodedValue = Base64.getDecoder().decode(encodedmsg);
            byte[] decValue = c.doFinal(decodedValue);
            String decryptedValue = new String(decValue);
            return decryptedValue;
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Emission.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Emission.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Emission.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Emission.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Emission.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
