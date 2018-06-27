#include "Draw.hpp"


Draw::Draw(){}
Draw::Draw(Camera* cam){
	this->cam = cam;
}


void Draw::display(Matrix* matrix, Shader &shader, std::vector<Light> &pointLights, std::vector<Light> &spotLights, Light &dirLight, 
	Material &material, ModelLoader &modelLoader, Grid &grid, GLuint &money, GLuint &life, Sound *sound, int &wallHeight, 
	std::vector<std::string> &enemyArray, int &level, Uint32 &gameStartTime){
	//reset everything
	glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);


	//glLoadIdentity();
	matrix->loadIdentity(matrix->modelMatrix);
	matrix->loadIdentity(matrix->viewMatrix);

	matrix->rotateX(matrix->viewMatrix, 70.0);

	glUseProgram(shader.programID);






	//MATERIAL
	material.sendPropertiesToShader();



	//updateCamera
	cam->Control(matrix, grid);
	cam->UpdateCamera(matrix);

	//SPREMEN SPODNE DVA LINA-not efficient
	glm::vec3 viewPosition = cam->getLocation();
	GLint viewPosLocation = glGetUniformLocation(shader.programID, "viewPos");
	glUniform3f(viewPosLocation, -viewPosition.x, -viewPosition.y, -viewPosition.z);


	//general Transformations:
	//angle
	angle += 1.0f;
	if (angle > 360) angle = angle - 360;



	//LIGHT MOVEMENT
	if (goRight){
		transl += 1;
		if (transl > 50) goRight = false;
	}
	else{
		transl -= 1;
		if (transl < 0) goRight = true;

	}





	//material
	material.setDefault();


	matrix->loadIdentity(matrix->modelMatrix);
	matrix->translate(matrix->modelMatrix, grid.GRID_W / 2.0f, 0.0, -grid.GRID_H / 2.0f);


	//LIGHT
	//DIR
	dirLight.sendPropertiesToShaderDir();
	//POINT
	int numLight = 0;
	for (int i = 0; i < grid.getPath().size(); i++){
		if (i % 5 == 0){
			for (Square &sq : grid.getSquareArray()){
				if (grid.getPath().at(i).x == sq.getPosition().x  && grid.getPath().at(i).z == sq.getPosition().z){


					matrix->pushMatrix(matrix->modelMatrix);


						glm::vec4 squarePos = glm::vec4(sq.getPosition(), 1.0f);

						matrix->loadIdentity(matrix->modelMatrix);
						matrix->translate(matrix->modelMatrix, squarePos.x, 20.0f, -squarePos.z);

						glm::vec4 lightNewPos = matrix->modelMatrix * glm::vec4(1.0f, 1.0f, 1.0f, 1.0f);
		
						if (displayLightSources) drawModel(modelLoader.modelLoader_Models["Cube"], matrix, shader, material);
						pointLights.at(numLight).sendPropertiesToShaderPoint(lightNewPos.x, lightNewPos.y, lightNewPos.z);

					matrix->popMatrix(matrix->modelMatrix);

					numLight++;
				}
			}
		}
	}


	//SPOT
	for (Light &light : spotLights){
		matrix->pushMatrix(matrix->modelMatrix);
		glm::vec4 lightPos = glm::vec4(light.getPosition(), 1.0f);

		matrix->loadIdentity(matrix->modelMatrix);
		//matrix->translate(matrix->modelMatrix, transl + lightPos.x, lightPos.y, lightPos.z);
		matrix->translate(matrix->modelMatrix, lightPos.x, lightPos.y, lightPos.z);

		glm::vec4 lightNewPos = matrix->modelMatrix * glm::vec4(1.0f, 1.0f, 1.0f, 1.0f);

		if(displayLightSources) drawModel(modelLoader.modelLoader_Models["Baloon"], matrix, shader, material);
		light.sendPropertiesToShaderSpot(lightNewPos.x, lightNewPos.y, lightNewPos.z);


		matrix->popMatrix(matrix->modelMatrix);
	}



	//map
	drawModel(modelLoader.modelLoader_Models["Map"], matrix, shader, material);
	//todo -> SPRAV MAP V NORMALIZIRAN AREA K JE DOLOÈEN Z GRID_MAP_W GRID_MAP_H









	//////////////////////////////////////////////////////////     ZA ORTHO PROJ????
	matrix->pushMatrix(matrix->modelMatrix);
	matrix->loadIdentity(matrix->modelMatrix);

	for (Square &sq : grid.getSquareArray()){
		if (!sq.isPath){
			//drawModel(modelLoader.modelLoader_Models["Rocket"], sq.object.getMatrix(), shader, material);
		}
	}
	matrix->popMatrix(matrix->modelMatrix);
	//////////////////////////////////////////////////////////

	//SPAWN
	bool canSpawnTimer = false;
	if (SDL_GetTicks() - gameStartTime > 5000) //poèaka 5 sec na zaèetk
		canSpawnTimer = true;
	if (spawn){
		if (canSpawnTimer)
			spawnEnemy(grid, sound, enemyArray);
		spawn = true;
	}



	updateEnemy(grid, matrix, shader, material, life);



	checkCollision(grid, matrix, modelLoader, shader, material, money, sound);




	drawTowers(matrix, shader, material, modelLoader, grid);


	drawHealth(grid, matrix, shader, material, modelLoader, life);
	drawWall(grid, matrix, shader, material, modelLoader, sound, wallHeight);
	drawSlums(grid, matrix, shader, material, modelLoader, sound);
	drawTiles(grid, matrix, shader, material, modelLoader);


	
	



	drawSkybox(matrix, shader, modelLoader, level);


	bool canUpgradeEnemy = updateInTime(enemyUpgradeTimer, 5000); //vsake 5 sec dodaš nekej lajfa d so enemy skoz težji
	if (canUpgradeEnemy){
		if (level == 1) lifeAddition = lifeAddition + 17;
		if (level == 2) lifeAddition = lifeAddition + 10;
		if (level == 3) lifeAddition = lifeAddition + 15;
		
	}


	//SOUND TRUMP AND SLUMS

	if (cam->getLocation().x > grid.GRID_W + 40){
		if (updateInTime(trumpSFXTimer, 6000))
			sound->playSFX(22, 22, 0);

	}
	else{
		sound->fadeOut(22, 1000);
		trumpSFXTimer -= 6000; //reset timer
	}
	if (cam->getLocation().x < 0 - 20){
		if (updateInTime(slumsTimer, 12000))
			sound->playSFX(28, 28, 0);
		
	}
	else{
		sound->fadeOut(28, 1000);
		slumsTimer -= 12000; //reset timer
	}



}



