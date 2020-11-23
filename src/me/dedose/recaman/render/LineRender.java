package me.dedose.recaman.render;

import me.dedose.recaman.Main;
import me.dedose.recaman.handlers.Handler;
import me.dedose.recaman.handlers.RenderObject;

import java.awt.geom.Rectangle2D;
import java.util.List;
import java.awt.*;

public class LineRender extends RenderObject {

    private List<Shape> shapes;
    private Handler handler;
    private Rectangle2D renderBounds;
    public LineRender(Sequence sequence, int steps, Handler handler){
        this.shapes = sequence.curves(sequence.a_n(steps), Main.WIDTH-200, Main.HEIGHT-200);
        this.handler = handler;
        renderBounds = new Rectangle2D.Double(
                -handler.translateX,
                -handler.translateY,
                Main.WIDTH/handler.scaleFactor,
                Main.HEIGHT/handler.scaleFactor);
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(2));
        renderBounds.setFrame(
                -handler.translateX,
                -handler.translateY,
                Main.WIDTH/handler.scaleFactor,
                Main.HEIGHT/handler.scaleFactor
        );
        for (Shape arc : shapes) {
            if(arc.getBounds().intersects(renderBounds)) g.draw(arc);
        }
    }
}
