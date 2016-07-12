package edu.strath.cg4103;

import java.awt.*;
import java.awt.event.*;

class RectangleCanvas extends Canvas{
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Dimension d = getSize();
		int max_width = d.width - 1, max_height = d.height - 1;
		g.setColor(Color.red);
		g.drawString("Width  (in pixels) = " + d.width,  10, 30);
		g.drawString("Height (in pixels) = " + d.height, 10, 60);
		g.setColor(Color.blue);
		g.drawRect(0, 0, max_width, max_height);
		//!TODO: replace the above line with 
		//       drawLine.
	}
}
public class DrawRectangle extends Frame {
	public DrawRectangle(){
		super("My Rectangle");
		setSize(600, 400);
		add("Center", new RectangleCanvas());
		addWindowListener( new WindowAdapter(){
		public void windowClosing(WindowEvent e){
			dispose();
			System.exit(0);
		}});
		setVisible(true);
	}
	public static void main(String[] args) {
		new DrawRectangle();
	}
}
