package me.dedose.recaman.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {

    public static int[] ANCHOR_POINT = null;

    /**
     * Resets anchor point
     * @param e MouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        ANCHOR_POINT = null;
    }

    /**
     * Sets anchor point for translation
     * @param e MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() != MouseEvent.BUTTON1) return;
        ANCHOR_POINT = new int[]{e.getX(), e.getY()};
    }
}
