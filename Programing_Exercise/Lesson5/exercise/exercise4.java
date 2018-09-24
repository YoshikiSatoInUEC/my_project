import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/*丸の描画の設定*/
class CirclePanel extends JPanel implements MouseListener
{

    /*フィールド*/
    private int radius = 5;         //円の半径
    private Color color = Color.red;//円の色
    private int x[],y[],r[];        //すべての丸の位置と半径を記録する位置を配列
    private Color c[];              //丸の色を記録するColorクラス型の配列
    private int num = 0;            //今まで記録した丸の数
    final static int MAX = 5000;    //記録できる丸の最大数(定数)

    /*初期化とMouseListenerの設定*/
    public CirclePanel()
    {
	x=new int[MAX];
	y=new int[MAX];
	r=new int[MAX];
	c=new Color[MAX];
	this.addMouseListener(this);
    }

    /*paintComponentの再定義*/
    public void paintComponent(Graphics g)
    {
	super.paintComponent(g);
	for(int i=0;i<num;i++)
	    {
		g.setColor(c[i]);
		g.fillOval(x[i]-r[i],y[i]-r[i],r[i]*2,r[i]*2);
	    }
    }

    /*丸のデータを保存*/
    private void addCircle(int x0,int y0)
    {
	if(num>=MAX)  return;  //numがMAX以上のときは実行しない
	x[num]=x0;
	y[num]=y0;
	r[num]=radius;
	c[num]=color;
	num++;
	this.repaint();       //JPanel再描画
    }

    /*マウスが押されるとaddCircle呼び出し*/
    public void mousePressed(MouseEvent e)
    {
	addCircle(e.getX(),e.getY());
    }

    /*他のイベントはなし*/
    public void mouseClicked(MouseEvent e){ }
    public void mouseReleased(MouseEvent e){ }
    public void mouseEntered(MouseEvent e){ }
    public void mouseExited(MouseEvent e){ }

    /*色の変更*/
    void setPanelColor(Color c)
    {
	color = c;
    }

    /*半径の値の変更*/
    void setRadius(int r)
    {
	radius = r;
    }

}


/*丸を表示させるフレーム設定*/
class CircleFrame extends JFrame implements ActionListener
{
    /*フィールド*/
    private CirclePanel panel;
    private JPanel p1,p2;
    private JButton b1,b2,b3,b4,b5;

    public CircleFrame()
    {
	/*JPanelの生成*/
	p1 = new JPanel();
	p2 = new JPanel();

	/*JButtonと描画パネルの生成*/
	 b1 = new JButton("YELLOW");
	 b2 = new JButton("BLUE");
	 b3 = new JButton("RED");
	 b4 = new JButton("radius=5");
	 b5 = new JButton("radius=10");
	panel=new CirclePanel();

	/*タイトル設定*/
	this.setTitle("CircleFrame");

	/*JPanelをGridLayoutで設定*/
	p1.setLayout(new GridLayout(3,1));
	p2.setLayout(new GridLayout(2,1));

	/*JPanelにボタンを貼り付け*/
	p1.add(b1);
	p1.add(b2);
	p1.add(b3);
	p2.add(b4);
	p2.add(b5);

	/*ボタンが押されたらAvtionListener*/
	b1.addActionListener(this);
	b2.addActionListener(this);
	b3.addActionListener(this);
	b4.addActionListener(this);
	b5.addActionListener(this);


	/*色のボタンを左側に、半径のボタンを右側に、真ん中には描画パネル*/
	this.add(p1,BorderLayout.WEST);
	this.add(p2,BorderLayout.EAST);
	this.add(panel,BorderLayout.CENTER);

	this.setSize(500,500);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);   
    }

    public void actionPerformed(ActionEvent e)
	{
	    if(e.getSource()==b1)
		panel.setPanelColor(Color.YELLOW);
	    else if(e.getSource()==b2)
		panel.setPanelColor(Color.BLUE);
	    else if(e.getSource()==b3)
		panel.setPanelColor(Color.RED);
	    else if(e.getSource()==b4)
		panel.setRadius(5);
	    else
		panel.setRadius(10);
	}
    



    public static void main(String argv[])
    {
	new CircleFrame();
    }
}
