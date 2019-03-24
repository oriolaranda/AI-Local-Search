package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.ArrayList;


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
    private MapCanvas mapCanvas1;

    protected GUIForm()
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
        nInput.setText("200");
        mInput.setText("100");
        seedInput.setText("1234");

        //Buttons
        generarEstatSolucio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        generarEscenari.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        generarEstatSolucio.setEnabled(false);


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
                numberFormatter(e, seedInput,INT,6);
            }
        });



    }

    //initialize hillClimbing Panel
    private void initHillClimbing()
    {
        executarHill.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        executarHill.setEnabled(false);
    }

    //initialize simulatedAnnealing Panel
    private void initSimulatedAnnealing()
    {
        kInput.setText("20");
        itInput.setText("10000");
        stepsItInput.setText("100");
        lambdaInput.setText("0.005");
        executarSim.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        executarSim.setEnabled(false);

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

        mapaInicial = new MapCanvas();

        //Hill Climbing panel
        successorsHill = new JPanel();
        successorsHill.setBorder(BorderFactory.createTitledBorder("Generador estats successors"));

        mapaHill = new MapCanvas();

        //Simulated Annealing panel
        successorsSim = new JPanel();
        successorsSim.setBorder(BorderFactory.createTitledBorder("Generador estats successors"));

        parametresSim = new JPanel();
        parametresSim.setBorder(BorderFactory.createTitledBorder("Paràmetres inicials"));

        mapaSim = new MapCanvas();

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
            if((evt.getKeyChar() != '.' && !Character.isDigit(evt.getKeyChar())) || input.getText().length() >= length || (evt.getKeyChar() == '.' && input.getText().contains("."))){
                evt.consume();
                getToolkit().beep();
            }
        }
    }

    protected boolean faltaOmplirAlgunCampEscenari() {
        return (nInput.getText().length() == 0 || mInput.getText().length() == 0 || seedInput.getText().length() == 0);
    }

    protected boolean faltaOmplirAlgunCampSimulated() {
        return (kInput.getText().length() == 0 || itInput.getText().length() == 0 || stepsItInput.getText().length() == 0 || lambdaInput.getText().length() < 3);
    }

    protected JButton getGenerarEscenari() {
        return generarEscenari;
    }

    protected JButton getGenerarEstatSolucio() {
        return generarEstatSolucio;
    }

    protected JButton getExecutarHill(){
        return executarHill;
    }

    protected JButton getExecutarSim() {
        return executarSim;
    }

    protected int[] getParametresIni()
    {
        //parametres inicials
        int [] v = new int[3];
        v[0] = Integer.parseInt(nInput.getText()); //n
        v[1] = Integer.parseInt(mInput.getText()); //m
        v[2] = Integer.parseInt(seedInput.getText()); //seed
        return v;
    }

    protected int getEstatSolucioInicial(){
        return estatsSolucioList.getSelectedIndex();
    }

    protected int getHeuristic() {
        return heuristicsList.getSelectedIndex();
    }

    protected int getSuccessorsHill() {
        return successorsListHill.getSelectedIndex();
    }

    protected int getSuccessorsSim() {
        return successorsListSim.getSelectedIndex();
    }

    protected void setTextAreaHill(String s) {
        textAreaHill.setText(s);
    }

    protected void setTextAreaSim(String s) {
        textAreaSim.setText(s);
    }

    protected String[] getParametresSimulated()
    {
        String[] v = new String[4];
        v[0] = kInput.getText(); //k
        v[1] = itInput.getText(); //iteracions
        v[2] = stepsItInput.getText(); //steps per iteracio
        v[3] = lambdaInput.getText(); //lambda

        return v;
    }




}
