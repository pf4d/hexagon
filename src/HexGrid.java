//HexGrid.java
//Evan Cummings
//11.10.9
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

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Dimension;

public class HexGrid extends JFrame
{
	public static void main(String[]args)
	{
		Toolkit toolkit =  Toolkit.getDefaultToolkit ();
      Dimension dim = toolkit.getScreenSize();
				
		JFrame frame = new JFrame("Hex Grid");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new GamePanel());
		frame.setLocation((dim.width-640)/2, (dim.height-480)/2);
		frame.setResizable(true);
		frame.setVisible(true);
		frame.pack();
	}
}
