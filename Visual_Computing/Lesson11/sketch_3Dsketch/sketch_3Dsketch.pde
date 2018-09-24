import processing.opengl.*;
float rad = 0;
PImage img1,img2,img3,img4,img5,img6;

void setup(){
  size(600,600,OPENGL);
  img1=loadImage("./leaf.jpg");
  img2=loadImage("./roof.jpg");
  img3 = loadImage("./wall.jpg");
  img4=loadImage("./trunk.jpg");
  img5=loadImage("./tire.jpg");
  img6=loadImage("./car_body.jpeg");
  noStroke();

}


/*tree*/
void leaf(){
  pushMatrix();
  beginShape(TRIANGLES);
  texture(img1);
  textureMode(NORMAL);
    vertex(0,.5,0,1,1);    vertex(-.5,0,-.5,0,1);  vertex(.5,0,-.5,1,0);
    vertex(0,.5,0,0,0);    vertex(.5,0,-.5,0,1);   vertex(.5,0,.5,1,0);
    vertex(0,.5,0,0,0);    vertex(.5,0,.5,0,1);    vertex(-.5,0,.5,1,0);
    vertex(0,.5,0,0,0);    vertex(-.5,0,.5,0,1);   vertex(-.5,0,-.5,1,0);
    vertex(-.5,0,-.5,0,0); vertex(.5,0,.5,0,1);    vertex(.5,0,-.5,1,0);
    vertex(-.5,0,-.5,0,0); vertex(.5,0,.5,0,1);    vertex(-.5,0,.5,1,0);
  endShape();
  popMatrix();
}
  
  void leaves(){
    pushMatrix();  translate(0,.5,0);  scale(.6,.6,.6); leaf(); popMatrix();
    pushMatrix();  translate(0,.25,0); scale(.8,.8,.8); leaf(); popMatrix();
    leaf();
  }
  
  void trunk(){
    pushMatrix();
      scale(.2,.4,.2);
      translate(0,.4,0);
      pushMatrix();
       beginShape(QUADS);
        texture(img4);
        textureMode(NORMAL);
        vertex(-.5,.5,-.5,0,0);    vertex(.5,.5,-.5,0,1);  vertex(.5,.5,.5,1,1); vertex(-.5,.5,.5,1,0);
        vertex(-.5,-.5,-.5,0,0);    vertex(.5,-.5,-.5,0,1);  vertex(.5,-.5,.5,1,1); vertex(-.5,-.5,.5,1,0);
        vertex(-.5,.5,-.5,0,0);    vertex(.5,.5,-.5,0,1);  vertex(.5,-.5,-.5,1,1); vertex(-.5,-.5,-.5,1,0);
        vertex(.5,.5,.5,0,0);    vertex(-.5,.5,.5,0,1);  vertex(-.5,-.5,.5,1,1); vertex(.5,-.5,.5,1,0);
        vertex(-.5,.5,-.5,0,0);    vertex(-.5,.5,.5,0,1);  vertex(-.5,-.5,.5,1,1); vertex(-.5,-.5,-.5,1,0);
        vertex(.5,.5,.5,0,0);    vertex(.5,.5,-.5,0,1);  vertex(.5,-.5,-.5,1,1); vertex(.5,-.5,.5,1,0);
       endShape();
      popMatrix();
    popMatrix();
  }
  
  void drawTree(float s){
    pushMatrix();
    scale(s,s,s);
    trunk();
    translate(0,.4,0);
    leaves();
    popMatrix();
  }
  
  
  /*house*/
  void drawHouse(float s){
    pushMatrix();
    scale(s,s,s);
    roof();
    translate(0,-.2,0);
    pushMatrix();
     beginShape(QUADS);
      texture(img3);
      textureMode(NORMAL);
      vertex(-.4,.25,-.4,0,0);    vertex(.4,.25,-.4,0,1);  vertex(.4,.25,.4,1,1); vertex(-.4,.25,.4,1,0);
      vertex(-.4,-.25,-.4,0,0);    vertex(.4,-.25,-.4,0,1);  vertex(.4,-.25,.4,1,1); vertex(-.4,-.25,.4,1,0);
      vertex(-.4,.25,-.4,0,0);    vertex(.4,.25,-.4,0,1);  vertex(.4,-.25,-.4,1,1); vertex(-.4,-.25,-.4,1,0);
      vertex(.4,.25,.4,0,0);    vertex(-.4,.25,.4,0,1);  vertex(-.4,-.25,.4,1,1); vertex(.4,-.25,.4,1,0);
      vertex(-.4,.25,-.4,0,0);    vertex(-.4,.25,.4,0,1);  vertex(-.4,-.25,.4,1,1); vertex(-.4,-.25,-.4,1,0);
      vertex(.4,.25,.4,0,0);    vertex(.4,.25,-.4,0,1);  vertex(.4,-.25,-.4,1,1); vertex(.4,-.25,.4,1,0);
  endShape();
  popMatrix();
  
  pushMatrix();
    beginShape(TRIANGLES);
    translate(0,.25,0);
    texture(img3);
    textureMode(NORMAL);
    vertex(-.4,.4,0,0,0);    vertex(-.4,0,-.4,0,1);  vertex(-.4,0,.4,1,1); 
    vertex(.4,0,-.4,0,1);  vertex(.4,.4,0,1,1); vertex(.4,0,.4,1,0);
  endShape();
  popMatrix();
  
    popMatrix();
  }
  
  void roof(){
  pushMatrix();
  beginShape(QUADS);
  texture(img2);
  textureMode(NORMAL);
    vertex(-.5,.5,0,0,0);    vertex(-.5,0,-.5,0,1);  vertex(.5,0,-.5,1,1); vertex(.5,.5,0,1,0);
    vertex(-.5,.5,0,0,0);    vertex(-.5,0,.5,0,1);  vertex(.5,0,.5,1,1); vertex(.5,.5,0,1,0);
  endShape();
  popMatrix();
}
  
  
  /*car*/
  void body(){
    pushMatrix();
     beginShape(QUADS);
      texture(img6);
      textureMode(NORMAL);
      vertex(-.1,0,-.05,0,0);    vertex(.1,.0,-.05,0,1);  vertex(.1,.0,.05,1,1); vertex(-.1,.0,.05,1,0);
      vertex(-.1,-.1,-.2,0,0);    vertex(.1,-.1,-.2,0,1);  vertex(.1,-.1,.2,1,1); vertex(-.1,-.1,.2,1,0);
      vertex(-.1,0,-.05,0,0);    vertex(.1,0,-.05,0,1);  vertex(.1,-.05,-.15,1,1); vertex(-.1,-.05,-.15,1,0);
      vertex(-.1,-.05,-.2,0,0);    vertex(.1,-.05,-.2,0,1);  vertex(.1,-.12,-.2,1,1); vertex(-.1,-.12,-.2,1,0);
      vertex(.1,-.05,-.15,0,0);    vertex(-.1,-.05,-.15,0,1);  vertex(-.1,-.05,-.2,1,1); vertex(.1,-.05,-.2,1,0);
      vertex(-.1,0,.05,0,0);    vertex(.1,0,.05,0,.1);  vertex(.1,-.05,.15,1,1); vertex(-.1,-.05,.15,1,0);
      vertex(-.1,-.05,.2,0,0);    vertex(.1,-.05,.2,0,1);  vertex(.1,-.12,.2,1,1); vertex(-.1,-.12,.2,1,0);
      vertex(.1,-.05,.15,0,0);    vertex(-.1,-.05,.15,0,1);  vertex(-.1,-.05,.2,1,1); vertex(.1,-.05,.2,1,0);
      
      /*side*/
      vertex(.1,-.05,.2,0,0);    vertex(.1,-.05,-.2,0,1);  vertex(.1,-.12,-.2,1,1); vertex(.1,-.12,.2,1,0);
      vertex(-.1,-.05,.2,0,0);    vertex(-.1,-.05,-.2,0,1);  vertex(-.1,-.12,-.2,1,1); vertex(-.1,-.12,.2,1,0);
      
      vertex(-.1,0,.05,0,0);    vertex(.1,0,-.05,0,1);  vertex(-.1,-.05,-.15,1,1); vertex(-.1,-.05,.15,1,1); 
      vertex(.1,0,-.05,0,0);    vertex(.1,0,.05,0,1);  vertex(.1,-.05,.15,1,0);   vertex(.1,-.05,-.15,1,0);
 
  endShape();
  popMatrix();
  }
  
  void tire(){    
    float x,y,z;
    float length = .03,radius1=.05,radius2=.05;
    rotate(PI/2);
    
    pushMatrix();
      beginShape(TRIANGLE_FAN);
      texture(img5);
      textureMode(NORMAL);
      y = -length / 2;
      vertex(0, y, 0,.5,.5);
      for(int deg = 0; deg <= 360; deg = deg + 10){
        x = cos(radians(deg)) * radius1;
        z = sin(radians(deg)) * radius1;
        vertex(x, y, z,0.5*sin(radians(deg))+0.5,0.5*cos(radians(deg))+0.5);
      }
    endShape();            
    
    beginShape(TRIANGLE_FAN);
    texture(img5);
    textureMode(NORMAL);
      y = length / 2;
      vertex(0, y, 0,0,0);
      for(int deg = 0; deg <= 360; deg = deg + 10){
        x = cos(radians(deg)) * radius2;
        z = sin(radians(deg)) * radius2;                
        vertex(x, y, z,0.5*sin(radians(deg))+0.5,0.5*cos(radians(deg))+0.5);
      }
    endShape();

    beginShape(TRIANGLE_STRIP);
      fill(0); 
      for(int deg =0; deg <= 360; deg = deg + 5){
        x = cos(radians(deg)) * radius1;
        y = -length / 2;
        z = sin(radians(deg)) * radius1;
        vertex(x, y, z);
        x = cos(radians(deg)) * radius2;
        y = length / 2;
        z = sin(radians(deg)) * radius2;
        vertex(x, y, z);
      }
    endShape();
    fill(255);
    
  popMatrix();
}
  
  void drawCar(float s){
    pushMatrix();
    scale(s,s,s);
    body();
    for(int i=-1;i<=1;i+=2){
      for(int j=-1;j<=1;j+=2){
        pushMatrix();
          translate(i*.08,-.1,j*.08);
          tire();
        popMatrix();
      }
    }
    popMatrix();
  }
  
  
  
  int k=-800;
  int x=0,y=0,z=0;
  void draw(){
    background(255);
    lights();
    camera(300+x, y, 300-z, 0, 0, 0, 0, -1, 0);
    fill(255);
    y++;
    
    /*placinging the trees*/
    for(int i=-10;i<=10;i++){
      pushMatrix();
        translate(0,0,i*80);
        drawTree(80);
      popMatrix();
    }
    
    /*placing the houses*/
    pushMatrix();
    translate(-300,30,50);
    for(int j=0;j<5;j++){
      for(int i=-10;i<10;i++){
        pushMatrix();
          translate(-150*j,0,150*i);
          drawHouse(130);
        popMatrix();
      }
    }   
    popMatrix();
    
    /*placing the cars*/
    pushMatrix();
    translate(200,15,600-k);
     for(int i=0;i<=10;i++){
        translate(0,0,-200);
        drawCar(100);
      }
    popMatrix();
    
    pushMatrix();
    translate(150,15,-400+k);
     for(int i=0;i<=10;i++){
        translate(0,0,200);
        drawCar(100);
      }
    popMatrix();
    
    k++;
    if(k>=-600){
      k=-800;
    }
    
    if(y>=200){
      y=200;
      x++;
      if(x>=50){
        x=50;
        z++;
        if(z>=800){
        z=800;
        }
      }
    }
  }
  