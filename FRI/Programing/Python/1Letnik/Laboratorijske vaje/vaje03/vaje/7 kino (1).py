__author__ = 'Roglan'
filmi = ['Poletje v skoljki 2', 'Ne cakaj na maj', 'Pod njenim oknom', 'Kekec', 'Poletje v skoljki', 'To so gadi']
ocene = [6.1, 7.3, 7.1, 8.1, 7.2, 7.7]
# TODO
# Napiši program, ki izpiše imena in ocene filmov katerih naslovi so sestvaljeni iz treh besed (vsebujejo dva presledka).
oboje = zip(filmi, ocene)
for movie, ocene in oboje:
    dolzina = str.split(movie)
    if len(dolzina) == 3:
        print(movie)
        print(ocene)
