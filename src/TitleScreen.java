import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TitleScreen {
    private final Runnable Start;

   public TitleScreen(Runnable Start) {
      this.Start = Start;
   }

    public void show() {

        JFrame frame = new JFrame("Menu de jeu");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JButton startButton = new JButton("Commencer le jeu");
        startButton.addActionListener((ActionEvent e) -> {
            frame.dispose();


            Start.run();
        });

        frame.add(startButton);
        frame.setVisible(true);
    }
}
