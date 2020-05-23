package me.dedose.recaman.render;

import me.dedose.recaman.Main;

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
    public List<Arc2D.Double> curves(int[] sequence, double bounds_x, double bounds_y) {
        List<Arc2D.Double> curves = new ArrayList<>();
        double totalWidth = 0;
        double maxHeight = 0;
        for (int i = 0; i < sequence.length; i++) {
            double x = (Main.WIDTH - bounds_x)/2 + sequence[i]*50;
            double y = Main.HEIGHT/2;
            double width = (a_n_exact(i+1) - sequence[i])*50;
            totalWidth += width;
            double height = (i % 2 == 0 ? -width : width);
            maxHeight = Math.max(height, maxHeight);
            curves.add(new Arc2D.Double(x, y, width, Math.abs(height), 0, (height < 0) ? -180 : 180, Arc2D.OPEN));
        }

        double xRatio = totalWidth > bounds_x ? bounds_x/totalWidth : 1;
        double yRatio = maxHeight > bounds_y ? bounds_y/maxHeight : 1;
        for (Arc2D.Double curve : curves) {
            curve.x *= xRatio;
            curve.width *= xRatio;
            curve.y *= yRatio;
            curve.height *= yRatio;
        }

        return curves;
    }
}
