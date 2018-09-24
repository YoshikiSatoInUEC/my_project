class A
{
    public A()
    {
	System.out.println("A()");
    }
}

class B extends A
{
    public B()
    {
	System.out.println("B()");
    }
    public B(int x)
    {
	System.out.println("B(int)");
    }
}

class C extends B
{
    public C()
    {
	System.out.println("C()");
    }
    public C(int x)
    {
	super(x);
	System.out.println("C(int)");
    }
    public C(double x)
    {
	this();
	System.out.println("C(double)");
    }
}

class main2 extends C
{
    public static void main (String argv[])
    {
	System.out.println("1.new C();");
	new C();
	System.out.println("2.new C(1);");
	new C(1);
	System.out.println("3.new C(1.0);");
	new C(1.0);
    }
}
