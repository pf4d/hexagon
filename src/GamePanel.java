// GamePanel.java
// Evan Cummings
// 11.22.9

/*
    Copyright (C) <2009>  <cummings.evan@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
