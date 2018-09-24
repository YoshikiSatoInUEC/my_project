import java.awt.*;
import javax.swing.*;

class CombiFrame2 extends JFrame 
{
    public CombiFrame2()
    {
	/*JPanelの生成*/
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();


	/*JPanelに貼り付けるためのJButtonを用意*/
	JButton b[] = new JButton[15];  

	for(int i=0;i<10;i++)
	    b[i] = new JButton(i+1+"");

	for(int i=11;i<=15;i++)    
	    b[i-1] = new JButton("<html>"
			       +"<span style='background-color:#ffff00;'>"
			       +i
			       +"</span>"
			       +"</html>");


	/*文字入力の部品TextAreaの設定*/
	JTextArea t = new JTextArea(10,20);

	/*タイトル設定*/
	this.setTitle("Panel Combination2");
  
	/*GridLayoutでボタンを貼り付けるための場所確保*/
	p1.setLayout(new GridLayout(1,10));
	p2.setLayout(new GridLayout(5,1));

	/*ボタン貼り付け*/
	for(int i=0;i<10;i++)
	    p1.add(b[i]);
	for(int i=10;i<15;i++)
	    p2.add(b[i]);

	/*レイアウト*/
	this.add(p1,BorderLayout.NORTH); //1~10までは上に貼り付け
	this.add(p2,BorderLayout.WEST);  //11~15までは左に貼り付け
	this.add(t,BorderLayout.CENTER); //TextAreaを真ん中に貼り付け


	this.pack();	//JFrameのサイズ指定
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
    }

    /*main関数*/
    public static void main(String argv[])
    {
	new CombiFrame2();
    }
}
