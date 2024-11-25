import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.awt.Image;
public class DynamicSprite extends SolidSprite {
    private boolean isWalking = true;
    private boolean isRunning = false;
    private double speed = 5.0;
    private double runningSpeed = 10.0;
    private final int spriteSheetNumberOfColumn = 10;
    private int timeBetweenFrame = 200;
    private Direction direction = Direction.EAST;


    public DynamicSprite(Image image, double x, double y, double width, double height) {
        super(image, x, y, width, height);
    }


    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
        if (isRunning) {
            speed = runningSpeed;
        } else {
            speed = 5.0;
        }
    }

    private void move() {
        switch (direction) {
            case NORTH -> this.y -= speed;
            case SOUTH -> this.y += speed;
            case EAST -> this.x += speed;
            case WEST -> this.x -= speed;
        }
    }

    private boolean isMovingPossible(ArrayList<Sprite> environment) {
        Rectangle2D.Double hitBox = new Rectangle2D.Double(x, y, width, height);
        switch (direction) {
            case NORTH -> hitBox.y -= speed;
            case SOUTH -> hitBox.y += speed;
            case WEST -> hitBox.x -= speed;
            case EAST -> hitBox.x += speed;
        }
        for (Sprite sprite : environment) {
            if (sprite instanceof SolidSprite && sprite != this) {
                Rectangle2D.Double otherHitBox = new Rectangle2D.Double(sprite.x, sprite.y, sprite.width, sprite.height);
                if (hitBox.intersects(otherHitBox)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void moveIfPossible(ArrayList<Sprite> environment) {
        if (isMovingPossible(environment)) {
            move();
        }
    }

    @Override
    public void draw(Graphics g) {
        int index = (int) (System.currentTimeMillis() / timeBetweenFrame % spriteSheetNumberOfColumn);
        g.drawImage(image, (int) x, (int) y, (int) (x + width), (int) (y + height),
                (int) (index * this.width), (int) (direction.getFrameLineNumber() * height),
                (int) ((index + 1) * this.width), (int) ((direction.getFrameLineNumber() + 1) * this.height), null);
    }
}
