#! python3
# searchBigFile.py

import os

#ディレクトリを渡り歩く
for foldername, subfolders, filenames in os.walk("/Users/Yoshiki/Desktop/"):
    for filename in filenames:
        nowPath = foldername + "/" + filename
        #100MBを超えるものを探す
        if os.path.getsize(nowPath) > pow(10,8):
            print(nowPath)
            #見つかったファイルの絶対パス表示
            print(str(os.path.getsize(nowPath)/pow(10,6)) + "MB")
