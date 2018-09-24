class Icosagon {
    public static void main(String[] args){
	TurtleFrame f;
	f = new TurtleFrame();
	Turtle m = new Turtle();
	f.add(m);
	for(int i=1;i<=20;i++)
	    {
		m.rt(18);
		m.fd(50);
	    }
    }
    }
