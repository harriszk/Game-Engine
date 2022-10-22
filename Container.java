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

        Scene gameScene = new Scene(this.width, this.height);
        this.frame.add(gameScene);

        this.frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                Dimension size = frame.getBounds().getSize();
                width = (int)size.getWidth();
                height = (int)size.getHeight();
                gameScene.updateSize(width, height);
            }
        });



        //this.frame.setResizable(false);



        //this.frame.pack();
    } // end constructor

    public void start(){
        this.frame.setVisible(true);
    }

} // end Container