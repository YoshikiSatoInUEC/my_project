#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#if defined(WIN32)
//#  pragma comment(linker, "/subsystem:\"windows\" /entry:\"mainCRTStartup\"")
#  include "glut.h"
#elif defined(__APPLE__) || defined(MACOSX)
#  include <GLUT/glut.h>
#else
#  include <GL/glut.h>
#endif

#define PI 3.141592653589793


/*パラメータ*/
GLfloat light0pos[] = { 1.0, 1.0, 1.0, 1.0 };
GLfloat light1pos[] = { 5.0, 3.0, 0.0, 1.0 };
GLfloat gray[] = {0.3, 0.3, 0.3,1.0 };
GLfloat gray2[] = {0.4, 0.4, 0.4,1.0 };
GLfloat white[]={0.7,0.7,0.7,1.0};
GLfloat green[]={0,0.01,0,1.0};
GLfloat glassblue[]={0.25,0.27,0.25,0.5};
int ex=-100,ez=10;
int r=0;
GLuint texid_1/*,texid_2*/;
GLdouble rad;
GLdouble rx; 
GLdouble ry;
GLfloat shininess = 40;

/*テクスチャのパラメータ*/
#define TEXWIDTH2 1000   //テクスチャの幅
#define TEXHEIGHT2 880  //テクスチャの高さ
static const char texture2[] = "shibuya.raw";  //テクスチャファイル名


/*solid用x-z座標*/
GLdouble x_z1[][2] = {
    {-175,100},
    {-145,125},
    {-300,250},
    {-140,250}
};

GLdouble x_z2[][2]={
    {-260,-60},
    {-175,-40},
    {-260,130},
    {-160,50}
};

GLdouble x_z3[][2]={
    {-160,0},
    {-160,50},
    {-175,-40},
    {-160,0}
};

GLdouble x_z4[][2]={
    {-20,160},
    {25,130},
    {-20,240},
    {60,270}
};

GLdouble x_z5[][2]={
    {25,130},
    {150,160},
    {60,270},
    {150,210}
};

GLdouble x_z6[][2]={
  {-90,-150},
  {-120,-120},
  {-80,-200},
  {-90,-230}
};

GLdouble x_z7[][2]={
  {-120,-120},
  {-260,-150},
  {-90,-230},
  {-260,-250}
};


/*直方体の作成*/
void cube(double x, double y, double z, double width, double height, double depth/*, GLuint Texture*/){
    int i,j;
    GLdouble vertex[][3] = {
        { x,		y-1,			z },       //0
        { x+width,	y-1,			z },       //1
        { x+width,	y+height,	z },       //2
        { x,		y+height,	z },       //3
        { x,    	y-1,      	z+depth }, //4
        { x+width,	y-1,			z+depth }, //5
        { x+width,	y+height,	z+depth }, //6
        { x,		y+height,	z+depth }  //7
    };
    
    int face[][4] = {
        { 0, 1, 2, 3 },
        { 1, 5, 6, 2 },
        { 5, 4, 7, 6 },
        { 4, 0, 3, 7 },
        { 4, 5, 1, 0 },
        { 3, 2, 6, 7 }
    };
    
    GLdouble normal[][3] = {
        { 0.0, 0.0,-1.0 },
        { 1.0, 0.0, 0.0 },
        { 0.0, 0.0, 1.0 },
        {-1.0, 0.0, 0.0 },
        { 0.0,-1.0, 0.0 },
        { 0.0, 1.0, 0.0 }
    };
    
    GLdouble texCoord[][2] = {
        { 1.0, 1.0 },
        { 0.0, 1.0 },
        { 0.0, 0.0 },
        { 1.0, 0.0 }
    };
    

    int k=0;
    /*直方体の描画*/
    //    glEnable(GL_TEXTURE_2D);
    // glBindTexture(GL_TEXTURE_2D,texid_1);


     glBegin(GL_QUADS);
    for (j = 0; j < 6; j++) {
      glNormal3dv(normal[j]);//法線ベクトル
    
      for (i = 3; i >=0; i--) {
	//	glTexCoord2dv(texCoord[i]);
	glVertex3dv(vertex[face[j][i]]);
      }

    }
    glEnd();
    //    glBindTexture(GL_TEXTURE_2D,texid_1);
    // glDisable(GL_TEXTURE_2D);
    //    glDisable(GL_ALPHA_TEST);
}



