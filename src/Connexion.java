import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Connexion implements Runnable {

    private Socket socket = null;
    public static Thread t2;
    public static String pseudo = null;

    private PrintWriter out = null;
    private BufferedReader in = null;
    private Scanner sc = null;

    private boolean connect = false;

    public Connexion(Socket s){

        socket = s;
    }

    public void run() {

        try {

            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            sc = new Scanner(System.in);



            while(!connect ){

                System.out.println("ton pseudo :");
                pseudo = sc.nextLine();
                out.flush();

                if(pseudo == null){
                    System.err.println("Vos informations sont incorrectes ");
                }else {
                    System.out.println("Connecté en tant que: "+pseudo);
                    out.println("Sous le pseudo suivant : "+pseudo);
                    out.flush();
                    System.out.println("Ecrivez votre premier message:");
                    connect = true;
                }

            }

            t2 = new Thread(new Chat_ClientServeur(socket,pseudo));
            t2.start();

        } catch (IOException e) {

            System.err.println("Le serveur ne répond plus ");
        }


    }

}
