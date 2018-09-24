%
% MatConvnetの標準ネットワーク(AlexNet)による DCNN特徴量と線形SVM 
%

function classify = DCNN(list)
addpath('/usr/local/class/object/matconvnet');
addpath('/usr/local/class/object/matconvnet/matlab');
vl_setupnn;
D=[];
% 学習済モデルの読み込み
net = load('imagenet-caffe-alex.mat') ;



%
%  DCNN特徴量の導出
%
for i=1:size(list,1)
  % 画像の読み込み, リサイズ(224x224)，平均画像の引き算
  im = imread(list{i}) ;
  im_ = single(im) ;
  im_ = imresize(im_, net.meta.normalization.imageSize(1:2)) ;
  im_ = im_ - net.meta.normalization.averageImage ;

  % CNNの実行．画像をネットワークに流す。
  res = vl_simplenn(net, im_);

  % squeeze関数で，ベクトル化。
  dcnnf=squeeze(res(end-3).x);
  % 最終的な dcnnf が画像特徴量となる。
  dcnnf=dcnnf/norm(dcnnf);
  D=[D,dcnnf];
end




%
%  5 fold cross validation & 線型SVMによる分類率計算
%
class = []; %各評価データの分類精度
label = [ones(100,1);ones(100,1)*(-1)];  
D = D.';
for i=1:5
  %評価用のlabelとデータ
  value = D(i:5:200,:); 
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
      learner=[learner;D(j,:)];
      lab_learn = [lab_learn;label(j,:)];
    end
  end

 %線型SVMの実行
  model = fitcsvm(learner,lab_learn,'KernelFunction','linear'); 
  [predicted_label, scores] =predict(model,value);
  class = [class,numel(find(lab_val==predicted_label))/numel(lab_val)];
end

classify = (class(1)+class(2)+class(3)+class(4)+class(5))/5;
end
