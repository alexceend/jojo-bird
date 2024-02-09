package gameObjects;

import gameObjects.GameObject;
import math.Vector2D;
import states.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class MovingObject extends GameObject {
    protected Vector2D direction;
    protected GameState gameState;
    public int width;
    public int height;

    public MovingObject(Point center, Vector2D direction, BufferedImage texture, GameState gameState) {
        super(center, texture);
        this.direction = direction;
        this.gameState = gameState;
        height = texture.getHeight();
        width = texture.getWidth();
    }

    public Rectangle getBoundingBox() {
        return new Rectangle((int) center.getX() - texture.getWidth()/ 2,
                (int) center.getY() - texture.getHeight() / 2,
                texture.getWidth(),
                texture.getHeight());
    }

    public BufferedImage getTexture() {
        return texture;
    }
}
