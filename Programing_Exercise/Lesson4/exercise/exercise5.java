/*Photoインターフェイス定義*/
interface Photo 
{
    void TakePhoto();
}

/*Cellularクラス定義*/
class Cellular implements Photo
{
    public void MakeCall()
    {
	System.out.println("携帯電話で電話を掛けました．");
    }
    public void TakePhoto()
    {
	System.out.println("携帯電話で写真を撮りました．");
    }

}

/*Cameraクラス定義*/
class Camera implements Photo
{
    public void TakePhoto()
    {
	System.out.println("デジカメで写真を撮りました．");
    }
}

/*VideoCamクラス定義*/
class VideoCam implements Photo
{
    public void RecordMovie()
    {
	System.out.println("ビデオカメラで動画を撮影しました．");
    }
    public void TakePhoto()
    {
	System.out.println("ビデオカメラで写真を撮りました．");
    }
}

/*Androidクラス定義*/
class Android implements Photo
{
    public void TakePhoto()
    {
	System.out.println("スマホで写真を撮りました．");
    }
    public void Browsing()
    {
	System.out.println("スマホでインターネットを見ました．");
    }
}

/*Photographerクラス定義*/
class Photographer
{
    final static int MAX = 20;
    private Photo camera[] = new Photo[MAX];
    private int num=0;

    public void add(Photo p)
    {
	if(num>=MAX) return;
	camera[num++]=p;
    }

    public void TakeAll()
    {
	for(int i=0;i<MAX;i++)
	    {   
		i++;
		System.out.println("["+i+"]");
		i--;
		camera[i].TakePhoto();
	    }
    }

    /*main関数*/
    public static void main(String argv[])
    {
	Photographer p = new Photographer();
	for(int i=0;i<MAX;i++)
	    {
		double r = Math.random();
		if(r<=0.25)
		    p.add(new Cellular());
		else if(r>0.25&&r<=0.50)
		    p.add(new Camera());
		else if(r>0.5&&r<=0.75)
		    p.add(new VideoCam());
		else
		    p.add(new Android());
	    }
	p.TakeAll();
    }

}
