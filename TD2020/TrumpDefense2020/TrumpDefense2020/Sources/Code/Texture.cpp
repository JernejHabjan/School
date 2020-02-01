#pragma once
//include hpp
#include "Texture.hpp"

//include libs
#include <FreeImage.h>
#include <string.h>

//include files
#include "Defines.hpp"



Texture::Texture() { bMipMapsGenerated = false; }

Texture::~Texture()
{
}

/*-----------------------------------------------

Name:		createFromData

Params:	a_sPath - path to the texture
format - format of data
bGenerateMipMaps - whether to create mipmaps

Result:	Creates texture from provided data.

/*---------------------------------------------*/

void Texture::createFromData(GLubyte* bData, int a_iWidth, int a_iHeight, int a_iBPP, GLenum format, bool bGenerateMipMaps)
{
	// Generate an OpenGL texture ID for this texture
	glGenTextures(1, &uiTexture);
	glBindTexture(GL_TEXTURE_2D, uiTexture);

	if (format == GL_RGB || format == GL_BGR) // We must handle this because of internal format parameter
	{
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, a_iWidth, a_iHeight, 0, format, GL_UNSIGNED_BYTE, bData);
		if (bGenerateMipMaps)glGenerateMipmap(GL_TEXTURE_2D);
	}
	else
	{
		glTexImage2D(GL_TEXTURE_2D, 0, format, a_iWidth, a_iHeight, 0, format, GL_UNSIGNED_BYTE, bData);
		if (bGenerateMipMaps)glGenerateMipmap(GL_TEXTURE_2D);
	}

	glGenSamplers(1, &uiSampler);

	sPath = "";
	bMipMapsGenerated = bGenerateMipMaps;
	iWidth = a_iWidth;
	iHeight = a_iHeight;
	iBPP = a_iBPP;
}


/*-----------------------------------------------

Name:		loadTexture2D

Params:	a_sPath - path to the texture

Result:	Loads texture from a file, supports most
graphics formats.

/*---------------------------------------------*/
bool Texture::loadTexture2D(std::string a_sPath, bool bGenerateMipMaps) {
	FREE_IMAGE_FORMAT fif = FIF_UNKNOWN;
	FIBITMAP* dib(0);

	fif = FreeImage_GetFileType(a_sPath.c_str(), 0); // Check the file signature and deduce its format

	if (fif == FIF_UNKNOWN) // If still unknown, try to guess the file format from the file extension
		fif = FreeImage_GetFIFFromFilename(a_sPath.c_str());

	if (fif == FIF_UNKNOWN) {// If still unkown, return failure
		print("Unknown file format of texture: " << a_sPath);
		system("PAUSE");
		//exit(1); ??
		return false;
	}

	if (FreeImage_FIFSupportsReading(fif)) // Check if the plugin has reading capabilities and load the file
		dib = FreeImage_Load(fif, a_sPath.c_str());
	if (!dib) {
		print("Plugin doesn't have reading compabilities for file: " << a_sPath);
		system("PAUSE");
		//exit(1); ??
		return false;
	}
		

	BYTE* bDataPointer = FreeImage_GetBits(dib); // Retrieve the image data

	iWidth = FreeImage_GetWidth(dib); // Get the image width and height
	iHeight = FreeImage_GetHeight(dib);
	iBPP = FreeImage_GetBPP(dib);

	// If somehow one of these failed (they shouldn't), return failure
	if (bDataPointer == NULL || iWidth == 0 || iHeight == 0) {
		print("Failed retrieving image data of image: " << a_sPath);
		system("PAUSE");
		//exit(1); ??
		return false;
	}
		

	// Generate an OpenGL texture ID for this texture
	glGenTextures(1, &uiTexture);
	glBindTexture(GL_TEXTURE_2D, uiTexture);

	int iFormat = iBPP == 24 ? GL_BGR : iBPP == 8 ? GL_LUMINANCE : 0;
	int iInternalFormat = iBPP == 24 ? GL_RGB : GL_DEPTH_COMPONENT;

	glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, iWidth, iHeight, 0, iFormat, GL_UNSIGNED_BYTE, bDataPointer);
	//glTexImage2D parameters:
	//1.target - in our case it is GL_TEXTURE_2D
	//2.texture LOD - Level Of Detail - we set this to zero - this parameter is used for defining mipmaps.The base level(full resolution) is 0. All subsequent levels(1 / 4 of the texture size, 1 / 16 of the texture size...) are higher, i.e. 1, 2 and so on.But we don't have to do it manually (even though we can, and we don't even have to define ALL mipmap levels if we don't want to, OpenGL doesn't require that), there is luckily a function for mipmap generation(soon we'll get into that).
	//3.internal format - specification says it's number of components per pixel, but it doesn't accept numbers, but constants like GL_RGB and so on(see spec).And even though we use BGR as format, we put here GL_RGB anyway, because this parameter doesn't accept GL_BGR, it really only informs about the number of components per texel. I don't find this very intuitive, but it's probably because of some backwards compatibility.
	//4.width - Texture width
	//5.height - Texture height
	//6.border - width of border - in older OpenGL specifications you could create a border around texture(it's really useless), in new 3.3 specification (and also in future specifications, like 4.2 in time of writing this tutorial), this parameter MUST be zero
	//7.format - Format in which we specify data, GL_BGR in this case
	//8.type - data type of single value, we use unsigned bytes, and thus GL_UNSIGNED_BYTE as data type
	//9.data - finally a pointer to the data


	if (bGenerateMipMaps) glGenerateMipmap(GL_TEXTURE_2D);

	FreeImage_Unload(dib);

	glGenSamplers(1, &uiSampler);

	sPath = a_sPath;
	bMipMapsGenerated = bGenerateMipMaps;
	
	return true; // Success
}

