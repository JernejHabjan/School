#pragma once
//includes
#include <string>
#include <iostream>

//defines
#define print(msg) std::cout << msg << std::endl
#define printNL(msg) std::cout << msg

#define FOR(x,n) for(int x = 0; x < n; ++x)
#define SFOR(q,s,e) for(int q=s;q<=e;q++)
#define RFOR(q,n) for(int q=n;q>=0;q--)
#define RSFOR(q,s,e) for(int q=s;q>=e;q--)

#define ESZ(elem) (int)elem.size()
