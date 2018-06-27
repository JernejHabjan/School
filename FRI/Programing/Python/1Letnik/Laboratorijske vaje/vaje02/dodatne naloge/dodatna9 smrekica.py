__author__ = 'Roglan'
""""a=int(input("vpisi visino smrekce: "))
x=1

while x<a:
    prazni_prostori=(a-x)//2
    sredina=a//2
    print(prazni_prostori*"",sredina-x*"*",prazni_prostori*"")
    x+=1
print (a+1*"*")"""

n = int(input('Vpi�i vi�ino: '))

for i in range(1, n + 1):
    print(' ' * (n - i) + '*' * (2 * i - 1))
