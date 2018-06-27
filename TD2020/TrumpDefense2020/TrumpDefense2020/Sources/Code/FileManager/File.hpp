#pragma once
//include libs
#include <vector>

typedef enum FileTypeEnum {
	INI = 0,
	TXT = 1,
	OBJ = 2,
	PNG = 3,
	JPG = 4,
	UNKNOWN = -1
}FileType;

class File {
private:
	std::vector<std::string> possibleSuffixes{
		"ini",
		"txt",
		"obj",
		"png",
		"jpg"
	};
public:
	FileType fileType;

	File();
	File(std::string path);
	~File();

	bool has_suffix(const std::string & str, const std::string & suffix);
	void determineFileType(std::string &path);
	FileType getFileType();
};

