// Scene.java
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.ArrayList;
import java.lang.Thread;

public class Scene extends JPanel {
    // Attributes
    private int SCREEN_WIDTH = 640;
    private int SCREEN_HEIGHT = 480;
    private int position;
    private ArrayList<Sprite> sprites;
    private int frame_rate;

    // Methods
    public Scene()
    {
        this.setPreferredSize(new Dimension(this.SCREEN_WIDTH, this.SCREEN_HEIGHT));
        sprites = new ArrayList<Sprite>();
        sprites.add(new Sprite(this, 10));
    } // end constructor

    public void paintComponent(Graphics g){
        for (Sprite sprite : sprites) {
            sprite.paintComponent(g);
        }
        this.repaint();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void start()
    {

    } // end start

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