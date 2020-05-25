package me.dedose.recaman.render;

import me.dedose.recaman.Main;
import me.dedose.recaman.handlers.Handler;
import me.dedose.recaman.handlers.RenderObject;

import java.awt.geom.Rectangle2D;
import java.util.List;
import java.awt.*;

public class LineRender extends RenderObject {

    private Sequence sequence;
    private List<Shape> shapes;
    private Handler handler;
    public LineRender(Sequence sequence, int steps, Handler handler){
        this.sequence = sequence;
        this.shapes = sequence.curves(sequence.a_n(steps), Main.WIDTH-200, Main.HEIGHT-200);
        this.handler = handler;
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(2));
        double scale = handler.scaleFactor;
        Rectangle2D.Double renderBounds = new Rectangle2D.Double(
                handler.translateX/scale,
                handler.translateX/scale,
                Main.WIDTH/scale,
                Main.HEIGHT/scale);
        for (Shape arc : shapes) {
            g.draw(arc);
            if(arc.getBounds().intersects(renderBounds)) g.draw(arc.getBounds());
        }
        g.draw(renderBounds);
        System.out.println(renderBounds);
    }
}
