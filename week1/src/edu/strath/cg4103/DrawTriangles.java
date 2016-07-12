package edu.strath.cg4103;

import java.awt.*;
import java.awt.event.*;

class TrianglesCanvas extends Canvas{
	// These are the coefficients that generate points along 
	// the sides of the triangle for new triangls
	final static float conv_p = (float) 0.05, conv_q = (float) (1.0 - conv_p);
	private int trias;
	public TrianglesCanvas(int trias){
		this.trias = trias;
	}
	
	int max_x, max_y, min_max_xy, x_center, y_center;
	private void init(){
		Dimension d = getSize();
		max_x = d.width - 1;
		max_y = d.height - 1;
		min_max_xy = Math.min(max_x, max_y);
		x_center = max_x / 2;
		y_center = max_y / 2;
	}
	// Below: rounding conversion functions.
	// These convert from logical coordinates to 
	// device coordinates.
	int roundx(float x){return Math.round(x);}
	int roundy(float y){return max_y - Math.round(y);}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		init();
		float side = 0.95F * min_max_xy;
		float side_half = 0.5F * side;
		// The ratio in 30, 60, 90 triangle
		float height = side_half * (float)Math.sqrt(3.0);
		float xA, yA, xB, yB, xC, yC;
		float xA1, yA1, xB1, yB1, xC1, yC1;
		xA = x_center - side_half;
		yA = y_center - 0.5F * height;
		xB = x_center + side_half;
		yB = yA;
		xC = x_center;
		yC = y_center + 0.5F * height;
		for(int i = 0; i < trias; i++){
			g.drawLine(roundx(xA), roundy(yA), roundx(xB), roundy(yB));
			g.drawLine(roundx(xA), roundy(yA), roundx(xC), roundy(yC));
			g.drawLine(roundx(xC), roundy(yC), roundx(xB), roundy(yB));
			xA1 = conv_p * xA + conv_q * xB;
			yA1 = conv_p * yA + conv_q * yB;
			xB1 = conv_p * xB + conv_q * xC;
			yB1 = conv_p * yB + conv_q * yC;
			xC1 = conv_p * xC + conv_q * xA;
			yC1 = conv_p * yC + conv_q * yA;
			xA = xA1; yA = yA1; xB = xB1; yB = yB1;
			xC = xC1; yC = yC1; 
		}
	}
}

public class DrawTriangles extends Frame {
	
	public DrawTriangles(int trias){
		super("Trianglar Hollow!");
		addWindowListener( new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dispose();
				System.exit(0);
			}
		});
		setSize(600, 400);
		add("Center", new TrianglesCanvas(trias));
		setVisible(true);
	}

	public static void main(String[] args) {
		new DrawTriangles(20);
	}

}
