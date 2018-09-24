class TextPolymorph
{
    public static void main (String argv[])
    {
	A a;
	B b;
	C c;

	a = new A();
	a.g();          //f1 A f2 A 表示

	b = new B();
	b.g();          //f1 B f2 A 表示

	c = new C();
	c.g();          //f1 B f2 C 表示

	a = new B();
	a.g();          //f1 B f2 A 表示

	a = new C();
	a.g();          //f1 B f2 A 表示
    }
}

class A 
{
    public void f1()
    {
	System.out.println("f1 A");
    }
    public void f2()
    {
	System.out.println("f2 A");
    }
    public void g()
    {
	f1();
	f2();
    }
}

class B extends A
{
    public void f1()
    {
	System.out.println("f1 B");
    }
}

class C extends B
{
    public void f2()
    {
	System.out.println("f2 C");
    }
}
