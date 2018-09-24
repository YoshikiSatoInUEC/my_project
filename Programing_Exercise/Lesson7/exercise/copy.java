 public void actionPerformed(ActionEvent e)
    {
	/*JTextField hex に対する処理*/
	if(e.getSource()==hex)
	    {
		String h = hex.getText();
		int d = Integer.parseInt(h,16);
		String str = Integer.toString(d,10);
		dec.setText(str);

	    }

	/*JTextField doc に対する処理*/
	else if(e.getSource()==dec)
	    {
		String d = dec.getText();
		int h = Integer.parseInt(d,10);
		String str = Integer.toString(h,16);
		hex.setText(str);
	    }
    }
