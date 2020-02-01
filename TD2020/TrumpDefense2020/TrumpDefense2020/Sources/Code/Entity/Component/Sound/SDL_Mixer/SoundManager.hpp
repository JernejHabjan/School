#pragma once
//include libs
#include <vector>
#include <unordered_map>

class SFX;

class SoundManager {
private:
	SoundManager();

	int numChannels;
	std::vector<bool> soundChannels; //false means empty
public:
	SoundManager(int &numChannels);
	~SoundManager();


	void setChannel(SFX *sfx, int channel);
	int getFreeChannel();

};
