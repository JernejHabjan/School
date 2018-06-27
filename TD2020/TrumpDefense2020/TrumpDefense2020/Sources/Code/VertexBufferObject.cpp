#pragma once
//include files
#include "defines.hpp"
#include "vertexBufferObject.hpp"

VertexBufferObject::VertexBufferObject() { bDataUploaded = false; }

/*-----------------------------------------------

Name:		createVBO

Params:	a_iSize - initial size of buffer

Result:	Creates vertex buffer object.

/*---------------------------------------------*/

void VertexBufferObject::createVBO(int a_iSize) {
	glGenBuffers(1, &uiBuffer);
	data.reserve(a_iSize);
	iSize = a_iSize;
}

/*-----------------------------------------------

Name:		releaseVBO

Params:	none

Result:	Releases VBO and frees all memory.

/*---------------------------------------------*/

void VertexBufferObject::releaseVBO() {
	glDeleteBuffers(1, &uiBuffer);
	bDataUploaded = false;
	data.clear();
}

/*-----------------------------------------------

Name:		mapBufferToMemory

Params:	iUsageHint - GL_READ_ONLY, GL_WRITE_ONLY...

Result:	Maps whole buffer data to memory and
returns pointer to data.

/*---------------------------------------------*/

void* VertexBufferObject::mapBufferToMemory(int iUsageHint) {
	if (!bDataUploaded)return NULL;
	void* ptrRes = glMapBuffer(iBufferType, iUsageHint);
	return ptrRes;
}

/*-----------------------------------------------

Name:		mapSubBufferToMemory

Params:	iUsageHint - GL_READ_ONLY, GL_WRITE_ONLY...
uiOffset - data offset (from where should
data be mapped).
uiLength - length of data

Result:	Maps specified part of buffer to memory.

/*---------------------------------------------*/

void* VertexBufferObject::mapSubBufferToMemory(int iUsageHint, APIuint uiOffset, APIuint uiLength) {
	if (!bDataUploaded)return NULL;
	void* ptrRes = glMapBufferRange(iBufferType, uiOffset, uiLength, iUsageHint);
	return ptrRes;
}

/*-----------------------------------------------

Name:		unmapBuffer

Params:	none

Result:	Unmaps previously mapped buffer.

/*---------------------------------------------*/

void VertexBufferObject::unmapBuffer() {
	glUnmapBuffer(iBufferType);
}

/*-----------------------------------------------

Name:		bindVBO

Params:	a_iBufferType - buffer type (GL_ARRAY_BUFFER, ...)

Result:	Binds this VBO.

/*---------------------------------------------*/

void VertexBufferObject::bindVBO(int a_iBufferType) {
	iBufferType = a_iBufferType;
	glBindBuffer(iBufferType, uiBuffer);
}

/*-----------------------------------------------

Name:		uploadDataToGPU

Params:	iUsageHint - GL_STATIC_DRAW, GL_DYNAMIC_DRAW...

Result:	Sends data to GPU.

/*---------------------------------------------*/

void VertexBufferObject::uploadDataToGPU(int iDrawingHint) {
	glBufferData(iBufferType, data.size(), &data[0], iDrawingHint);
	bDataUploaded = true;
	data.clear();
}

/*-----------------------------------------------

Name:		addData

Params:	ptrData - pointer to arbitrary data
uiDataSize - data size in bytes

Result:	Adds arbitrary data to VBO.

/*---------------------------------------------*/

void VertexBufferObject::addData(void* ptrData, APIuint uiDataSize) {
	data.insert(data.end(), (GLbyte*)ptrData, (GLbyte*)ptrData + uiDataSize);
}

/*-----------------------------------------------

Name:		getDataPointer

Params:	none

Result:	Returns data pointer (only before uplading).

/*---------------------------------------------*/

void* VertexBufferObject::getDataPointer() {
	if (bDataUploaded)return NULL;
	return (void*)data[0];
}

/*-----------------------------------------------

Name:		getBuffer

Params:	none

Result:	Returns VBO ID.

/*---------------------------------------------*/

APIuint VertexBufferObject::getBuffer() {
	return uiBuffer;
}


