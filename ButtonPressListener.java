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
                if(this.scene.on){
                    this.scene.pause();
                    //pause_button.setText("Play");
                    this.scene.on = false;
                } else {
                    this.scene.play();
                    //pause_button.setText("Pause");
                    this.scene.on = true;
                }
        }
    }
} // end ButtonPressListener