#include "Loader.hpp"

#define DISPLAY_LOADING

bool Loader::loadOBJ(
	const std::string path,
	std::vector<glm::vec3> & out_vertices,
	std::vector<glm::vec2> & out_uvs,
	std::vector<glm::vec3> & out_normals
	){

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


		while (ssin.good()){
			std::string word; //notr ena beseda
			ssin >> word;
			myWordVector.push_back(word);

		}
		//vector wordov je nafillan ... zdj pa 
		if (myWordVector.at(0) == "v"){
			glm::vec3 vertex;
			vertex.x = std::stof(myWordVector.at(1));
			vertex.y = std::stof(myWordVector.at(2));
			vertex.z = std::stof(myWordVector.at(3));
			temp_vertices.push_back(vertex);
		}
		else if (myWordVector.at(0) == "vt"){
			glm::vec2 uv;
			uv.x = std::stof(myWordVector.at(1));
			uv.y = std::stof(myWordVector.at(2));

			uv.y = -uv.y; // Invert V coordinate since we will only use DDS texture, which are inverted. Remove if you want to use TGA or BMP loaders.
			temp_uvs.push_back(uv);
		}
		else if (myWordVector.at(0) == "vn"){
			glm::vec3 normal;
			normal.x = std::stof(myWordVector.at(1));
			normal.y = std::stof(myWordVector.at(2));
			normal.z = std::stof(myWordVector.at(3));
			temp_normals.push_back(normal);
		}
		else if (myWordVector.at(0) == "f"){
			//split first

			std::string delimiter = "/";



			//SPARSAŠ V SEGMENTE
			for (GLuint i = 1; i < myWordVector.size(); i++)//gre po besedi
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

				if (segmented.size() != 3){
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
	for (unsigned int i = 0; i<vertexIndices.size(); i++){

		// Get the indices of its attributes
		unsigned int vertexIndex = vertexIndices[i];
		unsigned int uvIndex = uvIndices[i];
		unsigned int normalIndex = normalIndices[i];

		// Get the attributes thanks to the index
		glm::vec3 vertex = temp_vertices[vertexIndex - 1];
		glm::vec2 uv = temp_uvs[uvIndex - 1];
		glm::vec3 normal = temp_normals[normalIndex - 1];

		// Put the attributes in buffers
		out_vertices.push_back(vertex);
		out_uvs.push_back(uv);
		out_normals.push_back(normal);

	}
	return true;


}

SDL_Surface *Loader::getTextureFromFile(const std::string name, SDL_Window* screen){
	SDL_Surface *img = IMG_Load(name.c_str());
	if (img == NULL){
		printf("IMG_Load: %s\n", IMG_GetError());
		std::cout << name << " - img was not loaded!" << std::endl;
		return NULL;
	}

	SDL_GetWindowPixelFormat(screen);
	SDL_Surface *img2 = SDL_ConvertSurface(img, SDL_GetWindowSurface(screen)->format, SDL_SWSURFACE);

	if (img2 == NULL){
		std::cout << name << " - img2 was not loaded!" << std::endl;
		SDL_FreeSurface(img);

		return NULL;
	}

	SDL_FreeSurface(img);

	return img2;
}



GLuint Loader::loadTexture(const std::string name, SDL_Window* screen, int textureNumber){

#ifdef DISPLAY_LOADING
	std::cout << "Loading texture: " << name << std::endl;
#endif //DISPLAY_LOADING

	SDL_Surface* img2 = getTextureFromFile(name, screen);
	if (img2 == NULL){
		std::cout << name << " - failed to load texture!" << std::endl;
		return 0;
	}

	GLuint texture;
	glGenTextures(1, &texture);
	glBindTexture(GL_TEXTURE_2D, texture);

	//vhodni format
	glTexImage2D(GL_TEXTURE_2D, 0, GL_BGRA, img2->w, img2->h, 0, GL_BGRA, GL_UNSIGNED_BYTE, img2->pixels);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_NEAREST); //TO RABŠ D UKLUŠČ MIPMAP
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
	glGenerateMipmap(GL_TEXTURE_2D);  //Generate num_mipmaps number of mipmaps here.
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);


	GLfloat DEFAULT_ANISOTROPHY = 16.0f;
	GLfloat MAX_ANISOTROPY = 0.0f;
	glGetFloatv(GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT, &MAX_ANISOTROPY);
	GLfloat anisotrophy = DEFAULT_ANISOTROPHY > MAX_ANISOTROPY ? MAX_ANISOTROPY : DEFAULT_ANISOTROPHY;
	glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAX_ANISOTROPY_EXT, anisotrophy);



	SDL_FreeSurface(img2);

	if (!texture){
		std::cout << name << " - failed to load texture!" << std::endl;
		return 0;
	}

	return texture;
}


void Loader::loadCubemap(SDL_Window* screen, int &level){

#ifdef DISPLAY_LOADING
	std::cout << "Loading cubemap" << std::endl;
#endif //DISPLAY_LOADING

	std::vector<std::string> faces;
	if (level == 2){
		faces.push_back("Textures/CubemapNight/right.jpg");
		faces.push_back("Textures/CubemapNight/left.jpg");
		faces.push_back("Textures/CubemapNight/top.jpg");
		faces.push_back("Textures/CubemapNight/bottom.jpg");
		faces.push_back("Textures/CubemapNight/back.jpg");
		faces.push_back("Textures/CubemapNight/front.jpg");

	}
	else{
		faces.push_back("Textures/Cubemap/right.jpg");
		faces.push_back("Textures/Cubemap/left.jpg");
		faces.push_back("Textures/Cubemap/top.jpg");
		faces.push_back("Textures/Cubemap/bottom.jpg");
		faces.push_back("Textures/Cubemap/back.jpg");
		faces.push_back("Textures/Cubemap/front.jpg");

	}


	for (GLuint i = 0; i < faces.size(); i++){


		SDL_Surface* img2 = getTextureFromFile(faces.at(i).c_str(), screen);

		if (img2 == NULL){
			std::cout << "Failed to load cubemap!" << std::endl;
			getchar();

		}

		GLuint texture;

		glGenTextures(1, &texture);

		glActiveTexture(GL_TEXTURE0 + i);
		glBindTexture(GL_TEXTURE_2D, texture);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_BGRA, img2->w, img2->h, 0, GL_BGRA, GL_UNSIGNED_BYTE, img2->pixels);



		//new
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_R, GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glBindTexture(GL_TEXTURE_2D, 0);
		glTexImage2D(GL_TEXTURE_CUBE_MAP_POSITIVE_X + i, 0, GL_BGRA, img2->w, img2->h, 0, GL_BGRA, GL_UNSIGNED_BYTE, img2->pixels);


		SDL_FreeSurface(img2);

		if (!texture){
			std::cout << "Failed to load cubemap!" << std::endl;
			getchar();
		}
	}

	//specific for cubemap?
	glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
	glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
	glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
	glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
	glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_R, GL_CLAMP_TO_EDGE);
	glBindTexture(GL_TEXTURE_CUBE_MAP, 0);


}