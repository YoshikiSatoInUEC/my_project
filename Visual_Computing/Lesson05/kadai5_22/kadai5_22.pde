    int i=0;
    int x[] = new int[100];
    int y[] = new int[100];

void setup(){
size(640,480);
background(255);
smooth();
}

void draw(){}

void mouseClicked(){
  update(mouseX,mouseY);
}

void update(int p,int q){
  if(mouseButton==LEFT){
    if(i==100){
      i=0;
      background(255);
    }
    if(i%3==1&&i!=1){
    x[i]=x[i-2]+abs(x[i-1]-x[i-2])*2;
    y[i]=y[i-2]+abs(y[i-1]-y[i-2])*2;
    }
    else
    x[i]=p;y[i]=q;
    
    noStroke();
    fill(255,0,0);
    ellipse(x[i],y[i],5,5);
    
    if(i != 0){
      stroke(200);
      line(x[i-1],y[i-1],x[i],y[i]);
      
      if(i%3==0){
        stroke(0,0,255);
        noFill();
        bezier(x[i-3],y[i-3],x[i-2],y[i-2],x[i-1],y[i-1],x[i],y[i]);
      }
    }
    i++;
  }
  else if(mouseButton ==RIGHT){
    i=0;
    background(255);
  }
}