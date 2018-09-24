void setup(){
  size(240,150);
  PImage src = loadImage("rockey.jpg");
  image(src,0,0);
  PImage invert = colorInvert(src);
  invert.save("rockyInvert.jpg");
  PImage invert2 = loadImage("rockyInvert.jpg");
  image(invert2,src.width,0);
  
  saveFrame("rockeyInvert2.jpg");
}

PImage colorInvert(PImage colorImg){
  PImage invertImg = createImage(colorImg.width, colorImg.height, ALPHA);
  colorImg.loadPixels();
  for(int p=0;p<colorImg.width*colorImg.height;p++){
    invertImg.pixels[p] = 255 - colorImg.pixels[p];
  }
  
  invertImg.updatePixels();
  return invertImg;
}