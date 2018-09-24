void setup(){
  size(360,150);
  
  PImage src = loadImage("rockey.jpg");
  image(src,0,0);
  
  PImage mirror1 = Mirror1(src);
  image(mirror1,src.width,0);
  mirror1.save("rockeyMirror1.jpg");
  
  PImage mirror2 = Mirror2(src);
  image(mirror2,src.width*2,0);
  mirror2.save("rockeyMirror2.jpg");

saveFrame("rockeyMirror3.jpg");
}


PImage Mirror1(PImage srcImg){
  PImage mirrorImg = createImage(srcImg.width,srcImg.height,ALPHA);
  
  srcImg.loadPixels();
  for(int p=0;p<srcImg.width*srcImg.height;p++){
    if(p%srcImg.width<srcImg.width/2)
      mirrorImg.pixels[p] = srcImg.pixels[p];
    else
      mirrorImg.pixels[p]=srcImg.pixels[(p-2*(p%srcImg.width)+srcImg.width)];
  }
  srcImg.updatePixels();
 
  return mirrorImg;
}


PImage Mirror2(PImage srcImg){
  PImage mirrorImg = createImage(srcImg.width,srcImg.height,ALPHA);
  
  srcImg.loadPixels();
  for(int p=0;p<srcImg.width*srcImg.height;p++){
    mirrorImg.pixels[p]=srcImg.pixels[p];
    if(p%srcImg.width>srcImg.width/2)
      mirrorImg.pixels[(p-2*(p%srcImg.width)+srcImg.width)]=srcImg.pixels[p];
  }
  srcImg.updatePixels();
 
  return mirrorImg;
}