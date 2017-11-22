
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Emission implements Runnable,Cypher {

    public static String pseudo;
    private PrintWriter out;
    private String message = null;
    private String keeppseudo = null;
    private Scanner sc = null;
    public static String encryptionKey;
    public static String KeyCrypt;
    public static String key;

    public Emission(PrintWriter out,String p) {
        this.out = out;
        pseudo = p;


    }


    public void run() {

        sc = new Scanner(System.in);
        keeppseudo = pseudo;

        while(true){
            message = sc.nextLine();
            //out.println(pseudo+":"+message);
            //System.out.println("psueudo1 ="+keeppseudo);
            Emission t = new Emission(out,message);
            String encrypt = t.encodeAES128(message);

           /* System.out.println("message:" +message);
            System.out.println("encrypted value:" +encrypt);
            System.out.println("decrypted value:" + t.decode(t.encryptionKey, encrypt));*/
            out.println(keeppseudo+":"+encrypt+":"+key);
            out.flush();
        }

    }

    @Override
    public String encodeAES128(String value) {

        try {

            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);
            // Generate the secret key specs.
            SecretKey skey = kgen.generateKey();
            byte[] raw = skey.getEncoded();
            key = Base64.getEncoder().encodeToString(raw);
            this.encryptionKey = key;
            //System.out.println("------------------Key------------------");
            //System.out.println(key);
            //System.out.println("--------------End of Key---------------");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            String encrypt =  Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes()));
            return encrypt;
        }catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Emission.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Emission.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Emission.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Emission.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Emission.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String decodeAES128(String key,String encodedmsg) {
            return null;
    }
}
