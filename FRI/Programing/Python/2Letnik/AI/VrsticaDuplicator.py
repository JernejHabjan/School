text_file = open("neki.txt", "w")

with open('piracy-topics_genre.txt') as f:
    lines = f.read().splitlines()

    for line in lines:
        for i in range(0,30):
            text_file.write(line + "\n")





text_file.close()