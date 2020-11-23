package me.dedose.recaman.render;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.List;

public interface Sequence {

    /**
     * Sequence as a function of the amount of steps -> a(n)
     * @param steps Independent variable of the sequence -> n
     * @return all a(n) until n
     */
    int[] a_n(int steps);

    /**
     * Creates the shapes used to display the sequence
     * @param sequence array of numbers in order of the sequence, a_n(steps)
     * @param bounds_x Width of the display bounds
     * @param bounds_y Height of the display bounds
     * @return All shapes used to display the sequence
     */
    List<Shape> curves(int[] sequence, double bounds_x, double bounds_y);
}
