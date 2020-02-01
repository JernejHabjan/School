#pragma once
//include header
#include "Scene.hpp"

//include libs
#include <string>
#include <glm\glm.hpp>
#include <SDL\SDL.h>
#include <glm/gtc/matrix_transform.hpp>
#include <glm/gtc/type_ptr.hpp>

//include files
#include "..\..\Defines.hpp"
#include "..\Renderer.hpp"
#include "..\..\VertexBufferObject.hpp"
#include "..\..\Shader.hpp"
#include "..\..\Texture.hpp"
#include "..\..\StaticGeometry.hpp"
#include "..\..\ResourceManager.hpp"
#include "..\..\Matrix.hpp"
#include "..\..\Entity\Component\Camera\Camera.hpp"
#include "..\..\Entity\Component\Camera\WalkingCamera.hpp"
#include "..\..\Entity\Component\Camera\DebugCamera.hpp"
#include "..\..\freeTypeFont.hpp"
#include "..\..\skybox.h"
#include "..\..\Controller.hpp"
//#include "..\..\Geometry.hpp"

Scene::Scene()
{

}

Scene::Scene(std::string level, SDL_Window *screen) {

	//sceneInputHasher.insert({ SDLK_1, "decreaseScene" });
	//sceneInputHasher.insert({ SDLK_2, "increaseScene" }); ////////// TODO DA BOM LAHKO SWITCHOV SCENE


	this->screen = screen;
	this->level = level;
	
	if (level.compare("7") == 0) {
		init7();
	}
	
	else if (level.compare("joined") == 0) {
		initJoined();
	}
	else {
		print("unknow level - couldn't init level");
		system("PAUSE");
		exit(1);
	}
}

Scene::~Scene() {

}

void Scene::readSceneConditionsAndRules() {
	// TODO---- READS CONTROLLS
}


void Scene::draw(ResourceManager *resourceManager) {

	if (level == "7") {
		draw7(resourceManager);
	}else if (level == "joined") {
		drawJoined(resourceManager);
	}


	else {
		print("unknow level - couldn't draw level");
		system("PAUSE");
		exit(1);
	}
}





/**********  7 ***********/
float fRotationAngleCube = 0.0f, fRotationAnglePyramid = 0.0f;
float fCubeRotationSpeed = 0.0f, fPyramidRotationSpeed = 0.0f;
float fGlobalAngle;
int iTorusFaces1, iTorusFaces2;
float fSunAngle = 45.0f;
int iTorusFaces;
int iFontSize = 24; 
float fDryAmount = 0.75f;

VertexBufferObject vboSceneObjects;

Shader shVertexTex, shFragmentTex, shVertexCol, shFragmentCol;
Shader shShaders[5];
ShaderProgram spTextured, spColored;
ShaderProgram spDirectionalLight;
ShaderProgram spOrtho2D, spFont2D;

#define NUMTEXTURES 4
Texture tTextures[NUMTEXTURES];
Texture tGold, tSnow;
Texture tBlueIce, tBox;

Skybox sbMainSkybox;

FreeTypeFont ftFont;

APIuint uiVAOs[2]; 



