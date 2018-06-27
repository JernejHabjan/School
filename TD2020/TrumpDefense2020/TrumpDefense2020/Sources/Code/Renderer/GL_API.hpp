#pragma once
//include libs
#include <GL\glew.h>

#ifdef __GLEW_H__ //TODO
	#define APIuint GLuint //TODO CHANGE DA SE NE DEFINA ZE TAKOJ
	#define APIint GLint
	#define APIUniform1f glUniform1f
	#define APIUniform3f glUniform3f
	#define APIGetUniformLocation glGetUniformLocation
#endif // __GLEW_H__

class GL_API {
public:
	GL_API();
	~GL_API();

	bool bla();
};

