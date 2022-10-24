import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.time.*;
import java.time.temporal.ChronoUnit;


public class Timeline implements ActionListener {
    private int IDEALIZE_STEP_SIZE = 30; // per second
    private int current_step_size;

    Scene scene;
    Timer timer;

    Instant beginning, start, finish;
    Clock clock = Clock.systemUTC();
    long delta_t;

    ChronoUnit chronounit = ChronoUnit.valueOf("MILLIS");

    public Timeline(Scene s)
    {
        System.out.println("Timeline created...");
        this.scene = s;
        this.timer = new Timer(1000 / this.IDEALIZE_STEP_SIZE, this);
        this.timer.start();
        beginning = this.clock.instant();
        start = beginning;
    } // end constructor

    public void actionPerformed(ActionEvent e)
    {
        finish = this.clock.instant();
        delta_t = start.until(finish, this.chronounit);
        start = finish;

        this.scene.repaint();
        //this.scene.debugger.setText("Total: " + (float)(beginning.until(finish, chronounit) * 0.001) + "s --- dt: " + delta_t + "ms");
    } // end actionPerformed

    public void play()
    {
        // Get rid of the paused time as we don't want to make calculations
        // under the assumption that time wasn't stopped for some dt.
        this.timer.start();
        this.finish = this.clock.instant();

        delta_t = start.until(finish, this.chronounit);
        this.start = this.start.plus(delta_t, chronounit);
        this.beginning = this.beginning.plus(delta_t, chronounit);
    } // end play

    public void pause()
    {
        // Keep track of when we paused as to account for it later
        // when we unpause.
        this.start = this.clock.instant();
        this.timer.stop();
    } // end pause
}

/*
 * 
    private void updateDeltaTime()
    {
        displacement = LocalTime.now();

        delta_t = inital.until(displacement, this.chronounit);

        inital = displacement;

        debugger.setText("Delta t = " + delta_t + "ms");
    } // end updateDeltaTime
 */
