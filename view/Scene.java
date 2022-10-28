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
import model.KeyboardListener;
import model.Timeline;

import java.awt.Graphics;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.awt.MouseInfo;
import java.awt.Point;

public class Scene extends JPanel implements Observer, Recipient {
    // Attributes
    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;
    private int MOUSE_START_X;
    private int MOUSE_START_Y;
    private ArrayList<Sprite> sprites;
    private boolean moving = false;

    // Space and Time 
    private CoordinateSystem coords;
    private Timeline main_timeline;
    
    // Visual Assets and Listeners
    private JSlider zoom;
    private JButton play_pause_button = new JButton("Pause");
    public JLabel debugger;
    private ButtonPressListener listener = new ButtonPressListener(this);

    /* Misc:
     *     private int STEP_SIZE = 2;
     */

    // Methods
    public Scene(int width, int height, Subject subject)
    {
        System.out.println("Creating scene...");

        this.SCREEN_WIDTH = width;
        this.SCREEN_HEIGHT = height;
        this.setPreferredSize(new Dimension(this.SCREEN_WIDTH, this.SCREEN_HEIGHT));
        this.setBackground(Color.WHITE);

        // Timeline for the main scene (i.e. drawing a frame to the screen)
        main_timeline = new Timeline(this);
        
        coords = new CoordinateSystem(this.SCREEN_WIDTH, this.SCREEN_HEIGHT);

        subject.register(this);
        subject.register(coords);
        
        debugger = new JLabel();
        zoom = new JSlider();

        // Add visuals to the scene
        this.add(debugger);
        this.add(play_pause_button);
        this.add(zoom);

        this.play_pause_button.addActionListener(this.listener);

        // Test sprite(s)
        sprites = new ArrayList<Sprite>();
        sprites.add(new Sprite(50, 50));
        sprites.get(0).setImage("images/checkerboard.gif");
        subject.register(sprites.get(0));
    } // end constructor

    public void paintComponent(Graphics g){
        // Query the coordinate system for where things should be drawn.
        super.paintComponent(g);

        // --- BEGIN DRAWING COORDINATE SYSTEM ---
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

        // TODO: Fix how interval is choosen in x and y. Wonky when we are near the x- and y-axes!

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
            //drawSprite(sprite, g);
            sprite.draw(0.1, g);
        } // end for
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
                g.setColor(Color.WHITE);
                g.fillRect(pixel_value - 5, this.coords.getOffsetY() + 5, 10, 17);
                g.setColor(Color.BLACK);
                g.drawString("" + (int)global_unit, pixel_value - 4, this.coords.getOffsetY() + 18);
                break;
            case 1:
                g.drawLine(0, pixel_value, this.SCREEN_WIDTH, pixel_value);
                g.setColor(Color.WHITE);
                g.fillRect(this.coords.getOffsetX() - 20, pixel_value - 8, 17, 17);
                g.setColor(Color.BLACK);
                g.drawString("" + (int)global_unit, this.coords.getOffsetX() - 20, pixel_value + 5);
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
        s.update(this.coords.getDeltaTime());
        x = (int)(this.coords.convertPositionX(s.getPosition(0)));
        y = (int)(this.coords.convertPositionY(s.getPosition(1)));
        g.drawImage(s.getSpriteImageIcon().getImage(), x - (s.getWidth() / 2), y - (s.getHeight() / 2), s.getWidth(), s.getHeight(), s);
    } // end drawSprite

    public void update(Subject s)
    {
        this.SCREEN_WIDTH = s.getWidth();
        this.SCREEN_HEIGHT = s.getHeight();
        this.moving = s.getIsClicking();

        Point p = MouseInfo.getPointerInfo().getLocation();
        this.MOUSE_START_X = (int)p.getX();
        this.MOUSE_START_Y = (int)p.getY();
    } // end update

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
        play_pause_button.setText("Pause");
    } // end play

    public void pause()
    {
        this.coords.pause();
        play_pause_button.setText("Play");
    } // end pause

    // Clears all sprites from the screen
    public void clear()
    {

    } // end clear

    // Shows/Hides the cursor when it's over the scene
    public void toggleCursor()
    {

    } // end toggleCursor
} // end Scene