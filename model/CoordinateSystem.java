package model;
/**
 * CoordinateSystem.java
 * 
 * 
 */
import javax.swing.JPanel;
import view.Scene;

import common.Observer;
import common.Recipient;
import common.Subject;
import view.Sprite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;

public class CoordinateSystem implements Observer, Recipient {
    // Attributes
    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;
    private int UNIT_CONVERSION = 100;
    private Scene scene;
    private Timeline sprite_timeline;

    private int X_OFFSET;
    private int Y_OFFSET;

    private int MOUSE_START_X;
    private int MOUSE_START_Y;
    
    private int[] divisions = {0, 0, 0};

    Color d = Color.LIGHT_GRAY;
    Color axes = Color.BLACK;

    boolean moving = false;

    // Methods
    public CoordinateSystem(Scene s)
    {
        this.scene = s;
        this.X_OFFSET = 640 / 2;
        this.Y_OFFSET = 480 / 2;
        sprite_timeline = new Timeline(this);
    } // end constructor

    public void update(Subject s)
    {
        this.SCREEN_WIDTH = s.getWidth();
        this.SCREEN_HEIGHT = s.getHeight();
        //this.X_OFFSET = this.SCREEN_WIDTH / 2;
        //this.Y_OFFSET = this.SCREEN_HEIGHT / 2;
        this.moving = s.getIsClicking();
        Point p = MouseInfo.getPointerInfo().getLocation();
        this.MOUSE_START_X = (int)p.getX();
        this.MOUSE_START_Y = (int)p.getY();
    }
    
    public void drawCoords(Graphics g)
    {
        // This adjusts the zoom, we are just incresing or decreasing the step size
        // this.STEP_SIZE = (int)(40 * ((double)zoom.getValue() / (double)50)) + 10;

        //System.out.println("Global Center (Relative to frame): (" + this.X_OFFSET + " , " + this.Y_OFFSET + ")");

        /* 
        if(moving)
        {
            System.out.println("Start @ " + this.MOUSE_START_X + " , " + this.MOUSE_START_Y);
            Point p = MouseInfo.getPointerInfo().getLocation();
            System.out.println("Cursor @ " + p.getX() + " , " + p.getY());
            int compare_x = (int)p.getX();
            int compare_y = (int)p.getY();
            //System.out.println("Difference: " + (compare_x - this.MOUSE_START_X) + " , " + (compare_y - this.MOUSE_START_Y));

            this.X_OFFSET = this.X_OFFSET + (compare_x - this.MOUSE_START_X);
            this.Y_OFFSET = this.Y_OFFSET + (compare_y - this.MOUSE_START_Y);
            this.MOUSE_START_X = (int)p.getX();
            this.MOUSE_START_Y = (int)p.getY();
        }
        */

        //updateDivisions(UNIT_CONVERSION / 2);




        /*
        g.setFont(new Font("Arial", Font.PLAIN, 30)); 
        g.drawString("" + i, cur_pos_x, this.Y_OFFSET);
        g.drawString("" + (-1 * i), cur_neg_x, this.Y_OFFSET);
        */

        g.fillRect(this.X_OFFSET, 0, 2, this.SCREEN_HEIGHT);
        g.fillRect(0, this.Y_OFFSET, this.SCREEN_WIDTH, 2);
        //plotPoint(0, 0, 2, g);
    } // end drawCoords

    public void updateZoom(int value)
    {
        this.UNIT_CONVERSION = value;
    }

    public void drawSprite(Sprite s, Graphics g)
    {
        int x, y;
        x = (int)(this.X_OFFSET + (this.UNIT_CONVERSION * s.getPosition(0)));
        y = (int)(this.Y_OFFSET + (this.UNIT_CONVERSION * -1 * s.getPosition(1)));
        g.fillOval(x - 6, y - 6, 12, 12);
    } // end drawSprite

    public int convertAxes(double global_unit, int axis)
    {
        int position = -1;
        switch(axis){
            case 0:
                position = (int)(this.X_OFFSET + (this.UNIT_CONVERSION * global_unit));
                break;
            case 1:
                position = (int)(this.Y_OFFSET + (this.UNIT_CONVERSION * -1 * global_unit));
                break;
            default:
                break;
        } // end switch
        return position;
    } // end convertAxes

    public int convertPositionX(int x)
    {
        int converted = -1;

        converted = (int)(this.X_OFFSET + (this.UNIT_CONVERSION * x));
        // - (UNIT_CONVERSION / 2));

        return converted;
    } // end convertPositionX

    public int convertPositionY(int y)
    {
        int converted = -1;

        converted = (int)(this.Y_OFFSET + (this.UNIT_CONVERSION * -1 * y));
        // - (UNIT_CONVERSION / 2));

        return converted;
    } // end convertPositionY

    private void plotPoint(double x, double y, int size, Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillOval((int)(this.X_OFFSET + (this.UNIT_CONVERSION * x) - (size * UNIT_CONVERSION / 2)), (int)(this.Y_OFFSET + (this.UNIT_CONVERSION * -1 * y) - (size * UNIT_CONVERSION / 2)), size * UNIT_CONVERSION, size * UNIT_CONVERSION);
        g.setColor(Color.BLACK);
    } // end plotPoint

    public void updateDivisions() 
    {
        this.divisions[0] = this.UNIT_CONVERSION / 2;
        this.divisions[1] = (this.UNIT_CONVERSION / 4);
        this.divisions[2] = this.divisions[0] + this.divisions[1];
    } // end updateDivisions

    public void timesUp()
    {
        System.out.println("" + sprite_timeline.delta_t);
    } // end timesUp

    public void paintComponent(Graphics g)
    {
        drawCoords(g);
    }

    public int[] getDivisions()
    {
        return this.divisions;
    }

    public int getStartX()
    {
        int x_0 = (int)Math.floor(-1 * this.X_OFFSET / this.UNIT_CONVERSION);
        if(x_0 < 0)
        {
            x_0--;
        }
        return x_0;
    }

    public int getEndX(int x_0)
    {
        return (this.SCREEN_WIDTH / this.UNIT_CONVERSION) + x_0 + 2;
    }

    public int getStartY()
    {
        return (int)Math.floor((this.Y_OFFSET - this.SCREEN_HEIGHT) / this.UNIT_CONVERSION);
    }

    public int getEndY(int y_0)
    {
        return (this.SCREEN_HEIGHT / this.UNIT_CONVERSION) + y_0 + 1;
    }

    public void updateCenterX(int dx)
    {
        this.X_OFFSET += dx;
    }

    public void updateCenterY(int dy)
    {
        this.Y_OFFSET += dy;
    }

    public void play()
    {
        sprite_timeline.play();
    }

    public void pause()
    {
        sprite_timeline.pause();
    }
} // end CoordinateSystem 