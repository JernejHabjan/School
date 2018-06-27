#pragma once
class ResourceManager;
class Loader {
private:
	ResourceManager *resourceManager;
public:
	Loader(ResourceManager* resourceManager);
	~Loader();
	void load();
};

