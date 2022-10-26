package model;
import java.awt.event.*;
public class KeyboardListener implements KeyListener {
    public void keyTyped(KeyEvent e)
    {
        //System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());
    }

    public void keyPressed(KeyEvent e) 
    {
        System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());
    }

    public void keyReleased(KeyEvent e) 
    {
        //System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());
    }
}
