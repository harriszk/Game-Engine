// Sprite.java
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class Sprite extends JPanel {
    // Attributes
    private int size;
    private int[] position = {0, 0};
    private int image_angle = 0;
    private int move_angle = 0;
    private int speed = 0;
    private int[] velocity = {0, 0};
    private int[] acceleration = {0, 0};
    private Scene scene;
    private String bound_action = "NOTHING";

    // Methods
    public Sprite(Scene scene, int size)
    {
        this.scene = scene;
        this.size = size;
    } // end constructor

    public void paintComponent(Graphics g){
        g.setColor(new Color(145, 151, 156));
        g.drawOval(this.position[0], this.position[1], this.size, this.size);
        this.position[0]++;
        this.position[1]++;
        System.out.println("HERE");
    } // end paintComponent

    public void setImage()
    {

    } // end setImage

    public void draw()
    {

    } // end draw

    public void update()
    {

    } // end update

    public void toggleVisibility()
    {

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