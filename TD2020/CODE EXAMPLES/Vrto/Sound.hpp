#ifndef SOUND_HPP
#define SOUND_HPP

#include <AL/al.h>
#include <vector>
#include <fstream>

#include "../Utility/BitwiseUtilities.hpp"
#include "../Define/PrintDefines.hpp"

namespace Engine{
	namespace Sound{
		using namespace Utilities;

		enum SoundFormat{
			FORMAT_UNKNOWN,
			FORMAT_WAV,
			FORMAT_MP3
		};	

		struct Sound{
			SoundFormat m_fileFormat;
			std::string m_path;
			ALuint		m_format;
			char*		m_data;
			int			m_dataSize;
			int			m_numChannels;
			int			m_sampleRate;
			int			m_bitRate;

		public:
			Sound() :
				m_fileFormat(FORMAT_UNKNOWN),
				m_path(""),
				m_format(0),
				m_data(nullptr),
				m_numChannels(0),
				m_sampleRate(0),
				m_bitRate(0),
				m_dataSize(0)
			{
			}

			Sound(const std::string &soundPath) : 
				m_path(soundPath)
			{
				if (!soundPath.empty())
				{
					char _buffer[4];
					std::ifstream _input("data/sounds/" + m_path, std::ios::binary);

					_input.read(_buffer, 4);
					if (strncmp(_buffer, "RIFF", 4) != 0){
						ERROR("First header not 'RIFF'");
						return;
					}

					m_fileFormat = FORMAT_WAV;

					_input.read(_buffer, 4);
					_input.read(_buffer, 4);
					_input.read(_buffer, 4);
					_input.read(_buffer, 4);
					_input.read(_buffer, 2);

					_input.read(_buffer, 2);
					m_numChannels = convertDataToInteger(_buffer, 2);

					_input.read(_buffer, 4);
					m_sampleRate = convertDataToInteger(_buffer, 4);

					_input.read(_buffer, 4);
					_input.read(_buffer, 2);

					_input.read(_buffer, 2);
					m_bitRate = convertDataToInteger(_buffer, 2);

					_input.read(_buffer, 4); //data!!!

					_input.read(_buffer, 4);
					m_dataSize = convertDataToInteger(_buffer, 4);

					m_data = new char[m_dataSize];
					_input.read(m_data, m_dataSize);


					if (m_numChannels == 1)
						m_format = (m_bitRate == 8) ?
						AL_FORMAT_MONO8 : AL_FORMAT_MONO16;
					else
						m_format = (m_bitRate == 8) ?
						AL_FORMAT_STEREO8 : AL_FORMAT_STEREO16;
				}
			}

			~Sound(){
				if (m_data != nullptr && m_dataSize > 0){
					delete[] m_data;
				}
			}
	
		};

	}
}


#endif //SOUND_HPP