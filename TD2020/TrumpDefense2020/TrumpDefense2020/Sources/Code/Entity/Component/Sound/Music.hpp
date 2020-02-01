#pragma once
#include "Sound.hpp"
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

