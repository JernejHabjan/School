import math
def get_layer(i):
    if i == 0:
        nivo = 1
    elif i == 1:
        nivo = 2
    else:
        nivo = math.ceil(math.log(i+2, 2))
    return str(nivo)+" max" if nivo %2 == 0 else str(nivo)+" min"

def main():
    for i in range(10):
        nivo = get_layer(i)
        print(nivo)
main()
