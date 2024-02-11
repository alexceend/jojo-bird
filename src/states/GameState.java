package states;

import clases.Constants;
import gameObjects.*;
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
    private Animation bg;

    private Chronometer gameOverTimer = new Chronometer();
    private Chronometer blockSpawnRate = new Chronometer();

    private boolean gameOver = false;

    public static int score = 0;
    public static int highscore = 0;
    private static long lastCoinSpawned;

    private Sound coinSound = new Sound(Assets.coinSound);
     private Sound deathSound = new Sound(Assets.deathSound);

    public GameState() {
        score = 0;
        player = new Player(new Point(150, 200), Assets.player, this);
        for (MovingObject mo : new HashSet<>(movingObjects)) {
            mo.remove();
        }
        movingObjects.add(player);
        generateCoin();
        lastCoinSpawned = System.currentTimeMillis();
        bg = new Animation(
                Assets.bg,
                75,
                new Point(Constants.WIDTH / 2, Constants.HEIGHT / 2)
        );
        coinSound.changeVol(-6f);
        deathSound.changeVol(6f);
        
    }

    public void addScore(int val) {
        score += val;
    }

    @Override
    public void update() throws FileNotFoundException {
        //gameOverTimer.update();
        blockSpawnRate.update();
        bg.update();
        //if(Player.paused) GameState.changeState(new PauseState(this));
        if (gameOver) {
            GameState.changeState(new GameOverState());
        }
        player.update();
        collidesWith();

        for (MovingObject mo : new HashSet<>(movingObjects)) {
            mo.update();
            if ((mo instanceof Coin || mo instanceof Block) && isObjOutsideComponent(mo.getCenter()))
                movingObjects.remove(mo);
        }

        for (Animation a : animations) {
            a.update();
        }
        if (System.currentTimeMillis() - lastCoinSpawned > Constants.COIN_SPAWN_RATE) {
            generateCoin();
            lastCoinSpawned = System.currentTimeMillis();
        }
        if (!blockSpawnRate.isRunning()) generateBlock();
        if(Player.paused){
            State.changeState(new PauseState(this));
        }
        if(coinSound.getFramePosition() > 5000) coinSound.stop();

    }

    @Override
    public void draw(Graphics g) {
        drawBG(g);
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

    private void drawBG(Graphics g) {
        g.drawImage(bg.getCurrentFrame(),0,0,null);
    }

    private boolean isObjOutsideComponent(Point pos) {
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

                if (distance < (double) mo.getTexture().getWidth() / 2 + (double) mo.getTexture().getHeight() / 2) {
                    objectCollision(mo, mo1);
                }

                Rectangle rectangle1 = mo.getBoundingBox();
                Rectangle rectangle2 = mo1.getBoundingBox();

                if (rectangle1.intersects(rectangle2) && !(mo.equals(mo1))) {
                    objectCollision(mo, mo1);
                }
            }
        }
    }

    private void objectCollision(MovingObject a, MovingObject b) {
        if (a instanceof Player && b instanceof Coin) {
            addScore(1);
            b.remove();
            coinSound.play();
        }
        if (a instanceof Player && b instanceof Block) {
            deathSound.play();
            gameOver();
        }
    }

    public void gameOver() {
        gameOverTimer.run(Constants.GAME_OVER_TIME);
        gameOver = true;
        if(highscore < score) highscore = score;
    }

    private void generateCoin() {
        double x = Constants.WIDTH;
        double y = Math.random() * Constants.HEIGHT;
        movingObjects.add(new Coin(
                new Point((int) x, (int) y),
                new Vector2D(Constants.COIN_VELOCITY, 0),
                Assets.coinSprite, this));
    }

    private void generateBlock() {
        blockSpawnRate.run(3500);
        double x = Constants.WIDTH;
        double y = (Math.random() * (Constants.HEIGHT - Constants.MINIMUM_BLOCK_SPACE)) - (double) Assets.blockSprite.getHeight() / 2;
        double y2 = y + Constants.MINIMUM_BLOCK_SPACE + (double) Assets.blockSprite.getHeight();
        movingObjects.add(new Block(
                new Point((int) x, (int) y),
                new Vector2D(Constants.BLOCK_VELOCITY, 0),
                Assets.blockDown, this));

        movingObjects.add(new Block(
                new Point((int) x, (int) y2),
                new Vector2D(Constants.BLOCK_VELOCITY, 0),
                Assets.blockUp, this));
    }
}
