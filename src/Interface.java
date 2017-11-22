import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends JFrame {

    public static JPanel top = new JPanel();
    private JTextField jtf;
    private JTextField jtf1;
    private JLabel label = new JLabel("Ip du serveur");
    private JLabel label1 = new JLabel("Login");
    private JButton b= new JButton("OK");;
    private JButton b1= new JButton("OK");;

    public static Thread t1;

    public Interface(){


        this.setTitle("Chat Java");
        this.setSize(300, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);


        jtf = new JTextField();
        jtf.setPreferredSize(new Dimension(150, 30));
        jtf.setForeground(Color.BLUE);

       // jtf1 = new JTextField();
        //jtf1.setPreferredSize(new Dimension(150, 30));
       //jtf1.setForeground(Color.BLUE);

        top.add(label);
        //top.add(label1);
        top.add(jtf);
        //top.add(jtf1);
        top.add(b);
        //top.add(b1);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ip = jtf.getText();
                Client c = new Client(ip);

            }
        });

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = jtf1.getText();
//                t1 = new Thread(new Connexion(clientSocket));
//                t1.start();

            }
        });



        this.setContentPane(top);
        this.setVisible(true);
    }
}
