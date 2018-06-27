def selection_sort(xs):
    for i in range(len(xs)):
        mini = min(xs[i:])  # find minimum element
        min_index = xs[i:].index(mini)  # find index of minimum element
        xs[i + min_index] = xs[i]  # replace element at min_index with first element
        xs[i] = mini  # replace first element with min element
    return xs


print(selection_sort([5, 4, 7, 8, 2]))
