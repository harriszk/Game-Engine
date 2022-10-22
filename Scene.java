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

public class Scene extends JPanel implements ActionListener {
    // Attributes
    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;
    private ArrayList<Sprite> sprites;
    private Timeline time;
    boolean on = true;


    private ButtonPressListener listener = new ButtonPressListener(this);

    public JLabel debugger;
    private JButton pause_button = new JButton("Pause");
    int counter = 0;

    
    private int position = 0;

    // Methods
    public Scene(int width, int height)
    {
        System.out.println("Creating scene...");

        this.SCREEN_WIDTH = width;
        this.SCREEN_HEIGHT = height;
        this.setBackground(new Color(145, 151, 156));

        time = new Timeline(this);


        this.pause_button.addActionListener(this.listener);
        
        debugger = new JLabel();
        this.add(debugger);
        this.add(pause_button);



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

        

        /*
        for (Sprite sprite : sprites) 
        {
            sprite.draw(g);
        } // end for
        */

        //g.setColor(new Color(0xFF, 0xFF, 0xFF));
        //position += (int)(640 * delta_t * 0.001);
        
        //System.out.println("<" + position + ",50> @ " + (delta_t * 0.001));

        g.drawLine(320, 240, counter % this.SCREEN_WIDTH, 50);
        //g.fillRect(75, this.position, 50, 50);

        
        
        //System.out.println("Repainting scene.");
    }

    // This is the main game loop
    
    public void actionPerformed(ActionEvent e){
        /*
        counter++;
        
        Clock clock = Clock.systemUTC();
        //debugger.setText("UTC time = " + clock.instant());

        updateDeltaTime();

        //this.position += 2 * (1000 * delta_t);
        if(this.position > this.SCREEN_HEIGHT)
        {
            this.position = 0;
        }

        /*
        for (Sprite sprite : sprites) 
        {
            sprite.update();
        } // end for
        

        this.repaint();
        */
        
    } // end actionPerformed
    

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