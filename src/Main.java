import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    JFrame displayZoneFrame;
    RenderEngine renderEngine;
    DynamicSprite hero;
    PhysicEngine physicEngine;
    GameEngine gameEngine;


    public Main() throws Exception {
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(400, 600);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        renderEngine = new RenderEngine(new ArrayList<>());
        hero = new DynamicSprite(ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 200, 300, 48, 50);
        gameEngine = new GameEngine(hero);
        physicEngine = new PhysicEngine();
        Timer renderTimer = new Timer(50, (time) -> renderEngine.update());
        Timer gameTimer = new Timer(50, (time) -> gameEngine.update());
        Timer physicTimer = new Timer(50, (time) -> physicEngine.update());
        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);
        displayZoneFrame.addKeyListener(gameEngine);

        PlayGround level = new PlayGround("./data/level1.txt");


        for (Displayable sprite : level.getSpriteList()) {
            renderEngine.addToRenderList(sprite);
        }
        renderEngine.addToRenderList(hero);

        physicEngine.addMovingSprite(hero);
        physicEngine.setEnvironment(level.getSolidSpriteList());
        displayZoneFrame.addKeyListener(gameEngine);

    }


           public static void main(String[] args) {

                   TitleScreen titleScreen = new TitleScreen(() -> {
                       try {
                           new Main();
                       } catch (Exception ex) {

                       }
                   } );
                   titleScreen.show();

           }

}
