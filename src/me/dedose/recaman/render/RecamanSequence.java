package me.dedose.recaman.render;

import me.dedose.recaman.Main;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.*;
import java.util.List;

public class RecamanSequence implements Sequence{

    @Override
    public int[] a_n(int steps) {
        if(steps < 1) throw new NumberFormatException("Steps must be at least 1!");

        Set<Integer> process = new HashSet<>();
        int[] sequence = new int[steps + 1];
        sequence[0] = 0;
        for(int i = 1; i < steps + 1; i++){
            int num = sequence[i-1] - i;
            if(num > 0 && !process.contains(num)) sequence[i] = num;
            else {
                num = sequence[i-1] + i;
                sequence[i] = num;
            }
            process.add(num);
        }

        return sequence;
    }

    @Override
    public List<Shape> curves(int[] sequence, double bounds_x, double bounds_y) {
        List<Shape> curves = new ArrayList<>();
        for (int i = 0; i < sequence.length-1; i++) {
            double x = (Main.WIDTH - bounds_x)/2 + sequence[i]*50;
            double y = Main.HEIGHT/2;
            double width = (sequence[i+1] - sequence[i])*50;
            double height = (i % 2 == 0 ? -Math.abs(width) : Math.abs(width)); // Alternate from down to up
            curves.add(new Arc2D.Double(width < 0 ? x + width : x, y - Math.abs(height)/2, Math.abs(width), Math.abs(height), 0, (height < 0) ? -180 : 180, Arc2D.OPEN));
        }

        return curves;
    }
}
