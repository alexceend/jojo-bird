package states;

import gameObjects.Sound;
import graphics.Assets;
import graphics.Text;
import ui.Action;
import ui.Button;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MenuState extends State{

    ArrayList<ui.Button> buttons = new ArrayList<ui.Button>();
    Sound bgMusic = new Sound(Assets.bgMusic);

    public MenuState(){
        buttons.add(new Button(
                Assets.greyButtonRec,
                Assets.blueButtRec,
                315,
                440,
                "PLAY",
                new Action() {
                    @Override
                    public void doAction() throws FileNotFoundException {
                        State.changeState(new GameState());
                    }
                }
        ));
        bgMusic.loop();
        bgMusic.changeVol(-20f);
        bgMusic.play();
    }
    @Override
    public void update() throws FileNotFoundException {
        for(ui.Button b : buttons){
            b.update();
        }
    }

    @Override
    public void draw(Graphics g) {
         g.drawImage(Assets.title, 190, 150, null);
         g.drawImage(Assets.info, 400 - Assets.info.getWidth()/2, 500, null);
        for(ui.Button b : buttons){
            b.draw(g);
        }
    }
}
