import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;

/*描画した図形を記録する継承して使うFigureクラス(データ構造)*/
abstract class Figure
{
    /*フィールド*/
    protected int x,y,width,height;
    protected Color color;

    /*一度読み込んだ値をクラスの変数に代入するコンストラクタ*/
    public Figure(int x,int y,int w,int h,Color c)
    {
	this.x = x;
	this.y = y;
	width = w;
	height = h;
	color = c;
    }

    /*大きさを変更するメソッド*/
    public void setSize(int w,int h)
    {
	width = w;
	height = h;
    }

    /*位置を変更するメソッド*/
    public void setLocation(int x,int y)
    {
	this.x = x;
	this.y = y;
    }

    /*図形の位置と大きさを設定するメソッド*/
    public void reshape(int x1,int y1,int x2,int y2)
    {
	/*左上の点のx座標とy座標*/
	int xmin = Math.min(x1,x2);
	int ymin = Math.min(y1,y2);

	/*幅と高さ*/
	int w = Math.abs(x1-x2);
	int h = Math.abs(y1-y2);

	/*変更を保存*/
	setLocation(xmin,ymin);
	setSize(w,h);
    }

    abstract public void draw(Graphics g);
}

/*四角形を表すFigureのサブクラス*/
class RectangleFigure extends Figure
{
    /*引数付きのコンストラクタは継承されないためコンストラクタを定義し、superで呼び出す*/
    public RectangleFigure(int x, int y,int w,int h,Color c)
    {
	super(x,y,w,h,c);  
    }

    /*colorで指定した色で四角形を描くメソッド*/
    public void draw(Graphics g)
    {
	g.setColor(color);
	g.drawRect(x,y,width,height);
    }
}

/*Modelクラス*/
class DrawModel extends Observable
{
    /*フィールド*/
    protected ArrayList<Figure> fig;
    protected Figure drawingFigure;
    protected Color currentColor;

    /*コンストラクタ*/
    public DrawModel()
    {
	fig = new ArrayList<Figure>();
	drawingFigure = null;
	currentColor = Color.red;  //初期値は赤
    }

    /*図形を記録しているArrayList(全て)を返すメソッド*/
    public ArrayList<Figure> getFigures()
    {
	return fig;
    }

    /*ArrayListの中のidx番目の図形を取り出すメソッド*/
    public Figure getFigure(int idx)
    {
	return fig.get(idx);
    }

    /*新たに図形をつくって追加するメソッド*/
    public void createFigure(int x,int y)
    {
	Figure f = new RectangleFigure(x,y,0,0,currentColor); 
	                     //(x,y)にh=0,w=0のcurrentColorで指定した色の図形をつくる
	fig.add(f);          //つくった図形をArrayListに記録
	drawingFigure = f;   //drawingFigureにも記録

	/*Observerでmodelの変化をviewに通知し、再描画*/
	setChanged();
	notifyObservers();
    }

    /*操作中の図形の形を変更するメソッド*/
    public void reshapeFigure(int x1,int y1,int x2,int y2)
    {
	if(drawingFigure != null)
	    {
		drawingFigure.reshape(x1,y1,x2,y2);   //x,y座標,w,hの長さを変更し、

		/*Observerでmodelの変化をviewに通知し、再描画*/
		setChanged();
		notifyObservers();
	    }
    }
}

/*Viewクラス*/
class ViewPanel extends JPanel implements Observer
{
    protected DrawModel model;

    /*リスナーオブジェクトと関連づけるためのコンストラクタ*/
    public ViewPanel(DrawModel m,DrawController c)
    {
	this.setBackground(Color.white);
	this.addMouseListener(c);
	this.addMouseMotionListener(c);
	model = m;
	model.addObserver(this);
    }

    /*DrawModelが記録している図形を描画メソッド*/
    public void paintComponent(Graphics g)
    {
	super.paintComponent(g);
	for(Figure f:model.getFigures())
	    {
		f.draw(g);
	    }
    }

    /*Observerで呼び出されるupdateメソッドは再描画のみ*/
    public void update(Observable o,Object arg)
    {
	repaint();
    }
}

/*Controllerクラス*/
class DrawController implements MouseListener ,MouseMotionListener
{
    /*フィールド*/
    protected DrawModel model;
    protected int dragStartX,dragStartY;

    /*オブジェクト*/
    public DrawController (DrawModel a)
    {
	model = a;
    }

    /*MouseListenerのメソッド再定義*/
    public void mouseClicked(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    /*mouseを押したときのメソッド*/
    public void mousePressed(MouseEvent e)
    {
	dragStartX = e.getX();
	dragStartY = e.getY();
	model.createFigure(dragStartX,dragStartY); //押した場所にw=0,h=0の図形を作る
    }

    /*MouseMotionListenerのメソッド再定義*/
    public void mouseMoved(MouseEvent e){}
    public void mouseDragged(MouseEvent e)
    {
	model.reshapeFigure(dragStartX,dragStartY,e.getX(),e.getY());
               	//現在Dragしているところと最初に押し始めたときの点を頂点とする図形を作る
    }
}

    /*mainクラス*/
class DrawFrame extends JFrame
{
    /*フィールド(MVCを定義)*/
    DrawModel model;
    ViewPanel view1,view2,view3,view4;
    DrawController cont;

    /*コンストラクタ*/
    public DrawFrame()
    {
	/*各変数にコンストラクタを代入*/
	model = new DrawModel();
	cont = new DrawController(model);
	view1 = new ViewPanel(model,cont);
	view2 = new ViewPanel(model,cont);
	view3 = new ViewPanel(model,cont);
	view4 = new ViewPanel(model,cont);

	/*ViewPanelに境界線をつける*/
	view1.setBorder(new LineBorder(Color.blue,3));
	view2.setBorder(new LineBorder(Color.blue,3));
	view3.setBorder(new LineBorder(Color.blue,3));
	view4.setBorder(new LineBorder(Color.blue,3));

	/*ViewPanelを4つ生成*/
	this.setLayout(new GridLayout(2,2));
	this.add(view1);
	this.add(view2);
	this.add(view3);
	this.add(view4);

	/*フレーム設定*/
	this.setBackground(Color.black);
	this.setTitle("Ddraw Editor");
	this.setSize(1000,1000);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
    }

    /*mainメソッド*/
    public static void main(String argv[])
    {
	new DrawFrame();
    }
}