bool Draw::updateInTime(GLuint &timer, GLuint time){
	if (SDL_GetTicks() - timer > time){
		timer = SDL_GetTicks();
		return true;
	}
	else{
		return false;
	}
}


void Draw::prepareForDraw(Matrix* matrix, Shader &shader, Material &material){
	//matrix calculation
	glm::mat4 P = matrix->projMatrix;
	glm::mat4 M = matrix->modelMatrix;
	glm::mat4 V = matrix->viewMatrix;

	glm::mat4 PVM = P * V * M;
	glm::mat4 M_Transp_Inverse = glm::mat4(transpose(inverse(M)));

	// Send our transformation to the currently bound shader, 
	// in the "MVP" uniform
	glUniformMatrix4fv(matrix->MatrixP_ID, 1, GL_FALSE, glm::value_ptr(P));
	glUniformMatrix4fv(matrix->MatrixV_ID, 1, GL_FALSE, glm::value_ptr(V));
	glUniformMatrix4fv(matrix->MatrixM_ID, 1, GL_FALSE, glm::value_ptr(M));

	glUniformMatrix4fv(matrix->MatrixPVM_ID, 1, GL_FALSE, glm::value_ptr(PVM));
	glUniformMatrix4fv(matrix->MatrixM_Transp_Inverse_ID, 1, GL_FALSE, glm::value_ptr(M_Transp_Inverse));

}

void Draw::drawModel(ModelLoader* model, Matrix* matrix, Shader &shader, Material &material){
	prepareForDraw(matrix, shader, material);
	//actual draw
	model->activate();
}

void Draw::drawObject(GameObject* object, Matrix* matrix, Shader &shader, Material &material){

	//actual draw
	matrix->pushMatrix(matrix->modelMatrix);
	matrix->modelMatrix = object->getMatrix()->modelMatrix;
	prepareForDraw(matrix, shader, material);
	object->model->activate();
	matrix->popMatrix(matrix->modelMatrix);
}


void Draw::updateEnemy(Grid &grid, Matrix* matrix, Shader &shader, Material &material, GLuint &life){
	//UPDATE ENEMY
	for (Square &sq : grid.getSquareArray()){
		sq.object.hasMoved = false;
		sq.flyIngObject.hasMoved = false;
	}

	bool canUpdate = updateInTime(drawTimer, 500);
	bool canUpdateFlying = updateInTime(drawTimerFlying, 500);

	for (Square &sq : grid.getSquareArray()){

		if (!sq.object.hasMoved && !sq.flyIngObject.hasMoved){ //èe se še ni premaknu to sekundo
			if (sq.isPathFlying && sq.hasFlyingObject){

				drawObject(&sq.flyIngObject, matrix, shader, material);

				if (canUpdateFlying){ //vsako sekundo
					if (updatePath(sq, grid, life, grid.getPathFlying(), sq.flyIngObject, sq.getPosition().x, sq.getPosition().z) == 1) break;
				}
			}

			if (sq.isPath && sq.hasObject){
				sq.object.getMatrix()->rotateY(sq.object.getMatrix()->modelMatrix, sq.object.rotationAngle);
				drawObject(&sq.object, matrix, shader, material);
				sq.object.getMatrix()->rotateY(sq.object.getMatrix()->modelMatrix, -sq.object.rotationAngle); //rotacijo zavrtiš nazaj
				
				if (canUpdate){ //vsako sekundo
					if (updatePath(sq, grid, life, grid.getPath(), sq.object, sq.getPosition().x, sq.getPosition().z) == 1) break;

				}

			}
			
		}
	}
}


