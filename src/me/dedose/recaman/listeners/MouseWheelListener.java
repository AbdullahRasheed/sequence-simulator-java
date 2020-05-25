package me.dedose.recaman.listeners;

import me.dedose.recaman.handlers.Handler;

import java.awt.event.MouseWheelEvent;

public class MouseWheelListener implements java.awt.event.MouseWheelListener {

    private Handler handler;
    public MouseWheelListener(Handler handler){
        this.handler = handler;
    }

    /**
     * Handles scaling with mouse wheel
     * @param e MWheelEvent
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int scrolls = e.getWheelRotation();
        handler.scaleFactor -= 0.05*scrolls;
        if(handler.scaleFactor < 0.05) handler.scaleFactor = 0.05;
    }
}
