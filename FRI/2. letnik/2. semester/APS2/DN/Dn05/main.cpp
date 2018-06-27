#include <iostream>
#include <vector>

struct Or{
	std::vector<int> values;

	Or(){}

	template<typename ...Args>
	Or(int v, Args&&... args){
		values.push_back(v);
		for (int x : { std::forward<Args>(args)... }){
			values.push_back(x);
		}
	}
};

int max(const std::vector<int> &values){
	if (values.empty())return 0;

	int m = abs(values[0]);
	for (int i = 1; i < values.size(); ++i){
		int v = abs(values[i]);
		if (v > m)m = v;
	}

	return m;
}

int findMaxNumber(const std::vector<Or> &vec){
	int _max = 0;
	for (const Or &or : vec){
		int m = max(or.values);
		if (m > _max)_max = m;
	}

	return _max;
}

bool calc(const std::vector<Or> &vec, int vals){
	bool _result = true;
	for (const Or &or : vec){
		bool eval = false;
		for (auto &value : or.values){
			bool b = vals & (1 << (abs(value) - 1));
			eval = eval || (value < 0 ? !b : b);
		}

		_result &= eval;
	}

	return _result;
}

void printExpression(const std::vector<Or> &vec){
	if (vec.empty())return;

	std::cout << "(";
	for (int i = 0; i < vec.size(); ++i){
		int n = vec[i].values.size();
		for (int j = 0; j < n; ++j){
			int num = vec[i].values[j];
			bool neg = num < 0;

			std::cout << (neg ? "NOT(x" : "x") << abs(num) << (neg ? ")" : "") << ((j == (n - 1)) ? "" : " OR ");
		} std::cout << ((i == (vec.size() - 1)) ? ") \n" : ") AND (");
	}
}

void printEvals(const std::vector<Or> &vec, bool printAll = false){
	int _max = findMaxNumber(vec);
	int limit = (1 << _max);

	printExpression(vec);

	for (int i = _max - 1; i >= 0; --i){
		std::cout << "x" << i + 1 << " ";
	}std::cout << " R \n";

	for (int i = 0; i < limit; ++i){
		bool e = calc(vec, i);

		if (e || printAll){
			for (int j = _max - 1; j >= 0; --j){
				std::cout << (1 & (i >> j)) << (j == 0 ? " " : "  ");
			}

			std::cout << "| " << e << std::endl;
		}
	}
}

int main(){

	//2SAT
	printEvals({ Or(1, 2), Or(1, -2), Or(-1, 2) }, false);
	printEvals({ Or(-1, 2), Or(1, -3), Or(-1, -2), Or(-1, 3) }, false);
	printEvals({ Or(1, -2), Or(-1, 2), Or(2, -3), Or(3, -4), Or(-3, 4), Or(4, -1) }, false);
	printEvals({ Or(7, -6), Or(6, -5), Or(5, -4), Or(4, -3), Or(3, -2), Or(2, -1) }, false);



	std::cout << "--------------------------------------------------------------------------------" << std::endl;

	//3SAT
	printEvals({ Or(1, -2, 3), Or(1, -1, 2), Or(-1, 3, -3) }, true);
	printEvals({ Or(-2, 1, -2), Or(2, -1, 2), Or(-2, -1, -2), Or(2, 1, 2) }, true);
	printEvals({ Or(-1, 3, 4), Or(1, 3, 4), Or(-1, -4, -4), Or(2, -2, -3), Or(2, -3, -3), Or(-2, -3, -4) }, true);
	printEvals({ Or(1, 2, -3), Or(1, 2, -3), Or(-1, -2, 3), Or(-1, -2, -3), Or(-1, 2, -3), Or(-1, 2, 3) }, true);

	system("PAUSE");
	return 0;
}