void Scene::init7() {

	//readSceneInputsFromFile TODOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
	sceneInputHasher.insert({ SDLK_ESCAPE, "exit" });
	sceneInputHasher.insert({ SDLK_h, "magFilter" });
	sceneInputHasher.insert({ SDLK_j, "minFilter" });



	glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

	vboSceneObjects.createVBO();
	glGenVertexArrays(2, uiVAOs); // Create one VAO
	glBindVertexArray(uiVAOs[0]);

	vboSceneObjects.bindVBO();

	// Add cube to VBO

	FOR(i, 36)
	{
		vboSceneObjects.addData(&vCubeVertices[i], sizeof(glm::vec3));
		vboSceneObjects.addData(&vCubeTexCoords[i % 6], sizeof(glm::vec2));
	}

	// Add ground to VBO

	FOR(i, 6)
	{
		vboSceneObjects.addData(&vGround[i], sizeof(glm::vec3));
		vCubeTexCoords[i] *= 10.0f;
		vboSceneObjects.addData(&vCubeTexCoords[i % 6], sizeof(glm::vec2));
	}

	vboSceneObjects.uploadDataToGPU(GL_STATIC_DRAW);

	// Vertex positions
	glEnableVertexAttribArray(0);
	glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, sizeof(glm::vec3) + sizeof(glm::vec2), 0);
	// Texture coordinates
	glEnableVertexAttribArray(1);
	glVertexAttribPointer(1, 2, GL_FLOAT, GL_FALSE, sizeof(glm::vec3) + sizeof(glm::vec2), (void*)sizeof(glm::vec3));

	// Setup another VAO for untextured objects

	glBindVertexArray(uiVAOs[1]);
	// Here enable only first vertex attribute - position (no texture coordinates)
	glEnableVertexAttribArray(0);
	glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, sizeof(glm::vec3) + sizeof(glm::vec2), 0);

	// Load shaders and create shader programs

	shVertexTex.loadShader("Sources\\Data\\shaders\\7\\shaderTex.vert", GL_VERTEX_SHADER);
	shFragmentTex.loadShader("Sources\\Data\\shaders\\7\\shaderTex.frag", GL_FRAGMENT_SHADER);
	shVertexCol.loadShader("Sources\\Data\\shaders\\7\\shaderCol.vert", GL_VERTEX_SHADER);
	shFragmentCol.loadShader("Sources\\Data\\shaders\\7\\shaderCol.frag", GL_FRAGMENT_SHADER);

	spTextured.createProgram();
	spTextured.addShaderToProgram(&shVertexTex);
	spTextured.addShaderToProgram(&shFragmentTex);
	spTextured.linkProgram();

	spColored.createProgram();
	spColored.addShaderToProgram(&shVertexCol);
	spColored.addShaderToProgram(&shFragmentCol);
	spColored.linkProgram();

	tBlueIce.loadTexture2D("Sources\\Data\\textures\\7\\blueice.jpg", true);
	tBlueIce.setFiltering(TEXTURE_FILTER_MAG_BILINEAR, TEXTURE_FILTER_MIN_BILINEAR_MIPMAP);

	tBox.loadTexture2D("Sources\\Data\\textures\\7\\box.jpg", true);
	tBox.setFiltering(TEXTURE_FILTER_MAG_BILINEAR, TEXTURE_FILTER_MIN_BILINEAR_MIPMAP);

	glEnable(GL_DEPTH_TEST);
	glClearDepth(1.0);
	glClearColor(0.2f, 0.32f, 0.5f, 1.0f);

	camera = new WalkingCamera(glm::vec3(0.0f, 0.0f, -20.0f), glm::vec3(0.0f, 0.0f, -19.0f), glm::vec3(0.0f, 1.0f, 0.0f), 15.0f);
}
void Scene::draw7(ResourceManager *resourceManager) {
	glm::mat4 projMatrix = resourceManager->getMatrix()->projMatrix;


	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	// First render textured objects

	glEnable(GL_TEXTURE_2D);
	spTextured.useProgram();
	glBindVertexArray(uiVAOs[0]);

	int iModelViewLoc = glGetUniformLocation(spTextured.getProgramID(), "modelViewMatrix");
	int iProjectionLoc = glGetUniformLocation(spTextured.getProgramID(), "projectionMatrix");
	int iColorLoc = glGetUniformLocation(spTextured.getProgramID(), "color");

	glm::vec4 vWhiteColor(1.0f, 1.0f, 1.0f, 1.0f);
	glUniform4fv(iColorLoc, 1, glm::value_ptr(vWhiteColor)); // Set white for textures

	glUniformMatrix4fv(iProjectionLoc, 1, GL_FALSE, glm::value_ptr(projMatrix));

	glm::mat4 mModelView = camera->look();
	glm::mat4 mCurrent = mModelView;

	// Render ground

	tBlueIce.bindTexture();
	glUniformMatrix4fv(iModelViewLoc, 1, GL_FALSE, glm::value_ptr(mModelView));
	glDrawArrays(GL_TRIANGLES, 36, 6);

	// Render 5 opaque boxes

	tBox.bindTexture();

	FOR(i, 5)
	{
		float fSign = -1.0f + float(i % 2)*2.0f; // This just returns -1.0f or 1.0f (try to examine this)
		glm::vec3 vPos = glm::vec3(fSign*15.0f, 0.0f, 50.0f - float(i)*25.0f);
		mCurrent = glm::translate(mModelView, vPos);
		mCurrent = glm::scale(mCurrent, glm::vec3(8.0f, 8.0f, 8.0f));
		mCurrent = glm::rotate(mCurrent, fGlobalAngle + i*50.0f, glm::vec3(0.0f, 1.0f, 0.0f));
		glUniformMatrix4fv(iModelViewLoc, 1, GL_FALSE, glm::value_ptr(mCurrent));
		glDrawArrays(GL_TRIANGLES, 0, 36);
	}

	// Now switch to only colored rendering

	glDisable(GL_TEXTURE_2D);
	spColored.useProgram();
	glBindVertexArray(uiVAOs[1]);

	iModelViewLoc = glGetUniformLocation(spColored.getProgramID(), "modelViewMatrix");
	iProjectionLoc = glGetUniformLocation(spColored.getProgramID(), "projectionMatrix");
	iColorLoc = glGetUniformLocation(spColored.getProgramID(), "color");
	glUniformMatrix4fv(iProjectionLoc, 1, GL_FALSE, glm::value_ptr(projMatrix));

	// Render 5 transparent boxes

	glEnable(GL_BLEND);
	glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	glDepthMask(0); // Disable writing to depth buffer

	FOR(i, 5)
	{
		float fSign = 1.0f - float(i % 2)*2.0f; // Same case as before -  -1.0f or 1.0f
		glm::vec3 vPos = glm::vec3(fSign*15.0f, 0.0f, 50.0f - float(i)*25.0f);
		mCurrent = glm::translate(mModelView, vPos);
		mCurrent = glm::scale(mCurrent, glm::vec3(8.0f, 8.0f, 8.0f));
		mCurrent = glm::rotate(mCurrent, fGlobalAngle*0.8f + i*30.0f, glm::vec3(1.0f, 0.0f, 0.0f)); // Just a variation of first rotating
		glUniformMatrix4fv(iModelViewLoc, 1, GL_FALSE, glm::value_ptr(mCurrent));
		glUniform4fv(iColorLoc, 1, glm::value_ptr(vBoxColors[i]));
		glDrawArrays(GL_TRIANGLES, 0, 36);
	}
	glDisable(GL_BLEND);
	glDepthMask(1); // Re-enable writing to depth buffer

	//fGlobalAngle += resourceManager->getController()->sof(100.0f);






	SDL_GL_SwapWindow(screen);
}




