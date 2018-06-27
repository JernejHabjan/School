#include "Resources.hpp"



Resources::Resources(int &SCREEN_W, int &SCREEN_H, Sound* sound, Camera* cam, Matrix* matrix, std::vector<std::string> &enemyArray) :draw(cam){

	SCREEN_W = SCREEN_W;
	SCREEN_H = SCREEN_H;

	this->sound = sound;
	this->cam = cam;
	this->matrix = matrix;

	life = 10;
	wallHeight = 0;
	money = 200;

	this->enemyArray = enemyArray;
}


void Resources::R_initStart(SDL_Window* screen, Grid &grid, int &level){
	R_init(screen, matrix, grid, level);
}




void Resources::R_init(SDL_Window* screen, Matrix* matrix, Grid &grid, int &level){


	//init sound:

	sound->init();
	
	glClearColor(1.0, 1.0, 0.0, 1.0); //RUMEN CLEAR COLOR

	SDL_GL_SwapWindow(screen);
	float initialFOV = 45.0f;
	float zNear = 0.5f; //ker je cubemap velk 1x1x1
	float zFar = 200.0f;

	matrix->projMatrix = glm::perspective(initialFOV, SCREEN_W / SCREEN_H, zNear, zFar);
	//matrix->orthoMatrix = glm::ortho(0, (int)SCREEN_W, (int)SCREEN_H, 0, 1, 1000);
	matrix->orthoMatrix = glm::ortho(0, 50, 0, 50, 1, 1000);







	glEnable(GL_DEPTH_TEST); //we use depth buffer before draw
	// Accept fragment if it closer to the camera than the former one
	glDepthFunc(GL_LESS);


	glEnable(GL_CULL_FACE);


	//ALPHA ????????????????????????????????????????????????????????????
	//glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	//glEnable(GL_BLEND);
	//glDisable(GL_DEPTH_TEST);




	cam->setLocation(glm::vec3(grid.GRID_W / 2.0f, 50.0, -grid.GRID_H / 2.0f));

	cam->setLocation(0.0f, 50.0f, 0.0f); ///CAM 00



	// Create and compile our GLSL program from the shaders
	
	shader.programID = Shader::LoadShaders("vertex.vs", "fragment.frag");
	shader.programID_SkyBoxShader = Shader::LoadShaders("SkyBox.vs", "SkyBox.frag");
	//shader.programID_GUIShader = Shader::LoadShaders("gui.vs", "gui.frag");
	

	// Get a handle for our "MVP" uniform
	matrix->MatrixP_ID = glGetUniformLocation(shader.programID, "P");
	matrix->MatrixV_ID = glGetUniformLocation(shader.programID, "V");
	matrix->MatrixM_ID = glGetUniformLocation(shader.programID, "M");

	matrix->MatrixPVM_ID = glGetUniformLocation(shader.programID, "PVM");
	matrix->MatrixM_Transp_Inverse_ID = glGetUniformLocation(shader.programID, "M_Transp_Inverse");


	modelLoader.loadModels(screen, shader, level);



	//INIT OBJECTS:

	ObjectStorage::objectStorage_Models["SniperTower"] = new GameObject("SniperTower", modelLoader, level);
	ObjectStorage::objectStorage_Models["SniperTower2"] = new GameObject("SniperTower2", modelLoader, level);
	ObjectStorage::objectStorage_Models["Cannon"] = new GameObject("Cannon", modelLoader, level);
	ObjectStorage::objectStorage_Models["Cannon2"] = new GameObject("Cannon2", modelLoader, level);
	ObjectStorage::objectStorage_Models["MexicanBanjo"] = new GameObject("MexicanBanjo", modelLoader, level);
	ObjectStorage::objectStorage_Models["MexicanBaloon"] = new GameObject("MexicanBaloon", modelLoader, level);
	ObjectStorage::objectStorage_Models["MexicanBalooner"] = new GameObject("MexicanBalooner", modelLoader, level);
	ObjectStorage::objectStorage_Models["MexicanMafia"] = new GameObject("MexicanMafia", modelLoader, level);
	ObjectStorage::objectStorage_Models["Builder"] = new GameObject("Builder", modelLoader, level);
	ObjectStorage::objectStorage_Models["Odar"] = new GameObject("Odar", modelLoader, level); // za prevert kok je širok
	ObjectStorage::objectStorage_Models["BuildTile"] = new GameObject("BuildTile", modelLoader, level);


	


	//init grid:
	grid.initSq(matrix);
	
	//initBuildableSquares:

	for (Square &sq : grid.getSquareArray()){

		if (!sq.isPath){
			sq.object = *ObjectStorage::objectStorage_Models["BuildTile"];
			sq.object.setMatrix(&sq.getMatrix());
			sq.hasObject = true;
			sq.object.isBuildTile = true;

		}
	}
	//init defenses
	bool initAllDefenses = false;

	if (initAllDefenses){
		for (Square &sq : grid.getSquareArray()){

			if (!sq.isPath){
				sq.object = *ObjectStorage::objectStorage_Models["SniperTower"];
				sq.object.setMatrix(&sq.getMatrix());
				sq.hasObject = true;
			}
		}
	}


	glUseProgram(shader.programID);

	
	//ADD LIGHTS
	addDirLight(level);
	addPointLight(level, 2, 20, 2);
	addPointLight(level, 20.0, 20.0, -50.0);
	addPointLight(level, 20.0, 20.0, -50.0);
	addPointLight(level, 20.0, 20.0, -50.0);
	addPointLight(level, 20.0, 20.0, -50.0);
	addPointLight(level, 20.0, 20.0, -50.0);
	addPointLight(level, 20.0, 20.0, -50.0);
	addPointLight(level, 20.0, 20.0, -50.0);



	glm::vec3 startPoint = glm::vec3(grid.getPath().at(0).x - grid.SQUARE_SIZE, 1.0, grid.getPath().at(0).z + grid.SQUARE_SIZE / 8);
	glm::vec3 endPoint = glm::vec3(grid.getPath().at(grid.getPath().size() - 1).x + grid.SQUARE_SIZE, 1.0, grid.getPath().at(grid.getPath().size() - 1).z + grid.SQUARE_SIZE / 8);

	glm::vec3 startDir = glm::vec3(3.0f, -1.0f, 0.0f);
	glm::vec3 endDir = glm::vec3(-3.0f, -1.0f, 0.0f);

	glm::vec3 startColor = glm::vec3(0.0f, 1.0f, 0.0f);
	glm::vec3 endColor = glm::vec3(1.0f, 0.0f, 0.0f);

	addSpotLight(level, startPoint.x, startPoint.y, -startPoint.z, startDir.x, startDir.y, startDir.z, startColor.r, startColor.g, startColor.b);
	addSpotLight(level, endPoint.x, endPoint.y, -endPoint.z, endDir.x, endDir.y, endDir.z, endColor.r, endColor.g, endColor.b);
	
	dirLight.initDirLight(shader.programID, "dirLight.direction", "dirLight.ambient", "dirLight.diffuse", "dirLight.specular");

	//POINT LIGHTS:
	for (GLuint i = 0; i < pointLights.size(); i++){
		std::string pos = "pointLights[" + std::to_string(i) + "].position";
		std::string amb = "pointLights[" + std::to_string(i) + "].ambient";
		std::string diff = "pointLights[" + std::to_string(i) + "].diffuse";
		std::string spec = "pointLights[" + std::to_string(i) + "].specular";
		std::string constant = "pointLights[" + std::to_string(i) + "].constant";
		std::string linear = "pointLights[" + std::to_string(i) + "].linear";
		std::string quadratic = "pointLights[" + std::to_string(i) + "].quadratic";

		pointLights[i].initPointLight(shader.programID, pos.c_str(), amb.c_str(), diff.c_str(), spec.c_str(), constant.c_str(), linear.c_str(), quadratic.c_str());
	}

	//DIR LIGHTS:
	for (GLuint i = 0; i < spotLights.size(); i++){
		std::string dir = "spotLights[" + std::to_string(i) + "].direction";
		std::string pos = "spotLights[" + std::to_string(i) + "].position";
		std::string amb = "spotLights[" + std::to_string(i) + "].ambient";
		std::string diff = "spotLights[" + std::to_string(i) + "].diffuse";
		std::string spec = "spotLights[" + std::to_string(i) + "].specular";
		std::string constant = "spotLights[" + std::to_string(i) + "].constant";
		std::string linear = "spotLights[" + std::to_string(i) + "].linear";
		std::string quadratic = "spotLights[" + std::to_string(i) + "].quadratic";
		std::string cutOff = "spotLights[" + std::to_string(i) + "].cutOff";
		std::string outerCutOff = "spotLights[" + std::to_string(i) + "].outerCutOff";

		spotLights[i].initSpotLight(shader.programID, dir.c_str(), pos.c_str(), amb.c_str(), diff.c_str(), spec.c_str(), constant.c_str(), linear.c_str(), quadratic.c_str(), cutOff.c_str(), outerCutOff.c_str());
	}


	//material
	material.initMaterial(shader.programID, "material.ambient", "material.diffuse", "material.specular", "material.shininess");
	


	bool useSound = true;
	if (!useSound){
		for (int i = 0; i < 50; i++){
			sound->setChannelVolume(i, 0);
		}
	}
	else{
		switch (level){
		case 1:
			sound->playSFX(-1, 22, 0); //build a wall
			sound->playMusic(0, -1);//mariachi neskoncnokrat
			break;
		case 2:
			sound->playMusic(1, -1);//sandalMaker neskoncnokrat
			break;
		case 3:
			sound->playMusic(2, -1);//ReadyTheArmy neskoncnokrat
			break;
		}
		sound->setChannelVolume(1, 1.0f / 4.0f);
		sound->setChannelVolume(2, 1.0f / 2.0f);

		//CONSTRUCTION
		sound->setChannelVolume(3, 1.0f / 2.0f);
		sound->setChannelVolume(6, 1.0f / 2.0f);
		sound->setChannelVolume(7, 1.0f / 2.0f);
		sound->setChannelVolume(8, 1.0f / 2.0f);
		sound->setChannelVolume(9, 1.0f / 2.0f);
		sound->setChannelVolume(10, 1.0f / 2.0f);
		sound->setChannelVolume(11, 1.0f / 2.0f);
		sound->setChannelVolume(12, 1.0f / 2.0f);
		sound->setChannelVolume(13, 1.0f / 2.0f);
		sound->setChannelVolume(17, 1.0f / 2.0f);
		sound->setChannelVolume(18, 1.0f / 2.0f);


	}


	std::cout << "Init completed." << std::endl;


}



