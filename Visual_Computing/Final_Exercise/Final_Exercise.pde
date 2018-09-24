import processing.opengl.*;
void setup(){
  size(600,600,P3D);
  noStroke();
}


/*draw donats*/
void donats(float l,float radius1,float radius3,int angle1,int angle2){
  float x1,y,z1,x2,z2;

  pushMatrix();
   rotateX(PI/2);
   pushMatrix();
     beginShape(QUAD_STRIP);
      y =0;
      for(int deg = angle1; deg <= angle2; deg = deg + 10){
        x1 = cos(radians(deg)) * radius1;
        x2 = cos(radians(deg)) * radius3;        
        z1 = sin(radians(deg)) * radius1;
        z2 = sin(radians(deg)) * radius3;
        
        vertex(x2,y,z2);
        vertex(x1, y, z1);
      }     
    endShape();            
  popMatrix();
    
  pushMatrix();
    beginShape(QUAD_STRIP);
    y = l;  
      for(int deg = angle1; deg <= angle2; deg = deg + 10){
        x1 = cos(radians(deg)) * radius1;
        x2 = cos(radians(deg)) * radius3;        
        z1 = sin(radians(deg)) * radius1;
        z2 = sin(radians(deg)) * radius3;
        
        vertex(x2,y,z2);
        vertex(x1, y, z1);
      }
    endShape();            
    popMatrix();
    
    beginShape(TRIANGLE_STRIP);
      for(int deg =angle1; deg <= angle2; deg = deg + 5){
        x1 = cos(radians(deg)) * radius1;
        y = 0;
        z1 = sin(radians(deg)) * radius1;
        vertex(x1, y, z1);
        x1 = cos(radians(deg)) * radius1;
        y = 1;
        z1 = sin(radians(deg)) * radius1;
        vertex(x1, y, z1);
      }
    endShape();
    
    beginShape(TRIANGLE_STRIP);
      for(int deg = angle1; deg <= angle2; deg = deg + 5){
        x2 = cos(radians(deg)) * radius3;
        y = 0;
        z2 = sin(radians(deg)) * radius3;
        vertex(x2, y, z2);
        x2 = cos(radians(deg)) * radius3;
        y = l;
        z2 = sin(radians(deg)) * radius3;
        vertex(x2, y, z2);
      }
    endShape();
  popMatrix();
}


/*draw V*/
void V(){
  pushMatrix();
    beginShape(QUADS);
    vertex(-3,5,0); vertex(-3,5,1); vertex(-2,5,1); vertex(-2,5,0);
    vertex(-3,5,0); vertex(0,0,0); vertex(0,2,0); vertex(-2,5,0);
    vertex(-3,5,1); vertex(0,0,1); vertex(0,2,1); vertex(-2,5,1);
    vertex(-3,5,0); vertex(-3,5,1); vertex(0,0,1); vertex(0,0,0);
    vertex(-2,5,1); vertex(-2,5,0); vertex(0,2,0); vertex(0,2,1);
    
    vertex(3,5,0); vertex(3,5,1); vertex(2,5,1); vertex(2,5,0);
    vertex(3,5,0); vertex(0,0,0); vertex(0,2,0); vertex(2,5,0);
    vertex(3,5,1); vertex(0,0,1); vertex(0,2,1); vertex(2,5,1);
    vertex(3,5,0); vertex(3,5,1); vertex(0,0,1); vertex(0,0,0);
    vertex(2,5,1); vertex(2,5,0); vertex(0,2,0); vertex(0,2,1);
    
    endShape();  
  popMatrix();
}


/*draw i*/
void i(){
  float x,y,z;
  float length = 1,radius1=.3,radius2=.3;
    
  pushMatrix();
    translate(0,3,.5);
    rotateX(PI/2);
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
  popMatrix();
  
  pushMatrix();
  translate(0,1,.5);
  box(1,2,1);
  popMatrix();
}
  