bool Draw::updatePath(Square &sq, Grid &grid, GLuint &life, std::vector<glm::vec3> &path, GameObject &gameObject, float oldX, float oldZ){

	if (gameObject.coordinateIndex < path.size() - 1){ //ÈE ŠE NI NAKONC

		if (gameObject.type == "ground")
			sq.hasObject = false;
		sq.hasFlyingObject = false;


		gameObject.coordinateIndex++;
		float newX = path.at(gameObject.coordinateIndex).x; //nova koordinata v path
		float newZ = path.at(gameObject.coordinateIndex).z; //nova koordinata v path
	
		//GIVE THIS OBJECT TO NEW SQUARE NODE
		for (Square &sq1 : grid.getSquareArray()){

			if (sq1.getPosition().x == newX && sq1.getPosition().z == newZ && gameObject.hasMoved == false){ //tle ne nasledn node v getPathFlying

				if (gameObject.type == "ground"){
					sq1.object = sq.object; //skopiramo object v nov node
					sq1.object.hasMoved = true;
					sq1.object.setMatrix(&sq1.getMatrix());
					sq1.hasObject = true;

					//ENEMY ROTATION
					if (newX > oldX) sq1.object.rotationAngle = 0.0f;
					if (newX < oldX) sq1.object.rotationAngle = 180.0f;
					if (newZ < oldZ) sq1.object.rotationAngle = 270.0f;
					if (newZ > oldZ) sq1.object.rotationAngle = 90.0f;
 
				}
				if (gameObject.type == "flying"){
					sq1.flyIngObject = sq.flyIngObject; //skopiramo object v nov node
					sq1.flyIngObject.hasMoved = true;
					sq1.flyIngObject.setMatrix(&sq1.getMatrix());
					sq1.hasFlyingObject = true;
					
				}
			}
		}
	}
	else{ //ÈE JE NAKONC
		sq.hasObject = false;
		sq.hasFlyingObject = false;
		life--;
		return 1;

	}return 0;
}



void Draw::spawnEnemy(Grid &grid, Sound *sound, std::vector<std::string> &enemyArray){
	//SPAWN NEW ONE
	if (updateInTime(spawnTimer, 2000)){
		for (Square &sq : grid.getSquareArray()){
			if (sq.getPosition().x == grid.getPath().at(0).x && sq.getPosition().z == grid.getPath().at(0).z){
				for (std::string &enemyName : enemyArray){

					GameObject go = *ObjectStorage::objectStorage_Models[enemyName];
					go.hp += lifeAddition;

					if (go.type == "ground"){
						sq.object = go;
						sq.object.setMatrix(&sq.getMatrix());
						sq.object.coordinateIndex = 0;
						sq.hasObject = true;
					}
					else if (go.type == "flying"){//is Flying
						sq.flyIngObject = go;
						sq.flyIngObject.setMatrix(&sq.getMatrix());
						sq.flyIngObject.coordinateIndex = 0;
						sq.hasFlyingObject = true;
					}

					if (cam->getLocation().x < 0 + 30){ //ce sm cist na levi strani

						if (sq.object.type == "ground"){
							sound->playSFX(19, 19, 0); //SPAWN Ground SFX
						}
						else{
							sound->playSFX(0, 0, 0); //SPAWN baloon SFX
						}
					}
					else{

						if (sq.object.type == "ground"){
							sound->fadeOut(19, 200); //SPAWN Ground SFX
						}
						else{
							sound->fadeOut(0, 200); //SPAWN baloon SFX
						}

					}
				}


			}
		}
	}
}

