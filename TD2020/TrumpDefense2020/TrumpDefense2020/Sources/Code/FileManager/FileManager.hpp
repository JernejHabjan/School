#pragma once
//include libs
#include <string>

class FileReader;
class FileWriter;
class ResourceManager;
class FileManager {
private:
	FileReader *fr;
	FileWriter *fw;
public:
	FileManager(ResourceManager *resourceManager);
	~FileManager();

	bool fileWrite(std::string & path, std::string & data);
	bool fileRead(std::string path, ResourceManager *resourceManager);


};

