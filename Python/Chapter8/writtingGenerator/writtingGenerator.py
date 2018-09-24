#! python3
# writtingGenertator.py - ADJECTIVE、NOUN、ADVERB、VERBを置換する作文ジェネレーター
import re

#テキストファイルをインプット
text_file = open('sampleWritting.txt')
text_content = text_file.read()

part = ["ADJECTIVE","NOUN","ADVERB","VERB"]#置換する品詞

#置換キーワード抽出
keywords = []#置換キーワード入れる変数
keyword_regex = re.compile('[A-Z]{2,}')
keytmp = keyword_regex.findall(text_content)
for tmp in keytmp:
    if tmp in part:
        keywords.append(tmp)
print(keywords)

#置換する語を入力
changeVal = [] #空リスト作ってここにチェンジする値を入れる
for key in keywords:
    if(key[0] in ["A","E","I","O","U"]):
      print("Enter an "+key.lower()+":")
    else:
      print("Enter a "+key.lower()+":")
    changeVal.append(input())

#置換
for i in range(len(keywords)):
    text_regex = re.compile(keywords[i])
    text_content = text_regex.sub(changeVal[i],text_content,1)

#TODO:出力(シェル&テキスト)
print(text_content)
text_newFile = open('newWritting.txt','w')
text_newFile.write(text_content)
text_newFile.close()
text_file.close()
