import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine {
    private ArrayList<Displayable> renderList = new ArrayList<>();
    private int frames = 0;
    private int fps = 0;

    public RenderEngine(ArrayList<Displayable> renderList) {
        this.renderList = renderList;

        // Timer qui met à jour le FPS toutes les secondes
        new Timer(1000, e -> {
            fps = frames;
            frames = 0;  // Réinitialise le compteur de frames
        }).start();
    }

    @Override
    public void update() {
        repaint();
    }

    public void setRenderList(ArrayList<Displayable> renderList) {
        this.renderList = renderList;
    }

    public void addToRenderList(Displayable displayable) {
        renderList.add(displayable);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Displayable displayable : renderList) {
            displayable.draw(g);
        }

        frames++;  // Incrémente le compteur de frames à chaque appel de paint

        // Affiche les FPS
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("FPS: " + fps, 10, 20); // Affichage du FPS en haut à gauche

    }
}
