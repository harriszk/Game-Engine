/**
 * CoordinateSystem.java
 * 
 * 
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;

public class CoordinateSystem implements Observer {
    // Attributes
    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;
    private int UNIT_CONVERSION = 100;

    private int X_OFFSET;
    private int Y_OFFSET;

    private int MOUSE_START_X;
    private int MOUSE_START_Y;
    
    private int[] divisions = {0, 0, 0};

    Color d = Color.LIGHT_GRAY;
    Color axes = Color.BLACK;

    boolean moving = false;

    // Methods
    public CoordinateSystem()
    {
        this.X_OFFSET = 640 / 2;
        this.Y_OFFSET = 480 / 2;
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

        // x-axis
        // -> divisions left and right
        // --> 1/3 divisions between those
        // y-axis
        // -> repeat same as x

        //System.out.println("Global Center (Relative to frame): (" + this.X_OFFSET + " , " + this.Y_OFFSET + ")");

        if(moving)
        {
            //System.out.println("Start @ " + this.MOUSE_START_X + " , " + this.MOUSE_START_Y);
            Point p = MouseInfo.getPointerInfo().getLocation();
            //System.out.println("Cursor @ " + p.getX() + " , " + p.getY());
            int compare_x = (int)p.getX();
            int compare_y = (int)p.getY();
            //System.out.println("Difference: " + (compare_x - this.MOUSE_START_X) + " , " + (compare_y - this.MOUSE_START_Y));

            this.X_OFFSET = this.X_OFFSET + (compare_x - this.MOUSE_START_X);
            this.Y_OFFSET = this.Y_OFFSET + (compare_y - this.MOUSE_START_Y);
            this.MOUSE_START_X = (int)p.getX();
            this.MOUSE_START_Y = (int)p.getY();

        }

        
        updateDivisions(UNIT_CONVERSION / 2);


        int x_0 = (int)Math.floor(-1 * this.X_OFFSET / this.UNIT_CONVERSION);
        if(x_0 < 0)
        {
            x_0--;
        }

        int x_1 = (this.SCREEN_WIDTH / this.UNIT_CONVERSION) + x_0 + 2;

        for(int i = x_0; i < x_1; i++)
        {
            drawAxes(i, 0, g);
        }

        int y_0 = (int)Math.floor((this.Y_OFFSET - this.SCREEN_HEIGHT) / this.UNIT_CONVERSION);
        int y_1 = (this.SCREEN_HEIGHT / this.UNIT_CONVERSION) + y_0 + 1;

        for(int i = y_0; i < y_1; i++)
        {
            drawAxes(i, 1, g);
        }

        /*
        int stopx = (this.SCREEN_WIDTH / (2 * this.UNIT_CONVERSION)) + 2;
        int stopy = (this.SCREEN_HEIGHT / (2 * this.UNIT_CONVERSION)) + 2;


        int stop = stopx > stopy ? stopx : stopy;



        for(int i = 1; i < stop; i++)
        {
            int cur_pos_x = this.X_OFFSET + (this.UNIT_CONVERSION * i);
            int cur_neg_x = this.X_OFFSET + (this.UNIT_CONVERSION * -1 * i);

            int cur_pos_y = this.Y_OFFSET + (this.UNIT_CONVERSION * i);
            int cur_neg_y = this.Y_OFFSET + (this.UNIT_CONVERSION * -1 * i);
            g.setColor(d);
            for(int e : divisions)
            {
                g.drawLine(cur_pos_x - e, 0, cur_pos_x - e, this.SCREEN_HEIGHT);
                g.drawLine(cur_neg_x + e, 0, cur_neg_x + e, this.SCREEN_HEIGHT);
                g.drawLine(0, cur_pos_y - e, this.SCREEN_WIDTH, cur_pos_y - e);
                g.drawLine(0, cur_neg_y + e, this.SCREEN_WIDTH, cur_neg_y + e);
            }
        }

        g.setFont(new Font("Arial", Font.PLAIN, 30)); 

        for(int i = 1; i < stop; i++)
        {
            int cur_pos_x = this.X_OFFSET + (this.UNIT_CONVERSION * i);
            int cur_neg_x = this.X_OFFSET + (this.UNIT_CONVERSION * -1 * i);

            int cur_pos_y = this.Y_OFFSET + (this.UNIT_CONVERSION * i);
            int cur_neg_y = this.Y_OFFSET + (this.UNIT_CONVERSION * -1 * i);
            g.setColor(axes);
            g.drawLine(cur_pos_x, 0, cur_pos_x, this.SCREEN_HEIGHT);
            g.drawLine(cur_neg_x, 0, cur_neg_x, this.SCREEN_HEIGHT);
            g.drawLine(0, cur_pos_y, this.SCREEN_WIDTH, cur_pos_y);
            g.drawLine(0, cur_neg_y, this.SCREEN_WIDTH, cur_neg_y);

            g.drawString("" + i, cur_pos_x, this.Y_OFFSET);
            g.drawString("" + (-1 * i), cur_neg_x, this.Y_OFFSET);
        }
        */
        
        /*
        g.drawLine(this.X_OFFSET, 0, this.X_OFFSET, this.SCREEN_HEIGHT);
        g.drawLine(0, this.Y_OFFSET, this.SCREEN_WIDTH, this.Y_OFFSET);
        */

        g.fillRect(this.X_OFFSET, 0, 2, this.SCREEN_HEIGHT);
        g.fillRect(0, this.Y_OFFSET, this.SCREEN_WIDTH, 2);
        plotPoint(0.25, 0.75, g);
        plotPoint(1, 0.5, g);
    } // end drawCoords

    private void drawAxes(double global_unit, int axis, Graphics g)
    {
        int x, y;
        switch(axis){
            case 0:
                x = (int)(this.X_OFFSET + (this.UNIT_CONVERSION * global_unit));
                y = 0;
                g.drawLine(x, y, x, this.SCREEN_HEIGHT);
                break;
            case 1:
                x = 0;
                y = (int)(this.Y_OFFSET + (this.UNIT_CONVERSION * -1 * global_unit));
                g.drawLine(x, y, this.SCREEN_WIDTH, y);
                break;
            default:
                break;
        }
    }

    private void plotPoint(double x, double y, Graphics g)
    {
        g.setColor(Color.BLACK);
        if(x == 0)
        {
            g.setColor(Color.RED);
        }
        g.fillOval((int)(this.X_OFFSET + (this.UNIT_CONVERSION * x) - 5), (int)(this.Y_OFFSET + (this.UNIT_CONVERSION * -1 * y) - 5), 10, 10);
        g.setColor(Color.BLACK);
    }

    private void updateDivisions(int value)
    {
        this.divisions[0] = value;
        this.divisions[1] = (value / 2);
        this.divisions[2] = this.divisions[0] + this.divisions[1];
    }

    private void drawDivisionLines()
    {

    }
} // end CoordinateSystem 