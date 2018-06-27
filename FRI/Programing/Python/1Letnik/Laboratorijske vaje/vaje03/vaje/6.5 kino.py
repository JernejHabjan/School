__author__ = 'Roglan'
# posnet tudi drug film
filmi = [
    ('Poletje v skoljki 2', 6.1),
    ('Ne cakaj na maj', 7.3),
    ('Pod njenim oknom', 7.1),
    ('Kekec', 8.1),
    ('Poletje v skoljki', 7.2),
    ('To so gadi', 7.7)
]

najvisja = filmi[0][1]
for element in zip(filmi):
    for film, ocena in element:

        a = str.split(film)
        for besede in a:
            if besede == "2":
                print(film)
