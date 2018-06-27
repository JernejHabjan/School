#include "Texture.hpp"

Texture::Texture(){}

Texture::Texture(const std::string name, SDL_Window* screen, GLuint programID)
{
	this->screen = screen;
	this->programID = programID;
	this->name = name;

}


void Texture::loadAllTextures(){

	loadDiffuse();
	loadSpecular();


}

void Texture::loadDiffuse(){

	std::string path;

	if (name != "CubeMap" && name != "CubeMapNight"){
		path = ("Objects\\Textures\\" + name + "\\" + name + "Diffuse.png").c_str();
		Texture_Diff = Loader::loadTexture(path, screen, 0); //nièta tekstura

		if (Texture_Diff == 0){
			std::cout << "FAILED TO LOAD DIFFUSE... STOPPING" << std::endl;
			getchar();
		}


		glUseProgram(programID);
		glUniform1i(glGetUniformLocation(programID, "diffuseMap"), 0);
		glUseProgram(0);
	}



}


void Texture::loadSpecular(){

	std::string path;

	path = ("Objects\\Textures\\" + name + "\\" + name + "Specular.png").c_str();
	Texture_Spec = Loader::loadTexture(path, screen, 1); //druga tekstura

	glUseProgram(programID);
	glUniform1i(glGetUniformLocation(programID, "specularMap"), 1);
	glUseProgram(0);
}

void Texture::activateAll(){
	activateDiff();
	activateSpec();
}


void Texture::activateDiff(){
	glActiveTexture(GL_TEXTURE0 + 0);
	glBindTexture(GL_TEXTURE_2D, Texture_Diff);

}

void Texture::activateSpec(){
	glActiveTexture(GL_TEXTURE0 + 1);
	glBindTexture(GL_TEXTURE_2D, Texture_Spec);
}

