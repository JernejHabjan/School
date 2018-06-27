from random import randint

random_number = randint(1, 10)
guesses_left = 3
guess = 0
guess = input("vpisi stevilo")
while (guesses_left < 0):
    if (guess != random_number):
        guesses_left = guesses_left - 1
        guess = input("vpisi stevilo se enkrat")
        print("imate se ", guesses_left, " poskusov")
    else:
        print("You win!")
        break
else:
    print("You lose!")
