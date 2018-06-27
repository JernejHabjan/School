#pragma once

#include <ft2build.h>
#include FT_FREETYPE_H

#include "Texture.hpp"
#include "Shader.hpp"
#include "VertexBufferObject.hpp"

/********************************

Class:	FreeTypeFont

Purpose: Wraps FreeType fonts and
		 their usage with OpenGL.

********************************/

class FreeTypeFont
{
public:
	bool loadFont(std::string sFile, int iPXSize);
	bool loadSystemFont(std::string sName, int iPXSize);

	int getTextWidth(std::string sText, int iPXSize);

	void printFont(std::string sText, int x, int y, int iPXSize = -1);

	void releaseFont();

	void setShaderProgram(ShaderProgram* a_shShaderProgram);

	FreeTypeFont();
private:
	void createChar(int iIndex);

	Texture tCharTextures[256];
	int iAdvX[256], iAdvY[256];
	int iBearingX[256], iBearingY[256];
	int iCharWidth[256], iCharHeight[256];
	int iLoadedPixelSize, iNewLine;

	bool bLoaded;

	GLuint uiVAO;
	VertexBufferObject vboData;

	FT_Library ftLib;
	FT_Face ftFace;
	ShaderProgram* shShaderProgram;
};
