import os

for foldername, subfolders,filenames in os.walk('./'):
    print('The current folder is ' + foldername)

    for subfolder in subfolders:
        print('SUBFOLDER OF '+ foldername + ': ' + subfolder)

    for filename in filenames:
        print('FILE INSIDE ' + foldername + ': ' + filename)

    print('')
