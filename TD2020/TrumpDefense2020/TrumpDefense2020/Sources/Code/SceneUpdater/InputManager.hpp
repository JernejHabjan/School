#pragma once

//predefines
class ResourceManager;

/********************************

Class:	InputManager

Purpose: Reads saved commands and
reacts to user input

********************************/

class InputManager {
private:

	ResourceManager *resourceManager;
public:
	InputManager(ResourceManager *resourceManager);
	~InputManager();


	void readControllsFromFile();
	void interpretAction();

};