/*draw C*/
void C(){    
  float x1,y,z1,x2,z2;
  float length = 1,radius1=2.5,radius3=1.5;

  pushMatrix();
  translate(0,2.5,0);
    rotateX(PI/2);
    rotateY(PI);
    pushMatrix();
      beginShape(QUAD_STRIP);
      y =0;
      
      for(int deg = 45; deg <= 315; deg = deg + 10){
        x1 = cos(radians(deg)) * radius1;
        x2 = cos(radians(deg)) * radius3;
        
        z1 = sin(radians(deg)) * radius1;
        z2 = sin(radians(deg)) * radius3;
        
        vertex(x2,y,z2);
        vertex(x1, y, z1);
      }
     
    endShape();            
    popMatrix();
    
     pushMatrix();
      beginShape(QUAD_STRIP);
      y = length;
      
      for(int deg = 45; deg <= 315; deg = deg + 10){
        x1 = cos(radians(deg)) * radius1;
        x2 = cos(radians(deg)) * radius3;
        
        z1 = sin(radians(deg)) * radius1;
        z2 = sin(radians(deg)) * radius3;
        
        vertex(x2,y,z2);
        vertex(x1, y, z1);
      }
     
    endShape();            
    popMatrix();
    
    beginShape(TRIANGLE_STRIP);
      for(int deg =45; deg <= 315; deg = deg + 5){
        x1 = cos(radians(deg)) * radius1;
        y = 0;
        z1 = sin(radians(deg)) * radius1;
        vertex(x1, y, z1);
        x1 = cos(radians(deg)) * radius1;
        y = 1;
        z1 = sin(radians(deg)) * radius1;
        vertex(x1, y, z1);
      }
    endShape();
    
    beginShape(TRIANGLE_STRIP);
      for(int deg =45; deg <= 315; deg = deg + 5){
        x2 = cos(radians(deg)) * radius3;
        y = 0;
        z2 = sin(radians(deg)) * radius3;
        vertex(x2, y, z2);
        x2 = cos(radians(deg)) * radius3;
        y = length;
        z2 = sin(radians(deg)) * radius3;
        vertex(x2, y, z2);
      }
    endShape();
    
    beginShape(QUADS);
    vertex(cos(radians(315))*radius3,0,sin(radians(315))*radius3); 
    vertex(cos(radians(315))*radius3,length,sin(radians(315))*radius3); 
    vertex(cos(radians(315))*radius1,0,sin(radians(315))*radius1); 
    vertex(cos(radians(315))*radius1,length,sin(radians(315))*radius1); 
    
    vertex(cos(radians(45))*radius3,0,sin(radians(45))*radius3); 
    vertex(cos(radians(45))*radius3,length,sin(radians(45))*radius3); 
    vertex(cos(radians(45))*radius1,0,sin(radians(45))*radius1); 
    vertex(cos(radians(45))*radius1,length,sin(radians(45))*radius1); 
    endShape();
    
    
    popMatrix();

}


/*draw s*/
void s(){
 float x1,y,z1,x2,z2;
 float length = .5;
 
 pushMatrix(); 
  scale(2);
  translate(.5,1.2,0);
  rotate(-PI*11/10);
  
  for(int i=1;i<=2;i++){
  
   pushMatrix();
    float radius1=.7*(1-i*.2)-.08*(1-i),radius3=.2*(1-i*.2);
    translate(i*.25,-i*i*.2+1,0);
    rotateX(PI/2);
    rotateY((2-i)*PI);
    
     pushMatrix();      
      beginShape(QUAD_STRIP);
      y =0;      
      for(int deg = 0; deg <= 250; deg = deg + 10){
        x1 = cos(radians(deg)) * radius1;
        x2 = cos(radians(deg)) * radius3;        
        z1 = sin(radians(deg)) * radius1;
        z2 = sin(radians(deg)) * radius3;
        
        vertex(x2,y,z2);
        vertex(x1, y, z1);
      }
      endShape();            
   
      beginShape(QUAD_STRIP);
      y = length;
      for(int deg = 0; deg <= 250; deg = deg + 10){
        x1 = cos(radians(deg)) * radius1;
        x2 = cos(radians(deg)) * radius3;
        z1 = sin(radians(deg)) * radius1;
        z2 = sin(radians(deg)) * radius3;
        
        vertex(x2,y,z2);
        vertex(x1, y, z1);
      }
      endShape();            
     popMatrix();
    
     beginShape(TRIANGLE_STRIP);
      for(int deg=0; deg <= 250; deg = deg + 5){
        x1 = cos(radians(deg)) * radius1;
        y = 0;
        z1 = sin(radians(deg)) * radius1;
        vertex(x1, y, z1);
        x1 = cos(radians(deg)) * radius1;
        y = length;
        z1 = sin(radians(deg)) * radius1;
        vertex(x1, y, z1);
      }
     endShape();
    
     beginShape(TRIANGLE_STRIP);
      for(int deg=0; deg <= 240; deg = deg + 5){
        x2 = cos(radians(deg)) * radius3;
        y = 0;
        z2 = sin(radians(deg)) * radius3;
        vertex(x2, y, z2);
        x2 = cos(radians(deg)) * radius3;
        y = length;
        z2 = sin(radians(deg)) * radius3;
        vertex(x2, y, z2);
      }
     endShape();
    
     beginShape();     
      vertex(cos(radians(0))*radius3,0,sin(radians(0))*radius3); 
      vertex(cos(radians(0))*radius1,0,sin(radians(0))*radius1); 
      vertex(cos(radians(0))*radius1,length,sin(radians(0))*radius1); 
            vertex(cos(radians(0))*radius3,length,sin(radians(0))*radius3); 
     endShape();
     
    popMatrix();
  }
   popMatrix();
}


