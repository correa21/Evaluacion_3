package iteso.game;


import javax.swing.JFrame;

public class Window extends JFrame {

    public static final long serialVersionUID = 1L;
    private Board game;

    public Window() {

        
        this.setTitle("Juego Perron");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(720,280);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        game = new Board();
        this.pack();
        this.getContentPane().add(game);
        //game.
    }

}
