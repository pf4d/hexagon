//Hexagon.java
//Evan Cummings
//11.10.9
//
// http://www.gamedev.net/reference/articles/article1800.asp

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.awt.Polygon;

public class Hexagon
{
	private int h, r, b, a, s, x, y;
	private Polygon p;
	private int[] xPts;
	private int[] yPts;
	private Color c = new Color(0, 150, 0);
	
	// Constructor.
	public Hexagon(int x, int y, int s)
	{
		this.s = s;
		this.x = x;
		this.y = y;
		
		h = (int)((float)Math.sin(Math.PI/6) * s);
		r = (int)((float)Math.cos(Math.PI/6) * s);
		b = s + 2 * h;
		a = 2 * r;
		
		int p1X, p2X, p3X, p4X, p5X, p6X;
		int p1Y, p2Y, p3Y, p4Y, p5Y, p6Y;
		
		p1X = x;
		p2X = x+r;
		p3X = x+2*r;
		p4X = x+2*r;
		p5X = x+r;
		p6X = x;
		
		p1Y = y+h;
		p2Y = y;
		p3Y = y+h;
		p4Y = y+h+s;
		p5Y = y+2*h+s;
		p6Y = y+h+s;
		
		int []xPts = {p1X, p2X, p3X, p4X, p5X, p6X};
		int []yPts = {p1Y, p2Y, p3Y, p4Y, p5Y, p6Y};
		
		this.xPts = xPts;
		this.yPts = yPts;
		
		p = new Polygon(xPts, yPts, 6);
	}
	
	public int getB()
	{
		return b;
	}
	
	public int getA()
	{
		return a;
	}
	
	public int getH()
	{
		return h;
	}
	
	public int getR()
	{
		return r;
	}
	
	public int getS()
	{
		return s;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public Color getColor()
	{
		return c;
	}
	
	public void setColor(Color c)
	{
		this.c = c;
	}
	
	// Draws hexagon polygon object.
	public void drawHex(Graphics page)
	{
		fillHex(page, c);
		page.setColor(Color.black);
		page.drawPolygon(p);
	}
	
	// Fills hexagon polygon object.
	public void fillHex(Graphics page, Color c)
	{
		this.c = c;
		page.setColor(c);
		page.fillPolygon(p);
	}
	
	public String toString()
	{
		String str = "";
		
		str += "Side: " + Integer.toString(s);
		str += "\nH: " + Integer.toString(h);
		str += "\nR: " + Integer.toString(r);
		str += "\nA: " + Integer.toString(b);
		str += "\nB: " + Integer.toString(a);
		
		return str;
	}
}