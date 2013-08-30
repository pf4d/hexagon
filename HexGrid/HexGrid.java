//HexGrid.java
//Evan Cummings
//11.10.9

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