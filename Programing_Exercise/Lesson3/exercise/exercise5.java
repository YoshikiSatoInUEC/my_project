import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Radix extends JFrame implements ActionListener
{
    private JFrame f;
    private JTextField hex,dec;

    public Radix()
    {
	hex = new JTextField(3);
	dec = new JTextField(3);

	this.setLayout(new GridLayout(2,2));

	this.add(new JLabel("16"));
	this.add(hex);
	this.add(new JLabel("10"));
	this.add(dec);
	this.pack();

	hex.addActionListener(this);
	dec.addActionListener(this);

	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
	/*JTextField hex に対する処理*/
	if(e.getSource()==hex)
	    {
		String h = hex.getText();
		int d = Integer.parseInt(h,16);
		String str = Integer.toString(d,10);
		dec.setText(str);

	    }

	/*JTextField doc に対する処理*/
	else if(e.getSource()==dec)
	    {
		String d = dec.getText();
		int h = Integer.parseInt(d,10);
		String str = Integer.toString(h,16);
		hex.setText(str);
	    }
    }

	public static void main (String argv[])
	{
	    new Radix();
	}
}
