package states;

import graphics.Assets;
import ui.Action;
import ui.Button;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MenuState extends State{

    ArrayList<ui.Button> buttons = new ArrayList<ui.Button>();

    public MenuState(){
        /*buttons.add(new Button(
                Assets.greyButtonRec,
                Assets.blueButtRec,
                100,
                100,
                "HOLA",
                new Action() {
                    @Override
                    public void doAction() throws FileNotFoundException {

                    }
                }
        ));*/
    }
    @Override
    public void update() throws FileNotFoundException {
        for(ui.Button b : buttons){
            b.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawString("HOLA", 200,200);
        g.drawImage(Assets.player, 100, 100, null);
        for(ui.Button b : buttons){
            b.draw(g);
        }
    }
}
