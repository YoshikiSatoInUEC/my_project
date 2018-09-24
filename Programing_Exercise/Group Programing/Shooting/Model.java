import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

//MVCのMの部分をつくる

/*Figureクラス*/
class Figure {
    protected int x,y,width,height,speed;      //ローカル変数定義
    protected Color color;
    
    public Figure(int x,int y,int w,int h,int s,Color c) {
	this.x = x; this.y = y;  // this.x, this.y はフィールド変数を指します．
	width = w; height = h;   // ローカル変数で同名の変数がある場合は，this
	speed = s; color = c;    // を付けると，フィールド変数を指すことになります．
    }

    
    /*drawメソッド定義*/
    public void draw(Graphics g) {}
}

class Life extends Figure{ //ライフ
    protected int count;

    public Life(int x,int y, int l){
	super(x,y,10,10,0,Color.white);
	count = l;
    }

    public void draw(Graphics g){
	g.setColor(color);
	for(int i=0;i<count;i++)
	    g.fillRect(x+i*15,y,width,height);
    }
}

class Jiki extends Figure{ //自機クラス
    public Jiki(int x,int y,int w,int h,int s,Color c){
	super(x,y,w,h,s,c); //親コンストラクタの呼び出し
    }

    
    /*自機の描画*/
    public void draw(Graphics g){
	g.setColor(color);
	int xPoints[] = {x,x-width/8,x-width/2,x+width/2,x+width/8};
	int yPoints[] = {y-height/2,y,y+height/4,y+height/4,y};	
	g.fillPolygon(xPoints, yPoints,5);

	g.fillRect(x+width/8,y+height/4,width/8,height/8);
	g.fillRect(x-width/4,y+height/4,width/8,height/8);
    }
}



class Enemy extends Figure{ //敵機クラス
    /*フィールド*/
    protected int type;
    protected boolean dflag; //消滅フラグ
    protected int shotcount;
    
    /*コンストラクタ*/
    public Enemy(int x,int y,int w,int h,int s,int t,Color c){
	super(x,y,w,h,s,c);     //親クラス呼び
	type = t; //敵のタイプ
	dflag = false;
	shotcount = 0;
    }

    /*敵機の描画*/
    public void draw(Graphics g){
	g.setColor(color);
	g.fillOval(x,y,width,height);
	g.fillOval(x+width/2,y+height/2,width,height);
	g.fillOval(x-width/2,y+height/2,width,height);
    }

}

class JBullet extends Figure{ //弾クラス
    /*フィールド*/
    protected boolean dflag;  //消滅フラグ
    protected int type;   //敵弾の種類
    
    /*コンストラクタ*/
    public JBullet(int x,int y,int t){	
	super(x-3,y-3,10,20,13,Color.blue);
	type = t;
	dflag = false;
    }

    /*弾の描画*/
    public void draw(Graphics g){
	g.setColor(color);
	g.fillOval(x,y,width,height);
    }
}


class EBullet extends Figure{ //弾クラス
    /*フィールド*/
    protected boolean dflag;  //消滅フラグ
    protected int type;   //敵弾の種類
    
    /*コンストラクタ*/
    public EBullet(int x,int y,int t){	
	super(x-3,y-3,10,10,4,Color.red);
	type = t;
	dflag = false;
    }

    /*弾の描画*/
    public void draw(Graphics g){
	g.setColor(color);
	g.fillOval(x,y,width,height);
    }
}


/*Modelクラス*/
class Model {
    protected Jiki jiki;             //自機
    protected Life life;             //自機のライフ
    protected ArrayList<Enemy> enemy;  //敵機格納用の配列
    protected ArrayList<JBullet> jbullet; //自機弾格納用の配列
    protected ArrayList<EBullet> ebullet; //敵機弾格納用の配列
    protected Enemy drawEnemy;
    protected boolean gameover;
    
    /*オブジェクト*/
    public Model(){
	jiki = new Jiki(200,200,32,48,10,Color.yellow);   //自機の設定
	life = new Life(10,10,3);          //ライフ3からスタート
	/*配列格納*/
	enemy = new ArrayList<Enemy>();     //Enemy型の配列(敵)
	jbullet = new ArrayList<JBullet>();   //Bullet型の配列(自機弾)
	ebullet = new ArrayList<EBullet>();   //Bullet型の配列(敵機弾)
	drawEnemy = null;
    }

