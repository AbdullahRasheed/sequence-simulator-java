package me.dedose.recaman;

import me.dedose.recaman.handlers.Handler;
import me.dedose.recaman.handlers.RenderObject;
import me.dedose.recaman.listeners.MouseListener;
import me.dedose.recaman.listeners.MouseMotionListener;
import me.dedose.recaman.listeners.MouseWheelListener;
import me.dedose.recaman.render.LineRender;
import me.dedose.recaman.render.RecamanSequence;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable {

    /**
     * Initializing screen width and height
     */
    public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    /**
     * Thread state
     */
    private boolean running = false;

    /**
     * Locally defined classes
     */
    private Thread thread;
    private Handler handler;

    /**
     * Init
     */
    public Main(){
        this.handler = new Handler();
        this.addMouseListener(new MouseListener());
        this.addMouseWheelListener(new MouseWheelListener(handler));
        this.addMouseMotionListener(new MouseMotionListener(handler));

        new Window(WIDTH, HEIGHT, "Sequence Simulator", this);
        handler.addObject(new LineRender(new RecamanSequence(), 1000, handler));
    }

    /**
     * Start thread
     */
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    /**
     * Stop thread
     */
    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * Thread loop
     */
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta--;
            }
            if(running) render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    /**
     * Renders graphics
     */
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        g.dispose();
        bs.show();
    }

    /**
     * Clears removed objects from handler as a queue to avoid errors
     */
    private void tick(){
        for (RenderObject removePush : handler.removePush) {
            handler.object.remove(removePush);
        }
        handler.removePush.clear();
    }

    public static void main(String[] args){
        new Main();
    }
}
