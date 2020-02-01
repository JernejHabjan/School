#pragma once
//include hpp
#include "Music.hpp"

Music::Music() { }
Music::~Music() { free(musicFile); }

void Music::fadeOut(int &ms) {
	Mix_FadeOutMusic(ms);
}

void Music::play(int & arrayIndex, int & times) {
	if (musicFile != nullptr)
		Mix_PlayMusic(musicFile, times);
}