void Draw::checkCollision(Grid &grid, Matrix* matrix, ModelLoader &modelLoader, Shader &shader, Material &material, GLuint &money, Sound *sound){

	///CHECK COLLISION S TOWERJI


	for (Square &sq1 : grid.getSquareArray()){
		if ((sq1.hasObject && sq1.object.type == "ground") || sq1.hasFlyingObject){ //èe je ground in ne defense ali pa flying
			for (Square &sq2 : grid.getSquareArray()){
				if (sq2.hasObject && sq2.object.type == "defense"){ //DEFENSI

					float enemyX;
					float enemyZ;
					//getPosition
					if (sq1.object.type == "ground"){
						enemyX = glm::abs(sq1.object.getPosition().x);
						enemyZ = glm::abs(sq1.object.getPosition().z);
					}
				
					else{
						enemyX = glm::abs(sq1.flyIngObject.getPosition().x);
						enemyZ = glm::abs(sq1.flyIngObject.getPosition().z);
					}


					//check collisiion


					float defenseX = glm::abs(sq2.object.getPosition().x);
					float defenseZ = glm::abs(sq2.object.getPosition().z);


					


					if (SDL_GetTicks() - sq2.object.lastShotTime > 1000){

						if (glm::sqrt(glm::pow(enemyX - defenseX, 2) + glm::pow(enemyZ - defenseZ, 2)) < sq2.object.defenseRange){

							//UPDATE ANGLE
							float kateta = enemyX - defenseX;
							float hipotenuza = glm::sqrt(glm::pow(enemyX - defenseX, 2) + glm::pow(enemyZ - defenseZ, 2));
							float rotationAngle = glm::asin(kateta / hipotenuza);
							sq2.object.rotationAngle = glm::degrees(rotationAngle);
							if (enemyX < defenseX && enemyZ < defenseZ) sq2.object.rotationAngle = 90 + sq2.object.rotationAngle;
							if (enemyX > defenseX) sq2.object.rotationAngle =  270 - sq2.object.rotationAngle;
							if ((enemyX == defenseX) && enemyZ > defenseZ) sq2.object.rotationAngle = -90.0f;
							if ((enemyX == defenseX) && enemyZ < defenseZ) sq2.object.rotationAngle = 90.0f;
							if (enemyX < defenseX && enemyZ == defenseZ) sq2.object.rotationAngle = 0.0f;
							if (enemyX > defenseX && enemyZ == defenseZ) sq2.object.rotationAngle = 180.0f;


							//time
							sq2.object.lastShotTime = SDL_GetTicks();

							//FIRE A ROCKET
							float rocketPosX = (enemyX + defenseX) / 2.0f;
							float rocketPosZ = (enemyZ + defenseZ) / 2.0f; //RAKETA JE NASREDIN OBJEKTOV


							matrix->pushMatrix(matrix->modelMatrix);
							matrix->loadIdentity(matrix->modelMatrix);
							matrix->translate(matrix->modelMatrix, rocketPosX, 0, -rocketPosZ);
							drawModel(modelLoader.modelLoader_Models["Rocket"], matrix, shader, material);
							matrix->popMatrix(matrix->modelMatrix);


							//HP 
							if (sq1.object.type == "ground")
								sq1.object.hp -= sq2.object.dps;
							if (sq1.hasFlyingObject && sq2.object.canShootAir)
								sq1.flyIngObject.hp -= sq2.object.dps + 10; //air bonus

							if (sq1.object.type == "ground" && sq1.object.hp <= 0){

								//update money
								money += 10;


								//update grid
								sq1.hasObject = false;



								if (cam->getLocation().x > sq1.getPosition().x - 50 && cam->getLocation().x < sq1.getPosition().x + 50){
									sound->playSFX(1, 1, 0); //MONEY SFX
									sound->playSFX(2, 2, 0); //DIE SFX
								}

							}

							if (sq1.hasFlyingObject && sq1.flyIngObject.hp <= 0){ //FLYING
								//update money
								money += 20;


								
								//update grid
								sq1.hasFlyingObject = false;
								if (cam->getLocation().x > sq1.getPosition().x - 50 && cam->getLocation().x < sq1.getPosition().x + 50){
									sound->playSFX(1, 1, 0); //MONEY SFX
									sound->playSFX(2, 2, 0); //DIE SFX
								}
							}

							//////////////////////////////////////////////////////////////////////TLE ŠE FIXI DA SE RAKETA POMIKA PROT ENEMYJU
							if (cam->getLocation().x > sq1.getPosition().x - 50 && cam->getLocation().x < sq1.getPosition().x + 50){
								//sound->playSFX(4, 4, 0); //PEW SFX

								if (sq2.object.canShootAir){
									sound->playSFX(14, 14, 0); //FIRE SFX
								}
								else{
									sound->playSFX(26, 26, 0); //CANNON SFX
								}
								
							}

						}
					}
				}
			}
		}
	}

}




