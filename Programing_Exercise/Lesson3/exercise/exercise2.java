import javax.swing.*;
import java.awt.*;

class LabelFrame extends JFrame
{
    public LabelFrame() //Frame設定
    {
	this.setSize(400,400);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JLabel l1 = new JLabel(""      
		 +"<html>"
                 +"<img src='file:./Sample/lion.jpg' width=100 height=100 >"
                 +"</html>"
                 ,JLabel.RIGHT);
	this.add(l1,BorderLayout.SOUTH);
	JLabel l2 = new JLabel(""
         	 +"<html>"
                 +"<img src='file:./Sample/lion.jpg' width=100 height=100 >"
                 +"</html>"
                 ,JLabel.LEFT);
	this.add(l2,BorderLayout.NORTH);
	JLabel l3 = new JLabel(""
         	 +"<html>"
                 +"<img src='file:./Sample/lion.jpg' width=100 height=100 >"
                 +"</html>"
                 ,JLabel.CENTER);
	this.add(l3,BorderLayout.CENTER);



	this.setVisible(true);
    }

    public static void main(String argv[])
    {
	new LabelFrame();
    }
}
