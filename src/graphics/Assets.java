package graphics;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage player, coinSprite, blockSprite, blockUp, blockDown, title, info;

    public static BufferedImage[] bg = new BufferedImage[12];
    public static BufferedImage orangeButtSQ, blueButtRec, greyButtonRec, greenCheckButt, redcrossButt, bluecrossButt, bluecheckButt, greyButtonSQ;

    public static BufferedImage[] numbersImg = new BufferedImage[10];

    public static Font titleFontBig, titleFontMedium, titleFontTiny;
    public static Font normalFontBig, normalFontMedium, normalFontTiny;

    public static Clip explosion, coinSound, theWorld, bgMusic;

    public static void init(){
        player = Loader.ImageLoader("/playerr.png");
        coinSprite = Loader.ImageLoader("/coin.png");
        blockSprite = Loader.ImageLoader("/block.png");
        blockUp = Loader.ImageLoader("/blockup.png");
        blockDown = Loader.ImageLoader("/blockdown.png");
        title = Loader.ImageLoader("/title.png");
        info = Loader.ImageLoader("/info.png");

        for(int i = 0; i < bg.length; i++){
            bg[i] = Loader.ImageLoader("/bg/" + i + ".png");
        }

        orangeButtSQ = Loader.ImageLoader("/ui/orangeb1.png");
        greyButtonSQ = Loader.ImageLoader("/ui/grey_button07.png");
        blueButtRec = Loader.ImageLoader("/ui/blueb1.png");
        greyButtonRec = Loader.ImageLoader("/ui/greyb1.png");
        greenCheckButt = Loader.ImageLoader("/ui/green_boxCheckmark.png");
        redcrossButt = Loader.ImageLoader("/ui/red_boxCross.png");
        bluecrossButt = Loader.ImageLoader("/ui/blue_boxCross.png");
        bluecheckButt = Loader.ImageLoader("/ui/blue_boxCheckmark.png");

        explosion = Loader.loadSound("/sounds/explosion.wav");
        coinSound = Loader.loadSound("/sounds/coin.wav");
        theWorld = Loader.loadSound("/sounds/pause.wav");
        bgMusic = Loader.loadSound("/sounds/bg.wav");
        deathSound = Loader.loadSound("/sounds/death.wav");

        titleFontBig = Loader.loadFont("/fonts/vermin.otf", 42);
        titleFontMedium = Loader.loadFont("/fonts/vermin.otf", 20);
        titleFontTiny = Loader.loadFont("/fonts/vermin.otf", 13);
        normalFontBig = Loader.loadFont("/fonts/BubblyFrog.ttf", 42);
        normalFontMedium = Loader.loadFont("/fonts/BubblyFrog.ttf", 20);
        normalFontTiny = Loader.loadFont("/fonts/BubblyFrog.ttf", 13);

        for(int i = 0; i < numbersImg.length; i++){
            numbersImg[i] = Loader.ImageLoader("/numbers/" + i + ".png");
        }
    }
}
