#ifndef SPHERE_H
#define SPHERE_H

#include "..\..\..\..\..\Renderer\GL_API.hpp"
class Sphere
{
public:
    Sphere();
    ~Sphere();
    void init(APIuint vertexPositionID);
    void cleanup();
    void draw();

private:
    int lats, longs;
    bool isInited;
	APIuint m_vao, m_vboVertex, m_vboIndex;
    int numsToDraw;
};

#endif // SPHERE_H