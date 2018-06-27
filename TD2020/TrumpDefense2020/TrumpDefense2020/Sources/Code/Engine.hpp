#pragma once
class ResourceManager;


/*
										TODO LIST

#1 tutorial 11 je v joined tutorials ampak ni vidn multitexturing
#2 fixi debug camera mouse - pomoje je problem s focusam - ko grem z misko vn iz focusa in pol nazaj notr v window dela ok
#3 tutorial 7 združ zravn - dj prov shaderje zravn pa renderi kocke z barvam


*/



class Engine {
	ResourceManager *resourceManager;
public:
	Engine();
	const  ResourceManager * getResourceManager() const;
};

