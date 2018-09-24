#! python3
# stripLike.py - strip()関数と同等のもの
import re

def stripLike(text,rmText):
    if rmText == "":
        Init_regex = re.compile(r'^\s+')
        Last_regex = re.compile(r'$\s+')
    else:
        Init_regex = re.compile(r'^'+rmText)
        Last_regex = re.compile(r''+rmText+'$')

    text = Init_regex.sub("",text)
    text = Last_regex.sub("",text)

    return text
    
text = "www.example.www"
rmText = "www"
text = stripLike(text,rmText)
print(text)
