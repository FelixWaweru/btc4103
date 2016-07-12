package edu.strath.cg4103;

import java.awt.*;
import java.awt.event.*;

class AnisotropicCanvas extends Canvas{
	private boolean mousepressed = false;
	int maxX, maxY;
	// These capture the position of the mouse
	float mouse_x, mouse_y;
	float pixelWidth, pixelHeight;
	// Below, anisotropic mapping scales not necessarily
	// equal
	float rWidth = 10.0F;
	float rHeight = 7.5F;
	public AnisotropicCanvas(){
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				mousepressed = true;
				mouse_x = roundx(e.getX());
				mouse_y = roundy(e.getY());
				repaint(); // cause the paint() 
				           // method to be called.
			}
		});
	}
	
	float roundx(int x){ return x * pixelWidth; }
	float roundy(int y){ return (maxY - y) * pixelHeight; }
	int roundx(float x){ return Math.round(x/pixelWidth); }
	int roundy(float y){ return maxY - Math.round(y/pixelHeight); }
	
	private void init(){
		Dimension d = getSize();
		maxX = d.width - 1; maxY = d.height - 1;
		pixelWidth = rWidth / maxX; pixelHeight = rHeight / maxY;
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		init();
		int left = roundx(0.0F), right = roundx(rWidth);
		int bottom = roundy(0.0F), top = roundy(rHeight);
		if(mousepressed)
			g.drawString("Logical coordinates of selected point: x="
					+ mouse_x + " y=" + mouse_y, 20, 100);
		g.setColor(Color.RED);
		g.drawLine(left, bottom, right, bottom);
		g.drawLine(right, bottom, right, top);
		g.drawLine(right, top, left, top);
		g.drawLine(left, top, left, bottom);
	}
}

public class AnisotropicMapMode extends Frame {
	public AnisotropicMapMode(){
		super("Anisotropic Mapping Mode.");
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dispose();
				System.exit(0);
			}
		});
		setSize(400, 300);
		add("Center", new AnisotropicCanvas());
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		setVisible(true);
	}
	public static void main(String[] args) {
		new AnisotropicMapMode();
	}
}