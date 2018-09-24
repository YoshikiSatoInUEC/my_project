/*配列で計算したとき*/
#include<stdio.h>
#include<math.h>

double sum(double a[]);
void BubbleSort(double[],int n);

int main(void)
{
  /*変数宣言*/
  double x[]={1.0e16,-1.0e2,23,-6.4,3.6e2,-0.01,8.0,-70,5.0e3,1.2e-2,-3.0e3,46,-1.7e3,10,-5.0e2,7.0,-2.0e-3,0.3,-30,3.1,-1.0e16};//入力データ配列
  double result1,result2; //result1:入力順の総和、result2:ソートしたときの総和
  int i;

  result1 = sum(x);   //result1はそのまま総和．
  BubbleSort(x,(sizeof(x)/sizeof(x[0])));    //result2はソートして、

  /*ソート結果表示*/
  for(i=0;i<sizeof(x)/sizeof(x[0]);i++)
    printf("%f\n",x[i]);
  
  result2 = sum(x);   //総和を求める．

  printf("データの入力順に総和を求めたとき:%f\n",result1);
  printf("データ入力後、絶対値に関して昇順に並べ替えた後で総和を求めたとき:%f\n",result2);

  return 0;
}

/*総和を求める関数*/
double sum (double a[])
{
  int i;
  double sum;
  for(i=0;i<21;i++)
    sum+=a[i];
  return sum;
}

/*バブルソートする関数*/
void BubbleSort(double a[],int n)
{
  int i,j;
  double temp;
    
    /*aを絶対値昇順に*/
  for(i=0;i<n-1;i++)
    for(j=n-1;j>i;j--){
      if(fabs(a[j-1])>fabs(a[j])){
	  temp = a[j];
	  a[j] = a[j-1];
	  a[j-1] = temp;
 	}
    }            
}


