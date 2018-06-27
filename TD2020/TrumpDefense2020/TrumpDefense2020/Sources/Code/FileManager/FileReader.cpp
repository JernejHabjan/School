#pragma once
#include "FileReader.hpp"





#include <iostream>
#include <fstream>
#include <string>
#include <sstream>

#include <glm\glm.hpp>
#include <SDL\SDL.h>
#include <SDL\SDL_image.h>



#include "..\Defines.hpp"
#include "..\Renderer\GL_API.hpp" // TODO PREVELK INCLUDE?
#include "..\Renderer\Renderer.hpp"
#include "..\ResourceManager.hpp"
#include "..\Model.hpp"
#include "File.hpp"



#include <GL\glew.h>

FileReader::FileReader(ResourceManager *resourceManager)
{
	
	//READ JSON FILES WITH LIBRARY https://github.com/nlohmann/json
	//json j = "{ \"happy\": true, \"pi\": 3.141 }"_json;

	//ini files,  config files, texture, obj, materials...
	this->resourceManager = resourceManager;
	file = new File();
}


FileReader::~FileReader(){
	free(file);
}

bool FileReader::readINI(const std::string path) {
	return false;
}

bool FileReader::readOBJ(const std::string path) {

	#ifdef DISPLAY_LOADING
		std::cout << "Loading OBJ: " << path << std::endl;
	#endif //DISPLAY_LOADING

	std::vector<unsigned int> vertexIndices, uvIndices, normalIndices;
	std::vector<glm::vec3> temp_vertices;
	std::vector<glm::vec2> temp_uvs;
	std::vector<glm::vec3> temp_normals;

	std::ifstream ifs(path, std::ifstream::in);



	std::string line; //notr cev line
	while (std::getline(ifs, line)) {
		if (line.empty())                  // be careful: an empty line might be read
			continue;


		std::vector<std::string> myWordVector; //za storat worde

		std::stringstream ssin(line);


		while (ssin.good()) {
			std::string word; //notr ena beseda
			ssin >> word;
			myWordVector.push_back(word);

		}
		//vector wordov je nafillan ... zdj pa 
		if (myWordVector.at(0) == "v") {
			glm::vec3 vertex;
			vertex.x = std::stof(myWordVector.at(1));
			vertex.y = std::stof(myWordVector.at(2));
			vertex.z = std::stof(myWordVector.at(3));
			temp_vertices.push_back(vertex);
		}
		else if (myWordVector.at(0) == "vt") {
			glm::vec2 uv;
			uv.x = std::stof(myWordVector.at(1));
			uv.y = std::stof(myWordVector.at(2));

			uv.y = -uv.y; // Invert V coordinate since we will only use DDS texture, which are inverted. Remove if you want to use TGA or BMP loaders.
			temp_uvs.push_back(uv);
		}
		else if (myWordVector.at(0) == "vn") {
			glm::vec3 normal;
			normal.x = std::stof(myWordVector.at(1));
			normal.y = std::stof(myWordVector.at(2));
			normal.z = std::stof(myWordVector.at(3));
			temp_normals.push_back(normal);
		}
		else if (myWordVector.at(0) == "f") {
			//split first

			std::string delimiter = "/";



			//SPARSAŠ V SEGMENTE
			for (APIuint i = 1; i < myWordVector.size(); i++)//gre po besedi
			{

				std::vector<std::string> segmented;
				std::string s = myWordVector.at(i);

				///////////////////////
				size_t pos = 0;
				std::string token;
				while ((pos = s.find(delimiter)) != std::string::npos) {
					token = s.substr(0, pos);
					segmented.push_back(token);
					s.erase(0, pos + delimiter.length());
				}
				segmented.push_back(s);

				if (segmented.size() != 3) {
					std::cout << path << " - cannot be read by this parser!" << std::endl;

					getchar();
					return false;
				}



				vertexIndices.push_back(std::stoi(segmented.at(0)));
				uvIndices.push_back(std::stoi(segmented.at(1)));
				normalIndices.push_back(std::stoi(segmented.at(2)));

			}

		}

	}

	
	Model *model = new Model();

	for (unsigned int i = 0; i<vertexIndices.size(); i++) {

		// Get the indices of its attributes
		unsigned int vertexIndex = vertexIndices[i];
		unsigned int uvIndex = uvIndices[i];
		unsigned int normalIndex = normalIndices[i];

		// Get the attributes thanks to the index
		glm::vec3 vertex = temp_vertices[vertexIndex - 1];
		glm::vec2 uv = temp_uvs[uvIndex - 1];
		glm::vec3 normal = temp_normals[normalIndex - 1];

		// Put the attributes in buffers

		model->getVertices().push_back(vertex);
		model->getUVs().push_back(uv);
		model->getNormals().push_back(normal);

	}
	resourceManager->addModel(path, model);

	return true;
}




bool FileReader::fileRead(std::string path, ResourceManager *resourceManager){
	delete file;
	file = new File(path);
	
	switch (file->getFileType()) {
		case FileType::UNKNOWN:
			print("INI FILE");
			break;
		case FileType::INI:
			print("INI FILE");
			break;
		case FileType::OBJ:
			break;

		case FileType::PNG:
			break;

		case FileType::JPG:
			break;


	}

	std::string line;
	std::ifstream myfile(path);
	if (myfile.is_open())	{
		while (std::getline(myfile, line)) {
			//outputString += line;
			print(line);
		}myfile.close();
		return true;
	}
	else {
		print("Unable to open file >>" << path << "<<");
		return false;
	}
}

