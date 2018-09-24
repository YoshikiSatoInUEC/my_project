#include<GL/glut.h>
#include<stdio.h>
#include<stdlib.h>


/*パラメータ*/
GLfloat light0pos[] = { -100.0, 100.0, -100.0, 1.0 };
GLfloat light1pos[] = { 5.0, 3.0, 0.0, 1.0 };
GLfloat green[] = { 0.0, 0.0, 0.0, 0.0 };
int ex=0,ez=0;
int r=0;

/*solid用x-z座標*/
GLdouble x_z1[][2] = {
    {-60,110},
    {-50,120},
    {-120,150},
    {-50,150}
};

GLdouble x_z2[][2]={
    {-160,20},
    {-90,20},
    {-160,120},
    {-90,70}
};

GLdouble x_z3[][2]={
    {-60,20},
    {-50,45},
    {-60,70},
    {-50,45}
};

GLdouble x_z4[][2]={
    {90,120},
    {100,110},
    {90,140},
    {100,140}
};

GLdouble x_z5[][2]={
    {90,140},
    {170,140},
    {90,200},
    {170,190}
};


/*長方形の作成*/
void cube(double x, double y, double z, double width, double height, double depth/*, GLuint Texture*/){
    int i,j;
    GLdouble vertex[][3] = {
        { x,		y,			z },       //0
        { x+width,	y,			z },       //1
        { x+width,	y+height,	z },       //2
        { x,		y+height,	z },       //3
        { x,    	y,      	z+depth }, //4
        { x+width,	y,			z+depth }, //5
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
        { 0.0, 0.0 },
        { 1.0, 0.0 },
        { 1.0, 1.0 },
        { 0.0, 1.0 }
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



/*長方形の作成*/
void solid (GLdouble xandz[4][2] , double y, double height/*, GLuint Texture*/){
    int i,j;
    GLdouble vertex[][3] = {
        { xandz[0][0], y,		 xandz[0][1] },       //0
        { xandz[1][0], y,		 xandz[1][1] },       //1
        { xandz[1][0], y+height, xandz[1][1] },       //2
        { xandz[0][0], y+height, xandz[0][1] },       //3
        { xandz[2][0], y,      	 xandz[2][1] }, //4
        { xandz[3][0], y,	  	 xandz[3][1] }, //5
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
        { 1.0, 0.0 },
        { 1.0, 1.0 },
        { 0.0, 1.0 }
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





/*ディスプレイ関数*/
void display(void)
{
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    
    glLoadIdentity();
    
    /* 視点位置と視線方向 */
    gluLookAt(ex, 15.0, ez,ex+100, 15.0, ez, 0.0, 1.0, 0.0);
  //  gluLookAt(-200+ex,300,-300+ez,0,0,0,0,1,0);
    
    /* 光源の位置設定 */
    glLightfv(GL_LIGHT0, GL_POSITION, light0pos);
  //  glLightfv(GL_LIGHT1, GL_POSITION, light1pos);
    
    glRotated(r,0.0,1.0,0.0);
    
    /* 図形の描画 */
    cube(190,0,-90,40,70,100);//駅
    cube(190,15,10,40,20,100);//駅をつなぐ渡り廊下的なやつ
    cube(100,0,110,70,70,30);
    cube(120,0,-90,70,40,1);//看板
    cube(-250,0,-90,150,70,30);
    solid(x_z1,0,50);
    solid(x_z2,0,50);
    cube(-240,0,20,80,50,100);
    cube(-90,0,20,30,70,50);
    solid(x_z3,0,70);
    solid(x_z4,0,70);
    solid(x_z5,0,50);
    cube(190,0,110,40,150,120);
    cube(-250,20,-140,500,10,50);

    
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
    gluPerspective(30.0, (double)w / (double)h, 1.0, 1000.0);
    
    /* モデルビュー変換行列の設定 */
    glMatrixMode(GL_MODELVIEW);
}




/*キーボード操作*/
void specialkeyfunc(int key, int x, int y)
{
        if(key == GLUT_KEY_UP)
            ex+=3;
            
        if(key == GLUT_KEY_DOWN)
            ex-=3;
            
        if(key == GLUT_KEY_RIGHT)
            ez+=3;
        
        if(key == GLUT_KEY_LEFT)
            ez-=3;

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
void init(void)
{
    glClearColor(1.0, 1.0, 1.0, 1.0);
    
    glEnable(GL_DEPTH_TEST);
    
    glEnable(GL_CULL_FACE);
    glCullFace(GL_BACK);
    
    glEnable(GL_LIGHTING);
    glEnable(GL_LIGHT0);
 //   glEnable(GL_LIGHT1);
 //   glLightfv(GL_LIGHT1, GL_DIFFUSE, green);
 //   glLightfv(GL_LIGHT1, GL_SPECULAR, green);
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
