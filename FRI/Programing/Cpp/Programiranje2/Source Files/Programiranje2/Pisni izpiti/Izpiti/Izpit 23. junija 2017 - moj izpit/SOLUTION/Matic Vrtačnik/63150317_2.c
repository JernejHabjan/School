#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

typedef struct{
	int k;
	bool removed;
}Box;


int main() {	
	int n, k, t;
	scanf("%d%d%d", &n, &k, &t);

	int *remove = malloc(sizeof(int) * t);
	for (int i = 0; i < t; ++i) {
		int num;
		scanf("%d", &num);
		remove[i] = num;
	}
	
	Box **boxes = malloc(sizeof(Box) * n);
	for (int i = 0; i < n; ++i) {
		boxes[i] = malloc(sizeof(Box) * k);
		for (int j = 0; j < k; ++j) {
			boxes[i][j].removed = false;
			boxes[i][j].k = k * i + j;
		}
		
	}

	int numRemoved = 0;
	for (int i = 0; i < t; ++i) {
		int r = remove[i];

		int column = (int)(r / k);
		int row = r % k;

		boxes[column][row].removed = true;
		//printf("%d\n", boxes[column][row].k);
		for (int j = row + 1; j < k; ++j) {
			if (!boxes[column][j].removed) {
				numRemoved++;
			}
		}
	}

	printf("%d\n", numRemoved);

	free(remove);
	for (int i = 0; i < n; ++i)free(boxes[i]);
	free(boxes);
	
	return 0;
}
