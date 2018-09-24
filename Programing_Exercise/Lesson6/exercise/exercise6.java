import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

/*Figureクラス*/
class Figure 
{
    protected int x,y,size;

    /*オブジェクト生成*/
    Figure()
    {
	x = (int)(Math.random()*450);                //x座標
	y = (int)(Math.random()*450);                //y座標
	size = (int)(Math.random()*30+20);           //サイズ
    }
    void draw(Graphics g){}                //drawメソッド(以下で再定義)
    void fall(){y++;}                      //図形を下に動かすfallメソッド
}

/*Figureクラスを継承したCircleクラス*/
class Circle extends Figure
{
    void draw (Graphics g)           //drawメソッドの再定義
    {
	g.setColor(Color.RED);       //色を赤に指定して
	g.drawOval(x,y,size,size);   //円を描く
    }
}

/*Figureクラスを継承したBoxクラス*/
class Box extends Figure
{
    void draw (Graphics g)           //drawメソッドの再定義
    {
	g.setColor(Color.BLUE);      //色を青に指定して
	g.drawRect(x,y,size,size);   //正方形を描く
    }
}

/*図形を表示されるパネルであるJPanelを継承したMovingPanelクラス*/
class MovingPanel extends JPanel implements ActionListener
{
    private final static int NUM = 50;   //定数の定義
    private ArrayList<Figure> fig;       //可変長配列定義
    private javax.swing.Timer timer;     //timer定義

    /*オブジェクト生成*/
    MovingPanel()
    {
	timer = new javax.swing.Timer(10,this);
	fig = new ArrayList<Figure>();
	for(int i=0;i<NUM;i++)
	    {	
		fig.add(new Box());
		fig.add(new Circle());
	    }
	timer.start();

    }

    /*paintComponentをオーバーライドして、描画*/
    public void paintComponent(Graphics g)
    {
	super.paintComponent(g);
	for(Figure f :fig)
	    {
		f.draw(g);
	    }
    }

    /*actionPerformed定義*/
    public void actionPerformed(ActionEvent ev)
    {
	for(Figure f : fig)
	    {	
		f.fall();
		this.repaint();
	    }  

    }
}

/*JFrameを継承したRandomFrameクラス*/
class MovingFrame extends JFrame
{
    public MovingFrame()
    {
	this.setTitle("Moving Frame");
	this.setSize(500,530);
	this.add(new MovingPanel());
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
    }
    public static void main(String argv [])
    {
	new MovingFrame();
    }
}