void Draw::drawTowers(Matrix* matrix, Shader &shader, Material &material, ModelLoader &modelLoader, Grid &grid){


	glUseProgram(shader.programID);
	for (Square &sq : grid.getSquareArray()){
		//DRAW MAIN OBJECT

		if (!sq.isPath && sq.hasObject){
	
			if (!sq.object.canShootAir){
				sq.object.getMatrix()->rotateY(sq.object.getMatrix()->modelMatrix, sq.object.rotationAngle);
				drawObject(&sq.object, matrix, shader, material);
				sq.object.getMatrix()->rotateY(sq.object.getMatrix()->modelMatrix, -sq.object.rotationAngle); //rotacijo zavrtiš nazaj


			}
			else{
				drawObject(&sq.object, matrix, shader, material);
			}


			//DRAW OTHER STUFF
			//modelLoader.modelLoader_Models["Rocket"]->activate();

			/*if (sq.isSelected){

			//modelLoader.modelLoader_Models["GUI"]->activate();
			drawSquare(sq, shader, modelLoader);
			}*/


			//DRAW BOUNDING BOX
			prepareForDraw(matrix, shader, material);
			sq.object.drawBBox(matrix);
		}

	}
}



void Draw::drawSkybox(Matrix* matrix, Shader &shader, ModelLoader &modelLoader, int &level){

	glDepthFunc(GL_LEQUAL);
	glUseProgram(shader.programID_SkyBoxShader);

	glm::mat4 view = glm::mat4x3(glm::mat3(matrix->viewMatrix));	// Remove any translation component of the view matrix
	glUniformMatrix4fv(glGetUniformLocation(shader.programID_SkyBoxShader, "view"), 1, GL_FALSE, glm::value_ptr(view));
	glUniformMatrix4fv(glGetUniformLocation(shader.programID_SkyBoxShader, "projection"), 1, GL_FALSE, glm::value_ptr(matrix->projMatrix));

	if (level == 2) modelLoader.modelLoader_Models["CubeMapNight"]->activate();
	else modelLoader.modelLoader_Models["CubeMap"]->activate();
	

	glDepthFunc(GL_LESS); // Set depth function back to default
}




void Draw::drawHealth(Grid &grid, Matrix* matrix, Shader &shader, Material &material, ModelLoader &modelLoader, GLuint &life){
	matrix->pushMatrix(matrix->modelMatrix);
	matrix->loadIdentity(matrix->modelMatrix);
	matrix->translate(matrix->modelMatrix, grid.GRID_W / 4.0f, 10, -grid.GRID_H - 10);


	for (int i = 0; i < life; i++){
		matrix->translateX(matrix->modelMatrix, (grid.GRID_W / 4.0f) / life);
		drawModel(modelLoader.modelLoader_Models["Heart"], matrix, shader, material);
	}

	matrix->popMatrix(matrix->modelMatrix);
}


void Draw::drawTiles(Grid &grid, Matrix* matrix, Shader &shader, Material &material, ModelLoader &modelLoader){
	//DRAW TILES
	for (Square &sq : grid.getSquareArray()){
		if (sq.isPath){
			matrix->pushMatrix(matrix->modelMatrix);

			matrix->loadIdentity(matrix->modelMatrix);


			float x = sq.getPosition().x;
			float z = sq.getPosition().z;

			matrix->translate(matrix->modelMatrix, x, 0.3, -z);
			drawModel(modelLoader.modelLoader_Models["Tile"], matrix, shader, material);

			matrix->popMatrix(matrix->modelMatrix);
		}

	}


	//DRAW TUTORIAL TILE
	matrix->loadIdentity(matrix->modelMatrix);
	matrix->translateX(matrix->modelMatrix, grid.GRID_W + 30);
	drawModel(modelLoader.modelLoader_Models["Navodila"], matrix, shader, material);

}

