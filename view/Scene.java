package view;
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

import common.Observer;
import common.Recipient;
import common.Subject;
import model.ButtonPressListener;
import model.CoordinateSystem;
import model.Timeline;

import java.awt.Graphics;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.awt.MouseInfo;
import java.awt.Point;

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class Scene extends JPanel implements Observer, Recipient {
    // Attributes
    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;
    private int STEP_SIZE = 2;
    private JSlider zoom;
    private ArrayList<Sprite> sprites;
    private Timeline main_timeline;

    private CoordinateSystem coords;
    public boolean on = true;
    private boolean moving = false;
    private int MOUSE_START_X;
    private int MOUSE_START_Y;

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

        main_timeline = new Timeline(this);
        
        coords = new CoordinateSystem(this.SCREEN_WIDTH, this.SCREEN_HEIGHT);

        subject.register(this);
        subject.register(coords);


        this.pause_button.addActionListener(this.listener);
        
        debugger = new JLabel();
        this.add(debugger);
        this.add(pause_button);

        zoom = new JSlider();
        this.add(zoom);

        //this.setPreferredSize(new Dimension(this.SCREEN_WIDTH, this.SCREEN_HEIGHT));

        sprites = new ArrayList<Sprite>();
        sprites.add(new Sprite(this, 0, 0));
    } // end constructor

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        // --- BEGIN DRAWING COORDINATE SYSTEM ---
        // Query the coordinate system for where  things should be drawn.
        this.coords.updateDivisions();

        if(moving)
        {
            Point currentPosition = MouseInfo.getPointerInfo().getLocation();
            int compare_x = (int)currentPosition.getX();
            int compare_y = (int)currentPosition.getY();

            this.coords.updateCenterX(compare_x - this.MOUSE_START_X);
            this.coords.updateCenterY(compare_y - this.MOUSE_START_Y);

            this.MOUSE_START_X = compare_x;
            this.MOUSE_START_Y = compare_y;
        } // end if

        int x_0 = 0, x_1 = 0, y_0 = 0, y_1 = 0;

        x_0 = this.coords.getStartX();
        x_1 = this.coords.getEndX(x_0);
        y_0 = this.coords.getStartY();
        y_1 = this.coords.getEndY(y_0);

        for(int i = x_0; i < x_1; i++)
        {
            drawDivisionLines(i, 0, g);
        } // end for

        for(int i = y_0; i < y_1; i++)
        {
            drawDivisionLines(i, 1, g);
        } // end for

        for(int i = x_0; i < x_1; i++)
        {
            drawAxes(i, 0, g);
        } // end for

        for(int i = y_0; i < y_1; i++)
        {
            drawAxes(i, 1, g);
        } // end for

        /*
        g.setFont(new Font("Arial", Font.PLAIN, 30)); 
        g.drawString("" + i, cur_pos_x, this.Y_OFFSET);
        g.drawString("" + (-1 * i), cur_neg_x, this.Y_OFFSET);
        */

        // --- END DRAWING COORDINATE SYSTEM ---

        for (Sprite sprite : sprites) 
        {
            drawSprite(sprite, g);
        } // end for
        
        
        /* 

        // This adjusts the zoom, we are just incresing or decreasing the step size
        //this.STEP_SIZE = (int)(40 * ((double)zoom.getValue() / (double)50)) + 10;
        //System.out.println("Zoom value: " + this.STEP_SIZE);

        //coords.updateZoom((int)(40 * ((double)zoom.getValue() / (double)50)) + 10);

        for (Sprite sprite : sprites) 
        {
            sprite.update(time.beginning.until(time.finish, time.chronounit) * 0.001);
            coords.drawSprite(sprite, g);
        } // end for
        */



        //g.setColor(new Color(0xFF, 0xFF, 0xFF));
        //position += (int)(640 * delta_t * 0.001);
        
        //System.out.println("<" + position + ",50> @ " + (delta_t * 0.001));
        //int t = (int)(640 * time.beginning.until(time.finish, time.chronounit) * 0.001);
        //System.out.println("t = " + t);
        //g.fillOval(t / 2 % this.SCREEN_WIDTH, t / 2 % this.SCREEN_HEIGHT, 20, 20);
        //g.fillRect(75, this.position, 50, 50);

        
        
        //System.out.println("Repainting scene.");
    } // end paintComponent

    private void drawAxes(double global_unit, int axis, Graphics g)
    {
        int pixel_value = this.coords.convertAxes(global_unit, axis);
        if(pixel_value == -1)
        {
            return;
        } // end if

        if(global_unit == 0)
        {
            switch(axis){
                case 0:
                    g.fillRect(pixel_value, 0, 2, this.SCREEN_HEIGHT);
                    break;
                case 1:
                    g.fillRect(0, pixel_value, this.SCREEN_WIDTH, 2);
                    break;
                default:
                    break;
            } // end switch
            return;
        }

        switch(axis){
            case 0:
                g.drawLine(pixel_value, 0, pixel_value, this.SCREEN_HEIGHT);
                break;
            case 1:
                g.drawLine(0, pixel_value, this.SCREEN_WIDTH, pixel_value);
                break;
            default:
                break;
        } // end switch
    } // end drawAxes

    private void drawDivisionLines(int global_unit, int axis, Graphics g)
    {
        int pixel_value;
        int[] divisions = {0, 0, 0};

        pixel_value = this.coords.convertAxes(global_unit, axis);
        divisions = this.coords.getDivisions();

        g.setColor(new Color(166, 166, 166));
        switch(axis){
            case 0:
                for(int e : divisions)
                {
                    g.drawLine(pixel_value + e, 0, pixel_value + e, this.SCREEN_HEIGHT);
                } // end for
                break;
            case 1:
                for(int e : divisions)
                {
                    g.drawLine(0, pixel_value + e, this.SCREEN_WIDTH, pixel_value + e);
                } // end for
                break;
            default:
                break;
        } // end switch
        g.setColor(Color.BLACK);
    } // end drawDivisionLines

    private void drawSprite(Sprite s, Graphics g)
    {
        int x = 0, y = 0;
        s.update(this.coords.getTime());
        x = (int)(this.coords.convertPositionX(s.getPosition(0)));
        y = (int)(this.coords.convertPositionY(s.getPosition(1)));
        g.setColor(Color.BLUE);
        g.fillOval(x - 10, y - 10, 20, 20);
    } // end drawSprite

    public void update(Subject s)
    {
        this.SCREEN_WIDTH = s.getWidth();
        this.SCREEN_HEIGHT = s.getHeight();
        this.moving = s.getIsClicking();

        Point p = MouseInfo.getPointerInfo().getLocation();
        this.MOUSE_START_X = (int)p.getX();
        this.MOUSE_START_Y = (int)p.getY();
    }

    public void timesUp()
    {
        this.repaint();
    } // end timesUp

    public void end()
    {

    } // end end

    public void play()
    {
        this.coords.play();
        pause_button.setText("Pause");
    } // end play

    public void pause()
    {
        this.coords.pause();
        pause_button.setText("Play");
    } // end pause

    public void clear()
    {

    } // end clear

    public void toggleCursor()
    {

    } // end toggleCursor
} // end Scene