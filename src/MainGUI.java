package src;

import javax.swing.*;

public class MainGUI {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //Looks like the current OS
        SwingUtilities.invokeLater(new Runnable() //running GUI code on the Event Dispatch Thread
        {
            @Override
            public void run() {
                GUIForm gui = new GUIForm();
                gui.setVisible(true);
            }
        });

    }
}
