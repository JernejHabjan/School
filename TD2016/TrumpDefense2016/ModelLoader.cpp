#include "ModelLoader.hpp"

void ModelLoader::genBuffers(){
	glGenBuffers(1, &vertexbuffer);
	glBindBuffer(GL_ARRAY_BUFFER, vertexbuffer);
	glBufferData(GL_ARRAY_BUFFER, vertices.size() * sizeof(glm::vec3), &vertices[0], GL_STATIC_DRAW);


	if (loadWholeObj){
		glGenBuffers(1, &uvbuffer);
		glBindBuffer(GL_ARRAY_BUFFER, uvbuffer);
		glBufferData(GL_ARRAY_BUFFER, uvs.size() * sizeof(glm::vec2), &uvs[0], GL_STATIC_DRAW);

		glGenBuffers(1, &normalbuffer);
		glBindBuffer(GL_ARRAY_BUFFER, normalbuffer);
		glBufferData(GL_ARRAY_BUFFER, normals.size() * sizeof(glm::vec3), &normals[0], GL_STATIC_DRAW);
	}

}


ModelLoader::ModelLoader(){}

ModelLoader::ModelLoader(const std::string name, bool loadWholeObj, int loadTexture, SDL_Window* screen, GLuint programID){

	this->loadWholeObj = loadWholeObj;
	this->loadTexture = loadTexture;

	std::string path;

	//texture
	texture = Texture(name, screen, programID);




	texture.loadAllTextures();




	//OBJ
	path = std::string("Objects\\Models\\" + name + ".obj");

	bool loadingResult = Loader::loadOBJ(path, vertices, uvs, normals);
	if (loadingResult == false){
		assert(loadingResult && "Failed to model. Cannot continue.");
	}




	ModelLoader::genBuffers(); //poklièemo za gen Buffers

}

void ModelLoader::loadRegular(SDL_Window* screen, Shader shader){

	std::string arrayObjects[] = {
		"Cube",		
		//"MoneyPouch",
		"MexicanBalooner",
		"Hammer",
		"Heart",
		"MexicanBaloon",
		"MexicanMafia",
		"Builder",
		"Odar",
		"Wall",
		"Baloon",
		"MexicanBanjo",
		"SniperTower",
		"SniperTower2",
		"Rocket",
		"WhiteHouse",
		"WallHeight",
		"House1",
		"House2",
		"House3",
		"Cactus",
		"Tile",
		"Cannon",
		"Cannon2",
		"BuildTile",
		"Navodila"
	};

	for  (std::string var : arrayObjects){
		modelLoader_Models[var] = new ModelLoader(var, true, 2, screen, shader.programID);
	}
}

void ModelLoader::loadSpecial(SDL_Window* screen, Shader shader, int &level){

	std::string arrayObjects[] = {
		"Map"
	};

	for (std::string var : arrayObjects){
		modelLoader_Models[var] = new ModelLoader(var, true, 1, screen, shader.programID);  //PAZI DA JIM DAS PRAVI SHADER_ID
	}

	//SKYBOX//
	if (level == 2){
		modelLoader_Models["CubeMapNight"] = new ModelLoader("CubeMapNight", false, 0, screen, shader.programID_SkyBoxShader);
		Loader::loadCubemap(screen, level);
	}
	else{
		
		modelLoader_Models["CubeMap"] = new ModelLoader("CubeMap", false, 0, screen, shader.programID_SkyBoxShader);
		Loader::loadCubemap(screen, level);
	}


}

void ModelLoader::loadModels(SDL_Window* screen, Shader shader, int &level){
	std::cout << "Loading models." << std::endl;
	loadRegular(screen, shader);
	loadSpecial(screen, shader, level);
}


std::vector<glm::vec3> ModelLoader::getVertices(){
	return vertices;
}


void ModelLoader::activate(){
	//TEXTURE
	texture.activateAll();


	//OBJ

	// 1rst attribute buffer : vertices
	glEnableVertexAttribArray(0);
	glBindBuffer(GL_ARRAY_BUFFER, vertexbuffer);
	glVertexAttribPointer(
		0,                  // attribute
		3,                  // size
		GL_FLOAT,           // type
		GL_FALSE,           // normalized?
		0,                  // stride
		reinterpret_cast<const GLvoid*>(0)            // array buffer offset
		);
	if (loadWholeObj){
		// 2nd attribute buffer : UVs
		glEnableVertexAttribArray(1);
		glBindBuffer(GL_ARRAY_BUFFER, uvbuffer);
		glVertexAttribPointer(
			1,                                // attribute
			2,                                // size
			GL_FLOAT,                         // type
			GL_FALSE,                         // normalized?
			0,                                // stride
			(void*)0                          // array buffer offset
			);

		// 3rd attribute buffer : normals
		glEnableVertexAttribArray(2);
		glBindBuffer(GL_ARRAY_BUFFER, normalbuffer);
		glVertexAttribPointer(
			2,                                // attribute
			3,                                // size
			GL_FLOAT,                         // type
			GL_FALSE,                         // normalized?
			0,                                // stride
			(void*)0                          // array buffer offset
			);
	}


	// Draw the triangle !
	glDrawArrays(GL_TRIANGLES, 0, vertices.size());

	glDisableVertexAttribArray(0);
	if (loadWholeObj){
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(2);
	}


}




ModelLoader::~ModelLoader(){
	typedef std::map<std::string, ModelLoader*>::iterator it_type;
	for (it_type iterator = modelLoader_Models.begin(); iterator != modelLoader_Models.end(); iterator++) {

		delete(iterator->second);

		// iterator->first = key
		// iterator->second = value

	}


	glDeleteBuffers(1, &vertexbuffer);
	glDeleteBuffers(1, &uvbuffer);
	glDeleteBuffers(1, &normalbuffer);

}