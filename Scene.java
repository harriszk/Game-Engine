/**
 * Scene.java
 * 
 * This class is the canvas to which we shall draw an image.
 * We may offload the drawing of sprites to the sprites themselves
 * but the order in which object are colored is done here. 
 * 
 * Responsible for space and time.
 */

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class Scene extends JPanel implements Observer {
    // Attributes
    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;
    private int STEP_SIZE = 2;
    private JSlider zoom;
    private ArrayList<Sprite> sprites;
    private Timeline time;
    private CoordinateSystem coords;
    boolean on = true;

    private Subject subject;

    private ButtonPressListener listener = new ButtonPressListener(this);

    public JLabel debugger;
    private JButton pause_button = new JButton("Pause");
    int counter = 0;

    
    private int position = 0;

    // Methods
    public Scene(int width, int height, Subject subject)
    {
        System.out.println("Creating scene...");

        this.SCREEN_WIDTH = width;
        this.SCREEN_HEIGHT = height;
        this.subject = subject;
        this.setBackground(new Color(145, 151, 156));

        time = new Timeline(this);
        coords = new CoordinateSystem();

        subject.register(coords);


        this.pause_button.addActionListener(this.listener);
        
        debugger = new JLabel();
        this.add(debugger);
        this.add(pause_button);

        zoom = new JSlider();
        this.add(zoom);

        //this.setPreferredSize(new Dimension(this.SCREEN_WIDTH, this.SCREEN_HEIGHT));

        /* 
        sprites = new ArrayList<Sprite>();
        sprites.add(new Sprite(this, 50, 200));
        sprites.add(new Sprite(this, 25, 25));
        */

        //play();
    } // end constructor

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        coords.drawCoords(g);
        
        /*
        for(int i = 1; i < 9; i++)
        {
            int cur_x = (this.SCREEN_WIDTH / 2) + (40 * i);
            System.out.println("UNITS: " + cur_x);
            g.setColor(Color.BLACK);
            g.drawLine(cur_x, 0, cur_x, this.SCREEN_HEIGHT);
            g.setColor(Color.RED);
            for(int j = 1; j < 4; j++)
            {
                cur_x = (int)(cur_x - (40 * 0.25));
                System.out.println("" + cur_x);
                g.drawLine(cur_x, 0, cur_x, this.SCREEN_HEIGHT);
            }
        }
        */


        /*
        for (Sprite sprite : sprites) 
        {
            sprite.draw(g);
        } // end for
        */

        //g.setColor(new Color(0xFF, 0xFF, 0xFF));
        //position += (int)(640 * delta_t * 0.001);
        
        //System.out.println("<" + position + ",50> @ " + (delta_t * 0.001));
        int t = (int)(640 * time.beginning.until(time.finish, time.chronounit) * 0.001);
        //System.out.println("t = " + t);
        g.fillOval(t / 2 % 640, t / 2 % 640, 20, 20);
        //g.fillRect(75, this.position, 50, 50);

        
        
        //System.out.println("Repainting scene.");
    }
    
    

    public void update(Subject s)
    {
        this.SCREEN_WIDTH = s.getWidth();
        this.SCREEN_HEIGHT = s.getHeight();
    }

    public void end()
    {

    } // end end

    public void play()
    {
        time.play();
    } // end play

    public void pause()
    {
        time.pause();
    } // end pause

    public void clear()
    {

    } // end clear

    public void toggleCursor()
    {

    } // end toggleCursor
} // end Scene