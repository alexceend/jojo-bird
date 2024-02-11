package states;

import gameObjects.Sound;
import graphics.Assets;
import graphics.Text;
import ui.Action;
import ui.Button;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.HashSet;

public class GameOverState extends State{

    HashSet<Button> buttons = new HashSet<>();
    private Sound deathSound = new Sound(Assets.deathSound);


    public GameOverState(){
        buttons.add(new Button(
                Assets.greyButtonRec,
                Assets.blueButtRec,
                350 - Assets.greyButtonSQ.getWidth(),
                200,
                "PLAY AGAIN",
                new Action() {
                    @Override
                    public void doAction() throws FileNotFoundException {
                        State.changeState(new GameState());
                    }
                }
        ));

        buttons.add(new Button(
                Assets.greyButtonRec,
                Assets.blueButtRec,
                350 - Assets.greyButtonSQ.getWidth(),
                250,
                "MENU",
                new Action() {
                    @Override
                    public void doAction() throws FileNotFoundException {
                        State.changeState(new MenuState());
                    }
                }
        ));

        deathSound.play();
    }
    @Override
    public void update() throws FileNotFoundException{
        for(Button b : buttons){
            b.update();
        }

    }

    @Override
    public void draw(Graphics g) {
        for(Button b : buttons){
            b.draw(g);
        }
        Text.drawText(g,"HIGHSCORE:", new Point(365,370), true, Color.black, Assets.titleFontMedium);
        Point p = new Point(390, 390);
        String highscoreToString = Integer.toString(GameState.highscore);
        for (int i = 0; i < highscoreToString.length(); i++) {
            g.drawImage(Assets.numbersImg[Integer.parseInt(highscoreToString.substring(i, i + 1))],
                    (int) p.getX(), (int) p.getY(), null);
            p.setLocation(p.getX() + 20, p.getY());
        }

        Text.drawText(g,"SCORE:", new Point(380,450), true, Color.black, Assets.titleFontMedium);
        Point p2 = new Point(390, 470);
        String scoreToString = Integer.toString(GameState.score);
        for (int i = 0; i < scoreToString.length(); i++) {
            g.drawImage(Assets.numbersImg[Integer.parseInt(scoreToString.substring(i, i + 1))],
                    (int) p2.getX(), (int) p2.getY(), null);
            p2.setLocation(p2.getX() + 20, p2.getY());
        }
    }
}
