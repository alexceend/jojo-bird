package states;

import ui.Button;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MenuState extends State{

    ArrayList<ui.Button> buttons = new ArrayList<ui.Button>();
    @Override
    public void update() throws FileNotFoundException {
        for(ui.Button b : buttons){
            b.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        for(ui.Button b : buttons){
            b.draw(g);
        }
    }
}
