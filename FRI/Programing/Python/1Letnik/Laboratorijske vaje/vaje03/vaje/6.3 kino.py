__author__ = 'Roglan'
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
        if ocena >= 7:
            print(film)  # z oceno vsaj 7
            break

    if ocena >= 7:
        break
