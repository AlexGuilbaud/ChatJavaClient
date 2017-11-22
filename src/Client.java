import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static Thread t1;
    private String ip;
    private Scanner sc;

    public Client(String ip) {

        this.ip = ip;

        sc = new Scanner(System.in);
        final Socket clientSocket;
        final BufferedReader in;
        final PrintWriter out;


        try {
         /*
         * les informations du serveur ( port et adresse IP ou nom d'hote
         * 127.0.0.1 est l'adresse local de la machine
         */
            System.out.println("Demande de connexion");
            //ip = sc.nextLine();
            clientSocket = new Socket(ip,5000);
            System.out.println("Connexion établie avec le serveur, authentification :"); // Si le message s'affiche c'est que je suis connecté
            t1 = new Thread(new Connexion(clientSocket));
            t1.start();


        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
