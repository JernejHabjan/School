#ifndef SOUND_COMPONENT_HPP
#define SOUND_COMPONENT_HPP

#include <memory>
#include <SDL2\SDL_timer.h>

#include "Component.hpp"
#include "../Sound/Sound.hpp"
#include "../Utility/MathUtilities.hpp"
#include "../Define/Assert.hpp"

namespace Engine{
	namespace Entities{
		namespace Components{
			using namespace Utilities;

			enum SoundType{
				SOUND_TRIGGER		= 0,
				SOUND_CONTINUOUS	= 1,
				SOUND_GLOBAL		= 2,
				SOUND_ON_COLLISION  = 3
			};

			class SoundComponent : public Component{
			private:
				ALuint	m_bufferId;
				ALuint	m_sourceId;

				Uint32  m_durationMilis;
				bool	m_playing;

				SoundType m_type;
				ALfloat m_radius;
				ALfloat m_volume;
				ALfloat m_rolloffFactor;
				ALfloat m_referenceDistance;
				ALfloat m_maxDistance;
				glm::vec3 m_orientation;

				std::unique_ptr<Sound::Sound> m_sound;
				
				void checkNull() const{
					ASSERT(m_sound != nullptr, "Sound is null pointer!");
				}

				void init(){
					checkNull();

					if (failedLoading())return;

					alGenBuffers(1, &m_bufferId);
					alBufferData(
						m_bufferId, 
						m_sound->m_format, 
						m_sound->m_data, 
						m_sound->m_dataSize, 
						m_sound->m_sampleRate
					);

					alGenSources(1, &m_sourceId);
					alSourcei(m_sourceId, AL_BUFFER, m_bufferId);


					ALint _frequency, _bitsPerSample;
					alGetBufferi(m_bufferId, AL_FREQUENCY, &_frequency);
					alGetBufferi(m_bufferId, AL_BITS, &_bitsPerSample);

					float _sampleCount = static_cast<float>(m_sound->m_dataSize * 8 /
						(m_sound->m_numChannels * _bitsPerSample));

					float _durationSeconds = _sampleCount / static_cast<float>(_frequency);
					m_durationMilis = static_cast<Uint32>(1000.0f * _durationSeconds);
					PRINT("DURATION: " << m_durationMilis)
				
				}

				void setDefaultValues(){
					setType(SOUND_CONTINUOUS);
					setRadius(1.0f);
					setOrientation(glm::vec3(0.0f, -1.0f, 0.0f));
					setVolume(1.0f);
					setRolloffFactor(1.0f);
					setReferenceDistance(1.0f);
					setMaxDistance(1.0f);
				}

			public:
				SoundComponent(){
					m_sound = nullptr;
					setDefaultValues();
				}

				SoundComponent(
					const std::string &soundPath, 
					SoundType type = SOUND_CONTINUOUS)
				{
					m_sound = std::make_unique
						<Sound::Sound>(soundPath);

					setDefaultValues();
					m_type = type;
					init();
				}

				SoundComponent(
					std::string &&soundPath, 
					SoundType type = SOUND_CONTINUOUS)
				{
					m_sound = std::make_unique
						<Sound::Sound>(std::move(soundPath));

					setDefaultValues();
					m_type = type;
					init();
				}

				SoundComponent(Sound::Sound sound) = delete;
				SoundComponent(const Sound::Sound &sound) = delete;
				SoundComponent(Sound::Sound &&sound) = delete;

				~SoundComponent(){
					destroySound();
				}

				void destroySound(){
					stop();
					alDeleteSources(1, &m_sourceId);
					alDeleteBuffers(1, &m_bufferId);
				}

				void loop(){
					if (!m_playing){
						alSourcei(m_sourceId, AL_LOOPING, AL_TRUE);
						alSourcePlay(m_sourceId);
						m_playing = true;
					}
				}

				void play(){
					if (!m_playing){
						alSourcei(m_sourceId, AL_LOOPING, AL_FALSE);
						alSourcePlay(m_sourceId);
						m_playing = true;
					}
				}

				void pause(){
					alSourcePause(m_sourceId);
					m_playing = false;
				}

				void stop(){
					alSourceStop(m_sourceId);
					m_playing = false;
				}

				void stopIfEnded(){
					auto _timePlaying = getTimePlaying();
					//PRINT("TIME PLAYING: " << _timePlaying << " " << m_playing);

					if (m_playing && _timePlaying == 0){
						stop();
					}
				}

				//TIME PLAYING
				void setTimePlaying(Uint32 time){
					alSourcef(m_sourceId, AL_SEC_OFFSET, time / 1000.0f);
				}

				Uint32 getTimePlaying() const{
					float _time;
					alGetSourcef(m_sourceId, AL_SEC_OFFSET, &_time);

					return static_cast<Uint32>(_time * 1000.0f);
				}


