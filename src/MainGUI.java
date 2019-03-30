package src;

import javax.swing.*;

public class MainGUI {

    /**
     * MAIN PER PROVES CANVAS
     */
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //Looks like the current OS
        SwingUtilities.invokeLater(new Runnable() //running GUI code on the Event Dispatch Thread
        {
            @Override
            public void run() {
                //GUIForm gui = new GUIForm();
                //gui.setVisible(true);

                prova();

            }
        });

    }


    private static void prova() {
        JFrame frame = new JFrame();
        MapCanvas m = new MapCanvas();

        //m.drawPoint(50,50, Color.GREEN);
        frame.setContentPane(m);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(800, 600);
    }
}
