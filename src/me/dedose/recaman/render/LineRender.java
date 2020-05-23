package me.dedose.recaman.render;

import me.dedose.recaman.Main;
import me.dedose.recaman.handlers.RenderObject;

import java.awt.*;
import java.awt.geom.Arc2D;

public class LineRender extends RenderObject {

    private Sequence sequence;
    public LineRender(Sequence sequence){
        this.sequence = sequence;
        for (Arc2D.Double arc : sequence.curves(sequence.a_n(12), 800, 800)) {
            System.out.println(arc.x + "/" + arc.y + "/" + arc.width + "/" + arc.height);
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.black);
        for (Arc2D.Double arc : sequence.curves(sequence.a_n(12), Main.WIDTH-200, Main.HEIGHT-200)) {
            g.draw(arc);
        }
    }
}
