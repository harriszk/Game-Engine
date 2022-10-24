/**
 * CoordinateSystem.java
 * 
 * 
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

public class CoordinateSystem implements Observer {
    // Attributes
    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;
    private int UNIT_CONVERSION = 100;

    private int X_OFFSET;
    private int Y_OFFSET;
    
    private int[] divisions = {0, 0, 0};

    Color d = Color.LIGHT_GRAY;
    Color axes = Color.BLACK;

    // Methods
    public CoordinateSystem()
    {

    } // end constructor

    public void update(Subject s)
    {
        this.SCREEN_WIDTH = s.getWidth();
        this.SCREEN_HEIGHT = s.getHeight();
        this.X_OFFSET = this.SCREEN_WIDTH / 2;
        this.Y_OFFSET = this.SCREEN_HEIGHT / 2;
    }
    
    public void drawCoords(Graphics g)
    {
        // x-axis
        // -> divisions left and right
        // --> 1/3 divisions between those
        // y-axis
        // -> repeat same as x

        updateDivisions(UNIT_CONVERSION / 2);

        int stopx = (this.SCREEN_WIDTH / (2 * this.UNIT_CONVERSION)) + 2;
        int stopy = (this.SCREEN_HEIGHT / (2 * this.UNIT_CONVERSION)) + 2;
        int stop = stopx > stopy ? stopx : stopy;

        

        /*
        for(int i = 1; i < stopx; i++)
        {
            int cur_pos_x = this.X_OFFSET + (this.UNIT_CONVERSION * i);
            int cur_neg_x = this.X_OFFSET + (this.UNIT_CONVERSION * -1 * i);
            //System.out.println("UNITS: " + cur_pos_x);
            g.setColor(axes);
            g.drawLine(cur_pos_x, 0, cur_pos_x, this.SCREEN_HEIGHT);
            g.drawLine(cur_neg_x, 0, cur_neg_x, this.SCREEN_HEIGHT);
            g.setColor(d);
            for(int e : divisions)
            {
                g.drawLine(cur_pos_x - e, 0, cur_pos_x - e, this.SCREEN_HEIGHT);
                g.drawLine(cur_neg_x + e, 0, cur_neg_x + e, this.SCREEN_HEIGHT);
                //System.out.println(cur_pos_x - e);
            }
        }
        */

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

        g.drawLine(this.X_OFFSET, 0, this.X_OFFSET, this.SCREEN_HEIGHT);
        g.drawLine(0, this.Y_OFFSET, this.SCREEN_WIDTH, this.Y_OFFSET);
    } // end drawCoords

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


class Node {    
    int value;
    Node left, right;
          
    Node(int value)
    {
        this.value = value; 
        left = null; 
        right = null; 
    } // end constructor
}



/*
public void drawCoords(Graphics g)
    {
        // This adjusts the zoom, we are just incresing or decreasing the step size
        this.STEP_SIZE = (int)(40 * ((double)zoom.getValue() / (double)50)) + 10;

        g.setColor(new Color(145, 151, 156));

        // Seeing how many divisions we need to the up and to the left. 
        // We need to stop drawing somewhere.
        int stopx = this.SCREEN_WIDTH / (2 * this.STEP_SIZE);
        int stopy = this.SCREEN_HEIGHT / (2 * this.STEP_SIZE);

        // Below draws the coordinate system (x- and y-axis)

        /* 
        // This draws the divisions between units
        for(int i = 1; i <= stopx + 1; i++)
        {
            paintMiddle(g, (this.SCREEN_WIDTH / 2) + (this.STEP_SIZE * i), (this.SCREEN_WIDTH / 2) + (this.STEP_SIZE * (i - 1)));
            paintMiddle(g, (this.SCREEN_WIDTH / 2) + (this.STEP_SIZE * (-1 * i)), (this.SCREEN_WIDTH / 2) + (this.STEP_SIZE * ((-1 * i) + 1)));
        }
        *
        
        // This draws the unit divisions horizontially and vertically
        for(int i = 1; i <= stopx; i++)
        {
            g.setColor(new Color(90, 94, 97));
            g.drawLine((this.SCREEN_WIDTH / 2) + (this.STEP_SIZE * i), 0, (this.SCREEN_WIDTH / 2) + (this.STEP_SIZE * i), this.SCREEN_HEIGHT);
            g.drawLine((this.SCREEN_WIDTH / 2) - (this.STEP_SIZE * i), 0, (this.SCREEN_WIDTH / 2) - (this.STEP_SIZE * i), this.SCREEN_HEIGHT);
        } // end for
        
        for(int i = 1; i <= stopy; i++)
        {
            g.drawLine(0, (this.SCREEN_HEIGHT / 2) + (this.STEP_SIZE * i), this.SCREEN_WIDTH, (this.SCREEN_HEIGHT / 2) + (this.STEP_SIZE * i));
            g.drawLine(0, (this.SCREEN_HEIGHT / 2) - (this.STEP_SIZE * i), this.SCREEN_WIDTH, (this.SCREEN_HEIGHT / 2) - (this.STEP_SIZE * i));
        } // end for
        
        // This is the x- and y-axis
        g.setColor(Color.BLACK);
        g.drawLine(0, this.SCREEN_HEIGHT / 2, this.SCREEN_WIDTH, this.SCREEN_HEIGHT / 2);
        g.drawLine(this.SCREEN_WIDTH / 2, 0, this.SCREEN_WIDTH / 2, this.SCREEN_HEIGHT);        
    } 
*/