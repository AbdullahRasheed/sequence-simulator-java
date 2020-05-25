package me.dedose.recaman.listeners;

import me.dedose.recaman.handlers.Handler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MouseMotionListener extends MouseMotionAdapter {

    private Handler handler;
    public MouseMotionListener(Handler handler){
        this.handler = handler;
    }

    /**
     * Handles graphics translations
     * @param e MouseEvent
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if(MouseListener.ANCHOR_POINT == null) return;
        int x = e.getX();
        int y = e.getY();
        int[] anchor = MouseListener.ANCHOR_POINT;
        handler.translateX += x - anchor[0];
        handler.translateY += y - anchor[1];
        anchor[0] = x;
        anchor[1] = y;
    }
}
