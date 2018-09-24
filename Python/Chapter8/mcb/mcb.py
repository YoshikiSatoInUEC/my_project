#! python3
# mcb.pyw - クリップボードのテキストを保存・復元
# Usage:
# py.exe(or python) mcb.pyw save <keyword> - クリップボードをキーワードで紐づけて保存
# py.exe(or python) mcb.pyw <keyword> - キーワードに紐づけられたテキストをクリップボードにコピー
# py.exe(or python) mcb.pyw list - 全キーワードをクリップボードにコピー
# py.exe(or python) mcb.pyw delete <keyword> - キーワードをshelfから削除
# py.exe(or python) mcb.pyw delete - 全キーワードを削除

import shelve, pyperclip, sys

mcb_shelf = shelve.open('mcb')

#クリップボードの内容を保存
if len(sys.argv) == 3:
   if sys.argv[1].lower() == 'save':
       mcb_shelf[sys.argv[2]] = pyperclip.paste()
       print(mcb_shelf[sys.argv[2]])
   elif sys.argv[1].lower() == 'delete' and sys.argv[2] in mcb_shelf:
       del mcb_shelf[sys.argv[2]]
   else:
      print("error")

elif len(sys.argv) == 2:
   #キーワード一覧と、内容の読み込み
   if sys.argv[1].lower() == 'list':
      pyperclip.copy(str(list(mcb_shelf.keys())))
   elif sys.argv[1] in mcb_shelf:
      pyperclip.copy(mcb_shelf[sys.argv[1]])
   elif sys.argv[1].lower() == 'delete':
      for key in mcb_shelf:
          del mcb_shelf[key]
   else:
      print("error")

else:
   print("error")

mcb_shelf.close()
