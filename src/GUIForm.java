package src;

import javax.swing.*;
import java.awt.*;

public class GUIForm extends JFrame
{

    private JComboBox estatsSolucioList;
    private JComboBox heuristicsList;
    private JRadioButton algo1;
    private JRadioButton algo2;
    private JPanel rootPanel;
    private JPanel mapa;
    private JLabel algorismes;
    private JLabel titol;
    private JLabel estatsSolucio;
    private JLabel heuristics;
    private JButton executar;
    private JTextField mInput;
    private JTextField nInput;
    private JLabel nValue;
    private JLabel mValue;
    private JLabel footer;
    private JLabel rightMargin;
    private JLabel separador1;

    public GUIForm()
    {
        //Initialize JFrame
        setContentPane(rootPanel);
        setTitle("GUIFORM");
        setSize(700,450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here

        //ButtonGroup radio buttons
        algo1 = new JRadioButton();
        algo2 = new JRadioButton();
        ButtonGroup bg = new ButtonGroup();
        bg.add(algo1);
        bg.add(algo2);

        //Button executar
        executar = new JButton();
        executar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        /* Canvas ??*/
        //mapa = new JPanel();
    }
}
