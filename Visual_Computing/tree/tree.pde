int n = 1;
void setup(){
  size(800,600);
  textFont(createFont("Tempus Sans ITC",24));
  fill(0);
  smooth();
}

void drawTree(int level,float length){
  if(1==level){
    line(0,0,0,-length);
    return;
  }
  
  drawTree(--level,length);
  length=length*2/3;
  
  pushMatrix();
  translate(0,-length*3/2);
  rotate(-PI/6);
  drawTree(level,length);
  popMatrix();
  
  pushMatrix();
  translate(0,-length*3/2);
  rotate(PI/6);
  drawTree(level,length);
  popMatrix();
}

void mousePressed(){
  if((mouseButton == LEFT) && (n<16)) n++;
  else if((mouseButton == RIGHT) && (n>1)) n--;
}

void draw(){
  background(255);
  text("n = " +n,10,30);
  translate(width/2,height);
  drawTree(n,float(width/4));
}
  