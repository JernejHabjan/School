"""

Current money							(int)
Grid tile size							(vector)




0_Base
	Current Position (tile / xy) 		(vector)
	Current Health						(int)
	Move per turn 						(float)
	Damage per turn 					(int)
	Current executing action (constructing building, gathering resources, attacking, idle...)	(??????????????????????????????????)
BuildingMaster
	current position (tile /xy)    		(vector)
	current hp							(int)
	current producing units (array 0_base characterju in progression top characterja - to je za NPC in za Conscripte) (?????????????????????????)
	rally location					 	(vector)
	damage per turn						(int)
Construction site
	current position					(vector)
	current hp							(int)
	Progression							(float - percent)
Resources
	Position 							(vector)
	currently remaining resources		(int)


"""

import numpy as np


class Queue:
    def __init__(self):
        self.items = []

    def isEmpty(self):
        return self.items == []

    def enqueue(self, item):
        self.items.insert(0, item)

    def dequeue(self):
        return self.items.pop()

    def size(self):
        return len(self.items)


q = Queue()


class Tile:
    def __init__(self, location):
        self.location = np.array(location)
        self.actors = []


class Grid:


    def __init__(self, size): #zaenkrat je lahko sam kvadratno
        self.vSite = np.vectorize(Tile)
        self.size = size
        self.tiles = np.arange(size).reshape(np.sqrt(size), np.sqrt(size))

    def get_tiles(self):
        lattice = np.empty((np.sqrt(getGrid().size), np.sqrt(getGrid().size)), dtype=object)
        lattice[:, :] = self.vSite(self.tiles)

    def getActor(self, new_location):
        pass


grid = Grid(64)


def getGrid():
    return grid


class Resources:
    def __init__(self, current_position, amount):
        pass


class BuildingMaster:
    def __init__(self, current_position, rally_position, hp, damage, team):
        self.current_position = current_position
        self.rally_position = rally_position
        self.hp = hp
        self.current_production_time = 0  # production time of current unit
        self.producing_units = Queue.Queue()  # array 0_base characterju in progression top characterja - to je za NPC in za Conscripte
        self.occupants = set()
        self.damage = damage
        self.team = team

    def construct_unit(self):
        character = self.producing_units.top()
        production_time = character.production_time
        if production_time > self.current_production_time:
            # spawn character and reset timer
            self.producing_units.pop().spawn(self.current_position, self.rally_position)
            self.production_time = 0
        else:
            self.current_production_time += 1

    def manage_hp(self, in_hp):
        self.hp += in_hp

    def enter(self, actor):
        self.occupants.add(actor)

    def exit(self, actor):
        self.occupants.remove(actor)

    class DefenseMaster:
        # TODO
        todo = 1


class Zero_Base:
    def __init__(self, current_position, rally_position, hp, damage, team):
        self.current_position = current_position
        self.rally_position = rally_position
        self.hp = hp
        self.actions = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        self.damage = damage
        self.current_building = None  # building we are currently in
        self.team = team

    ##### HELPER FUNCITONS
    def friendly(self, actor):
        return self.team == actor.team

    def attack(self, actor):
        actor.manage_hp(-self.damage)

    def manage_hp(self, in_hp):
        self.hp += in_hp

    def execute_move(self, new_location):
        actor = getGrid().getActor(new_location)
        if actor:
            if self.friendly(actor):
                try:
                    building = actor.__bases__ == BuildingMaster
                    self.current_building = building
                    self.current_building.enter(actor)
                except:
                    print("not building master")
            else:
                self.attack(actor)
        else:
            self.current_position = new_location

    ###### ACTIONS
    def up(self):
        self.execute_move(self.current_position + np.array(0, +1, 0))

    def down(self):
        self.execute_move(self.current_position + np.array(0, -1, 0))

    def right(self):
        self.execute_move(self.current_position + np.array(+1, 0, 0))

    def left(self):
        self.execute_move(self.current_position + np.array(-1, 0, 0))

    def idle(self):
        return 1

    def mine_at_location(self):
        return 1

    def build_construction_office_at_location(self):
        return 1

    def build_house_at_location(self):
        return 1

    def build_mining_shack_at_location(self):
        return 1

    def build_barracks_at_location(self):
        return 1
