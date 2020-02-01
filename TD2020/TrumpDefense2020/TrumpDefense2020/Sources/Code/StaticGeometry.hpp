
#pragma once
#include <glm\glm.hpp>

#include "VertexBufferObject.hpp"

extern glm::vec4 vBoxColors[];

extern glm::vec3 vCubeVertices[36]; /// TODO CHANGE THIS SHIT TO FUNCTIONS
extern glm::vec2 vCubeTexCoords[6];
extern glm::vec3 vCubeNormals[6];
extern glm::vec3 vGround[6];


extern glm::vec3 vPyramidVertices[12];
extern glm::vec2 vPyramidTexCoords[3];

int generateTorus(VertexBufferObject &vboDest, float fRadius, float fTubeRadius, int iSubDivAround, int iSubDivTube);




