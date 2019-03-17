package src;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;

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
    private JTextField nInput;
    private JTextField mInput;
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

        //Initial values for n and m
        nInput.setText("100");
        mInput.setText("50");


        //Execute algorithm!
        executar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nInput.getText().length() == 0 || mInput.getText().length() == 0)
                    JOptionPane.showMessageDialog(rootPanel,"Falta algun camp per omplir", "Error", JOptionPane.WARNING_MESSAGE);
                else {
                    int n = Integer.parseInt(nInput.getText());
                    int m = Integer.parseInt(mInput.getText());
                }
            }
        });

        //JTexFields KeyListeners
        nInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                numberFormatter(e, nInput);
            }
        });

        mInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                numberFormatter(e, mInput);
            }
        });
    }


    private void createUIComponents() throws ParseException {
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

    //Only numbers for JTextFields
    private void numberFormatter(KeyEvent evt, JTextField input)
    {
        if(!Character.isDigit(evt.getKeyChar()) || input.getText().length()>= 3){
            evt.consume();
            getToolkit().beep();
        }
    }
}
