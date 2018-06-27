#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

typedef struct {
	int a, b;
}Pair;

void printAll(Pair *pairs, int n, int d, char *str) {
	if (d >= n) {	
		for (int i = 0; i < d; ++i) {
			printf("%c", str[i]);
		}printf("\n");
		return;
	}

	for (int i = pairs[d].a; i <= pairs[d].b; ++i) {
		str[d] = '0' + i;
		printAll(pairs, n, d + 1, str);
	}

}

int main() {
	int n;
	scanf("%d", &n);

	Pair *pairs = malloc(n * sizeof(Pair));
	for (int i = 0; i < n; ++i) {
		int a, b;
		scanf("%d%d", &a, &b);
		pairs[i].a = a;
		pairs[i].b = b;
	}

	char *tmp = malloc(n + 1);
	printAll(pairs, n, 0, tmp);
	free(tmp);

	return 0;
}   
