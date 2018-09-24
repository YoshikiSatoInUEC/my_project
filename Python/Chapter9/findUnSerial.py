#!python3
#findUnSerial.py  -  連番でないファイルを見つける

import os, sys, re

#spamxxx.txtの正規表現を作る
namePattern = re.compile(r"""^spam\d\d\d\.txt""")

#spamxxxの連番があるフォルダを見つける
for folders, subfolers, filenames in os.walk("/Users/Yoshiki/Desktop/my_project/"):
    if(len(filenames) > 0):
        if namePattern.search(filenames[0]):
            os.chdir(folders)
            #連番が飛んでる箇所を見つける
            for i in range(len(filenames)):
                name = "spam" + str("%03d"%(i+1)) + ".txt"
                if name not in filenames:
                    #後に続くファイル名を変更
                    for j in range(len(filenames)-1):
                        os.rename("spam" + str("%03d"%(i+j+2)) + ".txt","spam" + str("%03d"%(i+j+1)) + ".txt")
                        print(os.listdir(folders))
                    #連番ファイルの間に隙間を開ける
                    for j in range(len(filenames)-1):
                        os.rename("spam" + str("%03d"%(len(filenames)-j)) + ".txt","spam" + str("%03d"%(len(filenames)-j+1)) + ".txt")
                        if len(filenames)-j == i:
                            print(os.listdir(folders))
                            break;
                        break;