/*長方形の作成*/
void solid (GLdouble xandz[4][2] , double y, double height/*, GLuint Texture*/){
    int i,j;
    GLdouble vertex[][3] = {
        { xandz[0][0], y-1,		 xandz[0][1] },       //0
        { xandz[1][0], y-1,		 xandz[1][1] },       //1
        { xandz[1][0], y+height, xandz[1][1] },       //2
        { xandz[0][0], y+height, xandz[0][1] },       //3
        { xandz[2][0], y-1,      	 xandz[2][1] }, //4
        { xandz[3][0], y-1,	  	 xandz[3][1] }, //5
        { xandz[3][0], y+height, xandz[3][1] }, //6
        { xandz[2][0], y+height, xandz[2][1] }  //7
    };
    
    int face[][4] = {
        { 0, 1, 2, 3 },
        { 1, 5, 6, 2 },
        { 5, 4, 7, 6 },
        { 4, 0, 3, 7 },
        { 4, 5, 1, 0 },
        { 3, 2, 6, 7 }
    };
    
    GLdouble normal[][3] = {
        { 0.0, 0.0,-1.0 },
        { 1.0, 0.0, 0.0 },
        { 0.0, 0.0, 1.0 },
        {-1.0, 0.0, 0.0 },
        { 0.0,-1.0, 0.0 },
        { 0.0, 1.0, 0.0 }
    };
    
    GLdouble texCoord[][2] = {
        { 0.0, 0.0 },
        { 200.0, 0.0 },
        { 200.0, 200.0 },
        { 0.0, 200.0 }
    };
    
    
    //    glBindTexture(GL_TEXTURE_2D, Texture);
    
    /*直方体の描画*/
    glBegin(GL_QUADS);
    for (j = 0; j < 6; ++j) {
        glNormal3dv(normal[j]);
        for (i = 3; i >= 0; --i) {
            glTexCoord2dv(texCoord[i]);
            glVertex3dv(vertex[face[j][i]]);
        }
    }
    glEnd();
    
    //    glBindTexture(GL_TEXTURE_2D, 0);
}




/*更新毎に再度displayを読み込み*/
void idle(void)
{
    glutPostRedisplay();
}



void Ground()
{
  int j;

GLdouble vertex[][3] = {          //頂点の設定
  { -250, -5.0, -250 },
  { 250, -5.0, -250 },
  { 250, 0.0, -250 },
  { -250, 0.0, -250 },
  { -250, -5.0, 250 },
  { 250, -5.0, 250 },
  { 250, 0.0, 250 },
  { -250, 0.0, 250 }
};

int face[][4] = {                 //面の設定
  { 0, 1, 2, 3 },
  { 1, 5, 6, 2 },
  { 5, 4, 7, 6 },
  { 4, 0, 3, 7 },
  { 4, 5, 1, 0 },
  { 3, 2, 6, 7 }
};

 GLdouble normal[][3] = {          //光の処理
  { 0.0, 0.0,-1.0 },
  { 1.0, 0.0, 0.0 },
  { 0.0, 0.0, 1.0 },
  {-1.0, 0.0, 0.0 },
  { 0.0,-1.0, 0.0 },
  { 0.0, 1.0, 0.0 }
};

  /*テクスチャマッピング開始*/
  glEnable(GL_TEXTURE_2D);
  glBindTexture(GL_TEXTURE_2D,texid_1);

  glBegin(GL_QUADS);                //面で立方体を描く
  for (j = 0; j < 6; j++) {
    glNormal3dv(normal[j]);
    glTexCoord2d(0.0,0.0);   //テクスチャ貼り付け
    glVertex3dv(vertex[face[j][0]]);
    glTexCoord2d(0.0,1.0);
    glVertex3dv(vertex[face[j][1]]);
    glTexCoord2d(1.0,1.0);
    glVertex3dv(vertex[face[j][2]]);
    glTexCoord2d(1.0,0.0);
    glVertex3dv(vertex[face[j][3]]);
  }
  glEnd();
  glBindTexture(GL_TEXTURE_2D,0);
  glDisable(GL_TEXTURE_2D);
  /* アルファテスト終了 */
  glDisable(GL_ALPHA_TEST);

}



