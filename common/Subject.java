package common;
/**
 * Subject.java
 * 
 * 
 */

import java.awt.event.*;
import java.util.ArrayList;


public class Subject extends ComponentAdapter implements MouseListener {
    // Attributes
    private int COMPONENT_WIDTH;
    private int COMPONENT_HEIGHT;
    private boolean is_clicking;
    private ArrayList<Observer> clients;

    // Methods
    public Subject()
    {
        clients = new ArrayList<Observer>();
    } // end constructor

    public void register(Observer o)
    {
        clients.add(o);
    } // end register

    public void unregister(Observer o)
    {
        clients.remove(o);
    } // end unregister

    public int getWidth()
    {
        return COMPONENT_WIDTH;
    } // end getWidth

    public int getHeight()
    {
        return COMPONENT_HEIGHT;
    } // end getHeight
    
    public boolean getIsClicking()
    {
        return is_clicking;
    } // end getIsClicking

    public void componentResized(ComponentEvent e)
    {
        this.COMPONENT_WIDTH = e.getComponent().getWidth();
        this.COMPONENT_HEIGHT = e.getComponent().getHeight();
        notifyClients();
    } // end componentResized
    

    public void mouseEntered(MouseEvent e)
    {
        //System.out.println(e.getClickCount());
    }

    public void mouseClicked(MouseEvent e)
    {

    }
    public void mouseDragged(MouseEvent e)
    {

    }
    public void mouseExited(MouseEvent e)
    {

    }
    public void mouseMoved(MouseEvent e)
    {

    }
    public void mousePressed(MouseEvent e)
    {
        int x=e.getX();
        int y=e.getY();
        //System.out.println("Pressed @ " + x + "," + y);
        this.is_clicking = true;
        notifyClients();
    }
    public void mouseReleased(MouseEvent e)
    {
        int x=e.getX();
        int y=e.getY();
        //System.out.println("Released @ " + x + "," + y);
        this.is_clicking = false;
        notifyClients();
    }
    public void mouseWheelMoved(MouseWheelEvent e)
    {

    }

    private void notifyClients()
    {
        for(Observer client : clients)
        {
            client.update(this);
        } // end for
    } // end notifyClients
} // end Subject class
