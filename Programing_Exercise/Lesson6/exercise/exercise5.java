import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class OmikujiFrame extends JFrame implements ActionListener
{
    private JLabel label;
    private Timer timer;

    /*Frame定義*/
    public OmikujiFrame()                        
    {
	this.setSize(300,200);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setTitle("OmikujiFrame");

	label = new JLabel(" ",JLabel.CENTER); 
	this.add(label,BorderLayout.CENTER);

	timer = new Timer(100,this);
	timer.start();

	this.setVisible(true);
    }

    /*ボタン押したときのイベント定義*/
    public void actionPerformed(ActionEvent ev) 
    {
    double r = Math.random();              //乱数設定
	if(r>0.7)
	    {
		label.setText("good");
	    }
	else if(r<=0.7&&r>0.2)
	    {
		label.setText("so so");
	    }
	else
	    {
		label.setText("bad");
	    }
    }


    /*main関数*/
    public static void main (String argv[])    
    {
	new OmikujiFrame();
    }
}
