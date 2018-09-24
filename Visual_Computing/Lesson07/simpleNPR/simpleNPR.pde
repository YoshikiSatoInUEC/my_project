float[][] prewittH = {{-1,0,1},{-1,0,1},{-1,0,1}};
float[][] prewittV = {{-1,-1,-1},{0,0,0},{1,1,1}};
PImage src;

void setup(){
  size(120,150);
  background(255);
  src = loadImage("rocky.jpg");
}

void draw(){
  int i=int(random(src.width-1));
  int j=int(random(src.height-1));
  float[] diffV = filtering(src,prewittV);
  float[] diffH = filtering(src,prewittH);
  
  fill(src.get(i,j));
  translate(i,j);
  noStroke();
  rotate(atan(diffV[j*width+i]/diffH[j*width+i]));
  float d = sqrt(sq(diffV[j*width+i])+sq(diffH[j*width+i]));
  ellipse(0,0,10*d,30*d);
}

float[] filtering(PImage img, float f[][]){
  float[] diff = new float[img.width*img.height];
  float[] diff_max=new float[img.width*img.height];
  
    float min = 1000, max = -1000;
  for(int j = 1; j < img.height - 1; j++){  // scan image
    for(int i = 1; i < img.width - 1; i++){
      float sum = .0;
      for(int l = -1; l <= 1; l++) // filtering process
        for(int k = -1; k <= 1; k++)
          sum += f[l + 1][k + 1] * color(img.pixels[(j + l) * img.width + i + k]);
      if(sum < min) min = sum;
      else if(sum > max) max = sum;
      diff[j * img.width + i] = sum;
    }
  }

  float max_v=max>abs(min)?max : abs(min);
       for(int p = 0; p < img.width * img.height; p++) // save magnitude
    diff_max[p] = abs(diff[p] / max_v);
     
     return(diff_max);
}