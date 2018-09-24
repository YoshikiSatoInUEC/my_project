import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.CardLayout;
import java.util.*;
 
 
/*mainクラス*/
class TitleFrame extends JFrame implements KeyListener,ActionListener
{
    /*フィールド*/
    JPanel p1,p2,p3,p4,p5;     //パネル定義
    CardLayout layout;     //cardlayoutの定義
    boolean boo = false;   //操作を変えるためのフラグ
    boolean flag = false;
    private CharactorController chara;   //ゲーム画面
    int a = 20;
    javax.swing.Timer timer;
    private int time; 
     
    /*コンストラクタ(フレーム内の設定)*/   
    public TitleFrame()
    {
    /*JPanel設定*/
    p1 = new JPanel();     //大本のパネル
    p2 = new JPanel();    //１ページ目のパネル（タイトル画面）
    p3 = new JPanel();    //２ページ目のパネル（ゲーム画面）
    p4 = new JPanel();    //3ページ目のパネル（ゲームオーバー画面）
    p5 = new JPanel();    //4ページ目のパネル（クリア画面）
    chara = new CharactorController();   //2ページ目に貼り付けるゲーム画面
    timer = new javax.swing.Timer(1,this);
    timer.start();
 
    p2.setBackground(Color.BLACK);  //背景設定
    p4.setBackground(Color.BLACK);  //背景設定
    p5.setBackground(Color.BLACK);  //背景設定
     
     
    /*CardLayoutの設定*/
    layout = new CardLayout();
 
    /*KeyListener追加*/
    p1.addKeyListener(this);
    p1.setFocusable(true);
 
    /*p2,p4をBorderLayoutで設定*/
    p2.setLayout(new BorderLayout());
    p4.setLayout(new BorderLayout());
    p5.setLayout(new BorderLayout());
 
    /*p2にJLabelを貼り付ける*/
    JLabel l2 = new JLabel("<html>"
                               +"<span style='font-size:110pt;font-family:Impact,Charcoal;"
                   +"color:yellow;'>"
                   +"Shooting</span></html>",
                   +JLabel.CENTER);               
 
 
    JLabel l3 = new JLabel("<html>"
                   +"<span style='font-size:30pt;color:white;'>"
                   +"十字キーで移動、zで弾発射。<br/>"
                   +"<span style='font-size:30pt;color:white;'>"
                   +"Enterでゲームスタート!!"
                   +"</span></html>",
                   +JLabel.CENTER);
     
    p2.add(l2,BorderLayout.CENTER);
    p2.add(l3,BorderLayout.SOUTH);
 
    /*2ページ目*/
    p3.setLayout(new BorderLayout());
    p3.add(chara,BorderLayout.CENTER);
 
    /*p4にJLabelを貼り付ける*/
    JLabel l4 = new JLabel("<html>"
                               +"<span style='font-size:90pt;font-family:Impact,Charcoal;"
                   +"color:blue;'>"
                   +"GAMEOVER</span></html>",
                   +JLabel.CENTER);               
 
 
    JLabel l5 = new JLabel("<html>"
                   +"<span style='font-size:30pt;color:white'>"
                   +"2秒後、タイトル画面を表示します。"
                   +"</span></html>",
                   +JLabel.CENTER);
     
    p4.add(l4,BorderLayout.CENTER);
    p4.add(l5,BorderLayout.SOUTH);
 
    /*p4にJLabelを貼り付ける*/
    JLabel l6 = new JLabel("<html>"
                               +"<span style='font-size:120pt;font-family:Impact,Charcoal;"
                   +"color:red;'>"
                   +"GAME<br/>CLEAR!!</span></html>",
                   +JLabel.CENTER);               
 
 
    JLabel l7 = new JLabel("<html>"
                   +"<span style='font-size:30pt;color:white'>"
                   +"2秒後、タイトル画面を表示します。"
                   +"</span></html>",
                   +JLabel.CENTER);
 
     
    p5.add(l6,BorderLayout.CENTER);
    p5.add(l7,BorderLayout.SOUTH);
     
    /*p1をCardLayoutで設定し、p1にパネルを貼り付ける。*/
    p1.setLayout(layout);
    p1.add(p2,BorderLayout.CENTER);
    p1.add(p3,BorderLayout.CENTER);
    p1.add(p4,BorderLayout.CENTER);
    p1.add(p5,BorderLayout.CENTER);
    getContentPane().add(p1,BorderLayout.CENTER);           //これがないと表示されない(はず)
 
 
    /*Frame設定*/
    this.setSize(550,550);                                 //Frameは550*550(仮)
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    this.setTitle("Shooting");
    this.setVisible(true);
    }
 
    /*keyListener内のメソッド、keyPressedの設定*/
    public void keyPressed(KeyEvent e)
    {
     
    /*押されたボタンを読み取る*/
    int k = e.getKeyCode();
     
    /*押されたボタンがEnterキーかつフラグbooが降りていたら*/
    if(k == KeyEvent.VK_ENTER && boo ==false)
        {
        layout.next(p1);    //次のページへ移行し、
        boo = true;         //フラグを有効にして操作をゲームの操作にする
        chara.timerstart(); //timerをスタート
        }
     
    /*フラグbooが立っていたら*/
    if(boo == true)
        {
        if(k == KeyEvent.VK_RIGHT)         //右押すと
            chara.jrflag = true;    //CharactorControllerクラスのjrflagが立つ
 
        else if(k == KeyEvent.VK_LEFT)          //左押すと
            chara.jlflag = true;     //CharactorControllerクラスのflflagが立つ

	else if(k == KeyEvent.VK_UP)
	    chara.juflag = true;

	else if(k == KeyEvent.VK_DOWN)
	    chara.jdflag = true;

	else if(k == KeyEvent.VK_Z)
	    chara.jshotflag = true;
        }
    }
 
 
    /*キーをはなした時の操作*/
    public void keyReleased(KeyEvent e)
    {
    int k = e.getKeyCode();             //キーの情報を取得
    if(boo == true)                        //フラグが有効なら
        {
        switch(k)
            {
 
            case KeyEvent.VK_RIGHT:       //右ボタンを離すとjrflag無効
		chara.jrflag = false;
		break;
		
            case KeyEvent.VK_LEFT:         //左ボタンを離すとjlflag無効
		chara.jlflag = false;
		break;
		
	    case KeyEvent.VK_UP:
		chara.juflag = false;
		break;
	 
	    case KeyEvent.VK_DOWN:
		chara.jdflag = false;
		break;

	    case KeyEvent.VK_Z:
		chara.jshotflag = false;
		break;
 
            }
        }
     
    }
 
       /*キーを押したときの操作(弾を出す)*/
    public void keyTyped(KeyEvent e){}
 
    public void actionPerformed(ActionEvent ev)
    {
    if(chara.i == 1)
        {
        layout.next(p1);
        chara.i = 4;
        }
    if(chara.i == 2)
        {
        layout.next(p1);
        layout.next(p1);
        chara.i = 4;
        }
    if(chara.i == 4)
        {
        time++;
        if(time == 2000)
            {
            new TitleFrame();
            timer.stop();
            time = 0;
            setVisible(false);
            }
        }
    }
     
    /*mainメソッド(Frame生成のみ)*/
    public static void main(String argv[])
    {
    new TitleFrame();
    }
}
