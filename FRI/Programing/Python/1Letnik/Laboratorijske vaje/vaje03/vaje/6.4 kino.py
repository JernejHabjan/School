filmi = [
    ('Poletje v skoljki 2', 6.1),
    ('Ne cakaj na maj', 7.3),
    ('Pod njenim oknom', 7.1),
    ('Kekec', 8.1),
    ('Poletje v skoljki', 7.2),
    ('To so gadi', 7.7)
]
vsota_ocen = 0
counter = 0
for element in zip(filmi):
    for film, ocena in element:
        vsota_ocen += ocena
        counter += 1
print(float("%.2f" % (vsota_ocen / counter)))
