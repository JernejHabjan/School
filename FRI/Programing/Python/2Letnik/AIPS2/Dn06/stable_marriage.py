# Algoritem sestavljen po: Stable Marriage Problem - Numberphile
from collections import defaultdict
import numpy as np
import math

def propose(woman, proposed, preference_zenske,rejected, engaged ):
    womans_preferences = preference_zenske[woman]
    next_propose = womans_preferences[0]

    #prvo pogleda zenska kateri po listi jo je že zavrnil
    if woman in rejected.keys():
        for i in range(len(womans_preferences)):
            if womans_preferences[i] == rejected[woman]:
                if(i < len(womans_preferences)-1):
                    next_propose = womans_preferences[i+1] #izbere naslednjega
    #ce se ni zarocena ga prosi za roko
    #if(next_propose not in engaged):
    proposed[next_propose].append(woman)

def reject(man, preference_moski, proposed, engaged, rejected):
    mans_preferences = preference_moski[man]
    mens_proposals = proposed[man]

    for preference in mans_preferences:
        #izbere najboljso njemu
        for propose in mens_proposals:# ce ni forever -alone guy aka ce ga je zaprosila ksna
            if preference == propose:
                proposed[man] = [preference] #samo njo izbere, ostale zavrne
                engaged[man] = preference # se zarocita zacasno

                #reject women:
                mens_proposals.remove(preference)

    #zavrnemo
    for failed_proposal in mens_proposals:
        rejected[failed_proposal] = man #pove kdo jo je rejectov

def main():
    ST_PAROV = 4
    engaged = {}

    proposed = defaultdict(list)
    rejected = {}

    preference_moski = {
        "Luka": ["Veronika", "Petra", "Barbara", "Katarina"],
        "Anže": ["Petra", "Veronika"],
        "Tomaž": ["Barbara"],
        "Jan": ["Veronika", "Barbara", "Katarina"]
    }
    preference_zenske = {
        "Veronika": ["Luka", "Anže", "Tomaž"],
        "Petra": ["Anže", "Luka"],
        "Barbara": ["Tomaž", "Jan"],
        "Katarina": ["Tomaž", "Anže"]
    }
    for i in range(math.factorial(ST_PAROV)): ### da ponovimo večkrat - za vse možne pare...

        while(len(engaged) < ST_PAROV): # ko so vsi engaged ke konec
            for woman in preference_zenske.keys():
                propose(woman, proposed, preference_zenske,rejected, engaged)
            for man in preference_moski.keys():
                reject(man, preference_moski, proposed, engaged, rejected)

        for man, woman in engaged.items():
            print(man, "<3", woman)
        print()

        ##permute --- tako poiščemo več različnih zaročič
        for preferences in preference_zenske.values():
            np.random.permutation(preferences)
        for preferences in preference_moski.values():
            np.random.permutation(preferences)
main()
