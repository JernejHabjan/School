a= -2
b= 2

def ab_vrednost(x):
    if x<0:
        x=-x
    return x
if ab_vrednost(a)==ab_vrednost(b):
    print("ab je ista")
else:
    print("ni isto")
