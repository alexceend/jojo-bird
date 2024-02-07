package gameObjects;

import clases.Constants;
import clases.Window;
import input.Mouse;
import math.Vector2D;
import states.GameState;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends MovingObject {
    private final Canvas canvas = Window.instance.getCanvas();
    private boolean vivo = true;
    public static boolean paused = false;

    public Player(Point center, BufferedImage texture, GameState gameState) {
        super(center, new Vector2D(0, 1), texture, gameState);
        this.texture = texture;
    }


    @Override
    public void update() {
        if (Mouse.CLICKING && this.center.getY() > 0) {
            center.setLocation(center.getX(), center.getY() - Constants.PLAYER_VELOCITY);
        } else if (this.center.getY() < canvas.getHeight()) {
            center.setLocation(center.getX(), center.getY() + Constants.PLAYER_VELOCITY);
        }
    }

    @Override
    public void remove() {
        super.remove();
        gameState.gameOver();
    }

    @Override
    public void draw(Graphics g) {
        AffineTransform at = new AffineTransform();
        at.translate(center.getX() - (double) texture.getWidth() / 2, center.getY() - (double) texture.getHeight() / 2);
        if (gameState.getMovingObjects().contains(this)) ((Graphics2D) g).drawImage(texture, at, null);
    }
}