/*-----------------------------------------------

Name:		bindTexture

Params:	iTextureUnit - texture unit to bind texture to

Result:	Guess what it does :)

/*---------------------------------------------*/
void Texture::bindTexture(int iTextureUnit) {
	
	
	glActiveTexture(GL_TEXTURE0 + iTextureUnit);
	glBindTexture(GL_TEXTURE_2D, uiTexture);
	glBindSampler(iTextureUnit, uiSampler);

}

/*-----------------------------------------------

Name:		setFiltering

Params:	tfMagnification - mag. filter, must be from
ETextureFiltering enum
tfMinification - min. filter, must be from
ETextureFiltering enum

Result:	Sets magnification and minification
texture filter.

/*---------------------------------------------*/

void Texture::setFiltering(int a_tfMagnification, int a_tfMinification)
{
	// Set magnification filter
	if (a_tfMagnification == TEXTURE_FILTER_MAG_NEAREST)
		glSamplerParameteri(uiSampler, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	else if (a_tfMagnification == TEXTURE_FILTER_MAG_BILINEAR)
		glSamplerParameteri(uiSampler, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

	// Set minification filter
	if (a_tfMinification == TEXTURE_FILTER_MIN_NEAREST)
		glSamplerParameteri(uiSampler, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
	else if (a_tfMinification == TEXTURE_FILTER_MIN_BILINEAR)
		glSamplerParameteri(uiSampler, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
	else if (a_tfMinification == TEXTURE_FILTER_MIN_NEAREST_MIPMAP)
		glSamplerParameteri(uiSampler, GL_TEXTURE_MIN_FILTER, GL_NEAREST_MIPMAP_NEAREST);
	else if (a_tfMinification == TEXTURE_FILTER_MIN_BILINEAR_MIPMAP)
		glSamplerParameteri(uiSampler, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_NEAREST);
	else if (a_tfMinification == TEXTURE_FILTER_MIN_TRILINEAR)
		glSamplerParameteri(uiSampler, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);

	tfMinification = a_tfMinification;
	tfMagnification = a_tfMagnification;
}

void Texture::setSamplerParameter(GLenum parameter, GLenum value) {
	glSamplerParameteri(uiSampler, parameter, value);
}

int Texture::getMinificationFilter() { return tfMinification; }
int Texture::getMagnificationFilter() {	return tfMagnification; }
int Texture::getWidth() { return iWidth; }
int Texture::getHeight() { return iHeight; }
int Texture::getBPP() {	return iBPP; }


/*-----------------------------------------------

Name:		releaseTexture

Params:	none

Result:	Frees all memory used by texture.

/*---------------------------------------------*/


void Texture::releaseTexture() {
	glDeleteSamplers(1, &uiSampler);
	glDeleteTextures(1, &uiTexture);
}
