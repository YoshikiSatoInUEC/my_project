class Student {
    private String id;    // 学籍番号
    private String name;  // 名前
    private int grade;    // 成績

    void print() {
      System.out.println("ID   : "+id);  
      System.out.println("Name : "+name);
      System.out.println("Grade: "+grade);

    }
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

}

class main2 {
  public static void main(String argv[]){

    Student st  = new Student("01110","Suzuki",101);
    Student st2 = new Student("01111","Yamada",-1);

    st.print();       
    st2.print();
  }
}
