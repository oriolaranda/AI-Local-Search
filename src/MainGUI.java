package src;

import javax.swing.*;

public class MainGUI {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run() {
                GUIForm gui = new GUIForm();
                gui.setVisible(true);
            }
        });

    }
}
