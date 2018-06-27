def kreiraj_vozlisca(odcitki, LEN_ODCITEK):
    vozlisca = []
    vozlisca.append(odcitki[0][:LEN_ODCITEK-1])
    for i in range(len(odcitki)):
        vozlisca.append((odcitki[i][1: LEN_ODCITEK]))
    return vozlisca

def sestavi_vozlisca(vozlisca, i):
    #rekurzivno sestavljamo vozlišča ki pašejo skupaj. če pridemo do konca- i>=len(vozlisca) potem se vrnemo
    #gremo pa skozi vsa vozlišča, saj je lahko več vozlišč istih, in se moramo razvejati z rekurzijo
    if  i >= len(vozlisca):
        return True

    tmp_vozlisce = vozlisca[i]
    print(tmp_vozlisce)
    for vozlisce in vozlisca:
        if(tmp_vozlisce[1:] == vozlisce[:len(vozlisce)-1]): # ce mogoce pašeta skupej... pol se pomakn naprej
            sestavi_vozlisca(vozlisca, i + 1)
            #if():
def main():
    LEN_ODCITEK = 4
    odcitki = ["AGGA", "ATCA", "ATTA", "ATTT", "CAGG", "CATT", "TATC", "TCAG", "TCAT", "TTAT", "TTCA", "TTTC", "TTTT"]

    vozlisca = kreiraj_vozlisca(odcitki, LEN_ODCITEK)

    print(vozlisca)

    for i in range(len(vozlisca)):
        sestavi_vozlisca(vozlisca, i)
        print("")
main()