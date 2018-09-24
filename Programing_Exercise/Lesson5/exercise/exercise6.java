import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;



/*色の表示*/
class ColorPanel extends JPanel
{
    public ColorPanel()
    {
	this(Color.white);
    }

    public ColorPanel(Color c)
    {
	setColor(c);
	setPreferredSize(new Dimension(400,150));
    }

    public void setColor(Color c)
    {
	setBackground(c);
    }

    public Color getColor()
    {
	return getBackground();
    }
}
   
/*メインクラス*/
class ColorFrame extends JFrame implements ActionListener,ChangeListener
{
    private int i1,i2,i3;
    ColorPanel c;
    JPanel p1,p2,p3,p4;
    Color initColor;
     JTextField text1,text2,text3;
    JSlider sl1,sl2,sl3;

    public ColorFrame()
    {
	/*フィールド*/
	i1 = 0;
	i2 = 0;
	i3 = 0;
	initColor = new Color(i1,i2,i3);
	c = new ColorPanel(initColor);

	p1 = new JPanel();
        p2 = new JPanel();
	p3 = new JPanel();
	p4 = new JPanel();
	text1 = new JTextField(10);
	text2 = new JTextField(10);
	text3 = new JTextField(10);

	sl1=new JSlider(JSlider.HORIZONTAL,0,255,0);
	sl2=new JSlider(JSlider.HORIZONTAL,0,255,0);
	sl3=new JSlider(JSlider.HORIZONTAL,0,255,0);
	
	sl1.setPaintTicks(true);
	sl1.setMajorTickSpacing(50);
	sl1.setMinorTickSpacing(10);
	sl1.setLabelTable(sl2.createStandardLabels(50));
	sl1.addChangeListener(this);	
	sl1.setPaintLabels(true);
	
	sl2.setPaintTicks(true);
	sl2.setMajorTickSpacing(50);
	sl2.setMinorTickSpacing(10);
	sl2.setLabelTable(sl2.createStandardLabels(50));
	sl2.setPaintLabels(true);
	sl2.addChangeListener(this);
	
	sl3.setPaintTicks(true);
	sl3.setMajorTickSpacing(50);
	sl3.setMinorTickSpacing(10);
	sl3.setLabelTable(sl3.createStandardLabels(50));
	sl3.setPaintLabels(true);
	sl3.addChangeListener(this);

	this.setTitle("TestFrame");

	p1.add(c,BorderLayout.CENTER);

	this.add(p2,BorderLayout.SOUTH);
	p2.setLayout(new GridLayout(1,2));
	p2.add(p3);
	p2.add(p4);

	p3.setLayout(new GridLayout(3,1));
	p3.add(sl1);
	p3.add(sl2);
	p3.add(sl3);

	p4.setLayout(new GridLayout(3,1));
	p4.add(text1);
	p4.add(text2);
	p4.add(text3);
	text1.addActionListener(this);
	text2.addActionListener(this);
	text3.addActionListener(this);    
	
	this.add(p1,BorderLayout.CENTER);
	this.add(p2,BorderLayout.SOUTH);	
	this.pack();
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
    }

    public void stateChanged(ChangeEvent e)
    {
	String s;
	if(e.getSource()==sl1)
	    {
		i1 = sl1.getValue();
		s = Integer.toString(i1,16);
		text1.setText(s);
	    }
	else if(e.getSource()==sl2)
	    {
		i2 = sl2.getValue();
		s = Integer.toString(i2,16);
		text2.setText(s);
	    }
	else if(e.getSource()==sl3)
	    {
		i3 = sl3.getValue();
		s = Integer.toString(i3,16);
		text3.setText(s);
	    }

	initColor = new Color(i1,i2,i3);
	c.setColor(initColor);
    }
    
    public void actionPerformed(ActionEvent e)
    {
	String s;
	if(e.getSource()==text1)
	    {
		s = text1.getText();  
		i1 = Integer.parseInt(s,16);
		sl1.setValue(i1);
	    }
	else if(e.getSource()==text2)
	    {
		s = text2.getText();  
		i2 = Integer.parseInt(s,16);
		sl2.setValue(i2);
	    }

	else if(e.getSource()==text3)
	    {
		s = text3.getText();  
		i3 = Integer.parseInt(s,16);
		sl3.setValue(i3);
	    }
	initColor =new Color(i1,i2,i3);
	c.setColor(initColor);
    }

    public static void main(String argv[])
    {
	new ColorFrame();
    }
}
