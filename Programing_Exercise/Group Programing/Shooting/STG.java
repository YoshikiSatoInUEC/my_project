import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/*ゲーム画面に関するクラス*/
class CharactorController extends JPanel implements ActionListener                                                    
{
    /*フィールド*/
    private Model model;       //Modelのデータを取り込む
    private ArrayList<Enemy> enemy;  //敵格納配列定義
    private ArrayList<EBullet> ebullet; //敵弾格納用配列定義
    private ArrayList<JBullet> jbullet; //自機弾格納用配列定義
    private javax.swing.Timer timer; //timer(敵機の動作に関するtimer)
    public int time =0;             //timerを以下でtimeに換算。初期値は0
    protected int i = 0;
    
    boolean jshotflag = false;
    boolean jrflag = false;      //右ボタンに関するフラグ
    boolean jlflag = false;      //左ボタンに関するフラグ
    boolean jdflag = false;      //下ボタンに関するフラグ
    boolean juflag = false;      //上ボタンに関するフラグ
    boolean flag_timer = false;  //timerに関するフラグ
    boolean gameover = false;    //gameoverか否か判定するフラグ

    
    
    /*コンストラクタ*/
    public CharactorController()
    {
	timer = new javax.swing.Timer(50,this);    //timerにtimer挿入(0.05秒おき)

	model = new Model();  //modelオブジェクトの生成
	
	this.setBackground(Color.BLACK);  //背景設定
	this.setFocusable(true);          //キー入力をGUI部品が受け付ける

    }

    /*timerをスタートさせるメソッド*/
    public void timerstart()
    {
	timer.start();  //timerをスタート
    }

    /*timerを止めるメソッド*/
    public void timerstop()
    {
	timer.stop();
    }
    
	
    /*描画*/
   protected void paintComponent(Graphics g)
    {
      super.paintComponent(g);
       
      /*自機の描画*/
      model.drawJiki(g);

      /*ライフの描画*/
      model.drawLife(g);

      enemy = model.getEnemys();
      /*敵機の描画*/
       for(Enemy e:enemy){
	   e.draw(g);
       }

       ebullet = model.getBullets();
       /*敵弾の描画*/
       for(EBullet eb:ebullet){
	   eb.draw(g);
       }

       jbullet = model.getjBullets();
       /*自機の弾の描画*/
       for(JBullet jb:jbullet){
	   jb.draw(g);
       }
       
    }
    

    /*timerに対するactionPerformed(敵の生成)*/
    public void actionPerformed(ActionEvent e)
    {
	time++;                    //timer1を0.1秒に換算
	
	model.JikiMove(jrflag,jlflag,jdflag,juflag);
	if(jshotflag==true && time%5==0)
	    {
		model.createBullet(model.xJiki()+model.wJiki()/3,model.yJiki(),1);  //自機弾の発射
		model.createBullet(model.xJiki()-model.wJiki()/3,model.yJiki(),1);  //自機弾の発射
	    }
		
	Game();

	/*敵機の出現*/
	if(time == 10)   //1秒たったら
	    {
		for(int i=-320;i<=0;i+=80)
		    model.createEnemy(100,i,1);    

		for(int i=-320;i<=0;i+=80)
		    model.createEnemy(350,i,1);    
	    }
	if(time == 150)
	    {
		for(int i=-320;i<=0;i+=80)
		    model.createEnemy(50,i,2);    

		for(int i=-320;i<=0;i+=80)
		    model.createEnemy(400,i,3);    
	    }
	if(time == 200)
	    for(int i=-160;i<=0;i+=80)
		model.createEnemy(300,i,1);
	if(time == 230)
	    for(int i=-160;i<=0;i+=80)
		model.createEnemy(150,i,1);
	if(time == 260)
	    for(int i=-160;i<=0;i+=80)
		model.createEnemy(350,i,3);
	if(time == 290)
	    for(int i=-160;i<=0;i+=80)
		model.createEnemy(100,i,2);
	if(time == 360)
	    for(int i=-160;i<=0;i+=80)
		model.createEnemy(270,i,1);

	


	model.EnemyMove();
	model.BulletMove();
	model.HitCheck();
	model.DeleteFigure();

	repaint();                         //そして再描画
	
    }

    /*GameOver画面に遷移するためのメソッド*/
    public int Game()
    {
	if(model.GameOver() == true && i==0)
	    {
		i++;
	    }
	else if(time == 400)
	    {
		i++;
		i++;
	    }
	return i;
    }
    
}

    