    /*自機関連のメソッド*/
    /*自機のdrawメソッド*/
    public void drawJiki(Graphics g){ 
	jiki.draw(g);
    }
    /*自機の移動*/
    public void JikiMove(boolean r,boolean l,boolean d,boolean u){
	if(r==true && jiki.x <= 540) jiki.x += jiki.speed;
	if(l==true && jiki.x >= 10) jiki.x -= jiki.speed;
	if(d==true && jiki.y <= 530) jiki.y += jiki.speed;
	if(u==true && jiki.y >= 10) jiki.y -= jiki.speed;
    }
    public int xJiki(){return jiki.x;} //自機の中心x座標を返す
    public int yJiki(){return jiki.y;} //自機の中心y座標を返す
    public int wJiki(){return jiki.width;} //自機の横幅を返す

    /*敵機関連のメソッド*/
    /*敵を記録している配列を返す関数*/
    public ArrayList<Enemy> getEnemys() {
	return enemy;
    }

    /*何番目かを指定して敵を取り出すメソッド*/
    public Enemy getEnemy(int idx) {
	return enemy.get(idx);
    }
    
    public int xEnemy(int idx) {
	return enemy.get(idx).x; //敵機の中心x座標を返す
    }
    public int yEnemy(int idx) {
	return enemy.get(idx).y; //敵機の中心y座標を返す
    } 
	  
    /*敵機の生成*/
    public void createEnemy(int x,int y,int type){
	Enemy e = new Enemy(x,y,20,20,5,type,Color.green);  //敵を生成して   
	enemy.add(e);                                  //配列に格納して
    }

    /*敵機の動作の管理*/
    public void EnemyMove(){
	for(int i=0; i<enemy.size(); i++){
	    switch(enemy.get(i).type){
		
	    case 1:               //敵のタイプが1のとき		
		enemy.get(i).y+=enemy.get(i).speed+1;         //敵機のy座標を増やしていく
		enemy.get(i).shotcount++;                   //shotcountも増やす
		
		if(enemy.get(i).shotcount%50==0||enemy.get(i).shotcount==1)
		    //shotcountが50で割り切れるようになった時、敵機の場所に弾生成
		    {
			this.createBullet(this.xEnemy(i),this.yEnemy(i),10);
			this.createBullet(this.xEnemy(i),this.yEnemy(i),11);
			this.createBullet(this.xEnemy(i),this.yEnemy(i),12);
		    }		
		break;

	    case 2:               //敵のタイプが2のとき
		enemy.get(i).x+=enemy.get(i).speed;         //敵機のx座標を増やしていく
		enemy.get(i).y+=enemy.get(i).speed*2;         //敵機のy座標も増やしていく
		
		enemy.get(i).shotcount++;                   //shotcountを増やす
		if(enemy.get(i).shotcount%50==0||enemy.get(i).shotcount==1)            
		    //shotcountが50で割り切れるようになった時、敵機の場所に弾生成
		    {
			this.createBullet(this.xEnemy(i),this.yEnemy(i),10);
			this.createBullet(this.xEnemy(i),this.yEnemy(i),12);
		    }
		break;

	    case 3:               //敵のタイプが3のとき
		enemy.get(i).x-=enemy.get(i).speed;         //敵機のx座標を増やしていく
		enemy.get(i).y+=enemy.get(i).speed*2;         //敵機のy座標も増やしていく
		
		enemy.get(i).shotcount++;                   //shotcountを増やす
		if(enemy.get(i).shotcount%50==0||enemy.get(i).shotcount==1)            
		    //shotcountが50で割り切れるようになった時、敵機の場所に弾生成
		    {
			this.createBullet(this.xEnemy(i),this.yEnemy(i),10);
			this.createBullet(this.xEnemy(i),this.yEnemy(i),11);
		    }
		break;

	    }
	}
    }

    /*弾関連メソッド*/
    /*弾を記録している配列を返す関数*/
    public ArrayList<EBullet> getBullets() {
	return ebullet;
    }
    public ArrayList<JBullet> getjBullets() {
	return jbullet;
    }

