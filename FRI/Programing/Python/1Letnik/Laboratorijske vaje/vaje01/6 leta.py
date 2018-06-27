__author__ = 'jh0228'
leto = int(input("vpisi leto"))
print((leto % 100 != 0) & (leto % 4 == 0) & (leto % 400 == 0))
