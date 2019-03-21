package src;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.Random;

public class MapCanvas extends JPanel {

    private Graphics g;

    public MapCanvas() {

        this.setBounds(0,0,500,500);
        //this.setBackground(Color.CYAN);

        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()));
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        //g.setColor(Color.GREEN);
        //g.drawOval(500,500, 5,5);
        this.g = g;
        drawQuad();
        //dibuix();
    }

    private void drawPoint(int x, int y, Color color) {
        g.setColor(color);
        g.fillOval(x,y,5,5);
    }

    private void drawLine(int x1, int y1, int x2, int y2, Color color) {
        g.setColor(color);
        g.drawLine(x1,y1,x2,y2);
    }

    private void drawQuad() {
        for (int i = 5; i <= 500; i=i+5) {
            drawLine(i,0,i,500, Color.BLACK);
            drawLine(0, i,500,i, Color.BLACK);
        }
    }
    private void dibuix() {
        for(int i = 0; i < 400; ++i) {
            Random rn = new Random();
            int x = rn.nextInt(100);
            int y = rn.nextInt(100);
            drawPoint(5*x,y*5,Color.BLUE);
        }
    }

}
