#pragma once

//include parent
#include "Camera.hpp"

//include libs
#include <glm\glm.hpp>
#include <glm\gtc\matrix_transform.hpp>

/********************************

Class:	CWalkingCamera

Purpose:	Camera that can walk
			around the scene.

********************************/

class WalkingCamera : public Camera {
public:
	glm::mat4 look();
	void update(ResourceManager *resourceManager) override;

	void rotateViewY(float fAngle);
	void move(float fBy);

	WalkingCamera();
	WalkingCamera(glm::vec3 a_vEye, glm::vec3 a_vView, glm::vec3 a_vUp, float a_fSpeed);


	void setMovingKeys(int a_iForw, int a_iBack, int a_iLeft, int a_iRight, int  iUp, int iDown, int iSpeed) override;
	glm::vec3 &getVEye() override;

private:
	glm::vec3 vEye, vView, vUp;
	float fSpeed;

};