import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
public class GameEngine implements Engine, KeyListener {
    private DynamicSprite hero;

    public GameEngine(DynamicSprite hero) {
        this.hero = hero;
    }
    @Override
    public void update() {
    }@Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            hero.setRunning(true);
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            hero.setDirection(Direction.WEST);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            hero.setDirection(Direction.EAST);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            hero.setDirection(Direction.NORTH);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            hero.setDirection(Direction.SOUTH);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            hero.setRunning(false);
        }
    }
}
