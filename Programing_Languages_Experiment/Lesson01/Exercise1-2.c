/*課題２*/
#include<stdio.h>
#include<string.h>
#include<stdlib.h>

/*構造体宣言*/
typedef struct Name
{
  char firstname[100];
  char lastname[100];
  struct Name *next;
} Name;

/*プロトタイプ宣言*/
Name *BubbleSort_first(Name *head1);
Name *BubbleSort_last(Name *head1);
Name *createName(char *firstname,char *lastname);
void printName(Name *p);


int main(void){

  /*変数宣言*/
  char firstname[100],lastname[100],yesorno[3]={"yes"},yes[]={"yes"};
  Name *head1 = NULL,*head2 = NULL,*q,*p;

  while(strcmp(yesorno,yes)==0)
    {
      /*入力*/
      printf("firstnameを入力:");
      scanf("%s",firstname);
      printf("lastnameを入力:");
      scanf("%s",lastname);

      /*head1に入力されたデータをノードとして繋げ、バブルソートでlastnameの辞書順にソート*/
      q = createName(firstname,lastname);
      if(head1 == NULL)
	head1 = q;
      else{
	p = head1;
	while(p->next!=NULL)
	  p = p->next;
	p->next = q;
	head1 = BubbleSort_last(head1);
      }
   
      /*入力を続行するか否かの確認*/
      printf("続けて入力しますか？(yes/noで入力)");
      scanf("%s",yesorno);

    }
  printf("lastnameの辞書順\n");
  printName(head1);   //head1の表示

  /*新たにhead2にfirstnameの辞書順となる線形リストを生成し、表示*/
  p = head1;
  head2 = createName(p->firstname,p->lastname);    //先頭ノードを生成
  q = head2;
  p = p->next;
  while(p!=NULL){    //とりあえずまずhead1の線形リストをコピー
    q->next = createName(p->firstname,p->lastname);
    p = p->next;
    q = q->next;
  }

  /*バブルソートでfirstnameの辞書順に*/
  head2 = BubbleSort_first(head2);

  printf("firstnameの辞書順\n");
  printName(head2);
  
}

	
/*バブルソートする関数(firstname)*/
Name *BubbleSort_first(Name *head1)
{
  /*変数宣言*/
  Name *head2=NULL,*q,*temp,*p,*r;
  int i = 0;

    /*aを絶対値昇順に*/
  while(head1->next != NULL){      //head1にノードが一個付いている状態になるまでループ
    q = head1;               
    while(q->next->next != NULL){  //qが後ろから３番目になるまでループ
      if(q == head1 && strcmp(q->firstname,q->next->firstname)>0){  
	  temp = q;
	  q = q->next;
	  
	  head1 = q;             //head1は小さい方のノードを指すようになる．
	  temp->next = q->next;  //大きい方のノードは次のノードを指すようになる．
	  q->next = temp;        //小さい方のノードの次が大きい方のノードになる．
 	}
      if(strcmp(q->next->firstname,q->next->next->firstname)>0){
	  temp = q->next;
	  p = q;
	  q = q->next->next;
	  
	  p->next = q;           //head1は小さい方のノードを指すようになる．
	  temp->next = q->next;  //大きい方のノードは次のノードを指すようになる．
	  q->next = temp;        //小さい方のノードの次が大きい方のノードになる．
	}else
	  q = q->next;
    }

    if(head1 == q && q->next->next == NULL){
      if(strcmp(q->firstname,q->next->firstname)>0){
	q->next->next = q;
	head1 = q->next;
	q = q->next;
	q->next->next = NULL;
      }
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

 
/*バブルソートする関数(lastname)*/
Name *BubbleSort_last(Name *head1)
{
  /*変数宣言*/
  Name *head2=NULL,*q,*temp,*p,*r;
  int i = 0;

    /*aを絶対値昇順に*/
  while(head1->next != NULL){      //head1にノードが一個付いている状態になるまでループ
    q = head1;               
    while(q->next->next != NULL){  //qが後ろから３番目になるまでループ
      if(q == head1 && strcmp(q->lastname,q->next->lastname)>0){  
	  temp = q;
	  q = q->next;
	  
	  head1 = q;             //head1は小さい方のノードを指すようになる．
	  temp->next = q->next;  //大きい方のノードは次のノードを指すようになる．
	  q->next = temp;        //小さい方のノードの次が大きい方のノードになる．
 	}
      if(strcmp(q->next->lastname,q->next->next->lastname)>0){
	  temp = q->next;
	  p = q;
	  q = q->next->next;
	  
	  p->next = q;           //head1は小さい方のノードを指すようになる．
	  temp->next = q->next;  //大きい方のノードは次のノードを指すようになる．
	  q->next = temp;        //小さい方のノードの次が大きい方のノードになる．
	}else
	  q = q->next;
    }

    if(head1 == q && q->next->next == NULL){
      if(strcmp(q->lastname,q->next->lastname)>0){
	q->next->next = q;
	head1 = q->next;
	q = q->next;
	q->next->next = NULL;
      }
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
Name *createName(char *firstname, char *lastname)
{
  Name *temp;
  temp = (Name *)malloc(sizeof(Name));
  strcpy(temp->firstname,firstname);
  strcpy(temp->lastname,lastname);
  temp->next=NULL;
  return temp;
}

 
/*ノードの表示*/
void printName(Name *p){
  while(p!=NULL){
    printf("%s %s\n",p->firstname,p->lastname);
    p=p->next;
  }
}


/*実行結果*/
/*[s1410055@sol Programing Languages Experiment]$ ./Exercise1-2
firstnameを入力:mitsuo
lastnameを入力:wakatsuki
続けて入力しますか？(yes/noで入力)yes
firstnameを入力:akihiro
lastnameを入力:kashihara
続けて入力しますか？(yes/noで入力)yes
firstnameを入力:hayaru
lastnameを入力:syono
続けて入力しますか？(yes/noで入力)yes
firstnameを入力:takeshi
lastnameを入力:oda
続けて入力しますか？(yes/noで入力)no
lastnameの辞書順
akihiro kashihara
takeshi oda
hayaru syono
mitsuo wakatsuki
firstnameの辞書順
akihiro kashihara
hayaru syono
mitsuo wakatsuki
takeshi oda*/
