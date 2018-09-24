void setup(){
  size(400, 300, P3D);
  noStroke();
  smooth();
  textFont(createFont("Tempus Sans ITC", 24));
}

float l=0;
void draw(){
  background(255);
  
    pushMatrix();
    translate(200, 150, -50);
    rotateX(map(mouseY, 0, height, PI, -PI));
    rotateY(map(mouseX+l, 0, width, -PI, PI));
    l++;
    fill(0, 0, 0, 255);
    String s = "Visual Computing";
    float r = 2 * textWidth(s) / PI, theta = PI / s.length();
    for(int i = 0; i < s.length(); i++){
      pushMatrix();
        translate(-r * cos(theta * i), -75,-r * sin(theta * i));
        rotateY(-theta*i+PI/2);
        text(s.charAt(i), 0, 0, 0);
      popMatrix();
    }
  popMatrix();
  
  translate(width / 2, height / 2, 0);
  rotateX(map(mouseY, 0, height, PI, -PI));
  rotateY(map(mouseX, 0, width, -PI, PI));
  drawAxis('X', color(255, 0, 0, 120)); // X-axis + YZ-plane
  drawAxis('Y', color(0, 255, 0, 120)); // Y-axis + XZ-plane
  drawAxis('Z', color(0, 0, 255, 120)); // Z-axis + XY-plane
  
      saveFrame("ThreeDCharacterCylinerAnime.jpg");
}

void axis(char s, color c){
  int len = 200;
  fill(c);
  stroke(c);
  box(len, 1, 1);
  pushMatrix();
    translate(len / 2, 0, 0);
    sphere(3);
    text(s, 5, -5, 0);
  popMatrix();
  pushMatrix();
    translate(0, -len / 2, -len / 2);
    int ngrids = 20; // # of grid
    int xs = len / ngrids, ys = len / ngrids; // grid  interval
    for(int i = 1; i < ngrids; i++){
      line(0, 0, ys * i, 0, len, ys * i); // horizontal grid
      line(0, xs * i, 0, 0, xs * i, len); // vertical grid
    }
  popMatrix();

}

void drawAxis(char s, color c){
  switch(s){
    case 'X':
      axis(s, c);
      break;
    case 'Y':
       pushMatrix();
        rotateZ(PI / 2);
         axis(s, c);
       popMatrix();
       break;
    case 'Z':
       pushMatrix();
        rotateY(-PI / 2);
         axis(s, c);
       popMatrix();
       break;
  }
  

}