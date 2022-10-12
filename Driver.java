// Driver.java

public class Driver {
    public static void main(String args[])
    {
        try {
            Sprite s;
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } // end try/catch
    } // end main
} // end Driver