    /*弾の動作の管理*/
    public void BulletMove(){
	for(int i=0; i<jbullet.size(); i++){
	    switch(jbullet.get(i).type){
	    case 1:
		jbullet.get(i).y -= jbullet.get(i).speed;	
		break;

	    case 2:
		jbullet.get(i).y -= jbullet.get(i).speed;
		jbullet.get(i).x += jbullet.get(i).speed;
		break;
		
	    case 3:
		jbullet.get(i).y -= jbullet.get(i).speed;
		jbullet.get(i).x -= jbullet.get(i).speed;
		break;
	    
	    }
	}
	for(int i=0; i<ebullet.size(); i++){
	    switch(ebullet.get(i).type){
	    case 10:
		ebullet.get(i).x += ebullet.get(i).speed;
		ebullet.get(i).y += ebullet.get(i).speed*2;			
		break;

	    case 11:
		ebullet.get(i).x += ebullet.get(i).speed;
		ebullet.get(i).y += ebullet.get(i).speed;			
		break;

	    case 12:
		ebullet.get(i).x -= ebullet.get(i).speed;
		ebullet.get(i).y += ebullet.get(i).speed;			
		break;		
	    }
	}
    }

    
    /*弾の生成*/
    public void createBullet(int x, int y, int t){
	switch(t){
	case 1:
	    JBullet jb1 = new JBullet(x,y,t);
	    jbullet.add(jb1);
	    break;
	    
	case 2:
	    JBullet jb2 = new JBullet(x,y,t);
	    jbullet.add(jb2);
	    break;
	    
	case 3:
	    JBullet jb3 = new JBullet(x,y,t);
	    jbullet.add(jb3);
	    break;
	    

	    
	case 10:
	    EBullet eb10 = new EBullet(x,y,t);
	    ebullet.add(eb10);
	    break;

	case 11:
	    EBullet eb11 = new EBullet(x,y,t);
	    ebullet.add(eb11);
	    break;

	case 12:
	    EBullet eb12 = new EBullet(x,y,t);
	    ebullet.add(eb12);
	    break;
	}
    }

    /*その他*/
    /*衝突時の処理*/
    public void HitCheck(){	
	for(int i=0; i<enemy.size(); i++){
	    for(int j=0; j<jbullet.size(); j++){
		if(HitBox(enemy.get(i).x,enemy.get(i).y,
			  enemy.get(i).width,enemy.get(i).height,
			  jbullet.get(j).x,jbullet.get(j).y,
			  jbullet.get(j).width,jbullet.get(j).height)
		   == true){
		    jbullet.get(j).dflag = true; //敵機に当たった自機弾の消滅フラグを立てる
		    enemy.get(i).dflag = true; //自機弾に当たった敵機の消滅フラグを立てる
		}
	    }
	}
	for(int i=0; i<ebullet.size(); i++){
	    if(HitBox(jiki.x,jiki.y,
		      jiki.width,jiki.height,
		      ebullet.get(i).x,ebullet.get(i).y,
		      ebullet.get(i).width,ebullet.get(i).height)
	       == true){
		ebullet.get(i).dflag = true; //自機に当たった敵機弾の消滅フラグを立てる
		life.count--; //ライフの減少
	    }
	}
	for(int i=0; i<enemy.size(); i++){
	    if(HitBox(jiki.x,jiki.y,
		      jiki.width,jiki.height,
		      enemy.get(i).x,enemy.get(i).y,
		      enemy.get(i).width,enemy.get(i).height)
	       == true){
		enemy.get(i).dflag = true; //自機に当たった敵機の消滅フラグを立てる
		life.count--; //ライフの減少
		GameOver();
	    }   
	}		
    }
    
    /*矩形当たり判定*/
    public boolean HitBox(int x1,int y1,int w1,int h1,
			  int x2,int y2,int w2,int h2){
	if((x1 < x2 + w2) && (x2 < x1 + w1) &&
	   (y1 < y2 + h2) && (y2 < y1 + h1))
	    return true;
	else
	    return false;
    }

		        
    /*消滅の処理*/
    public void DeleteFigure(){
	for(int i=enemy.size()-1; i>=0; i--)
	    if(enemy.get(i).dflag==true)
		enemy.remove(i);
	
	for(int i=ebullet.size()-1; i>=0; i--)
	    if(ebullet.get(i).dflag==true)
		ebullet.remove(i);
	
	for(int i=jbullet.size()-1; i>=0; i--)
	    if(jbullet.get(i).dflag==true||jbullet.get(i).y<=0)
		jbullet.remove(i);
    }

    /*ゲームオーバー*/
    public boolean GameOver()
    {
	if(life.count < 1)
	    {
		return true;
	    }
	else
	    return false;
    }

    /*ライフの初期化*/
    public void LifeInit(){
	life.count = 3;
    }
    /*ライフの描画*/
    public void drawLife(Graphics g){ 
	life.draw(g);
    }
}


