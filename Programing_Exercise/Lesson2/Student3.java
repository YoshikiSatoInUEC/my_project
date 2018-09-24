class Student {
    private String id;    // 学籍番号
    private String name;  // 名前
    private int grade;    // 成績

    void print() {
      System.out.println("ID   : "+id);  
      System.out.println("Name : "+name);
      System.out.println("Grade: "+grade);

    }

      String getId()   { return this.id;   }
      String getName() { return this.name; }
      int    getGrade(){ return this.grade;}

    public Student (String id,String name,int grade)
    {
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

class Lesson
{
    private String name;   //課題名
    private String teacher;//担当者
    private int max;       //最大履修者数
    private int num;       //登録履修者数
    private Student st[];  //Student class の配列

    public Lesson(String name,String teacher,int max)
    {
	this.name = name;
	this.teacher=teacher;
	this.max=max;
	this.num=0;       //numは0に初期化．
	st=new Student[max];//配列の確保（オブジェクトは別に生成する必要がある）
    }
    public void add(Student s)
    {
	st[this.num++] = s;
    }
    public void print()
    {
	System.out.println("Lesson   :"+name);
	System.out.println("Teacher  :"+teacher);
	System.out.println("#Students:"+num);

	for(int i=0;i<num;i++)
	    {
		st[i].print_short();
	    }
	System.out.println("---------");
    }

    public void sort_id()
    {
	Student t;
	int i,j;
	String a[]=new String[num],b;

	for(int k=0;k<num;k++)
	    {
	    if(st[k]!=null)
	    a[k] = st[k].getId();
	    }


	for(i=num;i>0;i--)
	    {
	    for(j=1;j<i;j++)
		if(a[j-1].compareTo(a[j])>0)
		    {
			t=st[j-1];
			st[j-1]=st[j];
			st[j]=t;
   
			b=a[j-1];
			a[j-1]=a[j];
			a[j]=b;
    
		    }
	    }
    }

  
    public void sort_name()
    {
	Student t;
	int i,j;
	String a[]=new String[num],b;

	for(int k=0;k<num;k++)
	    {
	    if(st[k]!=null)
	    a[k] = st[k].getName();
	    }


	for(i=num;i>0;i--)
	    {
	    for(j=1;j<i;j++)
		if(a[j-1].compareTo(a[j])>0)
		    {
			t=st[j-1];
			st[j-1]=st[j];
			st[j]=t;
   
			b=a[j-1];
			a[j-1]=a[j];
			a[j]=b;
    
		    }
	    }
    }

    public void sort_grade()
    {
	Student t;
	int i,j;
	int a[]=new int[num],b;

	for(int k=0;k<num;k++)
	    {
	    if(st[k]!=null)
	    a[k] = st[k].getGrade();
	    }


	for(i=num;i>0;i--)
	    {
	    for(j=1;j<i;j++)
		if(a[j-1]<a[j])
		    {
			t=st[j-1];
			st[j-1]=st[j];
			st[j]=t;
   
			b=a[j-1];
			a[j-1]=a[j];
			a[j]=b;
    
		    }
	    }
    }





}

class main {

    public static void main(String argv[]){
	Lesson pro = new Lesson("Pro Enshu","Yanai",100);
	pro.add(new Student("012211","Dentsu Taro",80));
	pro.add(new Student("012215","Uec Jiro",54));
	pro.add(new Student("012217","Chofu Saburo",70));
	pro.add(new Student("012210","Enshu Shiro",60));

	pro.sort_id();
	pro.print();

	System.out.println();

	pro.sort_name();
	pro.print();

	System.out.println();

	pro.sort_grade();
	pro.print();
    }
}
