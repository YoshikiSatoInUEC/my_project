import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/*Observableクラス*/
class RadixObservable extends Observable
{
    private String value = "";  //フィールド

    /*文字列をgetするメソッド*/
    public String getValue(int n)
    {
	int d= Integer.parseInt(value);  //valueにある数をint型に
	return Integer.toString(d,n);    //n進数で返す
    }

    /*文字列をsetするメソッド*/
    public void setValue(String s,int n)
    {
	int d = Integer.parseInt(s,n); //1度10進数に
	value = Integer.toString(d);   //valueに格納
	setChanged();
	notifyObservers();
    }
}

/*2進数のObserverクラス*/
class RadixObserver2 extends JTextField implements Observer,ActionListener
{
    /*フィールド*/
    private RadixObservable radixobservable;  
    private int no;

    /*コンストラクタ*/
    public RadixObserver2(RadixObservable ro,int n)
    {
	no = n;
	radixobservable = ro;
	radixobservable.addObserver(this);   //Observer登録
	this.addActionListener(this);        //ActionListener登録
    }

    /*updateメソッド(valueに保存された文字列を表示)*/
    public void update(Observable o,Object arg)
    {
	String s = radixobservable.getValue(2);
	setText(s);
    }

    /*actionPerformedメソッド(入力された文字列をvalueに保存し、update呼び出し)*/
    public void actionPerformed(ActionEvent e)
    {
	String s = this.getText();
	radixobservable.setValue(s,no);
    }


}

/*8進数のクラス*/
class RadixObserver8 extends JTextField implements Observer,ActionListener
{
    /*フィールド*/
    private RadixObservable radixobservable;  
    private int no;

    /*コンストラクタ*/
    public RadixObserver8(RadixObservable ro,int n)
    {
	no = n;
	radixobservable = ro;
	radixobservable.addObserver(this);   //Observer登録
	this.addActionListener(this);        //ActionListener登録
    }


    /*updateメソッド(valueに保存された文字列を表示)*/
    public void update(Observable o,Object arg)
    {
	String s = radixobservable.getValue(no);
	setText(s);
    }

    /*actionPerformedメソッド(入力された文字列をvalueに保存し、update呼び出し)*/
    public void actionPerformed(ActionEvent e)
    {
	String s = this.getText();
	radixobservable.setValue(s,no);
    }


}

/*10進数のクラス*/
class RadixObserver10 extends JTextField implements Observer,ActionListener
{
    /*フィールド*/
    private RadixObservable radixobservable;  
    private int no;

    /*コンストラクタ*/
    public RadixObserver10(RadixObservable ro,int n)
    {
	no = n;
	radixobservable = ro;
	radixobservable.addObserver(this);   //Observer登録
	this.addActionListener(this);        //ActionListener登録
    }


    /*updateメソッド(valueに保存された文字列を表示)*/
    public void update(Observable o,Object arg)
    {
	String s = radixobservable.getValue(no);
	setText(s);
    }

    /*actionPerformedメソッド(入力された文字列をvalueに保存し、update呼び出し)*/
    public void actionPerformed(ActionEvent e)
    {
	String s = this.getText();
	radixobservable.setValue(s,no);
    }

}

/*16進数のクラス*/
class RadixObserver16 extends JTextField implements Observer,ActionListener
{
    /*フィールド*/
    private RadixObservable radixobservable;  
    private int no;

    /*コンストラクタ*/
    public RadixObserver16(RadixObservable ro,int n)
    {
	no = n;
	radixobservable = ro;
	radixobservable.addObserver(this);   //Observer登録
	this.addActionListener(this);        //ActionListener登録
    }


    /*updateメソッド(valueに保存された文字列を表示)*/
    public void update(Observable o,Object arg)
    {
	String s = radixobservable.getValue(no);
	setText(s);
    }

    /*actionPerformedメソッド(入力された文字列をvalueに保存し、update呼び出し)*/
    public void actionPerformed(ActionEvent e)
    {
	String s = this.getText();
	radixobservable.setValue(s,no);
    }


}


/*メインクラス*/
class Radix extends JFrame
{
    /*フィールド*/
    JPanel panel1,panel2;

    /*オブジェクト*/
    public Radix()
    {
    	/*JPanel代入*/
	panel1 = new JPanel();
	panel2 = new JPanel();

	/*それぞれのパネルをGridLayoutで*/
	panel1.setLayout(new GridLayout(4,1));
	panel2.setLayout(new GridLayout(4,1));

	RadixObservable r = new RadixObservable();

	/*PanelにLabel*/
	panel1.add(new JLabel("16"));
	panel1.add(new JLabel("10"));
	panel1.add(new JLabel("8"));
	panel1.add(new JLabel("2"));

	/*PanelにObserverつきText(RadixObserverクラス)代入*/
	panel2.add(new RadixObserver16(r,16));
	panel2.add(new RadixObserver10(r,10));
	panel2.add(new RadixObserver8(r,8));
	panel2.add(new RadixObserver2(r,2));

	/*panel貼り付け*/
	this.setLayout(new GridLayout(1,2));
	this.add(panel1);
	this.add(panel2);

	/*Frame設定*/
	this.pack();
	this.setSize(100,200);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
	this.setTitle("Radix Frame");
    }
 
    /*mainメソッド*/
	public static void main (String argv[])
	{
	    new Radix();
	}
}
