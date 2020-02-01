#pragma once
#include <GL\glew.h>


#include <stdio.h>
#include <string>
#include <vector>
#include <iostream>
#include <fstream>
#include <algorithm>

class Shader{
public:

	GLuint programID;
	GLuint programID_SkyBoxShader;
	GLuint programID_GUIShader;

	static GLuint LoadShaders(const char * vertex_file_path, const char * fragment_file_path);
};

