//include hpp
#include "walkingCamera.hpp"

//include libs
#include <iostream>

//include files
#include "..\..\..\Defines.hpp"
#include "..\..\..\ResourceManager.hpp"

WalkingCamera::WalkingCamera() :vEye(0.0f, 0.0f, 0.0f), vView(0.0f, 0.0f, -1.0f), vUp(0.0f, 1.0f, 0.0f), fSpeed(25.0f)
{
	SDL_ShowCursor(SDL_DISABLE);
	setMovingKeys(SDLK_w, SDLK_s, SDLK_a, SDLK_d, SDLK_SPACE, SDLK_LCTRL, SDLK_LSHIFT); // default;
}

WalkingCamera::WalkingCamera(glm::vec3 a_vEye, glm::vec3 a_vView, glm::vec3 a_vUp, float a_fSpeed)
{
	vEye = a_vEye;
	vView = a_vView;
	vUp = a_vUp;
	fSpeed = a_fSpeed;

	SDL_ShowCursor(SDL_DISABLE);
	setMovingKeys(SDLK_w, SDLK_s, SDLK_a, SDLK_d, SDLK_SPACE, SDLK_LCTRL, SDLK_LSHIFT); // default;
}

void WalkingCamera::setMovingKeys(int iForw, int iBack, int iLeft, int iRight, int  iUp, int iDown, int iSpeed)
{
	this->iForw = iForw;
	this->iBack = iBack;
	this->iLeft = iLeft;
	this->iRight = iRight;
	this->iUp = iUp;
	this->iDown = iDown;
	this->iSpeed = iSpeed;
}

glm::vec3 & WalkingCamera::getVEye()
{
	return glm::vec3(); // TODO --- THIS IS EMPTY BECAUSE ONLY DEBUG CAMERA HAS VEYE
}

/*-----------------------------------------------

Name:		look

Params:	none

Result:	"Looks" at the scene.

/*---------------------------------------------*/

glm::mat4 WalkingCamera::look()
{
	return glm::lookAt(vEye, vView, vUp);
}

/*-----------------------------------------------

Name:		rotateViewY

Params:	fAngle - angle to rotate by

Result:	Rotates view along Y axis by specified
			angle.

/*---------------------------------------------*/

void WalkingCamera::rotateViewY(float fAngle)
{
	glm::mat4 mRotation = glm::rotate(glm::mat4(1.0f), fAngle, glm::vec3(0.0f, 1.0f, 0.0f));
	glm::vec3 vDir = vView-vEye;
	glm::vec4 vNewView = mRotation*glm::vec4(vDir, 1.0f);
	vView = glm::vec3(vNewView.x, vNewView.y, vNewView.z);
	vView += vEye;
}

/*-----------------------------------------------

Name:		move

Params:	fBy - how much to move

Result:	Moves in the view direction.

/*---------------------------------------------*/

void WalkingCamera::move(float fBy)
{
	glm::vec3 vDir = vView-vEye;
	vDir *= fBy;
	vEye += vDir;
	vView += vDir;
}
/*-----------------------------------------------

Name:		update

Params:	none

Result:	Handles keys and updates camera.

/*---------------------------------------------*/

void WalkingCamera::update(ResourceManager *resourceManager) {
	
	const Uint8 *state = SDL_GetKeyboardState(NULL);
	if (state[SDL_GetScancodeFromKey(iForw)])
		move(5.0f);
	else if (state[SDL_GetScancodeFromKey(iBack)])
		move(-5.0f);
	if (state[SDL_GetScancodeFromKey(iLeft)])
		rotateViewY(glm::radians(10.0f));
	else if (state[SDL_GetScancodeFromKey(iRight)])
		rotateViewY(-glm::radians(10.0f));
	else if (state[SDL_GetScancodeFromKey(iUp)]) {
		vEye.y += 5.0f;
		vView.y += 5.0f;
	}
		
	else if (state[SDL_GetScancodeFromKey(iDown)]) {
		vEye.y -= 5.0f;
		vView.y -= 5.0f;
	}
	
	else if (state[SDL_GetScancodeFromKey(iSpeed)])
		fSpeed *= 5;
	
}