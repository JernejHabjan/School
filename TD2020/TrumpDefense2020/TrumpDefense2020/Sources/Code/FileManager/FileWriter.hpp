#pragma once
//include libs
#include <string>

class FileWriter {
public:
	FileWriter();
	~FileWriter();

	bool fileWrite(std::string &path, std::string &data);
};