/*ディスプレイ関数*/
void display(void)
{ 
  rad  = r*PI/180.0;
  rx=cos(rad);
  ry=sin(rad);

  glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
  
  glLoadIdentity();
  
  /* 視点位置と視線方向 */
  gluLookAt(ex, 3.0, ez,(100)*rx+ex, 15.0, (100)*ry+ez, 0.0, 1.0, 0.0);
  //gluLookAt(400+ex,500,400+ez,0,0,0,0,1,0);
  
  /* 光源の位置設定 */
  glLightfv(GL_LIGHT0, GL_AMBIENT, light0pos);
  
  /* 図形の描画 */
  glPushMatrix();
  glMaterialfv(GL_FRONT,GL_AMBIENT,white);
  Ground();
  glPopMatrix();

  glMaterialfv(GL_FRONT,GL_SHININESS,&shininess);
  glMaterialfv(GL_FRONT,GL_AMBIENT_AND_DIFFUSE,white);
  cube(180,0,-200,80,100,150);//東急
  cube(170,100,-200,30,20,30);//東急の上に乗っかってる箱的な部分
  solid(x_z6,0,100);  //109men's(前)
  solid(x_z7,0,100);  //109men's(後ろ)
  cube(-250,20,-300,500,10,50);//線路
  glMaterialfv(GL_FRONT,GL_AMBIENT_AND_DIFFUSE,gray2);
  cube(170,25,-50,30,5,160);//東急と井の頭線の建物をつなぐ渡り廊下的なやつ
  cube(170,55,-50,30,5,160);//東急と井の頭線の建物をつなぐ渡り廊下的なやつ
  cube(200,0,-250,60,110,50);//ハチ公改札と東急の間の少し大きな建物
  solid(x_z3,0,120);  //tsutaya(前)
  solid(x_z2,0,120);  //tsutaya(後ろ)
  cube(170,0,110,50,150,200); //井の頭線の駅
  glMaterialfv(GL_FRONT,GL_AMBIENT_AND_DIFFUSE,gray);
  solid(x_z4,0,90);//渋谷駅前ビル(前)
  solid(x_z5,0,90);//渋谷駅前ビル(後ろ)
  glPushMatrix();
  glRotatef(5,0,1,0);
  cube(100,0,-160,40,10,40);//アオガエルのとなりにある建物
  glPopMatrix();
  solid(x_z1,0,70);  //大盛堂書店
  glMaterialfv(GL_FRONT,GL_AMBIENT_AND_DIFFUSE,green);
  cube(10,0,-250,190,60,50);//ハチ公改札
  glMaterialfv(GL_FRONT,GL_AMBIENT_AND_DIFFUSE,glassblue);
  cube(170,25,-50,30,30,160);//東急と井の頭線の建物をつなぐ渡り廊下的なやつ(ガラス部分)
  cube(170,20,-200,10,30,150);//東急(ガラス張り)

  glutSwapBuffers();
  
  /* 一周回ったら回転角を 0 に戻す */
  if (r >= 360 || r <= -360) r = 0;
}



/*変換詳細設定*/
void resize(int w, int h){
    glViewport(0, 0, w, h);
    
    /* 透視変換行列の設定 */
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective(30.0, (double)w / (double)h, 1.0, 10000.0);
    
    /* モデルビュー変換行列の設定 */
    glMatrixMode(GL_MODELVIEW);
}




/*キーボード操作*/
void specialkeyfunc(int key, int x, int y)
{
  if(key == GLUT_KEY_UP){
    ex+=3*cos(rad);
    ez+=3*sin(rad);
  }
  
  if(key == GLUT_KEY_DOWN){
    ex-=3*cos(rad);
    ez-=3*sin(rad);
  }
  
  if(key == GLUT_KEY_RIGHT){
    ez+=3*cos(rad);
    ex-=3*sin(rad);
  }    

  if(key == GLUT_KEY_LEFT){      
    ez-=3*cos(rad);
    ex+=3*sin(rad);
  }
  glutPostRedisplay();
}





/*キーボード操作*/
void keyboard (unsigned char key,int x,int y){
    switch(key){
        case 'r':
            r++;
            glutPostRedisplay();
            break;
            
        case 'R':
            r--;
            glutPostRedisplay();
            break;
            
        case 'q':
        case 'Q':
        case '\033':
            exit(0);
            break;
            
        default:
            break;
    }


}



/*初期化操作*/
static void init(void)
{  
  /*テクスチャの読み込み*/
  GLubyte textures2[TEXHEIGHT2][TEXWIDTH2][3];   //テクスチャの読み込みに使う配列定義
  FILE *fp;
 
  /*画像の読み込み*/
  if((fp = fopen(texture2,"rb")) != NULL) {
    fread(textures2,sizeof(textures2),1,fp);
    fclose(fp);
  }
  else perror(texture2);


  //テクスチャの読み込み開始
  glPixelStorei(GL_UNPACK_ALIGNMENT, 1); //テクスチャ画像はバイト単位に詰め込まれている
  gluBuild2DMipmaps(GL_TEXTURE_2D,GL_RGB,TEXWIDTH2,TEXHEIGHT2,
		    GL_RGB,GL_UNSIGNED_BYTE,textures2);

  /*テクスチャを拡大・縮小する方法の指定*/
  glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
  glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR); 

  glClearColor(0.5, 0.95, 1.0, 1.0);
  
  glEnable(GL_DEPTH_TEST);
  
  glEnable(GL_CULL_FACE);
  glCullFace(GL_BACK);
    
  glEnable(GL_LIGHTING);
  glEnable(GL_LIGHT0);
}





/*main関数*/
int main(int argc, char *argv[])
{
    glutInit(&argc, argv);
    glutInitWindowSize(640,480);
    glutInitDisplayMode(GLUT_RGBA | GLUT_DOUBLE | GLUT_DEPTH);
    glutCreateWindow(argv[0]);
    glutDisplayFunc(display);
    glutReshapeFunc(resize);
    glutSpecialFunc(specialkeyfunc);
    glutKeyboardFunc(keyboard);
    init();
    glutMainLoop();
    return 0;
}
