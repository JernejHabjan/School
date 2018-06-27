class structDmgType:
    def __init__(self, fireDamage, iceDamage, PierceDamage):
        self.fireDamage = fireDamage
        self.iceDamage = iceDamage
        self.PierceDamage = PierceDamage


class structResistanceType:
    def __init__(self, fireDamage, iceDamage):
        self.fireDamage = fireDamage
        self.iceDamage = iceDamage


class structAttackProperties():
    damage = 4
    shootrange = 50
    canshoot = True
    canshootair = False
    damageTypes = structDmgType(1, 3, 0)

class structDefenseProperties():
    hp = 100
    armour = 20
    resistancetypes = structResistanceType(1, 3)


"""
    ##############################################################################################
"""


class Unit:


    def __init__(self, unitType):

        self.unitType = unitType
        self.DefenseProperties = structDefenseProperties()
        self.AttackProperties = structAttackProperties()
        
        
class Conscript(Unit):
    granadedmg = 40
    granadethrowRange = 20

    def __init__(self):
        super().__init__("Conscript")

    def throwGranade(self, targetLocation):
        targetLocation.Actors -= self.granadedmg


class Medic(Unit):
    healAmount = 0
    healRange = 0

    def __init__(self):
        super().__init__("Healer")
        self.healAmount = 10
        self.healRange = 50

    def heal(self):
        'heals actors in range by healAmount'


"""
    ##############################################################################################
"""


class BuildingMaster:
    def __init__(self):
        self.DefenseProperties = structDefenseProperties()
        self.constructionTime = 1.0

    def construct(self):
        pass


"""
    ##############################################################################################
"""


class DefenseMaster(BuildingMaster):

    def __init__(self):
        super().__init__()
        'component'
        self.componentTrap = True
        self.componentSphereOverlap = True # used when actor is in range of defense - can shoot

        self.AttackProperties = structAttackProperties()

        self.buildingType = "DefensiveBuilding"

    def shoot(self, target):
        if self.AttackProperties.canshoot and target.distance <= self.AttackProperties.shootrange:
            # check if we can shoot air if unit is in air
            if target.isInAir and not self.AttackProperties.canshootair:
                return
            target.Health -= self.AttackProperties.damage

    def deployTraps(self):
        'Pour oil on nearby actors or release hounds...'


class SniperTower(DefenseMaster):
    def __init__(self):
        super().__init__()
        self.AttackProperties.canshootair = True
        self.AttackProperties.componentTrap = False


"""
    ##############################################################################################
"""


class OffensiveMaster(BuildingMaster):
    def __init__(self):
        super().__init__()
        self.queueUnits = ()
        self.buildingType = "OffensiveBuilding"

    def createUnit(self, Unit):
        self.queueUnits.__add__(Unit)

    def maintainUnitsAmount(self):
        'gets called when unit dies built from this building '
        self.createUnit(Unit("dead type"))

    def rally(self):
        'rallies all units to location'


class Barracks(OffensiveMaster):
    def __init__(self):
        super().__init__()

    def createConscript(self):
        self.createUnit(Conscript())


class MedicHouse(OffensiveMaster):
    healAmount = 0
    healRange = 0

    def __init__(self):
        super().__init__()
        self.healAmount = 10
        self.healRange = 50

    def heal(self):
        'heals actors in range by healAmount'

    def createHealer(self):
        self.createUnit(Medic())


"""
    ##############################################################################################
"""

'no where this buildings and units will be stored - in my game state - now it already stores buildings'
