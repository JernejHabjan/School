#include "InputManager.hpp"



void InputManager::manageEvent(SDL_KeyboardEvent *key, Sound* sound, bool &running, bool &victory, Resources &resources, Grid &grid, int &level, Uint32 &gameStartTime){

	
	bool randomBuild = false;
	if (level == 3) randomBuild = true;

	int maxWallHeight = 35;
	bool notBought = true;
	int timesTried = 0;


	int index = 0;
	bool oneSelected = false;


	switch (key->keysym.sym){
	case SDLK_ESCAPE:
		running = false;
		break;
		/*case SDLK_p:
			if (!Mix_PlayingMusic()){
			sound->playMusic(0, -1);//-1 pomen d neskonènokrat
			}
			else if (Mix_PausedMusic()){
			Mix_ResumeMusic();
			}
			else{
			Mix_PauseMusic();
			}break;
			case SDLK_o:
			Mix_HaltMusic();
			Mix_HaltChannel(0);
			//Mix_HaltChannel(1); //upam da oba halta delata
			break;

			case SDLK_i:
			sound->playSFX(0, 0, 0); //ker channel, soundEffect, 3x loopa
			//Mix_PlayChannel(-1, soundEffect, 0); //-1 je next avaliable channel
			break;

			}*/

	case SDLK_SPACE:
		if (gameStartTime > 3000){
			if (resources.money >= 100){
				if (resources.wallHeight < maxWallHeight && !victory){
					resources.wallHeight += 5;
					resources.money -= 100;
					sound->playSFX(25, 25, 0); //WALL BUILD SFX
				}

				if (resources.wallHeight >= maxWallHeight){
					victory = true;
				}

			}break;
		}
		
	
	case SDLK_u:

		for (Square &sq : grid.getSquareArray()){

			

			if (sq.object.selected && resources.money >= 50 && sq.object.objectLevel < 2 && !sq.object.isBuildTile){ //TOWER UPGRADE COST = 50   //

				if (sq.object.canShootAir)
					sq.object = *ObjectStorage::objectStorage_Models["SniperTower2"]; //UPGRADES IT TO LEVEL 2   //ZAENKAT SM DO LVL2
				if (!sq.object.canShootAir)
					sq.object = *ObjectStorage::objectStorage_Models["Cannon2"];
				sq.object.setMatrix(&sq.getMatrix());
				sq.object.defenseRange += 20;
				sq.object.dps += 20;
				sq.object.objectLevel++;

				resources.money -= 50;

				sound->playSFX(24, 24, 0); //TOWER UPGRADE SFX

			}
		}
		break;
	


	case SDLK_b:
		std::cout << resources.money << std::endl;
		if (randomBuild){
			while (notBought){

				if (timesTried > grid.getSquareArray().size() - grid.getPath().size())
					notBought = false;


				int randIndex = rand() % grid.getSquareArray().size();
				Square &sq = grid.getSquareArray().at(randIndex);
				if (sq.object.isBuildTile && !sq.isPath && resources.money > 20){
					resources.money -= 20;
					sq.object = *ObjectStorage::objectStorage_Models["SniperTower"];
					sq.object.setMatrix(&sq.getMatrix());
					sq.hasObject = true;
					notBought = false;
					sound->playSFX(27, 27, 0);
					
				}
				timesTried++;
			}
		}
		else{

			
			for (GLuint i = 0; i < grid.getSquareArray().size(); i++){ //pogleda kdo je selectan
				if (grid.getSquareArray().at(i).hasObject && grid.getSquareArray().at(i).object.selected && grid.getSquareArray().at(i).object.isBuildTile){
					oneSelected = true;
					index = i;
				}

			}

			if (oneSelected){ //èe je ksn selectan
				Square &sq = grid.getSquareArray().at(index);
				if (resources.money > 20){
					resources.money -= 20;
					sq.object = *ObjectStorage::objectStorage_Models["SniperTower"];
					sq.object.setMatrix(&sq.getMatrix());
					sq.hasObject = true;
					sound->playSFX(27, 27, 0);
				}

			}

		}
		

		break;
	case SDLK_c:
		if (randomBuild){
			while (notBought){

				if (timesTried > grid.getSquareArray().size() - grid.getPath().size())
					notBought = false;



				int randIndex = rand() % grid.getSquareArray().size();
				Square &sq = grid.getSquareArray().at(randIndex);
				if (sq.object.isBuildTile && !sq.isPath && resources.money > 30){
					resources.money -= 30;
					sq.object = *ObjectStorage::objectStorage_Models["Cannon"];
					sq.object.setMatrix(&sq.getMatrix());
					sq.hasObject = true;
					notBought = false;
					sound->playSFX(27, 27, 0);
					
				}
				timesTried++;
			}
		}
		
		else{


			for (GLuint i = 0; i < grid.getSquareArray().size(); i++){ //pogleda kdo je selectan
				if (grid.getSquareArray().at(i).hasObject && grid.getSquareArray().at(i).object.selected && grid.getSquareArray().at(i).object.isBuildTile){
					oneSelected = true;
					index = i;
				}

			}

			if (oneSelected){ //èe je ksn selectan
				Square &sq = grid.getSquareArray().at(index);
				if (resources.money > 30){
					resources.money -= 30;
					sq.object = *ObjectStorage::objectStorage_Models["Cannon"];
					sq.object.setMatrix(&sq.getMatrix());
					sq.hasObject = true;
					sound->playSFX(27, 27, 0);
				}

			}
		}


		break;
	}
}