void Draw::drawWall(Grid &grid, Matrix* matrix, Shader &shader, Material &material, ModelLoader &modelLoader, Sound* sound, int &wallHeight){

	//DRAW WALL
	matrix->pushMatrix(matrix->modelMatrix);
	matrix->loadIdentity(matrix->modelMatrix);
	matrix->translate(matrix->modelMatrix, grid.GRID_W, 0.0, -grid.GRID_H / 2.0f); //da je na mapu
	matrix->translateX(matrix->modelMatrix, 10.0f); //daj mav naprej


	//HAMMER ROTATION ANIMATION
	if (goUpHammer){
		hammerRotationAnimation += 5 + 5* 1.0f / wallHeight;
		if (hammerRotationAnimation > 60) goUpHammer = false;
	}
	else{
		hammerRotationAnimation -= 5 + 5 * 1.0f / wallHeight;
		if (hammerRotationAnimation < 0) goUpHammer = true;

	}


	//DRAW ODAR

	//
	matrix->translate(matrix->modelMatrix, 0, 0, -grid.GRID_H / 2);
	drawModel(modelLoader.modelLoader_Models["Odar"], matrix, shader, material);

	matrix->pushMatrix(matrix->modelMatrix);
	matrix->translate(matrix->modelMatrix, 2.0, 5.0, -2.0);
	matrix->rotateY(matrix->modelMatrix, 90);
	drawModel(modelLoader.modelLoader_Models["Builder"], matrix, shader, material);
	drawHammer(matrix, shader, material, modelLoader, hammerRotationAnimation);
	matrix->popMatrix(matrix->modelMatrix);


	//
	matrix->translate(matrix->modelMatrix, 0, 0, +grid.GRID_H / 8);
	drawModel(modelLoader.modelLoader_Models["Odar"], matrix, shader, material);


	matrix->pushMatrix(matrix->modelMatrix);
	matrix->translate(matrix->modelMatrix, 2.0, 5.0, -2.0);
	matrix->rotateY(matrix->modelMatrix, 90);
	drawModel(modelLoader.modelLoader_Models["Builder"], matrix, shader, material);
	drawHammer(matrix, shader, material, modelLoader, hammerRotationAnimation);
	matrix->popMatrix(matrix->modelMatrix);

	//
	matrix->translate(matrix->modelMatrix, 0, 0, +grid.GRID_H / 8);
	drawModel(modelLoader.modelLoader_Models["Odar"], matrix, shader, material);


	matrix->pushMatrix(matrix->modelMatrix);
	matrix->translate(matrix->modelMatrix, 2.0, 5.0, -2.0);
	matrix->rotateY(matrix->modelMatrix, 90);
	drawModel(modelLoader.modelLoader_Models["Builder"], matrix, shader, material);
	drawHammer(matrix, shader, material, modelLoader, hammerRotationAnimation);
	matrix->popMatrix(matrix->modelMatrix);

	//
	matrix->translate(matrix->modelMatrix, 0, 0, +grid.GRID_H / 8);
	drawModel(modelLoader.modelLoader_Models["Odar"], matrix, shader, material);


	matrix->pushMatrix(matrix->modelMatrix);
	matrix->translate(matrix->modelMatrix, 2.0, 5.0, -2.0);
	matrix->rotateY(matrix->modelMatrix, 90);
	drawModel(modelLoader.modelLoader_Models["Builder"], matrix, shader, material);
	drawHammer(matrix, shader, material, modelLoader, hammerRotationAnimation);
	matrix->popMatrix(matrix->modelMatrix);

	//
	matrix->translate(matrix->modelMatrix, 0, 0, +grid.GRID_H / 8);
	drawModel(modelLoader.modelLoader_Models["Odar"], matrix, shader, material);


	matrix->pushMatrix(matrix->modelMatrix);
	matrix->translate(matrix->modelMatrix, 2.0, 5.0, -2.0);
	matrix->rotateY(matrix->modelMatrix, 90);
	drawModel(modelLoader.modelLoader_Models["Builder"], matrix, shader, material);
	drawHammer(matrix, shader, material, modelLoader, hammerRotationAnimation);
	matrix->popMatrix(matrix->modelMatrix);

	//
	matrix->translate(matrix->modelMatrix, 0, 0, +grid.GRID_H / 8);
	drawModel(modelLoader.modelLoader_Models["Odar"], matrix, shader, material);


	matrix->pushMatrix(matrix->modelMatrix);
	matrix->translate(matrix->modelMatrix, 2.0, 5.0, -2.0);
	matrix->rotateY(matrix->modelMatrix, 90);
	drawModel(modelLoader.modelLoader_Models["Builder"], matrix, shader, material);
	drawHammer(matrix, shader, material, modelLoader, hammerRotationAnimation);
	matrix->popMatrix(matrix->modelMatrix);

	//
	matrix->translate(matrix->modelMatrix, 0, 0, +grid.GRID_H / 8);
	drawModel(modelLoader.modelLoader_Models["Odar"], matrix, shader, material);


	matrix->pushMatrix(matrix->modelMatrix);
	matrix->translate(matrix->modelMatrix, 2.0, 5.0, -2.0);
	matrix->rotateY(matrix->modelMatrix, 90);
	drawModel(modelLoader.modelLoader_Models["Builder"], matrix, shader, material);
	drawHammer(matrix, shader, material, modelLoader, hammerRotationAnimation);
	matrix->popMatrix(matrix->modelMatrix);

	//
	matrix->translate(matrix->modelMatrix, 0, 0, +grid.GRID_H / 8);
	drawModel(modelLoader.modelLoader_Models["Odar"], matrix, shader, material);


	matrix->pushMatrix(matrix->modelMatrix);
	matrix->translate(matrix->modelMatrix, 2.0, 5.0, -2.0);
	matrix->rotateY(matrix->modelMatrix, 90);
	drawModel(modelLoader.modelLoader_Models["Builder"], matrix, shader, material);
	drawHammer(matrix, shader, material, modelLoader, hammerRotationAnimation);
	matrix->popMatrix(matrix->modelMatrix);

	//
	matrix->translate(matrix->modelMatrix, 0, 0, +grid.GRID_H / 8);
	drawModel(modelLoader.modelLoader_Models["Odar"], matrix, shader, material);


	matrix->pushMatrix(matrix->modelMatrix);
	matrix->translate(matrix->modelMatrix, 2.0, 5.0, -2.0);
	matrix->rotateY(matrix->modelMatrix, 90);
	drawModel(modelLoader.modelLoader_Models["Builder"], matrix, shader, material);
	drawHammer(matrix, shader, material, modelLoader, hammerRotationAnimation);
	matrix->popMatrix(matrix->modelMatrix);




	//WALL
	matrix->loadIdentity(matrix->modelMatrix);
	matrix->translate(matrix->modelMatrix, grid.GRID_W, 0.0, -grid.GRID_H / 2.0f); //da je na mapu

	//wallheight
	drawModel(modelLoader.modelLoader_Models["WallHeight"], matrix, shader, material);

	matrix->translateX(matrix->modelMatrix, 10.0f); //daj mav naprej


	//WHITE HOUSE
	matrix->translateX(matrix->modelMatrix, 25);
	//drawModel(modelLoader.modelLoader_Models["WhiteHouse"], matrix, shader, material);

	matrix->translate(matrix->modelMatrix, -7, 18, 0);
	matrix->rotateY(matrix->modelMatrix, 180);
	drawModel(modelLoader.modelLoader_Models["MexicanMafia"], matrix, shader, material); //trump himself
	matrix->rotateY(matrix->modelMatrix, -180);
	matrix->translate(matrix->modelMatrix, -18, -18, 0);




	float odarWidth = ObjectStorage::objectStorage_Models["Odar"]->getObjSize().x;

	matrix->translate(matrix->modelMatrix, odarWidth - 1, wallHeight, -0.55f);
	drawModel(modelLoader.modelLoader_Models["Wall"], matrix, shader, material);
	matrix->translateY(matrix->modelMatrix, -wallHeight);


	//WALL SFX

	if (cam->getLocation().x > grid.GRID_W - 10){
		bool canUpdate = updateInTime(hammerSFXTimer, 6000);
		if (canUpdate){

			sound->playSFX(3, 3, 0); //HAMMER SFX
			sound->playSFX(6, 6, 0); //CONSTRUCTION1 SFX
			sound->playSFX(7, 7, 0); //CONSTRUCTION2 SFX
			sound->playSFX(8, 8, 0); //CONSTRUCTION3 SFX
			sound->playSFX(9, 9, 0); //CONSTRUCTION4 SFX
			sound->playSFX(10, 10, 0); //CONSTRUCTION5 SFX
			sound->playSFX(11, 11, 0); //CONSTRUCTION6 SFX
			sound->playSFX(12, 12, 0); //CONSTRUCTION7 SFX
			sound->playSFX(13, 13, 1); //CONSTRUCTION6 SFX
			sound->playSFX(17, 17, 11); //CONSTRUCTION7 SFX
			sound->playSFX(18, 18, 8); //CONSTRUCTION6 SFX

		}
	}
	else{
		sound->fadeOut(3, 200); //HAMMER SFX
		sound->fadeOut(6, 200);
		sound->fadeOut(7, 200);
		sound->fadeOut(8, 200);
		sound->fadeOut(9, 200);
		sound->fadeOut(10, 200);
		sound->fadeOut(11, 200);
		sound->fadeOut(12, 200);
		sound->fadeOut(13, 200);
		sound->fadeOut(17, 200);
		sound->fadeOut(18, 200);
		hammerSFXTimer -= 6000; //reset timer
	}

	matrix->popMatrix(matrix->modelMatrix);
}

