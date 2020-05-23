package me.dedose.recaman.render;

import me.dedose.recaman.handlers.RenderObject;

import java.awt.*;
import java.awt.geom.Arc2D;

public class LineRender extends RenderObject {

    private Sequence sequence;
    public LineRender(Sequence sequence){
        this.sequence = sequence;
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.black);
        for (Arc2D.Double arc : sequence.curves(sequence.a_n(5), 800, 800)) {
            g.draw(arc);
        }
    }
}
