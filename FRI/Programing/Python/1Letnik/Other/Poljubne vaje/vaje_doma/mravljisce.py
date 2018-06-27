from random import randint


class Nest:
    def __init__(self):
        self.storage = []
        self.population = []
        self.queen = None
        self.rooms = dict()


class Ant():
    def __init__(self, Nest):
        self.life = 100
        self.equipment = None
        self.attack = 30
        self.nest = Nest

    def carry(self, item):
        self.equipment = item

    def bring(self, place, nest):
        self.nest.rooms[place] = self.equipment  #########
        self.nest = nest


class Queen(Ant):
    def command(self):  # ,ant katero kontrolira
        pass


class Solider(Ant):
    def __init__(self):
        self.security = randint(20, 50)
        self.attack += 40

    def repel(self, invader):
        self.life -= invader.attack
        invader.life -= self.attack


class Invader():
    def __init__(self):
        self.life = 100
        self.attack = randint(30, 50)

    def attack(self, antnest):
        pass


nest1 = Nest
Ant1 = Ant(nest1)
