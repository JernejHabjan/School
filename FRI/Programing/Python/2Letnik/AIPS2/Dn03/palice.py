DICT = {  # dolzina ->[cena, dobicek]
    1: [2, 2],
    2: [4, 4],
    3: [7, 7],
    4: [9, 9],
    5: [10, 10],
    6: [11, 11],
    7: [12, 12],
    8: [19, 19],
    9: [22, 22]
}
def calcProfit(minus=0):
    value = [x1[0] for x1 in DICT.values()]
    len = max(DICT.keys())
    solution = [0] * (int(len) + 1)
    for i in range(len + 1):
        maxPrice = value[i - 1]
        for j in range(i):
            prvi = maxPrice
            drugi = value[j] + solution[i - (j + 1)]
            ###### SPODAJ SO TRIJE PRIMERI --- ZA OBIČAJNO -> MINUS ==0, MINUS -> MINUS >0 in RAZMERJE -> BA
            if minus < 0:  ##BA
                razmerje = value[j] if value[j] > solution[i - (j + 1)] else solution[i - (j + 1)]  ##RAZMERJE B/Až
                if prvi <= drugi + razmerje:
                    maxPrice = prvi
                else:
                    maxPrice = drugi
            elif minus > 0:  # tukaj odšteje -2
                if prvi <= drugi + minus:
                    maxPrice = prvi
                else:
                    maxPrice = drugi
            else:  ##minus == 0
                maxPrice = max(prvi, drugi)
            solution[i] = maxPrice
            DICT[i][1] = maxPrice
    i = 1
    for key, arr in DICT.items():
        print(str(key) + " " + str(arr))
        i += 1
def main():
    # 1. - 2€ pri rezu
    calcProfit(2)  ## ODŠTEJE -2 PRI REZU
    print()  ##emty line
    # 2. -(b/a)€  -- b/a je razmerje rezov kjer je a krajša palica
    calcProfit(-1)  ##ODŠTEJE RAZMERJE B/A PRI REZU
main()
