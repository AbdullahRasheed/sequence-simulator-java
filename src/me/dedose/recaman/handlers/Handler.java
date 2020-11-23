package me.dedose.recaman.handlers;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    public LinkedList<RenderObject> object = new LinkedList<>();
    public LinkedList<RenderObject> removePush = new LinkedList<>();

    public double scaleFactor = 1;
    public double translateX = 0;
    public double translateY = 0;

    public void render(Graphics2D g){
        g.translate(translateX, translateY);
        g.scale(scaleFactor, scaleFactor);
        for (RenderObject renderObject : object) {
            renderObject.render(g);
        }
    }

    public void addObject(RenderObject object){
        this.object.add(object);
    }

    public void removeObject(RenderObject object){
        this.removePush.add(object);
    }
}
