import java.util.*;

/*Studentクラス*/
class Student 
{
    /*フィールド*/
    private String id;    // 学籍番号
    private String name;  // 名前
    private int grade;    // 成績

    /*学籍番号、名前、成績表示*/
    void print() 
    {
      System.out.println("ID   : "+id);  
      System.out.println("Name : "+name);
      System.out.println("Grade: "+grade);
    }

    /*学籍番号を返す関数の設定*/
      public String getId()   { return this.id;}
 
    /*オブジェクト*/
    public Student (String id,String name,int grade)
    {
	/*成績の範囲指定*/
	if(grade<=0)
	    {
		grade=0;
	    }
        if(grade>=100)
	    {
		grade=100;
	    }

	this.id=id;
	this.name=name;
	this.grade=grade;
    }

    public void print_short() //Studentオブジェクト中身表示
    {
	System.out.println(id+","+name+","+grade);
    }
}

/*Lessonクラス*/
class Lesson
{
    private String name;   //課題名
    private String teacher;//担当者
    private int max;       //最大履修者数
    private int num;       //登録履修者数
    ArrayList<Student> st;  //Student class の配列

    /*オブジェクト*/
    public Lesson(String name,String teacher,int max)
    {
	this.name = name;
	this.teacher=teacher;
	this.max=max;
	this.num=0;       //numは0に初期化．
	st = new ArrayList<Student>();//配列の確保（オブジェクトは別に生成する必要がある）
    }

    /*生徒の情報を追加*/
    public void add(Student s)
    {
	String s0 = s.getId();
	num++;

	for(int i=0;i<st.size();i++)
	    {
		Student s1 = st.get(i);
		if(s0.compareTo(s1.getId())<0)  //s0よりIDが大きいとき
		    {
			st.add(i,s);
			return;
		    }
	    }
	st.add(s);
    }


    /*授業の情報を表示*/
    public void print()
    {
	System.out.println("Lesson   :"+name);
	System.out.println("Teacher  :"+teacher);
	System.out.println("#Students:"+num);

	for(int i=0;i<num;i++)
	    {
		Student s = st.get(i);
		s.print_short();
	    }
	System.out.println("---------");
    }
}


class main {

    public static void main(String argv[]){
	Lesson pro = new Lesson("Pro Enshu","Yanai",100);
	pro.add(new Student("012211","Dentsu Taro",80));
	pro.add(new Student("012215","Uec Jiro",54));
	pro.add(new Student("012217","Chofu Saburo",70));
	pro.add(new Student("012210","Enshu Shiro",60));

	pro.print();

	System.out.println();

    }
}
