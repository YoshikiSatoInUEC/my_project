%
%  カラーヒストグラムと最近傍分類
%

function classify = color_histgram(list)
%
%  カラーヒストグラムのデータベースの生成
%
database=[];
for i=1:length(list)
  %画像を読み込みRGBをそれぞれ抽出
  I=imread(list{i});
  R=I(:,:,1);
  G=I(:,:,2);
  B=I(:,:,3);
  
  %64次元ベクトルに変換
  X64= floor(double(R)/64) *4*4 +  floor(double(G)/64) *4 +  floor(double(B)/64);
  X64_vec=reshape(X64,1,numel(X64));
  
  %ヒストグラム抽出し正規化
  h=histc(X64_vec,[0:63]);
  h=h / sum(h);
  
  database=[database; h];
end



%
%  5 fold cross validation & 類似度計算
%
label = [ones(100,1);ones(100,1)*(-1)];
class = 0;

for i=1:5
  %評価用データ
  value = database(i:5:200,:);   
  lab_val = label(i:5:200,:);
  
  %学習用データ
  learner=[];
    for j=1:200
        l=i;
        if i==5
           l=0;
        end
      if mod(j,5) ~= l
      learner=[learner;database(j,:)];
      end
    end
  
  %valueの各値に対してインターセクション値を求めて、
  %最も似ているものと同じグループに分類
  for j=1:size(value,1)
    sim=[];  
    for k=1:size(learner,1)  % learnerの行数が画像枚数
       sim=[sim sum(min(learner(k,:),value(j,:)))];  % インターセクション値を求める．
    end
    [sorted,index]=sort(sim,'descend'); % 大きい順にソート．
    
    if (index(1)<101) && (lab_val(j)==1)
        class = class +1;
    end 
    if (index(1)>100) && (lab_val(j)==-1)
        class = class + 1; 
    end
    
  end
  
end

%最終分類精度
classify = class/200;
end
  
   
   
   
   
