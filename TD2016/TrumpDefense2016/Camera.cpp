#define _USE_MATH_DEFINES
#include "Camera.hpp"


Camera::Camera(){}

Camera::Camera(SDL_Window* screen){
	this->screen = screen;
	camPitch = 0.0f;
	camYaw = 0.0f;
	movevel = 0.8f;
	mousevel = 0.2f;
	moveUpDown = 0.8f;
}

void Camera::lockCamera(){
	if (camPitch>90.0f)
		camPitch = 90.0f;
	if (camPitch<-90.0f)
		camPitch = -90.0f;
	if (camYaw<0.0f)
		camYaw += 360.0f;
	if (camYaw>360.0f)
		camYaw -= 360.0f;
}

void Camera::moveCamera(float dir){
	float rad = (camYaw + dir)*M_PI / 180.0f;
	loc.x -= sin(rad)*movevel;
	loc.z -= cos(rad)*movevel;
}

void Camera::moveCameraUp(float dir){
	float rad = (camPitch + dir)*M_PI / 180.0f;
	loc.y += sin(rad)*movevel;
}

void Camera::rtsCamera(Matrix* matrix, Grid &grid){
	//CURSOR
	SDL_Cursor* cursor;
	cursor = SDL_CreateSystemCursor(SDL_SYSTEM_CURSOR_HAND); //https://wiki.libsdl.org/SDL_CreateSystemCursor
	SDL_SetCursor(cursor);
	SDL_SetWindowGrab(screen, SDL_TRUE);


	int tmpx, tmpy;
	SDL_GetMouseState(&tmpx, &tmpy);


	if (tmpx < 50 || tmpx > SDL_GetWindowSurface(screen)->w - 50){
		if (tmpx > SDL_GetWindowSurface(screen)->w - 50)
			if (loc.x < grid.GRID_MAP_W)
				loc.x += 2.0f;
		if (tmpx < 50)
			if (loc.x > grid.GRID_W - grid.GRID_MAP_W)
				loc.x -= 2.0f;
	}

	if (tmpy < 50 || tmpy > SDL_GetWindowSurface(screen)->h - 50){
		if (tmpy > SDL_GetWindowSurface(screen)->h - 50)
			if (loc.z < grid.GRID_MAP_H / 2.0f - (grid.GRID_MAP_H - grid.GRID_H))
				loc.z += 2.0f;
		if (tmpy < 50)
			if (loc.z > -grid.GRID_MAP_H / 2.0f  - (grid.GRID_MAP_H - grid.GRID_H))
				loc.z -= 2.0f;	
	}

	const Uint8 *state = SDL_GetKeyboardState(NULL);
	if (state[SDL_SCANCODE_UP])
		if (loc.z > -grid.GRID_MAP_H / 2.0f - (grid.GRID_MAP_H - grid.GRID_H))
			loc.z -= 2.0f;
	if (state[SDL_SCANCODE_DOWN])
		if (loc.z < grid.GRID_MAP_H / 2.0f - (grid.GRID_MAP_H - grid.GRID_H))
			loc.z += 2.0f;
	if (state[SDL_SCANCODE_LEFT])
		if (loc.x > grid.GRID_W - grid.GRID_MAP_W)
			loc.x -= 2.0f;
	if (state[SDL_SCANCODE_RIGHT])
		if (loc.x < grid.GRID_MAP_W)
			loc.x += 2.0f;

	matrix->translate(matrix->viewMatrix, translatedX, translatedY, translatedZ);
	matrix->rotateX(matrix->viewMatrix, pitch);
	
}



void Camera::freeStyleCam(Matrix* matrix){


	int MidX = SDL_GetWindowSurface(screen)->w / 2;
	int MidY = SDL_GetWindowSurface(screen)->h / 2;

	int tmpx, tmpy;
	SDL_GetMouseState(&tmpx, &tmpy);
	camYaw += mousevel*(MidX - tmpx);
	camPitch += mousevel*(MidY - tmpy);
	lockCamera();
	SDL_ShowCursor(SDL_DISABLE);
	SDL_WarpMouseInWindow(screen, MidX, MidY);
	const Uint8 *state = SDL_GetKeyboardState(NULL);



	if (state[SDL_SCANCODE_W]){

		if (camPitch != 90 && camPitch != -90)
			moveCamera(0.0f);
		moveCameraUp(0.0f);
	}
	else if (state[SDL_SCANCODE_S]){

		if (camPitch != 90 && camPitch != -90)
			moveCamera(180.0f);
		moveCameraUp(180.0f);
	}
	if (state[SDL_SCANCODE_A]){

		moveCamera(90.0f);
	}
	else if (state[SDL_SCANCODE_D]){

		moveCamera(270.0f);
	}
	else if (state[SDL_SCANCODE_SPACE]){

		glm::vec3 vec = glm::vec3(loc.x, loc.y + moveUpDown, loc.z);
		setLocation(vec);

	}
	else if (state[SDL_SCANCODE_LSHIFT]){
		glm::vec3 vec = glm::vec3(loc.x, loc.y - moveUpDown, loc.z);
		setLocation(vec);

	}

	matrix->rotateX(matrix->viewMatrix, -camPitch);
	matrix->rotateY(matrix->viewMatrix, -camYaw);

}

void Camera::Control(Matrix* matrix, Grid &grid){


	bool useRTS = true;


	if (useRTS){
		rtsCamera(matrix, grid);

	}
	else{
		freeStyleCam(matrix);
	}



}

void Camera::UpdateCamera(Matrix* matrix){
	matrix->translate(matrix->viewMatrix, -loc.x, -loc.y, -loc.z);
}

//change the spherical coordinate system to cartesian
glm::vec3 Camera::getVector(){
	//Google->spherical to cartesian
	return (glm::vec3(-cos(camPitch*M_PI / 180.0f)*sin(camYaw*M_PI / 180.0f), sin(camPitch*M_PI / 180.0f), -cos(camPitch*M_PI / 180.0f)*cos(camYaw*M_PI / 180.0f)));
}
glm::vec3 Camera::getLocation(){
	return loc;
}


void Camera::setLocation(glm::vec3 vec){
	loc = vec;
}

void Camera::setLocation(float x, float y, float z){
	loc.x = x;
	loc.y = y;
	loc.z = z;

}

void Camera::lookAt(float pitch, float yaw){
	camPitch = pitch;
	camYaw = yaw;
}


void Camera::setSpeed(float mv, float mov){
	movevel = mv;
	mousevel = mov;
}


float Camera::getPitch(){
	return pitch;
}

void Camera::setPitch(float pitch){
	this->pitch = pitch;
}