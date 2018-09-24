PImage src;
float[][] biateralFilter;
int w=5;
int hw = int(w/2);

void setup(){
  size(600,150);
  background(255);
  src = loadImage("rocky.jpg");
  image(src,0,0);
  biateralFilter = biateral(1);
  image(filtering(src,biateralFilter,1,1),src.width,0);
  image(filtering(src,biateralFilter,1,2),src.width*2,0);
  image(filtering(src,biateralFilter,1,3),src.width*3,0);
  image(filtering(src,biateralFilter,1,4),src.width*4,0);
}

float[][] biateral(float s){
  float[][] filter = new float[w][w];
  float sum = 0;
 
  for(int n=-hw;n<=hw;n++)
    for(int m=-hw;m<=hw;m++){
     sum += filter[n+hw][m+hw] = exp(-(m*m+n*n)/2./s/s);
    }
     for(int i = 0; i < w * w; i++) 
    filter[int(i / w)][i % w] /= sum;
  return filter;
}


PImage filtering(PImage img, float f[][],float s,int n){
  PImage filteredImg = createImage(img.width, img.height, ALPHA);
  img.loadPixels();
  filteredImg.loadPixels();
  
  for(int a=0;a<n;a++)
  for(int j = hw; j < img.height - hw; j++){ 
    for(int i = hw; i < img.width - hw; i++){
      float sum1 = .0 ,sum2 = .0;
      for(int l = -hw; l <= hw; l++){ 
        for(int k = -hw; k <= hw; k++){
          int p = (j + l) * img.width + i + k;
          sum1 += f[l + hw][k + hw] * exp(-sq(img.pixels[j * img.width + i]-img.pixels[p])/2/s/s) * img.pixels[p];
          sum2 += f[l + hw][k + hw] * exp(-sq(img.pixels[j * img.width + i]-img.pixels[p])/2/s/s);
        }
      }      
  
      
      filteredImg.pixels[j * img.width + i] = int(sum1/sum2);
    }
  }
  filteredImg.updatePixels();
  return(filteredImg);
}