void Scene::initJoined() {



	//readSceneInputsFromFile TODOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
	sceneInputHasher.insert({ SDLK_ESCAPE, "exit" });
	sceneInputHasher.insert({ SDLK_h, "magFilter" });
	sceneInputHasher.insert({ SDLK_j, "minFilter" });
	sceneInputHasher.insert({ SDLK_UP, "increaseFontSize" });
	sceneInputHasher.insert({ SDLK_DOWN, "decreaseFontSize" });
	sceneInputHasher.insert({ SDLK_t, "decreaseDraught" });
	sceneInputHasher.insert({ SDLK_z, "increaseDraught" });


	glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

	vboSceneObjects.createVBO();
	glGenVertexArrays(1, uiVAOs); // Create one VAO
	glBindVertexArray(uiVAOs[0]);

	vboSceneObjects.bindVBO();

	// Add cube to VBO

	FOR(i, 36)
	{
		vboSceneObjects.addData(&vCubeVertices[i], sizeof(glm::vec3));
		vboSceneObjects.addData(&vCubeTexCoords[i % 6], sizeof(glm::vec2));
		vboSceneObjects.addData(&vCubeNormals[i / 6], sizeof(glm::vec3));
	}

	// Add ground to VBO

	FOR(i, 6)
	{
		vboSceneObjects.addData(&vGround[i], sizeof(glm::vec3));
		vCubeTexCoords[i] *= 10.0f;
		vboSceneObjects.addData(&vCubeTexCoords[i % 6], sizeof(glm::vec2));
		glm::vec3 vGroundNormal(0.0f, 1.0f, 0.0f);
		vboSceneObjects.addData(&vGroundNormal, sizeof(glm::vec3));
	}

	// Add torus to VBO and remember number of faces (triangles) of this torus
	iTorusFaces1 = generateTorus(vboSceneObjects, 7.0f, 2.0f, 20, 20);
	// Add sun torus to VBO
	iTorusFaces2 = generateTorus(vboSceneObjects, 10.0f, 6.0f, 10, 10);

	vboSceneObjects.uploadDataToGPU(GL_STATIC_DRAW);

	// Vertex positions
	glEnableVertexAttribArray(0);
	glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 2 * sizeof(glm::vec3) + sizeof(glm::vec2), 0);
	// Texture coordinates
	glEnableVertexAttribArray(1);
	glVertexAttribPointer(1, 2, GL_FLOAT, GL_FALSE, 2 * sizeof(glm::vec3) + sizeof(glm::vec2), (void*)sizeof(glm::vec3));
	// Normal vectors
	glEnableVertexAttribArray(2);
	glVertexAttribPointer(2, 3, GL_FLOAT, GL_FALSE, 2 * sizeof(glm::vec3) + sizeof(glm::vec2), (void*)(sizeof(glm::vec3) + sizeof(glm::vec2)));

	// Load shaders and create shader programs

	shShaders[0].loadShader("Sources\\Data\\shaders\\joined\\shader.vert", GL_VERTEX_SHADER);
	shShaders[1].loadShader("Sources\\Data\\shaders\\joined\\shader.frag", GL_FRAGMENT_SHADER);
	shShaders[2].loadShader("Sources\\Data\\shaders\\joined\\ortho2D.vert", GL_VERTEX_SHADER);
	shShaders[3].loadShader("Sources\\Data\\shaders\\joined\\ortho2D.frag", GL_FRAGMENT_SHADER);
	shShaders[4].loadShader("Sources\\Data\\shaders\\joined\\font2D.frag", GL_FRAGMENT_SHADER);

	spDirectionalLight.createProgram();
	spDirectionalLight.addShaderToProgram(&shShaders[0]);
	spDirectionalLight.addShaderToProgram(&shShaders[1]);
	spDirectionalLight.linkProgram();

	spOrtho2D.createProgram();
	spOrtho2D.addShaderToProgram(&shShaders[2]);
	spOrtho2D.addShaderToProgram(&shShaders[3]);
	spOrtho2D.linkProgram();

	spFont2D.createProgram();
	spFont2D.addShaderToProgram(&shShaders[2]);
	spFont2D.addShaderToProgram(&shShaders[4]);
	spFont2D.linkProgram();


	// Load textures

	std::string sTextureNames[] = { "ground.jpg", "box.jpg", "rust.jpg", "sun.jpg" };

	FOR(i, NUMTEXTURES) {
		tTextures[i].loadTexture2D("Sources\\Data\\textures\\joined\\" + sTextureNames[i], true);
		tTextures[i].setFiltering(TEXTURE_FILTER_MAG_BILINEAR, TEXTURE_FILTER_MIN_BILINEAR_MIPMAP);
	}

	glEnable(GL_DEPTH_TEST);
	glClearDepth(1.0);

	// Here we load font with pixel size 32 - this means that if we print with size above 32, the quality will be low
	ftFont.loadSystemFont("comic.ttf", 32);
	ftFont.setShaderProgram(&spFont2D);


	sbMainSkybox.loadSkybox("Sources\\Data\\skyboxes\\jajlands1\\", "jajlands1_ft.jpg", "jajlands1_bk.jpg", "jajlands1_lf.jpg", "jajlands1_rt.jpg", "jajlands1_up.jpg", "jajlands1_dn.jpg");

	camera = new DebugCamera(screen, glm::vec3(0.0f, 0.0f, 0.0f), glm::vec3(0.0f, 0.0f, -1.0f), glm::vec3(0.0f, 1.0f, 0.0f), 25.0f, 0.1f);
	//camera = new WalkingCamera(glm::vec3(0.0f, 1.0f, -30.0f), glm::vec3(0.0f, 1.0f, -29.0f), glm::vec3(0.0f, 1.0f, 0.0f), 35.0f);
}


