#pragma once
//include hpp
#include "SoundManager.hpp"

//include libs
#include <SDL\SDL.h>

//include files
#include "SFX.hpp"

SoundManager::SoundManager() { }
SoundManager::SoundManager(int &numChannels) {

	this->numChannels = numChannels;
	soundChannels = std::vector<bool>(numChannels, false);



	if (SDL_Init(SDL_INIT_AUDIO) == -1) {
		printf("SDL_Init: %s\n", SDL_GetError());
		exit(1);
	}
	// open 44.1KHz, signed 16bit, system byte order,
	//      stereo audio, using 1024 byte chunks
	if (Mix_OpenAudio(44100, MIX_DEFAULT_FORMAT, 2, 1024) == -1) {
		printf("Mix_OpenAudio: %s\n", Mix_GetError());
		exit(2);
	}
}
SoundManager::~SoundManager() { Mix_CloseAudio(); }



void SoundManager::setChannel(SFX * sfx, int channel) {
	sfx->setChannel(channel);
	soundChannels.at(channel) = true;
}

int SoundManager::getFreeChannel() { //returns 
	for (int  i = 0; i < soundChannels.size(); ++i) {
		if (soundChannels[i] == false)
			return i;
	}return -1;
}


