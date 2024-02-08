package states;

import clases.Constants;
import gameObjects.Chronometer;
import gameObjects.Coin;
import gameObjects.MovingObject;
import gameObjects.Player;
import graphics.Animation;
import graphics.Assets;
import graphics.Message;
import math.Vector2D;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

public class GameState extends State {

    private final Player player;
    public static final HashSet<MovingObject> movingObjects = new HashSet<>();
    public static final HashSet<Message> messages = new HashSet<>();
    private ArrayList<Animation> animations = new ArrayList<Animation>();

    private Chronometer gameOverTimer = new Chronometer();

    private boolean gameOver = false;

    private int score = 0;
    private static long lastCoinSpawned;

    public GameState() {
        player = new Player(new Point(50, 200), Assets.player, this);
        for (MovingObject mo : new HashSet<>(movingObjects)) {
            mo.remove();
        }
        movingObjects.add(player);
        generateCoin();
        lastCoinSpawned = System.currentTimeMillis();
    }

    public void addScore(int val) {
        score += val;
    }

    @Override
    public void update() throws FileNotFoundException {
        gameOverTimer.update();
        System.out.println(score);
        //if(Player.paused) GameState.changeState(new PauseState(this));
        if (gameOver && !gameOverTimer.isRunning()) {
            GameState.changeState(new MenuState());
        }
        player.update();
        collidesWith();

        for (MovingObject mo : new HashSet<>(movingObjects)) {
            mo.update();
            if(mo instanceof Coin && isCoinOutsideComponent(mo.getCenter())) movingObjects.remove(mo);
        }
        if (System.currentTimeMillis() - lastCoinSpawned > Constants.COIN_SPAWN_RATE) {
            generateCoin();
            lastCoinSpawned = System.currentTimeMillis();
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        player.draw(g);
        for (MovingObject mo : new HashSet<>(movingObjects)) mo.draw(g);
        for (Message m : new HashSet<>(messages)) m.draw((Graphics2D) g);
        drawScore(g);
    }

    private void drawScore(Graphics g) {
        Point p = new Point(700, 25);
        String scoreToString = Integer.toString(score);
        for (int i = 0; i < scoreToString.length(); i++) {
            g.drawImage(Assets.numbersImg[Integer.parseInt(scoreToString.substring(i, i + 1))],
                    (int) p.getX(), (int) p.getY(), null);
            p.setLocation(p.getX() + 20, p.getY());
        }
    }

    private boolean isCoinOutsideComponent(Point pos) {
        return pos.getX() < 0;
    }

    public HashSet<MovingObject> getMovingObjects() {
        return movingObjects;
    }

    protected void collidesWith() {
        HashSet<MovingObject> movingObjects = this.getMovingObjects();
        for (MovingObject mo : new HashSet<>(movingObjects)) {
            for (MovingObject mo1 : new HashSet<>(movingObjects)) {
                if (mo.equals(mo1)) continue;

                double distance = Math.sqrt(Math.pow(mo.getCenter().getX() - mo1.getCenter().getX(), 2) +
                        Math.pow(mo.getCenter().getY() - mo1.getCenter().getY(), 2));

                if (distance < (double) mo.width / 2 + (double) mo.height / 2) {
                    objectCollision(mo, mo1);
                }
            }
        }
    }

    private void objectCollision(MovingObject a, MovingObject b) {
        if(a instanceof Player && b instanceof Coin){
            addScore(1);
            b.remove();
        }
    }

    public void gameOver() {
        Message gameOverMsg = new Message(
                new Point(Constants.WIDTH / 2, Constants.HEIGHT / 2),
                true,
                "GAME OVER",
                Color.white,
                true
        );
        messages.add(gameOverMsg);
        gameOverTimer.run(Constants.GAME_OVER_TIME);
        gameOver = true;
    }

    private void generateCoin() {
        double x = Constants.WIDTH;
        double y = Math.random() * Constants.HEIGHT;
        movingObjects.add(new Coin(
                new Point((int) x, (int) y),
                new Vector2D(Constants.COIN_VELOCITY, 0),
                Assets.coinSprite, this));
    }
}
