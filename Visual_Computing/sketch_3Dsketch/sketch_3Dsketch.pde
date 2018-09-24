import processing.opengl.*;
float rad = 0;

void setup(){
  size(600,600,OPENGL);
  noStroke();
}


/*tree*/
void leaf(int r,int g,int b){
  fill(r,g,b);
  beginShape(TRIANGLES);
    vertex(0,.5,0);    vertex(-.5,0,-.5);  vertex(.5,0,-.5);
    vertex(0,.5,0);    vertex(.5,0,-.5);   vertex(.5,0,.5);
    vertex(0,.5,0);    vertex(.5,0,.5);    vertex(-.5,0,.5);
    vertex(0,.5,0);    vertex(-.5,0,.5);   vertex(-.5,0,-.5);
    vertex(-.5,0,-.5); vertex(.5,0,.5);    vertex(.5,0,-.5);
    vertex(-.5,0,-.5); vertex(.5,0,.5);    vertex(-.5,0,.5);
  endShape();
}
  
  void leaves(){
    pushMatrix();  translate(0,.5,0);  scale(.6,.6,.6); leaf(0,224,0); popMatrix();
    pushMatrix();  translate(0,.25,0); scale(.8,.8,.8); leaf(0,192,0); popMatrix();
    leaf(0,128,0);
  }
  
  void trunk(){
    fill(128,64,0);
    pushMatrix();
      scale(.2,.4,.2);
      translate(0,.4,0);
      box(1); 
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
    leaf(128,100,0);
    translate(0,-.2,0);
    fill(255,255,200);
    box(0.8,0.5,0.8);
    popMatrix();
  }
  
  
  /*car*/
  void body(float r,float g,float b){
    pushMatrix();
     fill(r,g,b);
     box(.2,.2,.2);
     pushMatrix();  translate(0,-.05,.1);  box(.2,.1,.2);  popMatrix();
     pushMatrix();  translate(0,-.05,-.1); box(.2,.1,.2);  popMatrix();
    popMatrix();
  }
  
  void tire(){
    fill(0);    
    float x,y,z;
    float length = .03,radius1=.05,radius2=.05;
    rotate(PI/2);
    
    pushMatrix();
      beginShape(TRIANGLE_FAN);
      y = -length / 2;
      vertex(0, y, 0);
      for(int deg = 0; deg <= 360; deg = deg + 10){
        x = cos(radians(deg)) * radius1;
        z = sin(radians(deg)) * radius1;
        vertex(x, y, z);
      }
    endShape();            
    
    beginShape(TRIANGLE_FAN);
      y = length / 2;
      vertex(0, y, 0);
      for(int deg = 0; deg <= 360; deg = deg + 10){
        x = cos(radians(deg)) * radius2;
        z = sin(radians(deg)) * radius2;
        vertex(x, y, z);
      }
    endShape();

    beginShape(TRIANGLE_STRIP);
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
  popMatrix();
}
  
  void drawCar(float s,float r,float g,float b){
    pushMatrix();
    scale(s,s,s);
    body(r,g,b);
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
  void draw(){
    background(255);
    lights();
    float cameraY = map(mouseY, 0, height, 400, -200);
    float theta = map(mouseX, 0, width, 0, TWO_PI);
    camera(300 * cos(theta), cameraY, 300 * sin(theta), 0, 0, 0, 0, -1, 0);
    
    
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
        drawCar(100,13,222,135);
      }
    popMatrix();
    
    pushMatrix();
    translate(150,15,-400+k);
     for(int i=0;i<=10;i++){
        translate(0,0,200);
        drawCar(100,13,120,235);
      }
    popMatrix();
    
    k++;
    if(k>=-600){
      k=-800;
    }
  }
  