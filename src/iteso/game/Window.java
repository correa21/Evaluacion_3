package iteso.game;


import javax.swing.JFrame;

public class Window extends JFrame {

    public static final long serialVersionUID = 1L;


    public Window() {

        add(new Board());
        setTitle("Juego Perron");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(720,280);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

}
