import random
import math

_ITERATIONS = 100000
_SET = [2, 3, 1, 5, 6, 4]

def getsumDifference(array_2d):
    # returns difference between all arrays - O(n^2)
    diff = 0
    for i in range(len(array_2d)):
        sum_arr1 = sum(array_2d[i])
        for j in range(len(array_2d)):
            sum_arr2 = sum(array_2d[j])
            diff += abs(sum_arr1 - sum_arr2)
    return diff


def main():
    MIN_DIFF = math.inf
    MIN_SET = {}

    for i in range(_ITERATIONS):  # n iterations
        rand_num_sets = random.randint(0, len(_SET) - 1)
        for j in range(rand_num_sets):  # split on random number of sets
            array_2d = []
            for i in range(0, len(_SET), rand_num_sets):
                array_2d.append(_SET[i: i + rand_num_sets])  # same chunks
            diff = getsumDifference(array_2d)
            if diff < MIN_DIFF:
                MIN_DIFF = diff
                MIN_SET = array_2d
    print(MIN_SET)


main()


