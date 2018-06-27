__author__ = 'Roglan'
# ki poišče in izpiše prva štiri števila, pri katerih je vsota deliteljev enaka številu.
# Program ničesar ne sprašuje, le štiri številke izpiše.

a = 1
sestevalec = 0
stevilo = 0
while stevilo != 4:
    x = 0
    while x < a:
        if x < 4:
            x += 1
        else:
            break
        if (a % x == 0):
            sestevalec += a
            if (sestevalec == a):
                print(stevilo)
            a = a + 1

            # TODO
