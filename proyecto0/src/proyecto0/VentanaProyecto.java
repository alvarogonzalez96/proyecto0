package proyecto0;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class VentanaProyecto extends JFrame{

	private JPanel panel;
	private Coche coche;
	
	public VentanaProyecto() {
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		JPanel botonera = new JPanel();
		panel = new JPanel();
		
		JButton botonAcelera, botonFrena, botonGiroDcha, botonGiroIzq;
		
		botonAcelera = new JButton("Acelera");
		botonAcelera.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				coche.acelerar();
				
			}
		});
		
		botonFrena = new JButton("Frena");
		botonFrena.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				coche.frenar();
				
			}
		});
		
		botonGiroDcha = new JButton("Gira dcha.");
		botonGiroDcha.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				coche.girarDerecha();
			}
		});
		
		botonGiroIzq = new JButton("Gira izq.");
		botonGiroIzq.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				coche.girarIzquierda();
				
			}
		});
		
		coche = new Coche(panel);
		JLabel imagen = coche.getImagen();
		panel.add(imagen);
		imagen.setLocation((int) coche.getX(), (int) coche.getY());
		imagen.setSize(coche.getAncho(), coche.getAlto());
		
		botonera.add(botonAcelera);
		botonera.add(botonFrena);
		botonera.add(botonGiroDcha);
		botonera.add(botonGiroIzq);
		
		cp.add(panel, BorderLayout.CENTER);
		cp.add(botonera, BorderLayout.SOUTH);
		
		this.setVisible(true);
		this.setSize(800, 800);
		this.setTitle("Proyecto0 - Coche");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		Thread hilo = new Thread() {
			@Override
			public void run() {
				super.run();
				while(true) {
					coche.update();
					imagen.setLocation((int) coche.getX(), (int) coche.getY());
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		
		hilo.start();
		
	} 
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new VentanaProyecto();
				
			}
		});
	}
}
