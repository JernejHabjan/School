#pragma once

//predefine
class GL_API;
class ResourceManager;

class RequirementsChecker {

private:
	RequirementsChecker();
	~RequirementsChecker();
public:


	static void checkGL_APIVersion(GL_API *glApi);
	static void checkInitFiles(ResourceManager *resourceManager);

	




};

