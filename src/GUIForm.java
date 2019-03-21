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
    final static int INT = 0;
    final static int DOUBLE = 1;

    private JComboBox estatsSolucioList;
    private JComboBox heuristicsList;
    private JPanel escenari;
    private JPanel mapaInicial;
    private JLabel titol;
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
    private JTextField kInput;
    private JTextField itInput;
    private JTextField stepsItInput;
    private JTextField lambdaInput;
    private JLabel kValue;
    private JLabel itValue;
    private JLabel stepsItValue;
    private JLabel lambdaValue;

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
                numberFormatter(e, nInput,INT,3);
            }
        });

        mInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                numberFormatter(e, mInput,INT,3);
            }
        });

        seedInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                numberFormatter(e, seedInput,INT,3);
            }
        });

        //JButton actionsListeners
        generarEscenari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nInput.getText().length() == 0 || mInput.getText().length() == 0 || seedInput.getText().length() == 0)
                    JOptionPane.showMessageDialog(escenari,"Falta algun camp per omplir", "Error", JOptionPane.WARNING_MESSAGE);
                else {
                    generarEscenari();
                }
            }
        });

        generarEstatSolucio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nInput.getText().length() == 0 || mInput.getText().length() == 0 || seedInput.getText().length() == 0)
                    JOptionPane.showMessageDialog(escenari,"Falta algun camp per omplir", "Error", JOptionPane.WARNING_MESSAGE);
                else {
                    generarEstatsSolucioInicials();
                }
            }
        });



    }

    //initialize hillClimbing Panel
    private void initHillClimbing()
    {

        executarHill.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //JButton ActionListener
        executarHill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nInput.getText().length() == 0 || mInput.getText().length() == 0 || seedInput.getText().length() == 0)
                    JOptionPane.showMessageDialog(escenari,"Falta algun camp per omplir", "Error", JOptionPane.WARNING_MESSAGE);
                else {
                    executarHillClimbing();
                }
            }
        });
    }

    //initialize simulatedAnnealing Panel
    private void initSimulatedAnnealing()
    {
        executarSim.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        // JTextFields KeyListeners
        kInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                numberFormatter(e,kInput,INT,3);
            }
        });

        itInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                numberFormatter(e,itInput,INT,6);
            }
        });

        stepsItInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                numberFormatter(e,stepsItInput,INT,3);
            }
        });

        lambdaInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                numberFormatter(e,lambdaInput,DOUBLE,6);
            }
        });


        //JButton ActionListener

        executarSim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kInput.getText().length() == 0 || itInput.getText().length() == 0
                        || stepsItInput.getText().length() == 0 || lambdaInput.getText().length() < 3)
                    JOptionPane.showMessageDialog(escenari,"Falta algun camp per omplir", "Error", JOptionPane.WARNING_MESSAGE);
                else {
                    executarSimulatedAnnealing();
                }
            }
        });
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

        parametresSim = new JPanel();
        parametresSim.setBorder(BorderFactory.createTitledBorder("Paràmetres inicials"));
        /* Canvas ??*/
        //mapaInicial = new JPanel();
    }



    //Only numbers for JTextFields
    /* type = 0 -> Int
       type = 1 -> Double
     */
    private void numberFormatter(KeyEvent evt, JTextField input, int type, int length)
    {
        if (type == 0) {
            if(!Character.isDigit(evt.getKeyChar()) || input.getText().length() >= length){
                evt.consume();
                getToolkit().beep();
            }
        } else if (type == 1) {
            if((evt.getKeyChar() != '.' && !Character.isDigit(evt.getKeyChar())) || (input.getText().length() >= length)){
                evt.consume();
                getToolkit().beep();
            }
        }

    }



    private void generarEscenari()
    {
        //parametres inicials
        int n = Integer.parseInt(nInput.getText());
        int m = Integer.parseInt(mInput.getText());
        int seed = Integer.parseInt(seedInput.getText());


    }

    private void generarEstatsSolucioInicials()
    {
        //funcio estats solucio incials
        //estatsSolucioList.getSelectedItem();
    }

    private void executarHillClimbing()
    {
        //funcio successors
        //successorsListSim.getSelectedItem();

        //heuristic
        //heuristicsList.getSelectedItem();
    }

    private void executarSimulatedAnnealing()
    {
        //parametres incials
        int k = Integer.parseInt(kInput.getText());
        int iteracions = Integer.parseInt(itInput.getText());
        int stepsIteracio = Integer.parseInt(stepsItInput.getText());
        double lambda = Double.parseDouble(lambdaInput.getText());

        //funcio successors
        //successorsListSim.getSelectedItem();

        //heuristic
        //heuristicsList.getSelectedItem();
    }
}
