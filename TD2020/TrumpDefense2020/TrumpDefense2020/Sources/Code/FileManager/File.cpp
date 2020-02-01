#pragma once
//include hpp
#include "File.hpp"

//include libs
#include <iostream>
#include <fstream>

//include files
#include "..\Defines.hpp"


File::File() { fileType = UNKNOWN; }
File::File(std::string path) { 
	fileType = UNKNOWN;
	determineFileType(path); 
}
File::~File() { }

bool File::has_suffix(const std::string &str, const std::string &suffix)
{
	return str.size() >= suffix.size() &&
		str.compare(str.size() - suffix.size(), suffix.size(), suffix) == 0;
}

void File::determineFileType(std::string & path) {
	for (std::vector<std::string>::size_type i = 0; i != possibleSuffixes.size(); i++){
		if (has_suffix(path, possibleSuffixes[i])) {
			fileType = static_cast<FileType>(i);
			return;

		}
	}
	fileType = UNKNOWN;
	print("Filetype in path >>" <<&path<< "<< isnt supported");
	system("PAUSE");
	exit(1);
}

FileTypeEnum File::getFileType()
{
	return fileType;
}


