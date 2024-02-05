package clases;

import states.MenuState;
import states.State;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.FileNotFoundException;

public class Window extends JFrame implements Runnable {
    public static Window instance;
    public static final int WIDTH = 800, HEIGHT = 800;

    private final Canvas canvas = new Canvas();
    private Thread thread;
    private boolean running = false;

    private final int FPS = 60;
    private double delta = 0;
    private int AVERAGEFPS = FPS;

    public Window() {
        super.setTitle("Jojo Bird");
        super.setSize(WIDTH, HEIGHT);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setResizable(false);
        super.setLocationRelativeTo(null);
        super.setVisible(true);

        final Dimension d = new Dimension(WIDTH, HEIGHT);
        canvas.setPreferredSize(d);
        canvas.setMaximumSize(d);
        canvas.setMinimumSize(d);
        canvas.setFocusable(true); //Para recibir entradas con el teclado

        //addMouseListener

        super.add(canvas);
    }

    public static void main(String[] args) {
        instance = new Window();
        instance.start();
    }

    private void update() throws FileNotFoundException {
        State.getCurrentState().update();
    }

    private void draw() {
        BufferStrategy bs = canvas.getBufferStrategy();

        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.fillRect(0, 0, WIDTH, HEIGHT); //Limpiar pantalla a cada frame
        g.setColor(Color.white); //Fondo de color de pantalla
        g.fillRect(0, 0, WIDTH, HEIGHT);

        State.getCurrentState().draw(g);

        //FPS
        g.setColor(Color.black);
        g.drawString("" + AVERAGEFPS, 10, 20);


        g.dispose();
        bs.show();
    }

    private void init() {
        State.changeState(new MenuState());
    }

    @Override
    public void run() {
        long now;
        long lastTime = System.nanoTime();
        int frames = 0;
        long time = 0;

        init();

        while (running) {
            now = System.nanoTime();

            delta += (now - lastTime) / ((double) 1000000000 / FPS);
            time += (now - lastTime);
            lastTime = now;

            if(delta >= 1){
                try {
                    update();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                draw();
                delta--;
                frames++;
            }

            if(time >= 1000000000){
                AVERAGEFPS = frames;
                frames = 0;
                time = 0;
            }
        }
        stop();
    }

    private void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    private void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    public Canvas getCanvas() {
        return canvas;
    }

}
