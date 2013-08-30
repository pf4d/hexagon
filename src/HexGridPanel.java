//HexGridPanel.java
//Evan Cummings
//11.10.9

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class HexGridPanel extends JPanel
{	
	// Screen and hex side dimensions.
	private final int WIDTH = 640;
	protected static final int HEIGHT = 480;
	private final int S = 20;
	
	//	Example hex object for calculations.
	private Hexagon hexEx = new Hexagon(0, 0, S);
	
	// Hex calculations.
	private int h = hexEx.getH();
	private int r = hexEx.getR();
	private int b = hexEx.getB();
	private int a = hexEx.getA();
	private int s = hexEx.getS();
	private int x = hexEx.getX();
	private int y = hexEx.getY();
	
	// Gradient of angled hex edge.
	float m = (float)h / r; 
	
	// Number of hexagons on the screen.
	private int numRow = HEIGHT / (h + S);
	private int numCol = WIDTH / a;
	
	// 2D array of hex objects.
	private Hexagon [][] grid = new Hexagon [numRow][numCol];
	
	// point used for display of x, y coordinates.
	private Point p = null;
	
	// Dimension of reference grid.
	private int sectX, sectY;
	
	// Coordinates within reference grid.
	private int sectPxlX, sectPxlY;
	
	// Type of reference Box.
	private char sectTyp = ' ';
	
	// Final hex array coordinates.
	private int arrayX, arrayY;
	
	// Color to fill.
	protected Color color = Color.cyan;
		
	// Constructor
	public HexGridPanel()
	{
		setPreferredSize(new Dimension(WIDTH -8, HEIGHT +13));
		setBackground(Color.black);
		
		MyMouseListener mListener = new MyMouseListener();
		addMouseMotionListener (mListener);
		addMouseListener (mListener);

		System.out.println(hexEx);
		System.out.println("\nNumRows: " + numRow +
			"\nNumCols: " + numCol);
		
		// array loader
		for (int r = 0; r < numRow; r++)
		{
			for (int c = 0; c < numCol; c++)
			{
				int PxX = c * 2 * this.r + (r & 1) * this.r;
				int PxY = r * (h + s);
				grid[r][c] = new Hexagon(PxX, PxY, S);
			}
		}
	}
	
	// Draw each individual hexagon object
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		
		// draw hexes
		page.setColor(new Color(50,50,50));
		for (int r = 0; r < numRow; r++)
		{
			for (int c = 0; c < numCol; c++)
			{
				grid[r][c].drawHex(page);
			}
		}
		
		// print out coordinates for testing
	/*	if (p != null)
      {
         page.setColor (Color.yellow);
			
      	if (p.x > WIDTH-42)
			{
         	page.drawString (p.x + " " + p.y, p.x-42, p.y);
				page.drawString (sectX + " " + sectY, p.x-42, p.y-10);
				page.drawString (sectPxlX + " " + sectPxlY, p.x-42, p.y-20);
				page.drawString (arrayX + " " + arrayY, p.x-42, p.y);
				page.drawString (Character.toString(sectTyp), p.x-42, p.y-40);
    		}
			     
         else
			{
         	page.drawString (p.x + " " + p.y, p.x, p.y);
				page.drawString (sectX + " " + sectY, p.x, p.y-10);
				page.drawString (sectPxlX + " " + sectPxlY, p.x, p.y-20);
				page.drawString (arrayX + " " + arrayY, p.x, p.y);
				page.drawString (Character.toString(sectTyp), p.x, p.y-40);
			}
		} */
	}
	
	// Gets current color.
	public Color getColor()
	{
		return color;
	}
	
	// Sets color.
	public void setColor(Color c)
	{
		color = c;
	}
	
	// Evaluate Hex Coordinates.
	public Point getHexCoord(Point p)
	{
		sectX = (int)p.getX() / (2 * r);
		sectY = (int)p.getY() / (h + s);
		
		sectPxlX = (int)p.getX() % (2 * r);
		sectPxlY = (int)p.getY() % (h + s);
		
		if ((sectY & 1) == 0) 
			sectTyp = 'A';
		else 
			sectTyp = 'B';
		
		if (sectTyp == 'A')
		{
			// middle
			arrayY = sectY;
			arrayX = sectX;
			// left Edge
			if (sectPxlY < (h - sectPxlX * m))
			{
			  arrayY = sectY - 1;
			  arrayX = sectX - 1;
			}
			// right Edge
			if (sectPxlY < (-h + sectPxlX * m))
			{
			  arrayY = sectY - 1;
			  arrayX = sectX;
			}
		}
		
		if (sectTyp == 'B')
		{
			// right side
			if (sectPxlX  >= r)
			{
				if (sectPxlY < (2 * h - sectPxlX * m))
				{
					arrayY = sectY - 1;
					arrayX = sectX;
				}
				else
				{
					arrayY = sectY;
					arrayX = sectX;
				}
			}
			// left side
			if (sectPxlX < r)
			{
				if (sectPxlY < (sectPxlX * m))
				{
					arrayY = sectY - 1;
					arrayX = sectX;   
				}
				else
				{
					arrayY = sectY;
					arrayX = sectX - 1;
				}
			}
		}
		
		Point temp = new Point(arrayX, arrayY);
		return temp;
	}
	
	// Mouse input events.
	private class MyMouseListener implements MouseListener,
														  MouseMotionListener
	{
		// Set color variable for hex coordinate.
		public void mousePressed(MouseEvent e) 
		{			
			p = e.getPoint();
			p = getHexCoord(p);
			
			int hexX = (int)p.getX();
			int hexY = (int)p.getY();
			
			if (hexY < numRow && hexX < numCol &&
				 hexY >= 0 && hexX >= 0)
				grid[hexY][hexX].setColor(color);
			
			repaint();
		}
		
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
		
		// Display hex coordinates under mouse cursor.
		public void mouseMoved(MouseEvent e) 
		{
			p = e.getPoint();
			repaint();
		}
		
		// Set color variable for hex coordinate.
		public void mouseDragged(MouseEvent e)	
		{
			p = e.getPoint();
			p = getHexCoord(p);
			
			int hexX = (int)p.getX();
			int hexY = (int)p.getY();
			
			if (hexY < numRow && hexX < numCol &&
				 hexY >= 0 && hexX >= 0)
				grid[hexY][hexX].setColor(color);
			
			repaint();
		}
	}
}