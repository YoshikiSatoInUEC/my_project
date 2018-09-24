import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/*Timeobserverableクラス*/
class TimeObservable extends Observable implements ActionListener
{
    /*timer定義*/
    private javax.swing.Timer timer;

    /*現在時間取得し、それぞれ代入*/
    Calendar cal = Calendar.getInstance();
    private int hour= cal.get(Calendar.HOUR_OF_DAY);
    private int min = cal.get(Calendar.MINUTE);
    private int sec = cal.get(Calendar.SECOND);

    String current_time;

    /*コンストラクタ(timer設定)*/
    TimeObservable()
    {
	timer = new javax.swing.Timer(1000,this);
	timer.start();
    }

    /*Timerに対するactionPerformed*/
    public void actionPerformed(ActionEvent e)
    {
	this.setValue();
    }

    /*1秒ごとカウントし、setするsetValueメソッド*/
    public void setValue()
    {
	setChanged();
	notifyObservers();
	sec++;

	if(sec>=60)
	    {
		sec = 0;
		min++;
	    }
	else if(min>=60)
	    {
		min = 0;
		hour++;
	    }
	else if(hour>=24)
	    {
		hour = 0;
	    }
    }

    /*時間を計算するメソッド*/
    public String getValue(int d)
    {
	int hour2;
	hour2 = hour + d;
	if(hour2>=24)
	    hour2 =  hour2 - 24;
	if(hour2<0)
	    hour2 = 24 + hour2;

	current_time = String.format("%02d:%02d:%02d",hour2,min,sec);
	return current_time;
    }
}

/*地名と時間の1セットを定義するクラス*/
class WorldTimeObserver extends JPanel implements Observer
{
    /*フィールド*/
    private TimeObservable timeobservable;
    private String place_name;
    private int diff;
    private JLabel label_place,label_time;

    /*コンストラクタ*/
    public WorldTimeObserver(TimeObservable to,String s,int d)
    {
	timeobservable = to;    //時間
	place_name =s;          //地名
	diff = d;               //時差

	timeobservable.addObserver(this);        //Observerをadd
	this.setLayout(new GridLayout(1,2));     //地名と時間
	Font font = new Font(Font.SANS_SERIF,Font.BOLD,32);

	label_place = new JLabel(place_name,JLabel.CENTER);
	label_time = new JLabel(to.getValue(diff),JLabel.CENTER); 

	label_place.setFont(font);
	label_time.setFont(font);
	this.add(label_place);
	this.add(label_time);
    }

    /*時間を書き換え、再度textにsetするメソッド*/
    public void update(Observable o,Object arg)
    {
	label_time.setText(timeobservable.getValue(diff));
    }
}

/*メインクラス*/
class WorldTimeFrame extends JFrame
{
    /*コンストラクタ*/
    public WorldTimeFrame()
    {
	this.setTitle("WorldTimeFrame");
	this.setLayout(new GridLayout(6,1));
	TimeObservable t = new TimeObservable();

	/*地名と時間をadd*/
	this.add(new WorldTimeObserver(t,"Tokyo",0));
	this.add(new WorldTimeObserver(t,"Beijing",-1));
	this.add(new WorldTimeObserver(t,"Paris",-8));
	this.add(new WorldTimeObserver(t,"London",-9));
	this.add(new WorldTimeObserver(t,"New York",-14));
	this.add(new WorldTimeObserver(t,"Los Angels",-17));

	/*Frame設定*/
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.pack();
	this.setVisible(true);
    }

    /*mainメソッド*/
    public static void main(String argv[])
    {
	new WorldTimeFrame();
    }
}
