package net.kiranatos.e99books.sierra;

import java.awt.*;
import javax.swing.*;

/*
 * страница 428 
 */

public class Animate {
    int x = 1;
    int y = 1;
    public static void main(String[] args) {
        Animate gui = new Animate();
        gui.go();
    }
    
    public void go() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyDrawP drawP = new MyDrawP();
        frame.getContentPane().add(drawP);
        
        frame.setSize(800, 800);
        frame.setVisible(true);
        drawP.setVisible(true);        
        for (int i = 0; i < 124; i++, x++, y++) {
            x++;
            
            drawP.repaint();      
            try {
                Thread.sleep(50);
            }
            catch (Exception ex) {}
        }
    }
    
    class MyDrawP extends JPanel {
        
        public void paint(Graphics g) {
            System.out.println(x);
            g.setColor(Color.red);
            g.fillRect(0, 0, 500, 250);
            g.setColor(Color.blue);
            g.fillRect(x, y, 500-x*2, 250-y*2);            
        }
        
    }
    
}
