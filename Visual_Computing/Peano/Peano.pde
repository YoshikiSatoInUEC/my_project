int n = 1;
void setup(){
  size(600,600);
  textFont(createFont("Tempus Sans ITC",24));
  fill(0);
  smooth();
}

void drawPeano(int level,float length, float x,float y,float i){
  if(1==level){
    line(x-i*length/2,y+length/2,x-i*length/2,y-length/2);
    line(x-i*length/2,y-length/2,x+i*length/2,y-length/2);
    line(x+i*length/2,y-length/2,x+i*length/2,y+length/2);
    return;
  }
  
  /*Peano*/
  drawPeano(--level,length/=2,x/=2,y/=2,i);
  pushMatrix();
  translate(length*2,0);
  drawPeano(level,length,x,y,i);
  popMatrix();
  
  
  pushMatrix();
  translate(length*2,length*2);
  rotate(PI/2);
  drawPeano(level,length,x,y,-1*i);
  popMatrix();

  pushMatrix();
  translate(length*2,length*4);
  rotate(-PI/2);
  drawPeano(level,length,x,y,-1*i);
  popMatrix();
  
  /*draw line*/
  float l = pow(2.0,level);
  line(length/l,y*2.0-length/l,length/l,y*2.0+length/l);
  line(x*2.0-length/l,y*2.0-length/l,x*2.0+length/l,y*2.0-length/l);
  line(x*4.0-length/l,y*2.0-length/l,x*4.0-length/l,y*2.0+length/l);
  
}  

void mousePressed(){
  if((mouseButton == LEFT) && (n<8)) n++;
  else if((mouseButton == RIGHT) && (n>1)) n--;
}

void draw(){
  background(255);
  text("n = " +n,10,30);
  translate(25,40);
  drawPeano(n,float(height-50)/2.0,float(height-50)/2.0,float(height-50)/2.0,1);
  saveFrame("Peano.gif");
}
  