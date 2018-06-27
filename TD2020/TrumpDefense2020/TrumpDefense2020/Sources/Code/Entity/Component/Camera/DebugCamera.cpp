//include hpp
#include "DebugCamera.hpp"

//include libs
#include <glm\gtx\rotate_vector.hpp>
#include <SDL\SDL.h>

//include files
#include "..\..\..\Defines.hpp"
#include "..\..\..\ResourceManager.hpp"
#include "..\..\..\Controller.hpp"


const float PI = float(atan(1.0)*4.0);

DebugCamera::DebugCamera()
{
	vEye = glm::vec3(0.0f, 0.0f, 0.0f);
	vView = glm::vec3(0.0f, 0.0, -1.0f);
	vUp = glm::vec3(0.0f, 1.0f, 0.0f);
	fSpeed = 25.0f;
	fSensitivity = 0.1f;
	setMovingKeys(SDLK_w, SDLK_s, SDLK_a, SDLK_d, SDLK_SPACE, SDLK_LCTRL, SDLK_LSHIFT); //default
	SDL_ShowCursor(SDL_DISABLE);
}

DebugCamera::DebugCamera(SDL_Window *screen, glm::vec3 a_vEye, glm::vec3 a_vView, glm::vec3 a_vUp, float a_fSpeed, float a_fSensitivity)
{
	this->screen = screen;
	vEye = a_vEye; vView = a_vView; vUp = a_vUp;
	fSpeed = a_fSpeed;
	fSensitivity = a_fSensitivity;
	setMovingKeys(SDLK_w, SDLK_s, SDLK_a, SDLK_d, SDLK_SPACE, SDLK_LCTRL, SDLK_LSHIFT); //default
	SDL_ShowCursor(SDL_DISABLE);
}

glm::vec3 & DebugCamera::getVEye()
{
	return vEye;
}

/*-----------------------------------------------

Name:	rotateWithMouse

Params:	none

Result:	Checks for moving of mouse and rotates
camera.

/*---------------------------------------------*/

void DebugCamera::rotateWithMouse() {


	int tmpx, tmpy;
	SDL_GetMouseState(&tmpx, &tmpy);

	int iCentX = SDL_GetWindowSurface(screen)->w / 2;
	int	iCentY = SDL_GetWindowSurface(screen)->h / 2;


	float deltaX = (float)(iCentX - tmpx)*fSensitivity;
	float deltaY = (float)(iCentY - tmpy)*fSensitivity;

	if (deltaX != 0.0f) {
		vView -= vEye;
		vView = glm::rotate(vView, glm::radians(deltaX), glm::vec3(0.0f, 1.0f, 0.0f));
		vView += vEye;
	}
	if (deltaY != 0.0f) {
		glm::vec3 vAxis = glm::cross(vView - vEye, vUp);
		vAxis = glm::normalize(vAxis);
		float fAngle = deltaY;
		float fNewAngle = fAngle + getAngleX();
		if (fNewAngle > -89.80f && fNewAngle < 89.80f) {
			vView -= vEye;
			vView = glm::rotate(vView, glm::radians(deltaY), vAxis);
			vView += vEye;
		}
	}


	SDL_WarpMouseInWindow(screen, iCentX, iCentY);

}

/*-----------------------------------------------

Name:	GetAngleY

Params:	none

Result:	Gets Y angle of camera (head turning left
and right).

/*---------------------------------------------*/

float DebugCamera::getAngleY()
{
	glm::vec3 vDir = vView - vEye; vDir.y = 0.0f;
	glm::normalize(vDir);
	float fAngle = acos(glm::dot(glm::vec3(0, 0, -1), vDir))*(180.0f / PI);
	if (vDir.x < 0)fAngle = 360.0f - fAngle;
	return fAngle;
}

DebugCamera::DebugCamera(SDL_Window * screen)
{
	this->screen = screen;
}

/*-----------------------------------------------

Name:	GetAngleX

Params:	none

Result:	Gets X angle of camera (head turning up
and down).

/*---------------------------------------------*/

float DebugCamera::getAngleX()
{
	glm::vec3 vDir = vView - vEye;
	vDir = glm::normalize(vDir);
	glm::vec3 vDir2 = vDir; vDir2.y = 0.0f;
	vDir2 = glm::normalize(vDir2);
	float fAngle = acos(glm::dot(vDir2, vDir))*(180.0f / PI);
	if (vDir.y < 0)fAngle *= -1.0f;
	return fAngle;
}

/*-----------------------------------------------

Name:	SetMovingKeys

Params:	a_iForw - move forward Key
a_iBack - move backward Key
a_iLeft - strafe left Key
a_iRight - strafe right Key

Result:	Sets Keys for moving camera.

/*---------------------------------------------*/

void DebugCamera::setMovingKeys(int iForw, int iBack, int iLeft, int iRight, int  iUp, int iDown, int iSpeed)
{
	this->iForw = iForw;
	this->iBack = iBack;
	this->iLeft = iLeft;
	this->iRight = iRight;
	this->iUp = iUp;
	this->iDown = iDown;
	this->iSpeed = iSpeed;
}

/*-----------------------------------------------

Name:	Update

Params:	none

Result:	Performs updates of camera - moving and
rotating.

/*---------------------------------------------*/

void DebugCamera::update(ResourceManager *resourceManager)
{
	rotateWithMouse();

	// Get view direction
	glm::vec3 vMove = vView - vEye;
	vMove = glm::normalize(vMove);
	vMove *= fSpeed;

	glm::vec3 vStrafe = glm::cross(vView - vEye, vUp);
	vStrafe = glm::normalize(vStrafe);
	vStrafe *= fSpeed;

	int iMove = 0;
	glm::vec3 vMoveBy;
	// Get vector of move

	
	const Uint8 *state = SDL_GetKeyboardState(NULL);
	if (state[SDL_GetScancodeFromKey(iForw)])
		vMoveBy += vMove*resourceManager->getController()->sof(1.0f);
	else if (state[SDL_GetScancodeFromKey(iBack)])
		vMoveBy -= vMove*resourceManager->getController()->sof(1.0f);
	if (state[SDL_GetScancodeFromKey(iLeft)])
		vMoveBy -= vStrafe*resourceManager->getController()->sof(1.0f);
	else if (state[SDL_GetScancodeFromKey(iRight)])
		vMoveBy += vStrafe*resourceManager->getController()->sof(1.0f);
	else if (state[SDL_GetScancodeFromKey(iUp)]) {
		vEye.y += 5.0f;
		vView.y += 5.0f;
	}
	else if (state[SDL_GetScancodeFromKey(iDown)]) {
		vEye.y -= 5.0f;
		vView.y -= 5.0f;
	}
	else if (state[SDL_GetScancodeFromKey(iSpeed)])
		vMoveBy *=5;

	vEye += vMoveBy; vView += vMoveBy;
}


/*-----------------------------------------------

Name:	Look

Params:	none

Result:	Returns proper modelview matrix to make
camera look.

/*---------------------------------------------*/

glm::mat4 DebugCamera::look()
{
	return glm::lookAt(vEye, vView, vUp);
}