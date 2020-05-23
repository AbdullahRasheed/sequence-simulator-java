package me.dedose.rechamann.render;

import java.awt.geom.Arc2D;
import java.util.List;

public interface Sequence {

    int[] a_n(int steps);

    List<Arc2D.Double> curves(int[] sequence, double bounds_x, double bounds_y);
}
