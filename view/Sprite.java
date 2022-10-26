package view;
/**
 * Sprite.java
 * 
 * This class is a sprite which may be thought of as
 * an object in space. It is defined by the position
 * of its center of mass as well as any external forces
 * that may be applied to the object. It is assumed that
 * all sprites are at rest from some stationary frame of reference
 * upon creation. So, all sprites will not move when first created
 * from the point of view of a POV which also doesn't move.
 */

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.*;
import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;

public class Sprite extends JPanel {
    // Attributes
    private int[] size = {0, 0};
    private double[] position = {0, 0};
    private double image_angle = 0;
    private double move_angle = 0;
    private int speed = 0;
    private int[] velocity = {0, 0};
    private int[] acceleration = {0, 0};
    private Scene scene;
    private String bound_action = "NOTHING";
    private boolean visible = true;


    private BufferedImage image;
    private ImageIcon img;

    // Methods
    public Sprite(Scene scene, int x, int y)
    {
        this.scene = scene;
        this.position[0] = x;
        this.position[1] = y;
        //img = new ImageIcon("/Users/zachary/Desktop/Intro to Game Graphics/Game-Engine/checkerboard.gif");
        //System.out.println(this.img.getIconWidth());
        //System.out.println(this.img.getIconHeight());
        /*
        try {
            this.image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {

        }
        */
    } // end constructor

    public double getPosition(int index)
    {
        return this.position[index];
    }

    public void setImage()
    {

    } // end setImage

    public void draw(double global_time, Graphics g)
    {
        update(global_time);
        checkBounds();

        if(!visible)
        {
            return;
        } // end if

        super.paintComponent(g);

        // These coordinates account for where we want the geometric center to be.
        //int transformed_x = this.position[0] - (this.img.getIconWidth() / 2);
        //int transformed_y = this.position[1] - (this.img.getIconHeight() / 2);

        //g.drawImage(img.getImage(), transformed_x, transformed_y, this);
        //g.drawRect(this.position[0], this.position[1], this.size[0], this.size[1]);
        //g.drawImage(image, this.position[0], this.position[1], this);

        /* 
        g.setColor(new Color(145, 151, 156));
        g.drawOval(this.position[0], this.position[1], this.size, this.size);
        g.fillOval(this.position[0], this.position[1], this.size, this.size);
        */
    } // end draw

    public void setPosition(double x, double y)
    {
        this.position[0] = x;
        this.position[1] = y;
    }

    // This should be done independently of the frame rate
    // to ensure that the distance an object travels is the 
    // same reguardless if there is a faster or slower frame rate.
    public void update(double t)
    {
        double x, y;
        double a = 1, b = 0.5, c = 0.75, h = 0, k = 0, r = 2;
        //setPosition(this.position[0] + dt * 0.001, this.position[1] - dt * 0.001);
        // Reference: https://www.physicsforums.com/threads/cool-parametric-equations.863611/
        // Epicycloid
        // x(t) = (a - b) cos t + c cos ((a/b - 1)t) 
        // y(t) = (a - b) sin t + c sin ((a/b - 1)t)
        //double x = ((a - b) * Math.cos(t)) + (c * Math.cos(((a / b) - 1) * t));
        //double y = ((a - b) * Math.sin(t)) + (c * Math.sin(((a / b) - 1) * t));
        

        // Ellipse (gamma(t) = <h + asin(t), k + bcos(t)>)
        //x = h + a * Math.sin(t % (2 * Math.PI));
        //y = k + b * Math.cos(t % (2 * Math.PI));

        //double x = r * (t - Math.sin(t));
        //double y = r * (1 - Math.cos(t));

        // x(t) = (a + b) cost − c cos((a/b + t)t)
        // y(t) = (a + b)sin t − c sin((a/b + 1)t)
        //x = ((a + b) * Math.cos(t % (2 * Math.PI))) - (c * Math.cos((((a / b) + t) * t) % (2 * Math.PI)));
        //y = ((a + b) * Math.sin(t % (2 * Math.PI))) - (c * Math.sin((((a / b) + 1) * t) % (2 * Math.PI)));

       // x = t - (Math.PI / 2) % (2 * Math.PI);
        //y = (a * Math.sin(t)) % (4 * Math.PI);


        x = (t/4) * ((t/4) + 1);
        y = 2 * (t/4) - 1;

        this.setPosition(x, y);
        //System.out.println(dt + ": <" + this.position[0] + "," + this.position[1] + ">");
    } // end update

    // 
    public void toggleVisibility()
    {
        visible = !visible;
    } // end toggleVisibility

    public void setSpeed()
    {
        
    } // end setSpeed

    public void setImageAngle(double theta)
    {
        this.image_angle = theta;
    } // end setImageAngle

    public void setMoveAngle(double theta)
    {
        this.move_angle = theta;   
    } // end setMoveAngle

    private void projectVector()
    {

    } // end projectVector

    public void addForce()
    {

    } // end addForce

    public void setBoundAction()
    {

    } // end setBoundAction

    public void checkBounds()
    {

    } // end checkBounds

    public boolean collidesWith(Sprite s)
    {
        return true;
    } // end collidesWith

    // Computes the 2-norm between two sprites.
    public double distanceTo(Sprite s)
    {
        double distance = 0, sum = 0;
        int dim = this.position.length;

        for(int i = 0; i < dim; i++)
        {
            sum += Math.pow(this.position[i] - s.position[i], 2);
        } // end for
        
        distance = Math.sqrt(sum);

        return distance;
    } // end distanceTo

    // Computes the angle to another sprite relative to the positive x-axis.
    // Returned value will be in the interval [0, pi]
    public double angleTo(Sprite s)
    {
        double angle = -1;

        return angle;
    } // end angleTo
} // end Sprite