void Scene::drawJoined(ResourceManager * resourceManager) {

	glm::mat4 projMatrix = resourceManager->getMatrix()->projMatrix;
	glm::mat4 orthoMatrix = resourceManager->getMatrix()->orthoMatrix;

	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	glEnable(GL_TEXTURE_2D);
	spDirectionalLight.useProgram();


	// Set light properties

	float fSine = sin(fSunAngle*3.1415 / 180.0);
	glm::vec3 vSunPos(cos(fSunAngle*3.1415 / 180.0) * 70, sin(fSunAngle*3.1415 / 180.0) * 70, 0.0);

	// We'll change color of skies depending on sun's position
	glClearColor(0.0f, glm::max(0.0f, 0.9f*fSine), glm::max(0.0f, 0.9f*fSine), 1.0f);

	spDirectionalLight.setUniform("vColor", glm::vec4(1.0f, 1.0f, 1.0f, 1.0f));

	spDirectionalLight.setUniform("sunLight.vColor", glm::vec3(1.0f, 1.0f, 1.0f));
	spDirectionalLight.setUniform("sunLight.vDirection", -glm::normalize(vSunPos));
	spDirectionalLight.setUniform("sunLight.fAmbientIntensity", 1.0f); // Full light for skybox


	spDirectionalLight.setUniform("projectionMatrix", projMatrix);




	/// JOINED TEXTURES
	// Set number of textures to 1
	spDirectionalLight.setUniform("numTextures", 1);
	// Set sampler 0 texture unit 0
	spDirectionalLight.setUniform("gSamplers[0]", 0);
	// Texture unit 0 FULLY contributes in final image
	spDirectionalLight.setUniform("fTextureContributions[0]", 1.0f);

	// END JOINED TEXTURES



	glm::mat4 mModelView = camera->look();
	glm::mat4 mModelToCamera;


	//SKYBOX SKYBOX


	//if(camera doenst have VEYE---- EXIT1)--- TODO
	spDirectionalLight.setUniform("normalMatrix", glm::mat4(1.0));
	spDirectionalLight.setUniform("modelViewMatrix", glm::translate(mModelView, camera->getVEye()));
	sbMainSkybox.renderSkybox();

	//END SKYBOX


	glBindVertexArray(uiVAOs[0]);
	spDirectionalLight.setUniform("sunLight.fAmbientIntensity", 0.55f);
	spDirectionalLight.setUniform("modelViewMatrix", &mModelView);




	// Render ground
	spDirectionalLight.setUniform("numTextures", 2);
	// Set sampler 0 texture unit 0
	spDirectionalLight.setUniform("gSamplers[0]", 0);
	// Set sampler 1 texture unit 1
	spDirectionalLight.setUniform("gSamplers[1]", 1);
	// Set contribution according to desertification factor

	spDirectionalLight.setUniform("fTextureContributions[0]", 1.0f - fDryAmount);
	spDirectionalLight.setUniform("fTextureContributions[1]", fDryAmount);
	// Bind texture 0 to texture unit 0
	tTextures[0].bindTexture(0);
	// Bind texture 1 to texture unit 1
	tTextures[1].bindTexture(1);

	glDrawArrays(GL_TRIANGLES, 36, 6);




	//render boxes


	//START TRANSPARENCY
	////PAZI TO NE DELA ÈE SAMO SKLOPM GLBLEND --- NAREJEN JE BLO ZA BARVE... ZDJ PA KAKO NA TEKSTURE???
	//glEnable(GL_BLEND);
	//glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	//glDepthMask(0); // Disable writing to depth buffer
	//START TRANSPARENCY

	spDirectionalLight.setUniform("numTextures", 1);
	spDirectionalLight.setUniform("fTextureContributions[0]", 1.0f);

	tTextures[1].bindTexture();
	FOR(i, 5)
	{
		float fSign = -1.0f + float(i % 2)*2.0f; // This just returns -1.0f or 1.0f (try to examine this)
		glm::vec3 vPos = glm::vec3(fSign*15.0f, 0.0f, 50.0f - float(i)*25.0f);
		mModelToCamera = glm::translate(glm::mat4(1.0), vPos);
		mModelToCamera = glm::scale(mModelToCamera, glm::vec3(8.0f, 8.0f, 8.0f));
		// We need to trasnsform normals properly, it's done by transpose of inverse matrix of rotations and scales
		spDirectionalLight.setUniform("normalMatrix", glm::transpose(glm::inverse(mModelToCamera)));
		spDirectionalLight.setUniform("modelViewMatrix", mModelView*mModelToCamera);
		glDrawArrays(GL_TRIANGLES, 0, 36);
	}
	///////// END TRANSPARENCY
	//glDisable(GL_BLEND);
	//glDepthMask(1); // Re-enable writing to depth buffer
	///////// END TRANSPARENCY




	// Render 5 tori (plural of torus :D)
	tTextures[2].bindTexture();

	FOR(i, 5)
	{
		float fSign = 1.0f - float(i % 2)*2.0f; // This just returns -1.0f or 1.0f (try to examine this)
		glm::vec3 vPos = glm::vec3(fSign*15.0f, 0.0f, 50.0f - float(i)*25.0f);
		mModelToCamera = glm::translate(glm::mat4(1.0), vPos);
		mModelToCamera = glm::rotate(mModelToCamera, fGlobalAngle + i*30.0f, glm::vec3(0.0f, 1.0f, 0.0f));
		spDirectionalLight.setUniform("normalMatrix", glm::transpose(glm::inverse(mModelToCamera)));
		spDirectionalLight.setUniform("modelViewMatrix", mModelView*mModelToCamera);
		glDrawArrays(GL_TRIANGLES, 42, iTorusFaces1 * 3);
	}


	// Render "sun" :D,
	tTextures[3].bindTexture();
	mModelToCamera = glm::translate(glm::mat4(1.0), vSunPos);
	spDirectionalLight.setUniform("modelViewMatrix", mModelView*mModelToCamera);
	spDirectionalLight.setUniform("normalMatrix", glm::transpose(glm::inverse(mModelToCamera)));

	// It must shine
	spDirectionalLight.setUniform("sunLight.fAmbientIntensity", 0.8f);
	glDrawArrays(GL_TRIANGLES, 42 + iTorusFaces1 * 3, iTorusFaces2 * 3);





	//// TEXT

	glDisable(GL_DEPTH_TEST);
	glDepthFunc(GL_ALWAYS);

	spFont2D.useProgram();
	// Font color, you can even change transparency of font with alpha parameter
	spFont2D.setUniform("vColor", glm::vec4(1.0f, 1.0f, 1.0f, 1.0f));
	spFont2D.setUniform("projectionMatrix", orthoMatrix);

	char buf[255];
	sprintf(buf, "Font Size: %d\nPress UP and DOWN arrow key to change\n\nTotal Torus Faces: %d", iFontSize, iTorusFaces);

	int viewPortHeight = resourceManager->getRenderer()->screenProperties.SCREEN_H;

	ftFont.printFont(buf, 20, viewPortHeight - 10 - iFontSize, iFontSize);

	ftFont.printFont("www.mbsoftworks.sk", 20, 20, 24);

	glEnable(GL_DEPTH_TEST);
	glDepthFunc(GL_LESS);




	// other settings:

	//fGlobalAngle += resourceManager->getController()->sof(glm::radians(100.0f));
	//fSunAngle += resourceManager->getController()->sof(glm::radians(100.0f));
	fGlobalAngle += resourceManager->getController()->sof(100.0f);
	fSunAngle += resourceManager->getController()->sof(100.0f);


	camera->update(resourceManager); //update camera
	SDL_GL_SwapWindow(screen);
}





