package me.dedose.recaman.render;

import me.dedose.recaman.Main;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecamanSequence implements Sequence{

    @Override
    public int[] a_n(int steps) {
        if(steps < 1) throw new NumberFormatException("Steps must be at least 1!");
        int[] sequence = new int[steps + 1];
        sequence[0] = 0;
        for(int i = 1; i < steps + 1; i++){
            int back = sequence[i-1] - i;
            if(back > 0 && !contains(sequence, back)) sequence[i] = back;
            else sequence[i] = sequence[i-1] + i;
        }

        return sequence;
    }

    @Override
    public int a_n_exact(int steps) {
        int[] sequence = a_n(steps);
        return sequence[sequence.length-1];
    }

    private boolean contains(int[] array, int num){
        for (int i : array) {
            if(i == num) return true;
        }
        return false;
    }

    @Override
    public List<Shape> curves(int[] sequence, double bounds_x, double bounds_y) {
        List<Shape> curves = new ArrayList<>();
        for (int i = 0; i < sequence.length; i++) {
            double x = (Main.WIDTH - bounds_x)/2 + sequence[i]*50;
            double y = Main.HEIGHT/2;
            double width = (a_n_exact(i+1) - sequence[i])*50;
            double height = (i % 2 == 0 ? -Math.abs(width) : Math.abs(width)); // Alternate from down to up
            curves.add(new Arc2D.Double(width < 0 ? x + width : x, y - Math.abs(height)/2, Math.abs(width), Math.abs(height), 0, (height < 0) ? -180 : 180, Arc2D.OPEN));
        }

        return curves;
    }
}
