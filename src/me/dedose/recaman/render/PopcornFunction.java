package me.dedose.recaman.render;

import me.dedose.recaman.Main;
import me.dedose.recaman.handlers.RenderObject;

import java.awt.*;

public class PopcornFunction extends RenderObject {

    private static final double SHRINK = 0.8;
    private static final int SIZE = 50;
    private static final int DETAIL = 500;

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.WHITE);
        for (int q = 1; q < DETAIL; q++) {
            for (int p = 1; p < q; p++) {
                if (gcd(p, q) == 1) {
                    g.fillOval(Main.WIDTH / q * p, Main.HEIGHT - Main.WIDTH / q, (int) (SIZE / (Math.pow(q, SHRINK))), (int) (SIZE / (Math.pow(q, SHRINK))));
                }
            }
        }
    }

    private int gcd(int x1, int x2) {
        while (x1 != x2) {
            if (x1 > x2) x1 -= x2;
            else x2 -= x1;
        }
        return x2;
    }
}
