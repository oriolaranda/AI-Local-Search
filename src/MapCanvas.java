package src;


import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MapCanvas extends JPanel {

    private Graphics g;
    private int nMapes;

    public MapCanvas() {

        setLayout(null);
        //this.setBounds(0,0,500,2000);
        setBackground(Color.CYAN);
        //setPreferredSize(new Dimension(400,400));
        //this.setSize(500,2000);
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
        setVisible(true);
        nMapes = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        //g.setColor(Color.GREEN);
        //g.drawOval(500,500, 5,5);
        this.g = g;
        drawQuad();
        drawPoint(495, 600, Color.GREEN);

        //dibuix();
    }

    private void drawPoint(int x, int y, Color color) {
        g.setColor(color);
        g.fillOval(x, y, 5, 5);
    }

    private void drawLine(int x1, int y1, int x2, int y2, Color color) {
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }

    private void drawQuad() {
        for (int i = 0; i <= nMapes; i = i + 550) {
            for (int j = 5; j <= 500; j = j + 5) {
                drawLine(j, i, j, i + 500, Color.BLACK);
                drawLine(0, i + j, 500, i + j, Color.BLACK);
            }
        }
    }

    private void dibuix() {
        for (int i = 0; i < 400; ++i) {
            Random rn = new Random();
            int x = rn.nextInt(100);
            int y = rn.nextInt(100);
            drawPoint(5 * x, y * 5, Color.BLUE);
        }
    }

}
