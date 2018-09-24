/*線形リストで計算したとき*/
#include<stdio.h>
#include<stdlib.h>
#include<math.h>

/*構造体宣言*/
typedef struct Node
{
  double data;
  struct Node *next;
} Node;

/*プロトタイプ宣言*/
double sum(Node *p);
Node *BubbleSort(Node *head1);
Node *createNode(double x);
void print_nodes(Node *p);

/*main関数*/
int main(void)
{
  /*変数宣言*/
  double x[]={1.0e16,-1.0e2,23,-6.4,3.6e2,-0.01,8.0,-70,5.0e3,1.2e-2,-3.0e3,46,-1.7e3,10,-5.0e2,7.0,-2.0e-3,0.3,-30,3.1,-1.0e16};//入力データ配列
  double result1,result2=0; //result1:入力順の総和、result2:ソートしたときの総和
  Node *head=NULL,*q;
  int i;

  /*線形リストの生成*/
  for(i=0;i<sizeof(x)/sizeof(x[0]);i++)
    if(i==0){
      head=createNode(x[i]);
      q=head;
    }else{
      q->next=createNode(x[i]);
      q=q->next;
    }

  result1 = sum(head);        //result1はそのまま総和．
  head = BubbleSort(head);    //result2はソートして、
  print_nodes(head);          //(表示して)
  result2 = sum(head);        //総和を求める．

  printf("データの入力順に総和を求めたとき:%f\n",result1);
  printf("データ入力後、絶対値に関して昇順に並べ替えた後で総和を求めたとき:%f\n",result2);

  return 0;
}

/*総和を求める関数*/
double sum (Node *p)
{
  double sum=0;
  while(p!=NULL) //p==NULLになるまで(最後のノードにたどり着くまで)加算
    {
      sum+=p->data;
      p=p->next;
    }
  return sum;
}

/*バブルソートする関数*/
Node *BubbleSort(Node *head1)
{
  /*変数宣言*/
  Node *head2=NULL,*q,*temp,*p,*r;
  int i = 0;

    /*aを絶対値昇順に*/
  while(head1->next != NULL){      //head1にノードが一個付いている状態になるまでループ
    q = head1;               
    while(q->next->next != NULL){  //qが後ろから３番目になるまでループ
      if(q == head1 && (fabs(q->data) > fabs(q->next->data))){  
	  temp = q;
	  q = q->next;
	  
	  head1 = q;             //head1は小さい方のノードを指すようになる．
	  temp->next = q->next;  //大きい方のノードは次のノードを指すようになる．
	  q->next = temp;        //小さい方のノードの次が大きい方のノードになる．
 	}
      if(fabs(q->next->data) > fabs(q->next->next->data)){
	  temp = q->next;
	  p = q;
	  q = q->next->next;
	  
	  p->next = q;           //head1は小さい方のノードを指すようになる．
	  temp->next = q->next;  //大きい方のノードは次のノードを指すようになる．
	  q->next = temp;        //小さい方のノードの次が大きい方のノードになる．
	}else
	  q = q->next;
    }
    r = q->next;                 //最後のノードにポインタr指定．
    r->next = NULL;              

    /*新たに絶対値昇順に線形リストを作り直す*/
    if(head2 == NULL){           //新たな線形リストのheadがNULLの時
      head2 = r;
      head2->next = NULL;
    }else{
      temp = head2;
      head2 = r;
      r->next = temp;
    }
    q->next = NULL;             //最後のノードのnextはNULL．
  }
  
  /*head1に残ったノードをhead2の線形リストにくっつける．*/
  temp = head2;
  head2 = q;
  q->next = temp;
  head1 = NULL;
  
  return head2;
}

/*ノードをつくる関数*/
Node *createNode(double x)
{
  Node *temp;
  temp = (Node *)malloc(sizeof(Node));
  temp->data=x;
  temp->next=NULL;
  return temp;
}

/*ノードの表示*/
void print_nodes(Node *p){
  while(p!=NULL){
    printf("%f\n",p->data);
    p=p->next;
  }
}
