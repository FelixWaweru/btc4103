package edu.strath.cg4103;

import java.awt.*;
import java.awt.event.*;

class CheckerboardCanvas extends Canvas {
	// The checkerboard has 8x8 boxes.
	private final static int checkers = 8;
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Dimension d = getSize();
		int side_dimension = (((d.height / checkers) > (d.width / checkers)) ? 
				(d.width / checkers) : (d.height / checkers));
		createCheckers(g, 0, 0, checkers, side_dimension);
	}
	// create a checkerboard of 'nxn' squares of side 'w' pixels
	// starting from position '(x,y)' on the canvas.
	private void createCheckers(Graphics g, int x, int y, int n, int w){
		for(int i = 0; i < n; ++i){
			for(int j = 0; j < n; ++j){
				g.setColor((i + n - j) % 2 == 0? 
						Color.lightGray : Color.darkGray);
				g.fillRect(x + i * w, y + j * w, w, w);
			}
		}
	}
}
public class DrawCheckerboard extends Frame {
	// Below is the constructor
	public DrawCheckerboard(int dimensions){
		super("My Checkerboard"); // Set the title
		setSize(dimensions - 30, dimensions); //Set the size of canvas 
		add("Center", new CheckerboardCanvas());
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dispose();
				System.exit(0);
			}
		});
		setVisible(true);
	}
	public static void main(String[] args) {
		// draw a checkerboard of dimensions 600x600
		new DrawCheckerboard(600); // see line 31
	}

}
