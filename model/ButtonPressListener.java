package model;
/**
 * ButtonPressListener.java
 * 
 * This class is responsible for the implementation of what
 * happens when a button is clicked. It determines the action
 * based on the text the button has. 
 * 
 * FIX: High cohesion between this and the scene.
 */

import java.awt.event.ActionListener;

import view.Scene;

import java.awt.event.ActionEvent;

public class ButtonPressListener implements ActionListener {
    private Scene scene;

    public ButtonPressListener(Scene s)
    {
        this.scene = s;
    }

    public void actionPerformed(ActionEvent e)
    {
        System.out.println(e.getActionCommand() + " button was pressed");

        switch(e.getActionCommand())
        {
            case "Pause":
                this.scene.pause();
                break;
            case "Play":
                this.scene.play();
                break;
        }
    }
} // end ButtonPressListener