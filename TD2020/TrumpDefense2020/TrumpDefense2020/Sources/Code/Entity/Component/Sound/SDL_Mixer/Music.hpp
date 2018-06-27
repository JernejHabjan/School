#pragma once
//include parent
#include "SDL_Sound.hpp"

//include libs
#include <SDL\SDL_mixer.h>

class Music : public SDL_Sound
{
private:
	Mix_Music* musicFile;
public:
	Music();
	~Music();

	void fadeOut(int &ms) override;
	void play(int &arrayIndex, int &times) override;

};

