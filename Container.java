/**
 * Container.java
 * 
 * This class is the container for the game engine.
 * This is the manager for the frame of the application
 * as well as the game scene.
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.*;
import java.awt.event.*;

public class Container {
    private JFrame frame;
    private int width = 640;
    private int height = 480;

    Container(){
        this.frame = new JFrame();

        this.frame.setTitle("Zachary's Game Engine");
        this.frame.setSize(this.width, this.height);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);

        // Listens for a change to the window size
        Subject s = new Subject();
        this.frame.addComponentListener(s);
        
        Scene gameScene = new Scene(this.width, this.height, s);
        this.frame.add(gameScene);


        //s.register(gameScene);



        //this.frame.setResizable(false);



        //this.frame.pack();
    } // end constructor

    public void start(){
        this.frame.setVisible(true);
    }

    public void update(Subject s)
    {
        this.width = s.getWidth();
        this.height = s.getHeight();
    }

} // end Container