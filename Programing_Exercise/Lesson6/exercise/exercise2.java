class ToStringText1
{
    public static void main(String argv[])
    {
	A1 a = new A1();
	System.out.println(a);
    }
}

class A1{}


class ToStringText2
{
    public static void main (String argv[])
    {
	A2 a = new A2();
	System.out.println(a);
    }
}

class A2
{
    public String toString()
    {
	return"Hey, I'm class A2";
    }
}
