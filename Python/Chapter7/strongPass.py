#! python3
# strongPass.py - 強いパスワード検出プログラム

import re

#強いパスワードとは？？
Upper_regex = re.compile(r'([A-Z])')
Lower_regex = re.compile(r'([a-z])')
Deciminal_regex = re.compile(r'([1-9])')

#入力
print("パスワードを入力してください")
text = input()

#正規表現で調べる
if Upper_regex.search(text) and Lower_regex and Deciminal_regex.search(text) and len(text)>=8:
    print("強いパスワードです")
else:
    print("弱いパスワードです")
