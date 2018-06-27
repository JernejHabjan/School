__author__ = 'jh0228'

temp_F = float(input("vpisi temperaturo v stopinjah celzija"))
temp_C = temp_F - 32
temp_C = temp_C / (9 / 5)
temp_K = temp_C + 273.15
print(temp_F, " F je enako ", temp_C, " C in ", temp_K, " K")