				//SOUND DATA AND POINTER STUFF
				void setSound(std::unique_ptr<Sound::Sound> &&sound){
					destroySound();
					m_sound = std::move(sound);

					init();
				}

				void setPath(const std::string &soundPath){
					destroySound();
					m_sound = std::make_unique
						<Sound::Sound>(soundPath);

					init();
				}

				void setPath(std::string &&soundPath){
					destroySound();
					m_sound = std::make_unique
						<Sound::Sound>(std::move(soundPath));

					init();
				}


				//LOCATION
				void setLocation(const glm::vec3 &location){
					alSource3f(m_sourceId, AL_POSITION,
						location.x, location.y, location.z);
				}

				void setLocation(glm::vec3 &&location){
					alSource3f(m_sourceId, AL_POSITION,
						std::move(location.x), 
						std::move(location.y),
						std::move(location.z)
					);
				}


				//ORIENTATION
				void setOrientation(const glm::vec3 &orientation){ //vector
					m_orientation = orientation;
					alListener3f(AL_ORIENTATION,
						m_orientation.x, m_orientation.y, m_orientation.z);
				}

				void setOrientation(glm::vec3 &&orientation){ //vector
					m_orientation = std::move(orientation);
					alListener3f(AL_ORIENTATION,
						m_orientation.x, m_orientation.y, m_orientation.z);
				}

				void setOrientation(float x, float y, float z){
					m_orientation = glm::vec3(x, y, z);
					alListener3f(AL_ORIENTATION, x, y, z);
				}

				void setOrientation(const glm::quat &orientation){
					m_orientation = getVectorFromRotation(orientation);
					alListener3f(AL_ORIENTATION,
						m_orientation.x, m_orientation.y, m_orientation.z);
				}

				void setOrientation(glm::quat &&orientation){
					m_orientation = getVectorFromRotation(std::move(orientation));
					alListener3f(AL_ORIENTATION,
						m_orientation.x, m_orientation.y, m_orientation.z);
				}


				//TYPE
				SoundType getType() const{
					return m_type;
				}

				void setType(SoundType type){
					m_type = type;
				}

				bool isPlaying() const{
					return m_playing;
				}


				//INNER RADIUS
				ALfloat getRadius() const{
					return m_radius;
				}

				void setRadius(ALfloat radius){
					m_radius = radius;
				}


				//VOLUME
				ALfloat getVolume() const{
					return m_volume;
				}

				void setVolume(ALfloat volume){
					m_volume = glm::clamp(volume, 0.0f, 1.0f);
					alSourcef(m_sourceId, AL_MAX_GAIN, 1.0);
					alSourcef(m_sourceId, AL_GAIN, m_volume);
				}


				//ROLLOFF FACTOR
				ALfloat getRolloffFactor() const{
					return m_rolloffFactor;
				}

				void setRolloffFactor(ALfloat factor){
					m_rolloffFactor = factor;
					alSourcef(m_sourceId, AL_ROLLOFF_FACTOR, factor);
				}


				//REFERENCE DISTANCE
				ALfloat getReferenceDistance() const{
					return m_referenceDistance;
				}

				void setReferenceDistance(ALfloat distance){
					m_referenceDistance = distance;
					alSourcef(m_sourceId, AL_REFERENCE_DISTANCE, distance);
				}


				//MAX DISTANCE
				ALfloat getMaxDistance() const{
					return m_maxDistance;
				}

				void setMaxDistance(ALfloat distance){
					m_maxDistance = distance;
					alSourcef(m_sourceId, AL_MAX_DISTANCE, distance);
				}


				//TODO GET SOUND LENGTH
				//TODO GET SOUND LENGTH
				//TODO GET SOUND LENGTH
				//TODO GET SOUND LENGTH
				//TODO GET SOUND LENGTH

				//GET SOUND VALUES
				Sound::Sound *getSound() const{
					return m_sound.get();
				}

				Sound::SoundFormat getFileFormat() const{
					checkNull();
					return m_sound->m_fileFormat;
				}

				const std::string &getPath() const{
					checkNull();
					return m_sound->m_path;
				}

				Uint32 getLength() const{
					return m_durationMilis;
				}

				ALuint getFormat() const{
					checkNull();
					return m_sound->m_format;
				}

				const char *getData() const{
					checkNull();
					return m_sound->m_data;
				}

				bool failedLoading() const{
					return !getData();
				}

				int getDataSize() const{
					checkNull();
					return m_sound->m_dataSize;
				}

				int getNumChannels() const{
					checkNull();
					return m_sound->m_numChannels;
				}

				int getSampleRate() const{
					checkNull();
					return m_sound->m_sampleRate;
				}

				int getBitRate() const{
					checkNull();
					return m_sound->m_bitRate;
				}


			};

		}
	}
}

#endif //SOUND_COMPONENT_HPP