void Resources::R_draw(Grid &grid, int &level, Uint32 &gameStartTime){
	draw.display(matrix, shader, pointLights, spotLights, dirLight, material, modelLoader, grid, money, life, sound, wallHeight, enemyArray, level, gameStartTime);
}


void Resources::addPointLight(int &level, float x, float y, float z){

	glm::vec3 lightPosition(x, y, z); 
	glm::vec3 lightAmbient;
	if (level == 2) lightAmbient = glm::vec3(0.0f, 0.0f, 0.0f);
	else lightAmbient = glm::vec3(0.2f, 0.2f, 0.2f);
	glm::vec3 lightDiffuse(0.8f, 0.8f, 0.8f);
	glm::vec3 lightSpecular(1.0, 1.0, 1.0);
	float constant = 1.0f;
	float linear = 0.009f;
	float quadratic = 0.0032f;

	Light light = Light(lightPosition, lightAmbient, lightDiffuse, lightSpecular, constant, linear, quadratic);

	pointLights.push_back(light);

}

void Resources::addDirLight(int &level){
	glm::vec3 lightDirection;
	glm::vec3 lightAmbient;
	glm::vec3 lightDiffuse;
	glm::vec3 lightSpecular;

	if (level == 2){
		lightDirection = glm::vec3(0, 0, 0);
		lightAmbient = glm::vec3(0, 0, 0);
		lightDiffuse = glm::vec3(0, 0, 0);
		lightSpecular = glm::vec3(0, 0, 0);
	}
	else{
		lightDirection = glm::vec3(-0.2f, -1.0f, -0.3f);
		lightAmbient = glm::vec3(0.05f, 0.05f, 0.05f);
		lightDiffuse = glm::vec3(0.7f, 0.6f, 0.3f);
		lightSpecular = glm::vec3(0.5f, 0.5f, 0.5f);
	}

	dirLight = Light(lightDirection, lightAmbient, lightDiffuse, lightSpecular);

}

void Resources::addSpotLight(int &level, float x, float y, float z, float dirX, float dirY, float dirZ, float r, float g, float b){
	glm::vec3 lightDirection(dirX, dirY, dirZ);
	glm::vec3 lightPosition(x, y, z);

	glm::vec3 lightAmbient;
	if (level == 2) lightAmbient = glm::vec3(0.0f, 0.0f, 0.0f);
	else lightAmbient = glm::vec3(r * 0.2f, g * 0.2f, b * 0.2f);

	glm::vec3 lightDiffuse(r * 0.8f, g * 0.8f, b * 0.8f);
	glm::vec3 lightSpecular(1.0, 1.0, 1.0);
	float constant = 1.0f;
	float linear = 0.0009f;
	float quadratic = 0.00032f;

	float cutOff = glm::cos(glm::radians(12.5f));
	float outerCutOff = glm::cos(glm::radians(15.0f));




	Light light = Light(lightDirection, lightPosition, lightAmbient, lightDiffuse, lightSpecular, constant, linear, quadratic, cutOff, outerCutOff);

	spotLights.push_back(light);


}
