#pragma once
#include "GameObject.hpp"
class ObjectStorage{

private:
	
public:
	ObjectStorage();
	
	static std::map <std::string, GameObject*> objectStorage_Models;
};
