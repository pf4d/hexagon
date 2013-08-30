// GamePanel.java
// Evan Cummings
// 11.22.9

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GamePanel extends JPanel
{
	// Panel for the grid and controls.
	private JPanel controlPanel, colorPanel;
	private HexGridPanel hexGridPanel;
	
	private JButton green, blue, brown, chooser;
	
	private JLabel title;
	
	private int h;
	
	// Constructor
	public GamePanel()
	{
		MyListener listener = new MyListener();
		Dimension dim = new Dimension(80, 20);
		h = HexGridPanel.HEIGHT;
		
		hexGridPanel = new HexGridPanel();
		controlPanel = new JPanel();
		
		colorPanel = new JPanel();
		colorPanel.setPreferredSize(new Dimension(300, 100));
		
		controlPanel.setPreferredSize(new Dimension(100, h));
		controlPanel.setBackground(Color.black);
		
		title = new JLabel("Pick your Color");
		title.setForeground(Color.gray);
		
		brown = new JButton("BROWN");
		green = new JButton("GREEN");
		blue = new JButton("BLUE");
		chooser = new JButton("PICK");
		
		brown.setPreferredSize(dim);
		green.setPreferredSize(dim);
		blue.setPreferredSize(dim);
		chooser.setPreferredSize(dim);
		
		brown.addActionListener(listener);
		green.addActionListener(listener);
		blue.addActionListener(listener);
		chooser.addActionListener(listener);
				
		controlPanel.add(title);
		controlPanel.add(brown);
		controlPanel.add(green);
		controlPanel.add(blue);
		controlPanel.add(chooser);
		
		this.add(hexGridPanel);
		this.add(controlPanel);
	}
	
	// ButtonListener
	private class MyListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == chooser)
			{
				Color shade = JColorChooser.showDialog(new JFrame(), 
															"Pick a Color!",
															hexGridPanel.getColor());
				hexGridPanel.setColor(shade);
			}
			
			if (e.getSource() == brown)
			{
				hexGridPanel.setColor(new Color(102,51,0));
			}
			
			if (e.getSource() == green)
			{
				hexGridPanel.setColor(new Color(0,75,0));
			}
			
			if (e.getSource() == blue)
			{
				hexGridPanel.setColor(Color.cyan);
			}
		}
	}
}