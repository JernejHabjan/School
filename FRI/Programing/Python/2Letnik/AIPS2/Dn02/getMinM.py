def getPrimes(n):
    primes = [2 ,3 ,5 ,7 ,11 ,13 ,17 ,19]
    for i in range(20 ,n):
        for j in range(2, i -1):
            if i % j == 0:
                break
        else:
            primes.append(i)
    return primes

def get():
    elements = [16, 15, 4, 0, 20, 5, 11]
    for m in range(7, 24):
        for p in getPrimes(1000):
            tempArray = [None] * m
            broke = False
            for k in elements:
                index = (p * k) % m
                if (tempArray[index] is None):
                    tempArray[index] = k
                else:
                    broke = True
                    break
            if not broke:
                print(str(m) + " " + str(p))
                return

get()
## VRNE m = 13, p = 2