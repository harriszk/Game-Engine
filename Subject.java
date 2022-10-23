/**
 * Subject.java
 * 
 * 
 */

import java.awt.event.*;
import java.util.ArrayList;

public class Subject extends ComponentAdapter {
    // Attributes
    private int COMPONENT_WIDTH;
    private int COMPONENT_HEIGHT;
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

    public void componentResized(ComponentEvent e)
    {
        this.COMPONENT_WIDTH = e.getComponent().getWidth();
        this.COMPONENT_HEIGHT = e.getComponent().getHeight();
        notifyClients();
    } // end componentResized

    private void notifyClients()
    {
        for(Observer client : clients)
        {
            client.update(this);
        } // end for
    } // end notifyClients
} // end Subject class
