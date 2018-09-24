import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/*内容に変化があった場合にObserverに一斉通知するStringObservableクラス*/
class IntObservable extends Observable
{
    private int value = 0;
    
    /*表示する文字列をgetするgetValueメソッド*/
    public String getValue(int n)
    {
	return Integer.toString(value+n);
    }

    /*表示する文字列をsetするsetValueメソッド*/
    public void setValue(String s,int n)
    {
	value = Integer.parseInt(s)-n;
	setChanged();      //状態が変化したことを知らせるフラグセット
	notifyObservers(); //登録されたすべてのObserverオブジェクトのupdateを呼び出し
    }
}

/*テキストを表示するTextFieldObserverクラス*/
class TextFieldObserver extends JTextField implements Observer, ActionListener
{
    private IntObservable intObservable;
    private int no;    //何番目のTextFieldか?

    /*Observerオブジェクト、ActionListenerをセットするコンストラクタ*/
    public TextFieldObserver(IntObservable so,int n)
    {
	no = n;
	intObservable = so;
	intObservable.addObserver(this);  //Observerオブジェクト登録
	this.setFont(new Font(Font.SANS_SERIF,Font.BOLD,26));  //文字を大きくする
	this.addActionListener(this);
    }

    /*notifyObserver();によって呼び出されるupdateメソッド(引数は今回使わない)*/
    public void update(Observable o,Object arg)
    {
	String s=intObservable.getValue(no); //stringObservableから新しい文字列抽出
	setText(s);                           //抽出した文字列を表示
    }

    /*actionPerformed*/
    public void actionPerformed(ActionEvent e)
    {
	String s = this.getText();
	intObservable.setValue(s,no);
    }
}

/*メインクラス*/
class ObserverFrame extends JFrame
{
    /*コンストラクタ*/
    public ObserverFrame()
    {
	this.setTitle("Observer Frame");             //タイトル設定
	this.setLayout(new GridLayout(5,1));         //レイアウト設定
	IntObservable s = new IntObservable(); 

	for(int i=0;i<5;i++)
	    this.add(new TextFieldObserver(s,i));      //全Gridにテキスト挿入

	/*Frame設定*/
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.pack();
	this.setSize(300,300);
	this.setVisible(true);
    }

    /*メインメソッド*/
    public static void main (String argv[])
    {
	new ObserverFrame();
    }
}
