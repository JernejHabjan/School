#pragma once
//include hpp
#include "FileWriter.hpp"

//include libs
#include <iostream>
#include <fstream>






FileWriter::FileWriter() { /*config files, save states etc... */ }
FileWriter::~FileWriter() { }

bool FileWriter::fileWrite(std::string &path, std::string &data) {
	std::ofstream outfile;
	outfile.open(path);


	outfile << data << std::endl;
	outfile.close();

	return false;
}
