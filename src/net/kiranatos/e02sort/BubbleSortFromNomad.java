package net.kiranatos.e02sort;

/*
Основа из
NomadRussian : https://www.youtube.com/playlist?list=PLQ3XyBdF2zAQnVIkZZHgABg4OBrRH92ES
Пишем змейку на Java 
press "R" - reset
press "S" - start/stop
*/

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BubbleSortFromNomad extends JPanel implements ActionListener {
	Timer 			timer 		= 	new Timer(1000/60, this);
	
	private int[] 	varibles 	= 	new int[32];
	private boolean processing 	= 	false;
	
	private int red0 = -1;
	private int red1 = 0;
	private int aT = 1; 
	private int aK = 0;
	
	private Color currentColor = Color.RED;
	
	public BubbleSortFromNomad() {
		varibles = getRandomArray();
		timer.start();
		addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()){
				case KeyEvent.VK_R:
					processing = false;
					varibles = getRandomArray();
					currentColor = Color.RED;
					aK = 0;
					aT = 0;
					red0 = -1;
					red1 = -1;
					break;
				case KeyEvent.VK_S:
					tick = 0;
					done = 0;
					processing = true;
					break;
				}
			}
			public void keyReleased(KeyEvent e) {
			}
			public void keyTyped(KeyEvent e) {				
			}
		});
		setFocusable(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for(int i = 0; i < 32; i++) {
			if((i == red0 || i == red1) && red0 >= 0) {
				g.setColor(currentColor);
			} else {
				g.setColor(Color.BLACK);
			}
			g.fillRect(i*30, 540, 24, -varibles[i]);
			g.setColor(Color.RED);
			g.setFont(new Font("Tahoma", Font.PLAIN, 12));
			g.drawString(String.valueOf(varibles[i]), i*30+2, 16);
		}
		if(currentColor == Color.GREEN) {
			for(int j = 0; j < aK; j++) {
				g.setColor(Color.GREEN);
				g.fillRect(j*30, 540, 24, -varibles[j]);
			}
		}
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("BubbleSort");
		f.setSize(960, 540);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.add(new BubbleSortFromNomad());
		f.setVisible(true);
                f.setAlwaysOnTop(true);
	}

	int tick = 0;
	int done = 0;
	
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		if(processing) {
			int wrongPos = 0;
			if(varibles[tick] > varibles[tick+1]) {
				currentColor = Color.RED;
				red0 = tick+1;
				red1 = tick+2;
				int j = varibles[tick];
				varibles[tick] = varibles[tick+1];
				varibles[tick+1] = j;
			} else {
				currentColor = Color.YELLOW; 
			}
			for(int i = 0; i < 31; i++) {
				if(varibles[i] > varibles[i+1]) {
					wrongPos++;
				}
			}
			if(wrongPos == 0) {
				processing 	= false;
				currentColor = Color.GREEN;
			}
		}
		tick++;
		if(tick > 30-done) {
			tick = 0;
			done++;
		}
		if(currentColor == Color.GREEN) {
			if(aT > 0) {
				aT--;
			}
			if(aK < 32 && aT == 0) {
				aK++;
				aT = 1;
			}
		}
	}
	
	private int[] getRandomArray() {
		int[] array = new int[32];
		for(int i = 0; i < 32; i++) {
			array[i] = (int)((Math.random()+0.1)*480);
		}
		return array;
	}
}
