#pragma once

#include <SDL\SDL_mixer.h> //sound
#include <SDL\SDL.h>
#include <iostream>
#include <vector>

class Sound{
private:
	std::vector<Mix_Music*> musicFiles;
	std::vector<Mix_Chunk*> SFXFiles;
	

	void initMusicFiles();
	void initSFXFiles();
public:

	void init();

	void playMusic(int arrayIndex, int times);
	void playSFX(int channel, int arrayIndex, int times);

	void fadeOut(int channel, int ms);

	void setChannelVolume(int channel, float factor);


	void free();

};

