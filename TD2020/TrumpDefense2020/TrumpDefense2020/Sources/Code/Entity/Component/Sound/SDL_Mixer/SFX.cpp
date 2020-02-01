#pragma once
//include hpp
#include "SFX.hpp"

//include files
#include "SoundManager.hpp"

SFX::SFX() { }

SFX::SFX(SoundManager * soundManager) {
	this->soundManager = soundManager;
	//Mix_ChannelFinished(channelDone);
}

SFX::~SFX() { free(SFXFile); }

void SFX::fadeOut(int &ms) {
	Mix_FadeOutChannel(channel, ms);
}

void SFX::play(int & arrayIndex, int & times) {
	if (SFXFile != nullptr && channel != NULL) {
		soundManager->setChannel(this, soundManager->getFreeChannel()); //sets empty channel if there is one
		if (channel) {
			Mix_PlayChannel(channel, SFXFile, times);
			
		}
	}
}

/*
void channelDone(int channel){ // TODO

	//soundManager->setChannel(this, -1); //free this channel
	printf("channel %d finished playing.\n", channel);
}
*/

void SFX::setChannel(int channel) {
	this->channel = channel;
}

int & SFX::getChannel()
{
	return channel;
}
