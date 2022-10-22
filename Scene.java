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
    private int STEP_SIZE = 40;
    private JSlider zoom;
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

        zoom = new JSlider();
        this.add(zoom);

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

        drawCoords(g);
        

        /*
        for (Sprite sprite : sprites) 
        {
            sprite.draw(g);
        } // end for
        */

        //g.setColor(new Color(0xFF, 0xFF, 0xFF));
        //position += (int)(640 * delta_t * 0.001);
        
        //System.out.println("<" + position + ",50> @ " + (delta_t * 0.001));
        int t = (int)(this.SCREEN_WIDTH * time.beginning.until(time.finish, time.chronounit) * 0.001);
        //System.out.println("t = " + t);
        g.fillOval(t / 2 % this.SCREEN_WIDTH, t / 2 % this.SCREEN_HEIGHT, 20, 20);
        //g.fillRect(75, this.position, 50, 50);

        
        
        //System.out.println("Repainting scene.");
    }

    // This is the main game loop
    
    public void actionPerformed(ActionEvent e){
        System.out.println(e.getActionCommand());
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
        */
        
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

    public void updateSize(int w, int h)
    {
        this.SCREEN_WIDTH = w;
        this.SCREEN_HEIGHT = h;
    }

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