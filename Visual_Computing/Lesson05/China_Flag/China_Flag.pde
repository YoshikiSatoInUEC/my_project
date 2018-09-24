void setup(){
  size(480, 320);
  background(244,0,2);
  fill(250,244,8);
  smooth();
  noStroke();
  translate(width/6, height / 4);
  scale(width/5, width/5);
  star();
}

void star(){
  beginShape();
  for(int i = 0; i < 5; i++){
    float theta = 2 * TWO_PI / 5 * i - HALF_PI;
    vertex(.5 * cos(theta), .5 * sin(theta));
  }
  endShape();
}

void draw(){
  pushMatrix();
  translate(width*12/30,height/5);
  rotate(-atan((width*7/30)/(height/20)));
  scale(width/15,width/15);
  star();
  popMatrix();
  
  pushMatrix();
  translate(width*2/5,height*7/20);
  rotate(atan((width*7/30)/(height/10)));
  scale(width/15,width/15);
  star();
  popMatrix();
  
  pushMatrix();
  translate(width/3,height*7/15);
  rotate(atan((width/6)/(height/5)));
  scale(width/15,width/15);
  star();
  popMatrix();
  
  pushMatrix();
  translate(width/3,height/10);
  rotate(-atan((width/6)/(height*3/20)));
  scale(width/15,width/15);
  star();
  popMatrix();
  }