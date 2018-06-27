#pragma once

#include <iostream>
#include <windows.h>
#include "Defines.hpp"
#include "Engine.hpp"


/* 
												TRUMP DEFENSE 2020
Engine developed by Jernej Habjan in 2017
Engine was referenced from tutorials from Megabyte softworks - http://www.mbsoftworks.sk/index.php?page=tutorials&series=1
*/





#undef main
int main(int argc, char **argv) {
	
	std::string filePath = argv[0];

	new Engine();

	system("PAUSE");
	return 0;
}
