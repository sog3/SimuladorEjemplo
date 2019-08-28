package cpu;

/**
 * @author Ahmed Elmowafy
 * Main class of the application that initializes Face Frame.
 */
public class inicio {

    public static void main(String[] args) {
        // TODO code application logic here
        /*Date offSet = new Date("Jun 9 00:02:00 2014");
        Date toDay = new Date();
        if( ! toDay.after(offSet)) 
        {*/
            Face face = new Face();
            face.show();
        /*}
        else
        {
            JOptionPane.showMessageDialog(null , " Sorry App is not valid now ");
            System.exit(0);
        }*/
    }
}
