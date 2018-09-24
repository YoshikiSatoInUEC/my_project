#! python3
# selectCopy.py
import os,sys
from shutil import copyfile

#新しいフォルダを作成
os.mkdir('newFolder')
os.chdir('newFolder')

#ディレクトリを渡り歩く
folder = os.path.abspath("/Users/Yoshiki/Desktop/")

for foldername, subfolders, filenames in os.walk(folder):
    for filename in filenames:
        if filename.endswith('.pdf') or filename.endswith('.jpg'):
            path = foldername + "/" +filename
            print(path)
            copyfile(path,"./"+filename)
