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
    private int[] position = {250, 250};
    private int image_angle = 0;
    private int move_angle = 0;
    private int speed = 0;
    private int[] velocity = {0, 0};
    private int[] acceleration = {0, 0};
    private Scene scene;
    private String bound_action = "NOTHING";
    private boolean visible = true;


    private BufferedImage image;
    private ImageIcon img;

    // Methods
    public Sprite(Scene scene, int width, int height)
    {
        this.scene = scene;
        this.size[0] = width;
        this.size[1] = height;
        img = new ImageIcon("/Users/zachary/Desktop/Intro to Game Graphics/Game-Engine/checkerboard.gif");
        System.out.println(this.img.getIconWidth());
        System.out.println(this.img.getIconHeight());
        /*
        try {
            this.image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {

        }
        */
    } // end constructor

    /* 
    public void paintComponent(Graphics g){
        g.setColor(new Color(145, 151, 156));
        g.drawOval(this.position[0], this.position[1], this.size, this.size);
        g.fillOval(this.position[0], this.position[1], this.size, this.size);
        //this.position[0]++;
        //this.position[1]++;
        //System.out.println("HERE");
    } // end paintComponent
    */

    public void setImage()
    {

    } // end setImage

    public void draw(Graphics g)
    {
        update();
        checkBounds();

        if(!visible)
        {
            return;
        } // end if

        System.out.println("Drawing the sprite.");
        super.paintComponent(g);

        // These coordinates account for where we want the geometric center to be.
        int transformed_x = this.position[0] - (this.img.getIconWidth() / 2);
        int transformed_y = this.position[1] - (this.img.getIconHeight() / 2);

        g.drawImage(img.getImage(), transformed_x, transformed_y, this);
        //g.drawRect(this.position[0], this.position[1], this.size[0], this.size[1]);
        //g.drawImage(image, this.position[0], this.position[1], this);

        /* 
        g.setColor(new Color(145, 151, 156));
        g.drawOval(this.position[0], this.position[1], this.size, this.size);
        g.fillOval(this.position[0], this.position[1], this.size, this.size);
        */
    } // end draw

    public void setPosition(int x, int y)
    {
        this.position[0] = x;
        this.position[1] = y;
    }

    public void update()
    {
        this.position[0] += 3;
    } // end update

    // 
    public void toggleVisibility()
    {
        visible = !visible;
    } // end toggleVisibility

    public void setSpeed()
    {
        
    } // end setSpeed

    public void setImageAngle()
    {
        
    } // end setImageAngle

    public void setMoveAngle()
    {
        
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
        if(this.position[0] > 640)
        {
            this.position[0] = 0;
        }
    } // end checkBounds

    public boolean collidesWith(Sprite s)
    {
        return true;
    } // end collidesWith

    public int distanceTo(Sprite s)
    {
        int distance = 0;

        return distance;
    } // end distanceTo

    public int angleTo(Sprite s)
    {
        int angle = -1;

        return angle;
    } // end angleTo
} // end Sprite