void displayTextureFiltersInfo(SDL_Window *screen)
{
	char buf[255];
	std::string sInfoMinification[] =
	{
		"Nearest",
		"Bilinear"
	};
	std::string sInfoMagnification[] =
	{
		"Nearest",
		"Bilinear",
		"Nearest on closest mipmap",
		"Bilinear on closest mipmap",
		"Trilinear"
	};
	sprintf(buf, "Mag. Filter: %s, Min. Filter: %s", sInfoMinification[tTextures[0].getMagnificationFilter()].c_str(),
		sInfoMagnification[tTextures[0].getMinificationFilter() - 2].c_str());
	SDL_SetWindowTitle(screen, buf);
}


/////////////////////////// SCENE UPDATER ///////////////////////////////////


void Scene::updateScene(SDL_Keycode key, ResourceManager * resourceManager) {

	std::unordered_map<SDL_Keycode, std::string>::const_iterator got = sceneInputHasher.find(key);

	if (got != sceneInputHasher.end()) {
	
		std::string action = got->second;
		if (action == "exit") {
			exit(1);
		}
	

		//camera updated in camera class


		else if (action == "magFilter") {
			FOR(i, NUMTEXTURES) {
				tTextures[i].setFiltering((tTextures[i].getMagnificationFilter() + 1) % 2, tTextures[i].getMinificationFilter());
				displayTextureFiltersInfo(screen);
			}

		}
		else if (action == "minFilter") {

			FOR(i, NUMTEXTURES) {
				int iNewMinFilter = tTextures[i].getMinificationFilter() == TEXTURE_FILTER_MIN_TRILINEAR ? TEXTURE_FILTER_MIN_NEAREST : tTextures[i].getMinificationFilter() + 1;
				tTextures[i].setFiltering(tTextures[i].getMagnificationFilter(), iNewMinFilter);
				displayTextureFiltersInfo(screen);
			}
		}

		else if (action == "increaseRotationSpeed") {
			fCubeRotationSpeed += glm::radians(3.0f); fPyramidRotationSpeed += glm::radians(3.0f);

		}

		else if (action == "decreaseRotationSpeed") {
			fCubeRotationSpeed -= glm::radians(3.0f); fPyramidRotationSpeed -= glm::radians(3.0f);

		}

	
		else if (action == "increaseFontSize") {
			iFontSize++;
		}

		else if (action == "decreaseFontSize") {
			iFontSize--;
		}

		//multitexturing


		
		else if (action == "increaseDraught") {
			if (fDryAmount < 1.0f) {
				fDryAmount += 0.05f;
				print("draught increased");
			}
		}
		else if (action == "decreaseDraught") {
			if (fDryAmount > 0.0f) {
				fDryAmount -= 0.05f;
				print("draught decreased");
			}
		}

	}
		



}


