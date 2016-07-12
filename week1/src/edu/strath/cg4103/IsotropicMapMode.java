package edu.strath.cg4103;

import java.awt.*;
import java.awt.event.*;

class IsotropicCanvas extends Canvas{
	private boolean mousepressed = false;
	int mouse_x, mouse_y;
	int centerX, centerY;
	float pixelSize;
	float rWidth = 10.0F, rHeight = 10.0F;
	
	public IsotropicCanvas(){
		addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				mousepressed = true;
				mouse_x = e.getX();
				mouse_y = e.getY();
				// cause paint() to be called
				repaint();
			}
		});
	}
	private void init(){
		Dimension d = getSize();
		pixelSize = Math.max(rWidth/(d.width - 1), rHeight/(d.height - 1));
		centerX = (d.width - 1)/2; centerY = (d.height - 1)/2;
	}
	
	float roundx(int x){ return (x - centerX) * pixelSize; }
	float roundy(int y){ return (centerY - y) * pixelSize; }
	int roundx(float x){ return Math.round(centerX + x/pixelSize); }
	int roundy(float y){ return Math.round(centerY - y/pixelSize); }
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		init();
		int left = roundx(-rWidth/2), right = roundx(rWidth/2);
		int bottom = roundy(-rHeight/2), top = roundy(rHeight/2);
		int xmid = roundx(0F), ymid = roundy(0F);
		
		g.drawLine(xmid, bottom, right, ymid);
		g.drawLine(right, ymid, xmid, top);
		g.drawLine(xmid, top, left, ymid);
		g.drawLine(left, ymid, xmid, bottom);
		
		if(mousepressed)
			g.drawString("Logical Coordinates: x = " + mouse_x
					+ " y = " + mouse_y, 20, 100);
	}
}

public class IsotropicMapMode extends Frame {
	public IsotropicMapMode(){
		super("Isotropic Mapping Mode");
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				dispose();
				System.exit(0);
			}
		});
		setSize(600, 400);
		add("Center", new IsotropicCanvas());
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		setVisible(true);
	}
	public static void main(String[] args) {
		new IsotropicMapMode();
	}
}