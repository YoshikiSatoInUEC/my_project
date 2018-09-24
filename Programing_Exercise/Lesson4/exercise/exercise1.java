class Student
{
    private String id;     //学籍番号
    private String name;   //名前
    private int grade;     //成績

    /*情報表示*/
    void print()  
    {
	System.out.println("ID         : "+this.id);
	System.out.println("Name       : "+this.name);
	System.out.println("Grade      : "+this.grade);
    }

    /*ID設定*/
    void setId(String id)  
    {
	this.id = id;
    }

    /*名前設定*/
    void setName(String name)
    {
	this.name = name;
    }

    /*成績設定*/
    void setGrade(int grade)
    {
	this.grade = grade;
    }
}


/*Studentクラスを継承したStudent3*/
class Student3 extends Student
{
    private String course;  //コース

    /*コース設定*/
    void setCourse(String course)
    {
	this.course = course;
    }

    /*情報表示*/
    void print()
    {
	super.print();            //Studentクラスのprintメソッド呼び出し
	System.out.println("Course     : "+this.course);   
    }

}


class Student4 extends Student3
{

    private String supervisor;  //指導教員名

    void setSupervisor(String supervisor)
    {
	this.supervisor = supervisor;
    }

    void print()
    {
	super.print();            //Studentクラスのprintメソッド呼び出し
	System.out.println("Supervisor : "+this.supervisor);
    }

}

class main 
{
    public static void main(String argv[])
    {
	Student3 st3 = new Student3();
	Student4 st4 = new Student4();

	/*3年生の情報設定*/
	st3.setId("1410055");
	st3.setName("佐藤 禎紀");
	st3.setGrade(3);
	st3.setCourse("メディア情報学");

	/*4年生の情報設定*/
	st4.setId("1410055");
	st4.setName("佐藤 禎紀");
	st4.setGrade(3);
	st4.setCourse("経営情報学");
	st4.setSupervisor("由良憲二");

	/*情報表示*/
	System.out.println("[3年生]");
	st3.print();
	System.out.println("[4年生]");
	st4.print();
    }
}