/*draw u*/
void u(){
  pushMatrix();
   translate(-1,1.5,.5);
   box(1,3,1);
   translate(2,.6,0);
   box(1,1.7,1);
  popMatrix();
  
  pushMatrix();
   translate(0,1.5,0);
   donats(1,1.5,.5,0,180);
  popMatrix();
  
}


/*draw a*/
void a(){
  pushMatrix();
   translate(-.7,0,0);
   pushMatrix();
   translate(.5,.55,.5);
   box(1,.7,1);
   translate(0,1.3,0);
   box(1,.7,1);
   translate(-.5,-.55,0);
   box(.7,2.4,1);
  popMatrix();
  

  pushMatrix();
   translate(1,1.2,0);
   rotateZ(PI/2);
   donats(1,1,.3,0,180);
  popMatrix();  
  
 pushMatrix();
   translate(.65,2.3,0);
   rotateZ(PI);
   donats(1,1,.3,0,170);
  popMatrix();
  popMatrix();
  
}


/*draw l*/
void l(){
  pushMatrix();
    translate(0,2.5,0);
    box(1,5,1);
  popMatrix();
}

/*draw o*/
void o(){
  pushMatrix();
  translate(0,1.5,0);
  donats(1,1.5,.7,0,360);
  popMatrix();
  
}

/*draw m*/
void m(){
  pushMatrix();
  translate(0,1,.5);
    box(1,2,1);
    
    pushMatrix();
    translate(1.6,.5,0);
    box(1,3,1); 
    popMatrix();
    
    pushMatrix();
      translate(.8,.8,-.5);
      rotateZ(PI);
      donats(1,1.3,.3,0,180);
    popMatrix();
    
    pushMatrix();
      translate(-.8,.8,-.5);
      rotateZ(PI);
      donats(1,1.3,.3,0,180);
    popMatrix();
    
    pushMatrix();
      translate(-1.6,0,0);
      box(1,2,1);
    popMatrix();
  popMatrix();
}

/*draw p*/
void p(){
  translate(1,0,0);
  pushMatrix();
    translate(0,.3,.5);
    box(1,4,1);
  popMatrix();
  
  pushMatrix();
    translate(-1,1.3,0);
    donats(1,1.3,.5,0,360);
  popMatrix();
  
}

/*draw t*/
void t(){
  pushMatrix();
  translate(0,1.75,.5);
  box(1,3.5,1);
  translate(0,.75,0);
  box(2,1,1);
  popMatrix();
  
}

/*draw n*/
void n(){
  pushMatrix();
    translate(1,1.4,.5);
    box(1,2.8,1);
  popMatrix();
  
  pushMatrix();
    translate(0,1.6,0);
    rotateZ(PI);
    donats(1,1.5,.5,0,180);
  popMatrix();
  
  pushMatrix();
    translate(-1,.9,.5);
    box(1,1.8,1);
  popMatrix();
  
}

/*draw g*/
void g(){
  pushMatrix();
  translate(-1,0,0);
    pushMatrix();
      translate(0,.6,.5);
      box(1,3,1);
    popMatrix();
  
    pushMatrix();
      translate(1,1.3,0);
      donats(1,1.3,.5,0,360);
    popMatrix();
  
    pushMatrix();
      translate(.9,-.8,0);
      donats(1,1.4,.4,0,180);
    popMatrix();
  popMatrix();
}


/*draw main*/
int j=0,i=0,k=0,l=0;
void draw(){
  background(0);
  
  directionalLight(255,215,0,-1,-5,-1);
  directionalLight(255,215,0,1,5,-1);
  
 if(j<520){
 camera(400-j,30,100,300-j,0,10,0,-1,0);
 pointLight(255,215,0,300-j,20,30);
 }
 
 if(j>=520&i<520){
  camera(170-i,30,100,270-i,0,10,0,-1,0); 
  pointLight(255,215,0,270-i,20,30);
  i+=2;
 }
 
  if(j>=520&&i>=520){
  camera(-90,10,-50+k,-8,0,0,0,-1,0);
  pointLight(255,215,0,150,-30,40);
  pointLight(255,215,0,-135,-30,40);
  l++;
  if(k<=550)
    k+=3;
  if(l>=1000){
  j=0;i=0;k=0;l=0;
    }
  }
  
  pushMatrix();
  scale(10);
  fill(255);

  translate(28,0,0); V(); shininess(5.0);
  translate(-4,0,0); i(); shininess(5.0);
  translate(-3,0,0); s();
  translate(-4,0,0); u();
  translate(-4,0,0); a();
  translate(-3.5,0,0); l();
  translate(-6,0,0); C();
  translate(-4,0,0); o();
  translate(-4,0,0); m();
  translate(-4.5,0,0); p();
  translate(-4.5,0,0); u();
  translate(-3.5,0,0); t();
  translate(-2.5,0,0); i();
  translate(-3.5,0,0); n();
  translate(-3.5,0,0); g();
  popMatrix();
  
  if(j<=600){
  j+=2;
  }
  
}