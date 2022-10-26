package model;
/**
 * CoordinateSystem.java
 * 
 * 
 */
import common.Observer;
import common.Recipient;
import common.Subject;

public class CoordinateSystem implements Observer, Recipient {
    // Attributes
    private Timeline sprite_timeline;
    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;
    private int UNIT_CONVERSION = 100;
    private int X_OFFSET;
    private int Y_OFFSET;
    private float t;
    private int[] divisions = {0, 0, 0};
    private boolean paused = false;

    // Methods
    public CoordinateSystem(int width, int height)
    {
        this.SCREEN_WIDTH = width;
        this.SCREEN_HEIGHT = height;
        this.X_OFFSET = width / 2;
        this.Y_OFFSET = height / 2;
        sprite_timeline = new Timeline(this);
    } // end constructor

    public void timesUp()
    {
        // Update global time t for sprites defined with a parametrized equation
        t = (float)(this.sprite_timeline.beginning.until(this.sprite_timeline.finish, this.sprite_timeline.chronounit) * 0.001);
    } // end timesUp

    public void play()
    {
        sprite_timeline.play();
        paused = false;
    } // end play

    public void pause()
    {
        sprite_timeline.pause();
        paused = true;
    } // end pause

    public void update(Subject s)
    {
        this.SCREEN_WIDTH = s.getWidth();
        this.SCREEN_HEIGHT = s.getHeight();
    } // end update

    public void updateZoom(int value)
    {
        this.UNIT_CONVERSION = value;
    } // end updateZoom

    public void updateCenterX(int dx)
    {
        this.X_OFFSET += dx;
    } // end updateCenterX

    public void updateCenterY(int dy)
    {
        this.Y_OFFSET += dy;
    } // end updateCenterY

    public void updateDivisions() 
    {
        this.divisions[0] = this.UNIT_CONVERSION / 2;
        this.divisions[1] = (this.UNIT_CONVERSION / 4);
        this.divisions[2] = this.divisions[0] + this.divisions[1];
    } // end updateDivisions

    public int convertAxes(double global_unit, int axis)
    {
        int position = -1;
        switch(axis){
            case 0:
                position = (int)(this.X_OFFSET + (this.UNIT_CONVERSION * global_unit));
                break;
            case 1:
                position = (int)(this.Y_OFFSET + (this.UNIT_CONVERSION * -1 * global_unit));
                break;
            default:
                break;
        } // end switch
        return position;
    } // end convertAxes

    public int convertPositionX(double x)
    {
        int converted = -1;
        converted = (int)(this.X_OFFSET + (this.UNIT_CONVERSION * x));

        return converted;
    } // end convertPositionX

    public int convertPositionY(double y)
    {
        int converted = -1;
        converted = (int)(this.Y_OFFSET + (this.UNIT_CONVERSION * -1 * y));

        return converted;
    } // end convertPositionY

    public int[] getDivisions()
    {
        return this.divisions;
    }  // end getDivisions

    public int getStartX()
    {
        int x_0 = (int)Math.floor(-1 * this.X_OFFSET / this.UNIT_CONVERSION);
        if(x_0 < 0)
        {
            x_0--;
        } // end if
        return x_0;
    } // end getStartX

    public int getEndX(int x_0)
    {
        return (this.SCREEN_WIDTH / this.UNIT_CONVERSION) + x_0 + 3;
    } // end getEndX

    public int getStartY()
    {
        return (int)Math.floor((this.Y_OFFSET - this.SCREEN_HEIGHT) / this.UNIT_CONVERSION);
    } // end getStartY

    public int getEndY(int y_0)
    {
        return (this.SCREEN_HEIGHT / this.UNIT_CONVERSION) + y_0 + 3;
    } // end getEndY

    public int getOffsetX()
    {
        return this.X_OFFSET;
    }

    public int getOffsetY()
    {
        return this.Y_OFFSET;
    }

    public float getTime()
    {
        return this.t;
    } // end getTime

    public float getDeltaTime()
    {
        return this.sprite_timeline.delta_t;
    }
} // end CoordinateSystem 