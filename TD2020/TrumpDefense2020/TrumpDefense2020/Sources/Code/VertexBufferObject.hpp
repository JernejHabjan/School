#pragma once
//include libs
#include <vector>

//include files
#include "Renderer\GL_API.hpp"

/********************************

Class:	VertexBufferObject

Purpose:	Wraps OpenGL vertex buffer
object.

********************************/
class VertexBufferObject {
public:
	void createVBO(int a_iSize = 0);
	void releaseVBO();

	void* mapBufferToMemory(int iUsageHint);
	void* mapSubBufferToMemory(int iUsageHint, APIuint uiOffset, APIuint uiLength);
	void unmapBuffer();

	void bindVBO(int a_iBufferType = GL_ARRAY_BUFFER);
	void uploadDataToGPU(int iUsageHint);

	void addData(void* ptrData, APIuint uiDataSize);

	void* getDataPointer();
	APIuint getBuffer();

	VertexBufferObject();

private:
	APIuint uiBuffer;
	int iSize;
	int iBufferType;
	std::vector<GLbyte> data;

	bool bDataUploaded;
};