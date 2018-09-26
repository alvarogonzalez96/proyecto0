package proyecto0;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Coche {

	private double x, y;
	private double velocidad;
	private double angulo;
	private int alto,ancho;
	private ImagenCoche imagen;
	private JPanel panelCoche;
	
	public Coche(JPanel panelCoche) {
		this.x = 100;
		this.y = 100;
		this.velocidad = 0;
		this.angulo = 0; 
		this.ancho = 100;
		this.alto = 120;
		this.panelCoche = panelCoche;
		
		BufferedImage im = null;
		try {
			im = ImageIO.read(new File("/Users/alvaro/Downloads/ud/prog3/pr00/coche.png"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		Image img = im.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		
		ImageIcon icono = new ImageIcon(img);
		imagen = new ImagenCoche(icono, this);
		
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public double getAngulo() {
		return angulo;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public ImagenCoche getImagen() {
		return imagen;
	}

	public void setImagen(ImagenCoche imagen) {
		this.imagen = imagen;
	}

	public JPanel getPanelCoche() {
		return panelCoche;
	}

	public void setPanelCoche(JPanel panelCoche) {
		this.panelCoche = panelCoche;
	}

	public void update() {
		this.x = x + velocidad*Math.sin(Math.PI*angulo/180);
		this.y = y + velocidad*Math.cos(Math.PI*angulo/180);
		imagen.setBounds((int) x, (int) y, ancho, alto);
		
		rebote();
	}
	
	public void rebote() {
		int anchura = panelCoche.getWidth();
		int altura = panelCoche.getHeight();
		
		if(this.x < 0) {
			if(this.angulo <= 270) {
				this.angulo = angulo - 2*(this.angulo - 180);
			} else {
				this.angulo = angulo + 2*(360 - this.angulo); 
			}
		}else if(this.x + this.ancho * Math.sin(Math.PI * this.angulo / 180) > anchura) {
			if(this.angulo > 90) {
				this.angulo = angulo + 2*(180 - this.angulo);
			} else {
				this.angulo = angulo - 2*(this.angulo);
			}
		}
		restarAngulo();
		
		if(this.y < 0) {
			if(this.angulo <= 180) {
				this.angulo = angulo - 2 * (this.angulo - 90);
			} else {
				this.angulo = angulo + 2 * (270 - this.angulo);
			}
		} else if(this.y + this.alto * (Math.cos(Math.toRadians(angulo))) > altura) {
			if(this.angulo >= 90) {
				this.angulo = angulo - 2 * (this.angulo - 270);
			} else {
				this.angulo = angulo + 2 * (90 - this.angulo);
			}
		}
		restarAngulo();
	}
	
	private void restarAngulo() {
		if(this.angulo >= 360) {
			this.angulo = angulo - 360;
		}
		if(this.angulo < 0) {
			this.angulo = angulo + 360;
		}
	}
	
	public void acelerar() {
		this.velocidad = velocidad + 1;
	}
	
	public void frenar() {
		if(velocidad >= 1) {
			this.velocidad = velocidad - 1;
		}
	}
	
	public void girarIzquierda() {
		this.angulo = angulo + 2;
		restarAngulo();
	}
	
	public void girarDerecha() {
		this.angulo = angulo - 2;
		restarAngulo();
	}
	
	@Override
	public String toString() {
		return "Coche [x=" + x + ", y=" + y + ", velocidad=" + velocidad + ", angulo=" + angulo + ", alto=" + alto
				+ ", ancho=" + ancho + ", imagen=" + imagen + ", panelCoche=" + panelCoche + "]";
	}
	
	
}
