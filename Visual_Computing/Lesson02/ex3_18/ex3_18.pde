size(500,500);
background(255);
colorMode(HSB,100);

int x = 250, y=250;
int h = 130,r = 80;
float rad;
for(int i =0; i<100; i++){
  rad = radians(i*3.6);
  stroke(i,100,100);
  line(x+r*cos(rad),y+r*sin(rad),x+(r+h)*cos(rad),y+(r+h)*sin(rad));
}