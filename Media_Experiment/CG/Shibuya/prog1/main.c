#include<GLUT/glut.h>

GLdouble vertex[][3]={
    {0.0,0.0,0.0},
    {1.0,0.0,0.0},
    {1.0,1.0,0.0},
    {0.0,1.0,0.0},
    {0.0,0.0,1.0},
    {1.0,0.0,1.0},
    {1.0,1.0,1.0},
    {0.0,1.0,1.0},
};

int face[][4]={
    {0,1,2,3},
    {1,5,6,2},
    {5,4,7,6},
    {4,0,3,7},
    {4,5,1,0},
    {3,2,6,7}
};

GLdouble color[][3]={
    {1.0,0.0,0.0},
    {0.0,1.0,0.0},
    {0.0,0.0,1.0},
    {1.0,1.0,0.0},
    {1.0,0.0,1.0},
    {0.0,1.0,1.0}
};


void idle (void){
    glutPostRedisplay();
}

void display(void){
    int i,j;
    static int r = 0;
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    
    glLoadIdentity();
    
    gluLookAt(3.0,4.0,5.0,0.0,0.0,0.0,0.0,1.0,0.0);  //視点位置と視線変更
    glRotated((double)r,0.0,1.0,0.0);  //図形の回転
    
    /*図形の描画*/
    glColor3d(0.0,0.0,0.0);
    glBegin(GL_QUADS);
    for(j=0;j<6;j++){
        glColor3dv(color[j]);
        for(i=0;i<4;i++){
            glVertex3dv(vertex[face[j][i]]);
        }
    }
    glEnd();
    
    glutSwapBuffers();
    
    /*一周回ったら回転書くを0に戻す*/
    if(r++ >= 360) r=0;
}

void resize(int w,int h){
    glViewport(0,0,w,h);
    
    /*透視変換行列の設定*/
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective(30.0,(double)w/(double)h,1.0,100.0);
    
    /*モデルビュー変換行列の設定*/
    glMatrixMode(GL_MODELVIEW);
}

void mouse(int button,int state,int x,int y){
    switch(button){
        case GLUT_LEFT_BUTTON:
            if(state == GLUT_DOWN){
                glutIdleFunc(idle);  //アニメーション開始
            }else{
                glutIdleFunc(0);  //アニメーション停止
            }
        break;
            
        case GLUT_RIGHT_BUTTON:
            if(state == GLUT_DOWN){
                glutPostRedisplay();  //コマ送り(1ステップだけ進める)
            }
        break;
            
        default:
        break;
    }
}

void keyboard(unsigned char key, int x,int y){
    switch(key){
        case'q':
        case'Q':
        case'\033':  //'\033'はESCのASCIIコード
            exit(0);
        default:
        break;
    }
}

void init(void){
    glClearColor(1.0,1.0,1.0,1.0);
    glEnable(GL_DEPTH_TEST);
    
    glEnable(GL_CULL_FACE);
    glFrontFace(GL_CW);
    glCullFace(GL_BACK);
}

int main(int argc,char *argv[]){
    glutInit(&argc,argv);
    glutInitDisplayMode(GLUT_RGBA | GLUT_DOUBLE |GLUT_DEPTH);
    glutCreateWindow(argv[0]);
    glutDisplayFunc(display);
    glutReshapeFunc(resize);
    glutMouseFunc(mouse);
    glutKeyboardFunc(keyboard);
    init();
    glutMainLoop();
    
}

