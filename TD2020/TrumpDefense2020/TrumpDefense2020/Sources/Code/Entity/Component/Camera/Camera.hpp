#pragma once
//include libs
#include <SDL\SDL.h>
#include <glm\glm.hpp>

class ResourceManager;
class Camera 
{
protected:
	int iForw, iBack, iLeft, iRight, iUp, iDown, iSpeed;
public:
	Camera();
	~Camera();
	Camera *camera;
	void initCamera(SDL_Window *screen);
	virtual void update(ResourceManager *resourceManager);
	virtual glm::mat4 look();

	void readCameraControls(); // TODO
	virtual void setMovingKeys(int a_iForw, int a_iBack, int a_iLeft, int a_iRight, int  iUp, int iDown, int iSpeed);

	virtual glm::vec3 &getVEye();

};

