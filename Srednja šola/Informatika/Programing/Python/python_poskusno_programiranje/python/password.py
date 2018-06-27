a= input ("izberi passowrd")
b=0
c=0
while b<4:
    b=b+1
    c=3-b
    if b<4:
        g=input("svoj passoword")
        if g != a:
            print("imas se ",c, " poskusa")
        else:
         print("mas to")
         break
    else:
        print ("zafailov si")
        break
