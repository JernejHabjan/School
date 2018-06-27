#pragma once

#include <gl\glew.h>
#include "VertexBufferObject.hpp"
#include <string>
#include "Texture.hpp"

/********************************

Class: Skybox

Purpose: Class for using skybox.

********************************/


class Skybox
{
public:
	void loadSkybox(std::string a_sDirectory, std::string a_sFront, std::string a_sBack, std::string a_sLeft, std::string a_sRight, std::string a_sTop, std::string a_sBottom);
	void renderSkybox();

	void releaseSkybox();
private:
	GLuint uiVAO;
	VertexBufferObject vboRenderData;
	Texture tTextures[6];
	std::string sDirectory;
	std::string sFront, sBack, sLeft, sRight, sTop, sBottom;
};