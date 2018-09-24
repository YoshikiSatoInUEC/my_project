class Eratos
{
    public static void main(String argv[])
    {
	int a[]=new int[201];
	for(int i=2;i<=200;i++)
	    a[i]=0;
	for(int i=2;i<=200;i++)
	    if(a[i]==0)
		{
		    for(int j=2;i*j<=200;j++)
			a[i*j]=1;
		}
	for(int i=2;i<=200;i++)
	    if(a[i]==0)
		System.out.println(i);
    }
}
