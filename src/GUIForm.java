package src;

import javax.swing.*;
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
    private JPanel escenari;
    private JPanel mapaInicial;
    private JLabel titol;
    private JButton executar;
    private JTextField nInput;
    private JTextField mInput;
    private JLabel nValue;
    private JLabel mValue;
    private JLabel footer;
    private JLabel rightMargin;
    private JPanel hillClimbing;
    private JPanel simulatedAnnealing;
    private JPanel rootPanel;
    private JTabbedPane tabbedPane;
    private JTextField seedInput;
    private JLabel seedValue;
    private JButton generarEscenari;
    private JButton generarEstatSolucio;
    private JPanel parametresIni;
    private JPanel estatsSolucio;
    private JPanel heuristics;
    private JLabel separador;
    private JComboBox successorsListHill;
    private JPanel leftPanel1;
    private JTextArea textAreaHill;
    private JPanel mapaHill;
    private JPanel leftPanel0;
    private JPanel successorsHill;
    private JButton executarHill;
    private JTextArea textAreaSim;
    private JPanel successorsSim;
    private JComboBox successorsListSim;
    private JPanel leftPanel2;
    private JPanel mapaSim;
    private JButton executarSim;
    private JPanel parametresSim;

    public GUIForm()
    {
        //Initialize JFrame
        setContentPane(rootPanel);
        setTitle("GUIFORM");
        setSize(800,700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //setResizable(false);

        initEscenari(); //escenari panel
        initHillClimbing(); //hillclimbing panel
        initSimulatedAnnealing(); //simulatedannealing panel

    }

    //initialize escenari Panel
    private void initEscenari()
    {
        //Initial values for n, m & seed
        nInput.setText("100");
        mInput.setText("50");
        seedInput.setText("2");

        //Buttons
        generarEstatSolucio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        generarEscenari.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


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

        seedInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                numberFormatter(e, seedInput);
            }
        });

        //JButton actionsListeners
        generarEscenari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nInput.getText().length() == 0 || mInput.getText().length() == 0 || seedInput.getText().length() == 0)
                    JOptionPane.showMessageDialog(escenari,"Falta algun camp per omplir", "Error", JOptionPane.WARNING_MESSAGE);
                else {
                    int n = Integer.parseInt(nInput.getText());
                    int m = Integer.parseInt(mInput.getText());
                    int seed = Integer.parseInt(seedInput.getText());
                }
            }
        });

        generarEstatSolucio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nInput.getText().length() == 0 || mInput.getText().length() == 0 || seedInput.getText().length() == 0)
                    JOptionPane.showMessageDialog(escenari,"Falta algun camp per omplir", "Error", JOptionPane.WARNING_MESSAGE);
                else {
                    int n = Integer.parseInt(nInput.getText());
                    int m = Integer.parseInt(mInput.getText());
                    int seed = Integer.parseInt(seedInput.getText());
                }
            }
        });



    }

    //initialize hillClimbing Panel
    private void initHillClimbing()
    {

        executarHill.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    //initialize simulatedAnnealing Panel
    private void initSimulatedAnnealing()
    {
        executarSim.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }


    private void createUIComponents() throws ParseException {
        // TODO: place custom component creation code here

        //Escenari panel
        parametresIni = new JPanel();
        estatsSolucio = new JPanel();
        heuristics = new JPanel();

        parametresIni.setBorder(BorderFactory.createTitledBorder("Paràmetres inicials"));
        estatsSolucio.setBorder(BorderFactory.createTitledBorder("Estat solució inicial"));
        heuristics.setBorder(BorderFactory.createTitledBorder("Heurístic"));

        //Hill Climbing panel
        successorsHill = new JPanel();
        successorsHill.setBorder(BorderFactory.createTitledBorder("Estats successors"));

        //Simulated Annealing panel
        successorsSim = new JPanel();
        successorsSim.setBorder(BorderFactory.createTitledBorder("Estats successors"));

        /* Canvas ??*/
        //mapaInicial = new JPanel();
    }



    //Only numbers for JTextFields
    private void numberFormatter(KeyEvent evt, JTextField input)
    {
        if(!Character.isDigit(evt.getKeyChar()) || input.getText().length() >= 3){
            evt.consume();
            getToolkit().beep();
        }
    }
}
