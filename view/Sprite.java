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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import common.Observer;
import common.Recipient;
import common.Subject;

import javax.imageio.ImageIO;

public class Sprite extends JPanel implements Observer {
    // Attributes
    private int SCREEN_WIDTH, SCREEN_HEIGHT;
    private int width, height;
    private double[] position = {30, 250};
    private double image_angle = 0;
    private double move_angle = 0;
    private int speed = 0;
    private double[] velocity = {5, 5};
    private double[] acceleration = {0, 0};
    private String bound_action;
    private boolean visible = true;

    private ImageIcon image;

    // Methods
    public Sprite(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.bound_action = "BOUNCE";
        image = new ImageIcon("images/missing_image.jpeg");
        //System.out.println(this.image.getIconWidth());
        //System.out.println(this.image.getIconHeight());
    } // end constructor

    public void update(Subject s)
    {
        this.SCREEN_WIDTH = s.getWidth();
        this.SCREEN_HEIGHT = s.getHeight();
    } // end update

    public double getPosition(int index)
    {
        return this.position[index];
    }

    public ImageIcon getSpriteImageIcon()
    {
        return this.image;
    }

    public int getWidth()
    {
        return this.width;
    }

    public int getHeight()
    {
        return this.height;
    }

    public void setImage(String image_location)
    {
        Path path = Paths.get(image_location);
        if(Files.exists(path))
        {
            this.image = new ImageIcon(image_location);
        }
    }

    public void draw(double dt, Graphics g)
    {
        update(dt);
        checkBounds();

        if(!visible)
        {
            return;
        } // end if

        //super.paintComponent(g);

        g.drawImage(this.image.getImage(), (int)(this.position[0] - (this.width / 2)), (int)(this.position[1] - (this.height / 2)), this.width, this.height, this);

        // These coordinates account for where we want the geometric center to be.
        //int transformed_x = this.position[0] - (this.image.getIconWidth() / 2);
        //int transformed_y = this.position[1] - (this.image.getIconHeight() / 2);

        //g.drawImage(image.getImage(), transformed_x, transformed_y, this);
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
    public void update(double dt)
    {
        // The commented out lines are for a sprite thats position is determined by
        // some parametric equation, gamma(t) = <x(t), y(t)>, using time as a parameter.
        //double x, y;
        //double a = 1, b = 0.5, c = 0.75, h = 0, k = 0, r = 2, n = 4;
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

        // Limaçon trisectrix
        //x = a * (1 + Math.cos(t) + Math.cos(2 * t));
        //y = a * (1 + Math.sin(t) + Math.sin(2 * t));

        //double x = r * (t - Math.sin(t));
        //double y = r * (1 - Math.cos(t));

        // x(t) = (a + b) cost − c cos((a/b + t)t)
        // y(t) = (a + b)sin t − c sin((a/b + 1)t)
        //x = ((a + b) * Math.cos(t % (2 * Math.PI))) - (c * Math.cos((((a / b) + t) * t) % (2 * Math.PI)));
        //y = ((a + b) * Math.sin(t % (2 * Math.PI))) - (c * Math.sin((((a / b) + 1) * t) % (2 * Math.PI)));
        
        //x = t - (Math.PI / 2) % (2 * Math.PI);
        //y = (a * Math.sin(t)) % (4 * Math.PI);

        //x = (t/4) * ((t/4) + 1);
        //y = 2 * (t/4) - 1;

        this.position[0] += this.velocity[0] * dt;
        this.position[1] += this.velocity[1] * dt;

        System.out.println(dt + ": <" + this.velocity[0] * dt + "," + this.velocity[1] * dt + ">");
    } // end update

    public void toggleVisibility()
    {
        visible = !visible;
    } // end toggleVisibility

    public void setSpeed(int speed)
    {
        this.speed = speed;
    } // end setSpeed

    public void setImageAngle(double theta)
    {
        this.image_angle = theta;
    } // end setImageAngle

    public void setMoveAngle(double theta)
    {
        this.move_angle = theta;   
    } // end setMoveAngle

    // Using trig to go from polar to eulcidean
    private void projectVector(double r, double theta)
    {
        this.setPosition(r * Math.cos(theta), r * Math.sin(theta));
    } // end projectVector

    public void addForce()
    {

    } // end addForce

    public void setBoundAction(String action)
    {
        this.bound_action = action;
    } // end setBoundAction

    public void checkBounds()
    {
        switch(this.bound_action)
        {
            case "NOTHING":
                if(this.position[0] > this.SCREEN_WIDTH) {
                    this.visible = false;
                } else if(this.position[1] > this.SCREEN_HEIGHT) {
                    this.visible = false;
                } // end if
                break;
            case "BOUNCE":
                if(this.position[0] > this.SCREEN_WIDTH) {
                    this.velocity[0] *= -1;
                } else if(this.position[0] < 0) {
                    this.velocity[0] *= -1;
                } // end if
                
                if(this.position[1] > this.SCREEN_HEIGHT) {
                    this.velocity[1] *= -1;
                } else if(this.position[1] < 0) {
                    this.velocity[1] *= -1;
                } // end if
                break;
            default:
                System.out.println("Unrecognized or no bound action set.");
                break;
        } // end switch
    } // end checkBounds

    public boolean collidesWith(Sprite s)
    {
        if(s.position[0] - s.width > this.position[0] + this.width)
        {
            return false;
        } // end if

        if(s.position[0] + s.width < this.position[0] - this.width)
        {
            return false;
        } // end if

        if(s.position[1] - s.height > this.position[1] + this.height)
        {
            return false;
        } // end if

        if(s.position[1] + s.height > this.position[1] - this.height)
        {
            return false;
        } // end if

        return true;
    } // end collidesWith

    // Computes the 2-norm between two sprites.
    public double distanceTo(Sprite s)
    {
        double distance = 0, sum = 0;
        int dimension = this.position.length;

        for(int i = 0; i < dimension; i++)
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
        double angle = -1, dx, dy;

        dx = this.position[0] - s.position[0];
        dy = this.position[1] - s.position[1];

        angle = Math.atan2(dy, dx);

        return angle;
    } // end angleTo
} // end Sprite