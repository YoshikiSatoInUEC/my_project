/*課題3*/
#include<stdio.h>
#include<stdlib.h>

/*ノード型宣言*/
typedef struct Node{
  char data;
  struct Node *next;
} Node;

/*プロトタイプ宣言*/
void print(Node *head);
Node *rePoland(char *formula);
Node *pop(Node *p);
Node *push(Node *head,char c);
int priority(char c);

/*メイン関数*/
int main(void){
  /*変数宣言*/
  Node *head = NULL;
  char formula[100];
  int i=0;

  /*入力*/
  printf("算術式を入力:");
  scanf("%s",formula);  
  head = rePoland(formula);

  return 0;
}

/*文字列出力関数*/
void print(Node *head){
  Node *p;
  p = head;
  while(p!=NULL){
      printf("%c",p->data);
      p = p->next;
  }
  printf("\n");
}

/*逆ポーランド記法変換関数*/
Node *rePoland(char *formula){
  int i=1,x,y;
  Node *head;

  head = push(head,formula[0]);
  while(formula[i]!='\0'){

    x = priority(head->data);
    y = priority(formula[i]);
    while(x>=y){
       head = pop(head);
       if(head==NULL)
	 break;
       x = priority(head->data);
       }
    head = push(head,formula[i]); 
    x = priority(head->data);
    i++;
    }
  
  /*head == NULLになるまで繰り返しpop*/
  while(head!=NULL){
    head=pop(head);
    }
    printf("\n");
    return head;
}
 
/*popする関数*/
Node *pop(Node *head){
   Node *temp=NULL;
   if(head->next!=NULL)
      temp = head->next;
   if(head->data!='('&&head->data!=')')
     printf("%c",head->data);
  free(head);
  head = temp;

    return head;
}

/*pushする関数*/
Node *push(Node *head,char c){
  Node *temp;
  temp = (Node *)malloc(sizeof(Node));
  temp->next=NULL;
  temp->data = c;

  /*ノードの入れ替えが必要か否か*/
  if(head != NULL){
    temp->next = head;
    head = temp;
    temp = NULL;
  }else if(head == NULL){
    head = temp;
    head->next = NULL;
  }

   return head;
}
int priority(char c){
  if(c =='=')
    return 0;
  else if(c == '(')
    return 4;
  else if(c == ')')
    return 1;
  else if(c == '+'||c == '-')
    return 2;
  else if(c == '/'||c == '*')
    return 3;
  else 
    return 5;
}



/*実行結果
Yoshikis-MacBook:Lesson02 Yoshiki$ ./Exercise2-1 
算術式を入力:A=(B-C)/D+E*F
ABC-D/EF*+=
Yoshikis-MacBook:Lesson02 Yoshiki$ ./Exercise2-1 
算術式を入力:A=B-(C/D+E)*F
ABCD/E+-F*=
Yoshikis-MacBook:Lesson02 Yoshiki$ ./Exercise2-1 
算術式を入力:A=B-C/(D+E*F)
ABCD/EF*+-=
*/
