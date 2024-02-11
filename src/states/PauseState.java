package states;

import gameObjects.Player;
import gameObjects.Sound;
import graphics.Assets;
import ui.Action;
import ui.Button;

import java.awt.*;
import java.io.FileNotFoundException;

public class PauseState extends State{
    private Button returnButton, exitButton;
    private Sound pauseSound = new Sound(Assets.theWorld);

    public PauseState(GameState gameState){
        //return
        returnButton = new ui.Button(
                Assets.greyButtonRec,
                Assets.blueButtRec,
                325,
                440,
                "RETURN",
                new Action() {
                    @Override
                    public void doAction() {
                        Player.paused = false;
                        State.changeState(gameState);
                    }
                }
        );
        //exitButton
        exitButton = new ui.Button(
                Assets.greyButtonRec,
                Assets.blueButtRec,
                325,
                500,
                "EXIT GAME",
                new Action() {
                    @Override
                    public void doAction() {
                        Player.paused = false;
                        State.changeState(new MenuState());
                    }
                }
        );

        pauseSound.changeVol(-30f);
        pauseSound.play();

    }

    @Override
    public void update() throws FileNotFoundException {
        returnButton.update();
        exitButton.update();

        //if(pauseSound.getFramePosition() > 5000) pauseSound.stop();

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Assets.title, 190, 150, null);
        returnButton.draw(g);
        exitButton.draw(g);
    }
}