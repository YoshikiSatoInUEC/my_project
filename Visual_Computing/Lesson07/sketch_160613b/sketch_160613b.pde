PImage src;
float[][] gaussianFilter;
int w = 5; // filter width
int hw = int(w / 2); // half size of filter width

void setup(){
  size(240, 150);
  background(255);
  src = loadImage("rocky.jpg");
  image(src, 0, 0);
  gaussianFilter = gaussian(1); // generate gaussian filter with standard deviation s
println("a");
  image(filtering(src, gaussianFilter), src.width, 0);
}

float[][] gaussian(float s) {
  float[][] filter = new float[w][w];
  float sum = 0;
  for(int j = -hw; j <= hw; j++)
    for(int i = -hw; i <= hw; i++)  // 1/(2\pi * \sigma^2) is omitted
      sum += filter[j + hw][i + hw] = exp(-(i * i + j * j) / 2. / s / s);

  for(int i = 0; i < w * w; i++) // nomilize filter weights(sum = 1)
    filter[int(i / w)][i % w] /= sum; // 1D index converts 2D index
  return filter;
}
PImage filtering(PImage img, float f[][]){
  PImage filteredImg = createImage(img.width, img.height, ALPHA);
  img.loadPixels();
  filteredImg.loadPixels();
  for(int j = hw; j < img.height - hw; j++){ // scan image
    for(int i = hw; i < img.width - hw; i++){
      float sum_r = .0, sum_g = .0, sum_b = .0;
      for(int l = -hw; l <= hw; l++){ // filtering process
        for(int k = -hw; k <= hw; k++){
          int p = (j + l) * img.width + i + k;
          sum_r += f[l + hw][k + hw] * red(img.pixels[p]);
          sum_g += f[l + hw][k + hw] * green(img.pixels[p]);
          sum_b += f[l + hw][k + hw] * blue(img.pixels[p]);
        }
      }
      filteredImg.pixels[j * img.width + i] = color(sum_r, sum_g, sum_b);
    }
  }
  filteredImg.updatePixels();
  return(filteredImg);
}