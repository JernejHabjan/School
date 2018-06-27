#pragma once
//include parent
#include "SDL_Sound.hpp"

//include libs
#include <SDL\SDL_mixer.h>

class SoundManager;
class SFX : public SDL_Sound
{
private:
	SFX();
	SoundManager *soundManager;

	Mix_Chunk* SFXFile;
	int channel;
public:
	SFX(SoundManager *soundManager);
	~SFX();

	void fadeOut(int &ms) override;
	void play(int &arrayIndex, int &times) override;


	void setChannel(int channel);
	int &getChannel();

	
};

