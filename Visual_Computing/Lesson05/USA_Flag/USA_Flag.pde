
void setup(){
  size(380,200);
  background(#B22234);
  noStroke();

  fill(#3C3B6E);
  rect(0,0,380*2/5,200*7/13);

  fill(255);
  int i=1;

  for(;i<7;i+=2)
  rect(380*2/5,200*i/13,380*3/5,200/13);

  for(i=7;i<=13;i+=2)
  rect(0,200*i/13,380,200/13);
  
  smooth();
  noStroke();
}

void star(){
  beginShape();
  for(int i=0;i<5;i++){
       float theta =2*TWO_PI /5*i-HALF_PI;
       vertex(.5*cos(theta),.5*sin(theta));
  }
  endShape();
}

void draw(){
  for(int j=1;j<8;j+=2){
  for(int i=0;i<6;i++){
  pushMatrix();
  translate(38/3+38*2*i/3,20*7*j/13);
  scale(160/13);  
  star();
  popMatrix();
  }
  for(int i=0;i<5;i++){
  pushMatrix();
  translate(38*2/3+38*2*i/3,20*7*(j+1)/13);
  scale(160/13);  
  star();
  popMatrix();
  }
  }
  for(int i=0;i<6;i++){
  pushMatrix();
  translate(38/3+38*2*i/3,20*7*9/13);
  scale(160/13);  
  star();
  popMatrix();
  }
}