void Draw::drawHammer(Matrix* matrix, Shader &shader, Material &material, ModelLoader &modelLoader, float hammerRotationAnimation){
	
	matrix->translate(matrix->modelMatrix, -0.5f, 1.0f, 0.3f);
	//matrix->rotateX(matrix->modelMatrix, hammerRotationAnimation);
	drawModel(modelLoader.modelLoader_Models["Hammer"], matrix, shader, material);
	//matrix->rotateX(matrix->modelMatrix, -hammerRotationAnimation);
	matrix->translate(matrix->modelMatrix, 0.5f, -1.0f, -0.3f);
}

void Draw::drawSlums(Grid &grid, Matrix* matrix, Shader &shader, Material &material, ModelLoader &modelLoader, Sound* sound){

	//ROUND 1
	matrix->loadIdentity(matrix->modelMatrix);
	matrix->translate(matrix->modelMatrix, -40, 0, -grid.GRID_H);

	drawModel(modelLoader.modelLoader_Models["House1"], matrix, shader, material);
	matrix->translate(matrix->modelMatrix, 0, 0, +grid.GRID_H / 4.0f);

	drawModel(modelLoader.modelLoader_Models["House2"], matrix, shader, material);
	matrix->translate(matrix->modelMatrix, 5, 0, 5);
	drawModel(modelLoader.modelLoader_Models["Cactus"], matrix, shader, material);
	matrix->translate(matrix->modelMatrix, -5, 0, -5);

	matrix->translate(matrix->modelMatrix, 0, 0, +grid.GRID_H / 4.0f);
	drawModel(modelLoader.modelLoader_Models["House1"], matrix, shader, material);

	matrix->translate(matrix->modelMatrix, 0, 0, +grid.GRID_H / 4.0f);
	//drawModel(modelLoader.modelLoader_Models["House3"], matrix, shader, material);

	matrix->translate(matrix->modelMatrix, 5, 0, 5);
	drawModel(modelLoader.modelLoader_Models["Cactus"], matrix, shader, material);
	matrix->translate(matrix->modelMatrix, -10, 0, 2);
	drawModel(modelLoader.modelLoader_Models["Cactus"], matrix, shader, material);

	matrix->translate(matrix->modelMatrix, 0, 0, +grid.GRID_H / 4.0f - 10);
	//drawModel(modelLoader.modelLoader_Models["House3"], matrix, shader, material);
	matrix->translate(matrix->modelMatrix, -10, 0, 5);
	drawModel(modelLoader.modelLoader_Models["Cactus"], matrix, shader, material);
	//ROUND 2

	matrix->loadIdentity(matrix->modelMatrix);
	matrix->translate(matrix->modelMatrix, -20, 0, -grid.GRID_H);

	//drawModel(modelLoader.modelLoader_Models["House3"], matrix, shader, material);
	matrix->translate(matrix->modelMatrix, 0, 0, +grid.GRID_H / 4.0f);

	drawModel(modelLoader.modelLoader_Models["House1"], matrix, shader, material);
	matrix->translate(matrix->modelMatrix, 5, 0, 5);
	drawModel(modelLoader.modelLoader_Models["Cactus"], matrix, shader, material);
	matrix->translate(matrix->modelMatrix, -5, 0, -5);

	matrix->translate(matrix->modelMatrix, 0, 0, +grid.GRID_H / 4.0f);
	drawModel(modelLoader.modelLoader_Models["House2"], matrix, shader, material);

	matrix->translate(matrix->modelMatrix, 5, 0, 5);
	drawModel(modelLoader.modelLoader_Models["Cactus"], matrix, shader, material);
	matrix->translate(matrix->modelMatrix, 5, 0, 0);
	drawModel(modelLoader.modelLoader_Models["Cactus"], matrix, shader, material);
	matrix->translate(matrix->modelMatrix, -10, 0, 0);

	matrix->translate(matrix->modelMatrix, -10, 0, +grid.GRID_H / 4.0f);
	drawModel(modelLoader.modelLoader_Models["House1"], matrix, shader, material);

	matrix->translate(matrix->modelMatrix, 5, 0, +grid.GRID_H / 4.0f);
	drawModel(modelLoader.modelLoader_Models["House2"], matrix, shader, material);


	//ROUND3
	matrix->loadIdentity(matrix->modelMatrix);
	matrix->translate(matrix->modelMatrix, -50, 0, -grid.GRID_H);


	drawModel(modelLoader.modelLoader_Models["House1"], matrix, shader, material);
	matrix->translate(matrix->modelMatrix, 0.0f, 0.0f, +grid.GRID_H / 4.0f + 10.0f);

	//drawModel(modelLoader.modelLoader_Models["House3"], matrix, shader, material);
	matrix->translate(matrix->modelMatrix, 5, 0, 5);
	drawModel(modelLoader.modelLoader_Models["Cactus"], matrix, shader, material);
	matrix->translate(matrix->modelMatrix, -5, 0, -5);

	matrix->translate(matrix->modelMatrix, 0, 0, +grid.GRID_H / 4.0f + 10);
	drawModel(modelLoader.modelLoader_Models["House1"], matrix, shader, material);

	matrix->translate(matrix->modelMatrix, 5, 0, 5);
	drawModel(modelLoader.modelLoader_Models["Cactus"], matrix, shader, material);
	matrix->translate(matrix->modelMatrix, 5, 0, 0);
	drawModel(modelLoader.modelLoader_Models["Cactus"], matrix, shader, material);





}
