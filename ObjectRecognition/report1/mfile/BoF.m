%
% BoFベクトルと非線形SVMによる分類
%
function classify = BoF(list)


addpath('/usr/local/class/object/MATLAB/sift');
run('/usr/local/class/object/MATLAB/vlfeat/vl_setup');


%
%  codebook生成
%
%sift特徴の計算
siftvec=[];
for i=1:size(list,1)
  I=im2double(rgb2gray(imread(list{i})));
  [pnt,desc] = sift_rand(I,'randn',500);
  siftvec = [siftvec,desc];
end

%k-means法で代表ベクトルを抽出
[codebook,idx]=vl_kmeans(siftvec,500);
dim=size(codebook,1);
k=size(codebook,2);




%
%  BoFベクトルの抽出
%
bovw=[];
for i=1:size(list,1)
    %画像からsift特徴を抽出
    c=zeros(k,1);
    I=im2double(rgb2gray(imread(list{i})));
    [pnt,desc]=sift_rand(I,'randn',2000);

    %k次元の零行列を生成し,ヒストグラムベクトルとしてのVWの出現頻度をカウント
    for j=1:size(desc,2)
      s=zeros(1,k);
      for t=1:128
        s=s+(codebook(t,:)-desc(t,j)).^2;
      end
      [dist,sidx]=min(s);
      c(sidx,1)=c(sidx,1)+1.0;
    end
    %正規化し、i番目の画像のBoFとする。
    c=c/sum(c);
    bovw=[bovw c];
end 
    bovw = bovw.';



%
%  5-fold cross validation & 非線型SVMによる分類率計算
%
class=[];%各評価データの分類精度
label = [ones(100,1);ones(100,1)*(-1)];

for i=1:5
  %評価用のlabelとデータ
  value = bovw(i:5:200,:); 
  lab_val = label(i:5:200,:); 
  
  %学習用のlabelとデータ
 learner=[];
 lab_learn=[];
  for j=1:200
   l=i;
        if i==5
           l=0;
        end
      if mod(j,5) ~= l
      learner=[learner;bovw(j,:)];
      lab_learn = [lab_learn;label(j,:)];
    end
  end
  
  %非線型SVM  
  model=fitcsvm(learner,lab_learn,'KernelFunction','rbf', 'KernelScale','auto');
  [predicted_label, scores] =predict(model,value);
  class = [class,numel(find(lab_val==predicted_label))/numel(lab_val)];
end
classify = (class(1)+class(2)+class(3)+class(4)+class(5))/5;
end