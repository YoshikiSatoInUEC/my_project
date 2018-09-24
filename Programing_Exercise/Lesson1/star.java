class Star {
    public static void main(String[] args){
	TurtleFrame f;
	f = new TurtleFrame();
	Turtle m = new Turtle();
	f.add(m);
	for(int i=1;i<=5;i++)
	    {
		m.rt(360*3/5);
		m.fd(300);
	    }
    }
    }
