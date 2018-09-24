import java.awt.*;
import javax.swing.*;
import java.util.*;

/*Figureクラス*/
class Figure 
{
    protected int x,y,size;
    private final static Color cl[]={Color.RED,Color.BLUE,
				     Color.GREEN,Color.BLACK,Color.ORANGE};
    protected Color color;

    Figure()
    {
	x = (int)(Math.random()*450);                //x座標
	y = (int)(Math.random()*450);                //y座標
	size = (int)(Math.random()*30+20);           //サイズ
	color = cl[(int)(Math.random()*cl.length)];  //色
    }
    void draw(Graphics g){}                //drawメソッド(以下で再定義)
}

/*Figureクラスを継承したCircleクラス*/
class Circle extends Figure
{
    void draw (Graphics g)           //drawメソッドの再定義
    {
	g.setColor(color);       //色を赤に指定して
	g.drawOval(x,y,size,size);   //円を描く
    }
}

/*Figureクラスを継承したBoxクラス*/
class Box extends Figure
{
    void draw (Graphics g)           //drawメソッドの再定義
    {
	g.setColor(color);      //色を青に指定して
	g.drawRect(x,y,size,size);   //円を描く
    }
}

/*Figureクラスを継承したCrossクラス*/
class Cross extends Figure
{
    void draw (Graphics g)           //drawメソッドの再定義
    {
	g.setColor(color);
	g.drawLine(x,y,x+size,y+size);
	g.drawLine(x+size,y,x,y+size);
    }
}

/*Figureクラスを継承したTriangleクラス*/
class Triangle extends Figure
{
    void draw (Graphics g)           //drawメソッドの再定義
    {
	g.setColor(color);
	g.drawLine(x+size/2,y,x,y+size);
	g.drawLine(x,y+size,x+size,y+size);
	g.drawLine(x+size,y+size,x+size/2,y);
    }
}

/*図形を表示されるパネルであるJPanelを継承したRandomPanelクラス*/
class RandomPanel extends JPanel
{
    private final static int NUM = 25;   //定数の定義
    private ArrayList<Figure> fig;       //可変長配列定義


    RandomPanel()
    {
	fig = new ArrayList<Figure>();
	for(int i=0;i<NUM;i++)
	    {
		fig.add(new Box());
		fig.add(new Circle());
		fig.add(new Cross());
		fig.add(new Triangle());
	    }
    }

    public void paintComponent(Graphics g)
    {
	for(Figure f :fig)
	    {
		f.draw(g);
	    }
    }
}

/*JFrameを継承したRandomFrameクラス*/
class RandomFrame extends JFrame
{
    public RandomFrame()
    {
	this.setTitle("Random Frame");
	this.setSize(500,530);
	this.add(new RandomPanel());
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
    }
    public static void main(String argv [])
    {
	new RandomFrame();
    }
}
