import javax.swing.*;
import java.awt.*;

class GridFrame extends JFrame 
{
    public GridFrame()
    {
	this.setSize(1000,500);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLayout(new GridLayout(10,10));
	for(int i=1;i<=100;i++)
	    {
		this.add(new JButton(i+""));
		this.setVisible(true);
	    }

    }
	public static void main (String argv[])
	{
	    new GridFrame();
	}
}
