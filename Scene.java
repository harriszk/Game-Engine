/**
 * Scene.java
 * 
 * This class is the canvas to which we shall draw an image.
 * We may offload the drawing of sprites to the sprites themselves
 * but the order in which object are colored is done here. 
 */

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.ArrayList;
import java.lang.Thread;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;

public class Scene extends JPanel implements ActionListener {
    // Attributes
    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;
    private int FRAME_RATE = 24;
    private ArrayList<Sprite> sprites;

    Timer clock;
    
    private int position = 0;

    // Methods
    public Scene(int width, int height)
    {
        System.out.println("Creating scene...");

        this.SCREEN_WIDTH = width;
        this.SCREEN_HEIGHT = height;
        this.setBackground(new Color(145, 151, 156));
        this.clock = new Timer(50, this);



        //this.setPreferredSize(new Dimension(this.SCREEN_WIDTH, this.SCREEN_HEIGHT));
        sprites = new ArrayList<Sprite>();
        sprites.add(new Sprite(this, 50, 200));
        //sprites.add(new Sprite(this, 25, 25));

        //sprites.get(0).setPosition(600, 75);
        //sprites.get(1).setPosition(20, 50);

        this.clock.start();
    } // end constructor

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for (Sprite sprite : sprites) 
        {
            sprite.draw(g);
        } // end for
        

        g.setColor(new Color(0xFF, 0xFF, 0xFF));
        
        g.fillRect(this.position, this.position, 50, 50);

        
        
        System.out.println("Repainting scene.");
    }

    // This is the main game loop
    public void actionPerformed(ActionEvent e){
        //happens once each 50 ms
        this.position += 1;
        if(this.position > this.SCREEN_HEIGHT)
        {
            this.position = 0;
        }
        
        this.repaint();
    } // end actionPerformed

    public void end()
    {

    } // end end

    public void pause()
    {

    } // end pause

    public void clear()
    {

    } // end clear

    public void toggleCursor()
    {

    } // end toggleCursor
} // end Scene