#include <stdio.h>
#include <math.h>

char s[100002];
char b;

int val(int n) {
	int i, v = 0;
	for(i = 0; i < n; i++) {
		if(s[i] < '0' || s[i] > '9') return 0;
		int a = s[i] - '0';
		v += a * pow(10, n - i - 1);
	}
	return v;
}

int main() {
	long long sum = 0, j = 0;
	while(b != '\n') {
		scanf("%c", &b);
		s[j] = b;
		j++;
		if(b == ' ') {
			sum += val(j-1);
			j = 0;
		}
	}
	sum += val(j-1);
	printf("%lld\n", sum);
	return 0;
}