void InputManager::onMouseWheelScroll(SDL_Event &event, Camera* cam){

	glm::vec3 camLocation = cam->getLocation();
	float pitch = cam->getPitch();

	switch (event.wheel.type){
	case SDL_MOUSEWHEEL:

		if (event.wheel.y < 0){
			//MOUSE WHEEL DOWN
			if (camLocation.y < 100){
				camLocation.y += 5.0;
				if (pitch < 0){

					camLocation.z -= MOVE_DISTANCE;
					cam->setPitch(pitch + ROTATE_PITCH);

				}
				cam->setLocation(camLocation.x, camLocation.y, camLocation.z);
			}
		}
		else{
			//MOUSE WHEEL UP
			if (camLocation.y > 10){
				camLocation.y -= 5.0;
				if (pitch > 10 - 70){
					camLocation.z += MOVE_DISTANCE;
					cam->setPitch(pitch - ROTATE_PITCH);
				}
				
				cam->setLocation(camLocation.x, camLocation.y, camLocation.z);
			}
		}
		break;

	default:
		break;
	}

}

void InputManager::manageMousePressEvent(SDL_MouseButtonEvent& e, float SCREEN_W, float SCREEN_H, Camera* cam, Matrix &matrix, Grid &grid, Sound* sound){
	if (e.button == SDL_BUTTON_LEFT){

		float camX = cam->getLocation().x;
		float camY = cam->getLocation().y;
		float camZ = cam->getLocation().z;


		int tmpx, tmpy;
		SDL_GetMouseState(&tmpx, &tmpy);

		float xMouse = ((2.0f * tmpx) / SCREEN_W) - 1.0f;
		float yMouse = ((2.0f * tmpy) / SCREEN_H) - 1.0f;

		glm::vec4 screenMouseCoords = glm::vec4(-xMouse, yMouse, 1.0f, -1.0f); /////////TLE SM DAV -XMouse da dela pr gridu prov
		glm::mat4 _P = matrix.projMatrix;
		glm::mat4 _V = matrix.viewMatrix;


		glm::mat4 _PInverse = glm::inverse(_P);
		glm::mat4 _VInverse = glm::inverse(_V);

		glm::vec4 toView = _PInverse * screenMouseCoords;
		glm::vec4 toWorld = _VInverse * toView;

		glm::vec3 mouseVector = glm::vec3(toWorld.x, toWorld.y, toWorld.z);
		mouseVector += cam->getLocation();
		mouseVector = -mouseVector;


		glm::vec3 normalizedMouseVector = glm::normalize(mouseVector); //TO HOÈEM


		float factor = -camY / normalizedMouseVector.y; //////////

		glm::vec3 camtoWorld = glm::vec3(  -(camX + factor * normalizedMouseVector.x), camY + factor * normalizedMouseVector.y, camZ + factor * normalizedMouseVector.z);

		//MEJN JE BL JE DESNO
		float xMultiplierFactor = camY * (0.64f + ((100.0f - camY) * 0.027f) ) / 50.0f;
		float zMultiplierFactor = 1.0f + (100.0f - camY) * 0.0005f;



		glm::vec3 gridCoords = glm::vec3(((camtoWorld.x / xMultiplierFactor) *grid.GRID_W), camtoWorld.y, (-camtoWorld.z / zMultiplierFactor) * grid.GRID_H);

		//IF OBJECT INTERSECTED... 

		for (Square &sq : grid.getSquareArray()){

			if (!sq.isPath && sq.hasObject){

				glm::vec4 modelCoordinates = sq.object.getMatrix()->modelMatrix * glm::vec4(1.0, 1.0, 1.0, 1.0);

				bool selected = sq.object.selected;
				bool inRangeX = gridCoords.x  < modelCoordinates.x + grid.SQUARE_SIZE / 2 && gridCoords.x > modelCoordinates.x - grid.SQUARE_SIZE / 2;
				bool inRangeZ = gridCoords.z < modelCoordinates.z + grid.SQUARE_SIZE / 2 && gridCoords.z > modelCoordinates.z - grid.SQUARE_SIZE / 2;

				if (!selected && inRangeX && inRangeZ){
					sq.object.selected = true;
					sound->playSFX(23, 23, 0); //TOWER UPGRADE SFX
				}
				else{
					sq.object.selected = false;

				}
			}

		}

	}
}

