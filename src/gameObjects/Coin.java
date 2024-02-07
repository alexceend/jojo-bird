package gameObjects;

import clases.Window;
import math.Vector2D;
import states.GameState;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Coin extends MovingObject{
    private final Canvas CANVAS = Window.instance.getCanvas();

    public Coin(Point center, Vector2D direction, BufferedImage texture, GameState gameState) {
        super(center, direction, texture, gameState);
        this.texture = texture;
    }

    @Override
    public void update() {
        center.translate((int)direction.getX(), (int)direction.getY());
    }

    @Override
    public void draw(Graphics g) {
        AffineTransform at = new AffineTransform();
        at.translate(center.getX() - (double) texture.getWidth() / 2, center.getY() - (double) texture.getHeight() / 2);
        if(gameState.getMovingObjects().contains(this)) ((Graphics2D) g).drawImage(texture, at, null);
    }
}
