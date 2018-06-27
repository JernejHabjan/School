#pragma once

#include <iostream>

#include "SoundManager.hpp"

class Sound {
protected:
	std::string name;
	std::string path;

	short priority;
	short volume;
	short pitch;

	//3d Sound settings
	unsigned int minDistance;
	unsigned int maxDistance;




public:
	Sound();
	~Sound();

	virtual void fadeOut(int &ms); // TODO ZAKAJ NE MORE BITI PURE VIRTUAL
	virtual void play(int &arrayIndex, int &times);

	std::string getName();
};

