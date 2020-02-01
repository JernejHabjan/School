#pragma once
//include libs
#include <string>

class SDL_Sound {
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
	SDL_Sound();
	~SDL_Sound();

	virtual void fadeOut(int &ms); // TODO ZAKAJ NE MORE BITI PURE VIRTUAL
	virtual void play(int &arrayIndex, int &times);

	std::string getName();
};

