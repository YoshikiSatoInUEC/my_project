#! python3
# searchReg.py - フォルダ内のすべての.txtを開いてマッチする語を検索

import re,os

#正規表現を入力
print("Input the regex")
reg = input()

#ファイル内容から入力されたものに一致するものを取り出す
output = []
for filename in os.listdir("./"):
    if re.search('.txt$',filename):
      print(filename)
      file_content = open(filename)
      lines = file_content.readlines()
      newlines = []
      for i in range(len(lines)):
        if re.search(reg,lines[i]):
            newlines.append(lines[i].replace('\n',''))
#        print(lines)
      if not len(newlines) == 0:
        output.append(newlines)

print(output)
