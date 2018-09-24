import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ButtonPanel extends JPanel implements ActionListener
{

    /*JButton,JLabelの定義*/
    private JLabel label;
    private JButton b1,b2,b3;

    ButtonPanel()
    {
	/*オブジェクトの確保*/
	b1 = new JButton("Button 1");
	b2 = new JButton("Button 2");
	b3 = new JButton("Button 3");
	label = new JLabel("0",JLabel.CENTER);

	/*b1,b2,b3が押されるとactionPerformedが呼び出し*/
	b1.addActionListener(this);
	b2.addActionListener(this);
	b3.addActionListener(this);

	/*JButton,JPanelの追加*/
	setLayout(new GridLayout(2,2));
	add(b1);
	add(b2);
	add(b3);
	add(label);
    }


    /*getSourceでどのボタンが押されたか検知し、それぞれに対して処理*/
    public void actionPerformed(ActionEvent e)
    {
	if(e.getSource()==b1)
	    label.setText("1");
	else if(e.getSource()==b2)
	    label.setText("2");
	else if(e.getSource()==b3)
	    label.setText("3");
    }
    
}


class TestButton extends JFrame
{
    public TestButton()
    {
	ButtonPanel b = new ButtonPanel();
	this.setTitle("Test Button");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.add(b);
	this.pack();
	this.setVisible(true);
    }

    public static void main(String argv[])
    {
	new TestButton();
    }
}
