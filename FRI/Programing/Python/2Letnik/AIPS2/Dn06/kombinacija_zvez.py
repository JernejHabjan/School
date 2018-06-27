import numpy as np
import math

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


def main():
    for i in range(0, math.factorial(len(preference_moski))):  # probamo najveckrat n!

        np.random.permutation(preference_moski)  # shuffle preferences
        np.random.permutation(preference_zenske)  # shuffle preferences

        engagements = {}
        for zenska, preference in preference_zenske.items():
            for preferenca in preference:
                if zenska in preference_moski[preferenca]:
                    # obstaja trdno povezana povezava
                    engagements[zenska] = preference

        ##do tu imamo samo mocno povezane komponente, kjer ima zenska več preferenc ki ji pašejo in
        # jo imajo fantje nazaj radi

        ##preveris ce je se kaksna zenska brez para, nato odstranis trdno povezano
        for zenska, preference in preference_zenske.items():
            if zenska not in engagements.keys():
                for moski, preference in preference_moski.items():
                    if moski not in engagements.values():

                        # zdaj ju imamo ko še nista poročena
                        # zdaj preverimo če ima vsaj en rad drugega
                        if zenska in preference_moski[moski]:
                            engagements[moski] = zenska
                            # in ju združimo


main()
