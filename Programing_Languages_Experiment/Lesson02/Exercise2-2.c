/*課題4*/
/*線形リストで計算したとき*/
#include<stdio.h>
#include<stdlib.h>
#include<math.h>

/*構造体宣言*/
typedef struct Node
{
  double data;
  struct Node *left;   //左部分木
  struct Node *right;  //右部分木
} Node;

/*プロトタイプ宣言*/
double Sum(Node *p);
Node *createNode(double x);
Node *createtree(Node *head,Node *p);
void PrintTree(Node *p);

/*main関数*/
int main(void)
{
  /*変数宣言*/
  double x[]={1.0e16,-1.0e2,23,-6.4,3.6e2,-0.01,8.0,-70,5.0e3,1.2e-2,-3.0e3,46,-1.7e3,10,-5.0e2,7.0,-2.0e-3,0.3,-30,3.1,-1.0e16};//入力データ配列
  double result=0;
  Node *head=NULL,*q=NULL;
  int i;

  head=createNode(x[i]);
  /*線形リストの生成*/
  for(i=1;i<sizeof(x)/sizeof(x[0]);i++){
      q=createNode(x[i]);
      head = createtree(head,q);         //二分木の生成して
      q = NULL;
  }
  printf("二分木のノードを絶対値で昇順に表示\n");
   PrintTree(head);
  result = Sum(head);        //総和を求める．
  
  printf("データ入力後、絶対値に関して昇順に並べ替えた後で総和を求めたとき:%f\n",result);

  return 0;
}


/*ノードをつくる関数*/
Node *createNode(double x){
  Node *temp;
  temp = (Node *)malloc(sizeof(Node));
  temp->data=x;
  temp->left=NULL;
  temp->right=NULL;
  return temp;
}

/*二分木の生成（ここがおかしい）*/
Node *createtree(Node *head,Node *p){
  Node *q;
  q = head;
   while(q->left != p&& q->right != p){
     if(fabs(p->data) < fabs(q->data)){
       if(q->left==NULL)
	 q->left = p;
       else
	 q = q->left;
     }else{
       if(q->right==NULL)
	 q->right = p;
       else
	 q = q->right;
     }
   }
   return head;
}

/*総和を求める関数*/
double Sum(Node *p){
  double sum=0;
  if(p!=NULL){
    sum += Sum(p->left);
    sum += p->data;
    sum += Sum(p->right);
  }
  return sum;
}
  
void PrintTree(Node *p) {
if(p != NULL) {
  PrintTree(p->left);
  printf("%f\n",p->data);
  PrintTree(p->right);
 }
}      

/*実行結果
Yoshikis-MacBook:Lesson02 Yoshiki$ ./Exercise2-2
二分木のノードを絶対値で昇順に表示
-0.002000
-0.010000
0.012000
0.300000
3.100000
-6.400000
7.000000
8.000000
10.000000
23.000000
-30.000000
46.000000
-70.000000
-100.000000
360.000000
-500.000000
-1700.000000
-3000.000000
5000.000000
10000000000000000.000000
-10000000000000000.000000
データ入力後、絶対値に関して昇順に並べ替えた後で総和を求めたとき:52.000000
*/
