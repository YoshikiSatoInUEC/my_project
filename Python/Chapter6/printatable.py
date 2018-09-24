#! python3
table_data=[['apples','oranges','cherries','banana'],
            ['Alice','Bob','Carol','David'],
            ['dogs','cats','moose','goose']]

#print_table関数を定義
def print_table(table_data):
    #各行の最大幅を計算
    col_width = [0] * len(table_data) #要素全て0の配列(要素数は行数)
    for i in range(len(table_data)):
        col_width[i] = len(max(table_data[i],key=len))

    #表作成
    for i in range(len(table_data[0])):
        text = ""
        for j in range(len(table_data)):
            text = text + " "+table_data[j][i].rjust(col_width[j])
        print(text)
 
print_table(table_data)
    
