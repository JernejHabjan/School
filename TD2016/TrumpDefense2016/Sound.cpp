#include "Sound.hpp"




void Sound::init(){
	SDL_Init(SDL_INIT_AUDIO); //sound
	if (Mix_OpenAudio(44100, MIX_DEFAULT_FORMAT, 2, 2048) < 0){//2 chunks-stereo, 2048-2kb
		std::cout << "Error: " << Mix_GetError() << std::endl;
	}
	Mix_AllocateChannels(50);

	initMusicFiles();
	initSFXFiles();
}

void Sound::initMusicFiles(){

	musicFiles.push_back(Mix_LoadMUS("Sounds\\Music\\Mariachi.mp3")); 
	musicFiles.push_back(Mix_LoadMUS("Sounds\\Music\\SandalMaker.mp3"));
	musicFiles.push_back(Mix_LoadMUS("Sounds\\Music\\ReadyTheArmy.mp3"));
}

void Sound::initSFXFiles(){
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\baloon.wav"));
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\cash.wav"));
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\die.wav"));
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\hammer.wav"));
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\pew.wav"));
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\spawn.wav"));
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\constr1.wav"));
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\constr2.wav"));
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\constr3.wav"));
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\constr4.wav"));
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\constr5.wav"));
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\constr6.wav"));
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\constr7.wav"));
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\Crusader\\puller_strain.wav")); //index 13
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\Crusader\\arrwdth_09.wav"));
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\Crusader\\flamearrow_03.wav"));
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\Crusader\\hit_01.wav"));
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\Crusader\\mason_chip1.wav"));
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\Crusader\\metrock_03.wav"));
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\Crusader\\firepop2.wav"));
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\Crusader\\cheer2.wav")); //WIN
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\Crusader\\woodhit_06.wav")); //LOSE LIFE
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\buildWall.wav")); //index 22
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\Crusader\\select.wav")); 
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\Crusader\\towerUpgrade.wav")); 
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\Crusader\\wallUpgrade.wav")); 
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\cannon.wav"));
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\Crusader\\buy.wav")); //index 27
	SFXFiles.push_back(Mix_LoadWAV("Sounds\\SFX\\mexicoChatter.wav"));
	
}

void Sound::playMusic(int arrayIndex, int times){
	if (musicFiles.size() > 0 && musicFiles.size() >= arrayIndex + 1)
		Mix_PlayMusic(musicFiles.at(arrayIndex), times);
}


void Sound::playSFX(int channel, int arrayIndex, int times){
	
	if (SFXFiles.size() > 0 && SFXFiles.size() >= arrayIndex + 1)
		Mix_PlayChannel(channel, SFXFiles.at(arrayIndex), times);
	

}

void Sound::free(){
	//MUSIC
	for (int i = 0; i < musicFiles.size(); i++){
		Mix_FreeMusic(musicFiles.at(i));
		musicFiles.at(i) = nullptr;
	}

	//SFX
	for (int i = 0; i < SFXFiles.size(); i++){
		Mix_FreeChunk(SFXFiles.at(i));
		SFXFiles.at(i) = nullptr;
	}

	//QUIT MIX
	Mix_Quit();
}


void Sound::fadeOut(int channel, int ms){
	Mix_FadeOutChannel(channel, ms);
	
}



void Sound::setChannelVolume(int channel, float factor){
	Mix_Volume(channel, MIX_MAX_VOLUME * factor);
}