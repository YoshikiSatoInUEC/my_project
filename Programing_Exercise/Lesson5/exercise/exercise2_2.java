import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class ButtonPanel extends JPanel implements ActionListener
{

    /*JPanel,JButtonの定義*/
    private JLabel label;
    private JButton b1,b2,b3;

    ButtonPanel()
    {
	/*ボタン、ラベルの設定*/
	b1 = new JButton("Button 1");
	b2 = new JButton("Button 2");
	b3 = new JButton("Button 3");
	label = new JLabel("0",JLabel.CENTER);

	/*ボタンそれぞれに対してActionListenerを設定*/
	b1.addActionListener(this);
	b2.addActionListener(this);
	b3.addActionListener(this);

	/*ActionCommand設定*/
	b1.setActionCommand("1");
	b2.setActionCommand("2");
	b3.setActionCommand("3");

	/*レイアウト*/
	setLayout(new GridLayout(2,2));
	add(b1);
	add(b2);
	add(b3);
	add(label);
    }

    /*actionPerformed設定*/
    public void actionPerformed (ActionEvent e)
    {
	String es = e.getActionCommand(); //どの部品のイベントかという情報を取得
	label.setText(es);                //取得した情報をlabelに出力
    }
}

class TestButton extends JFrame
{
    /*ウィンドウ設定*/
    public TestButton()
    {
	ButtonPanel b = new ButtonPanel();
	this.setTitle("Test Button");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.add(b);
	this.pack();
	this.setVisible(true);
    }

    /*main関数*/
    public static void main (String argv[])
    {
	new TestButton();
    }
}
