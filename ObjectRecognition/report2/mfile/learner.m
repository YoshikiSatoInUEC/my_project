%
%  画像学習関数 
%  (入力:ポジティブ画像のリスト,ネガティブ画像のリスト)
%  (出力:学習済モデルパラメーターが格納される構造体model)
%
function model = learner(list_P,list_N)
addpath('/usr/local/class/object/matconvnet');
addpath('/usr/local/class/object/matconvnet/matlab');
vl_setupnn;   %初期設定
net = load('imagenet-caffe-alex.mat') ;
list = [list_P;list_N];
n = size(list_P,1);
learn_D=[];

%
%  各画像に対してDCNN導出
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
  learn_D=[learn_D,dcnnf];
end
learn_D=learn_D.';



%
%  線形SVMの導出
%

label = [ones(n,1);ones(500,1)*(-1)];  %トレーニングラベル
model = fitcsvm(learn_D,label,'KernelFunction','linear'); 
end