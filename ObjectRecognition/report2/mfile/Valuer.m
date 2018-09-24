%
%  Flickerの画像を評価する関数
%  (入力:評価する画像のリスト、学習関数でつくったモデル)
%  (出力:ソート結果を書き込んだファイル)
%
function Valuer(list_val,model)
addpath('/usr/local/class/object/matconvnet');
addpath('/usr/local/class/object/matconvnet/matlab');
vl_setupnn;   %初期設定
net = load('imagenet-caffe-alex.mat') ;
test_D=[];


%
%  各画像に対してDCNN導出
%
for i=1:size(list_val,1)
  % 画像の読み込み, リサイズ(224x224)，平均画像の引き算
  im = imread(list_val{i}) ;
  im_ = single(im) ;
  im_ = imresize(im_, net.meta.normalization.imageSize(1:2)) ;
  im_ = im_ - net.meta.normalization.averageImage ;

  % CNNの実行．画像をネットワークに流す。
  res = vl_simplenn(net, im_);

  % squeeze関数で，ベクトル化。
  dcnnf=squeeze(res(end-3).x);
  % 最終的な dcnnf が画像特徴量となる。
  dcnnf=dcnnf/norm(dcnnf);
  test_D=[test_D,dcnnf];
end
test_D=test_D.';


%
%  SVM分類関数で分類&ソート
%
[predicted_label, scores] = predict(model,test_D);
[sort_scores,index] = sort(scores(:,2),'descend');



%
%  list(index)で出力
%
list_val = list_val.';
sort_scores = sort_scores.';

file = fopen('sort_img.txt','w');
for i=1:size(list_val,2)
  fprintf(file,'%s %f\n',list_val{index(i)},sort_scores(i));
end
fclose(file);
end


