package me.dedose.recaman.render;

import java.awt.geom.Arc2D;
import java.util.List;

public interface Sequence {

    int[] a_n(int steps);

    int a_n_exact(int steps);

    List<Arc2D.Double> curves(int[] sequence, double bounds_x, double bounds_y);
}
