package graphics;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage player;
    public static BufferedImage orangeButtSQ, blueButtRec, greyButtonRec, greenCheckButt, redcrossButt, bluecrossButt, bluecheckButt, greyButtonSQ;

    public static Font titleFontBig, titleFontMedium, titleFontTiny;
    public static Font normalFontBig, normalFontMedium, normalFontTiny;

    public static Clip explosion, playerShoot;

    public static void init(){
        player = Loader.ImageLoader("/player/player.png");
        orangeButtSQ = Loader.ImageLoader("/ui/orangeb1.png");
        greyButtonSQ = Loader.ImageLoader("/ui/grey_button07.png");
        blueButtRec = Loader.ImageLoader("/ui/blueb1.png");
        greyButtonRec = Loader.ImageLoader("/ui/greyb1.png");
        greenCheckButt = Loader.ImageLoader("/ui/green_boxCheckmark.png");
        redcrossButt = Loader.ImageLoader("/ui/red_boxCross.png");
        bluecrossButt = Loader.ImageLoader("/ui/blue_boxCross.png");
        bluecheckButt = Loader.ImageLoader("/ui/blue_boxCheckmark.png");

        explosion = Loader.loadSound("/sounds/explosion.wav");
        playerShoot = Loader.loadSound("/sounds/playerShoot.wav");

        titleFontBig = Loader.loadFont("/fonts/TOADFROG.ttf", 42);
        titleFontMedium = Loader.loadFont("/fonts/TOADFROG.ttf", 20);
        titleFontTiny = Loader.loadFont("/fonts/TOADFROG.ttf", 13);
        normalFontBig = Loader.loadFont("/fonts/BubblyFrog.ttf", 42);
        normalFontMedium = Loader.loadFont("/fonts/BubblyFrog.ttf", 20);
        normalFontTiny = Loader.loadFont("/fonts/BubblyFrog.ttf", 13);
    }
}
