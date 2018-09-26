package proyecto0;

import java.awt.*;
import javax.swing.*;

public class ImagenCoche extends JLabel {

	private Coche coche;
	
	public ImagenCoche(ImageIcon ic, Coche coche) {
		super(ic);
		this.coche = coche;
	}
	
	@Override
	protected void paintComponent(Graphics gr) {
		Graphics2D g = (Graphics2D) gr;
		g.rotate(-Math.PI * (coche.getAngulo() - 90) / 180, this.getWidth() / 2, this.getHeight() / 2);
		super.paintComponent(g);
	}
}
