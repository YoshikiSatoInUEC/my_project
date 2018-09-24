import javax.swing.*;
import java.awt.*;

class MickeyPanel extends JPanel
{
    public void paintComponent(Graphics g)
    {
	super.paintComponent(g);
	g.fillOval(150,150,200,200);
	g.fillOval(95,96,110,110);
	g.fillOval(297,97,110,110);
    }
}

class MickeyFrame extends JFrame
{
    public MickeyFrame()
    {
	this.setTitle("MickeyFrame");
	this.setSize(500,500);
	this.add(new MickeyPanel());
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
    }
    public static void main (String argv[])
    {
	new MickeyFrame();
    }
}
