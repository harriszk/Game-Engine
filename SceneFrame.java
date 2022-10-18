import javax.swing.JFrame;

public class SceneFrame extends JFrame {
    SceneFrame(){
        this.add(new Scene());
        this.setTitle("Scene");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    } // end constructor
} // end SceneFrame