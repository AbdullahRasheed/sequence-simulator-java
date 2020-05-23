package me.dedose.rechamann;

import me.dedose.rechamann.handlers.Handler;
import me.dedose.rechamann.handlers.RenderObject;
import me.dedose.rechamann.render.RecamannSequence;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Arrays;

public class Main extends Canvas implements Runnable {

    public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    private boolean running = false;

    private Thread thread;
    private Handler handler;

    public Main(){
        this.handler = new Handler();

        new Window(WIDTH, HEIGHT, "test", this);

        System.out.println(Arrays.toString(new RecamannSequence().a_n(12)));;
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

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

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics2D g = (Graphics2D) bs.getDrawGraphics();

        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        g.dispose();
        bs.show();
    }

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
