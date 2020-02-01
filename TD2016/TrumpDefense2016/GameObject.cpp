#include "GameObject.hpp"

GameObject::GameObject(){}
GameObject::GameObject(std::string name, ModelLoader &modelLoader, int &level){

	this->name = name;
	if (level == 2)	defenseRange = 20.0f;
	else defenseRange = 30.0f;
	dps = 10.0f;
	lastShotTime = 0;
	objectLevel = 1;

	//ENEMY
	coordinateIndex = 0;
	hasMoved = false;
	hp = 100;
	if (level == 2) hp = 70;
	if (level == 3) hp = 120;

	std::string ground[] = {	//UPDATE WHEN NEW MODELS ARRIVE
		"MexicanMafia",
		"MoneyPouch",
		"Builder",
		"MexicanBanjo"
	};

	std::string flying[] = {
		"MexicanBaloon",
		"MexicanBalooner",
		"Baloon"
	};


	std::string defense[] = {
		
		"SniperTower",
		"SniperTower2",
		"Cannon",
		"Cannon2"
	};

	std::string other[] = {
		"Cube",
		"Hammer",
		"Wall",
		"Odar",
		"Rocket",
		"BuildTile"
	};

	for (std::string groundName : ground){
		if (name == groundName) type = "ground";
	}
	for (std::string flyingName : flying){
		if (name == flyingName) type = "flying";
	}
	for (std::string defenseName : defense){
		if (name == defenseName) type = "defense";
		if (name == defenseName && (name == "Cannon" ||name == "Cannon2")){
			canShootAir = false; //cannon nemore strelat gor
			dps = dps + dps * 0.5f; //ma 150% dps
		}
	}
	for (std::string otherName : other){
		if (name == otherName) type = "other";
	}



	model = modelLoader.modelLoader_Models[name];

	getBBox();
}


void GameObject::getBBox(){

	std::vector<glm::vec3> vertices = model->getVertices(); 

	if (vertices.size() == 0)
		return;

	// Cube 1x1x1, centered on origin
	GLfloat bbox[] = {
		-0.5, -0.5, -0.5, 1.0,
		 0.5, -0.5, -0.5, 1.0,
		 0.5,  0.5, -0.5, 1.0,
		-0.5,  0.5, -0.5, 1.0,
		-0.5, -0.5,  0.5, 1.0,
		 0.5, -0.5,  0.5, 1.0,
		 0.5,  0.5,  0.5, 1.0,
		-0.5,  0.5,  0.5, 1.0,
	};

	glGenBuffers(1, &vbo_bbox);
	glBindBuffer(GL_ARRAY_BUFFER, vbo_bbox);
	glBufferData(GL_ARRAY_BUFFER, sizeof(bbox), bbox, GL_STATIC_DRAW);
	glBindBuffer(GL_ARRAY_BUFFER, 0);

	GLushort elements[] = {
		0, 1, 2, 3,
		4, 5, 6, 7,
		0, 4, 1, 5, 2, 6, 3, 7
	};

	glGenBuffers(1, &ibo_elements);
	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo_elements);
	glBufferData(GL_ELEMENT_ARRAY_BUFFER, sizeof(elements), elements, GL_STATIC_DRAW);
	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);


	GLfloat
		minX, maxX,
		minY, maxY,
		minZ, maxZ; //BOUNDING BOX

	minX = maxX = vertices[0].x;
	minY = maxY = vertices[0].y;
	minZ = maxZ = vertices[0].z;
	for (int i = 0; i < vertices.size(); i++) {
		if (vertices[i].x < minX) minX = vertices[i].x;
		if (vertices[i].x > maxX) maxX = vertices[i].x;
		if (vertices[i].y < minY) minY = vertices[i].y;
		if (vertices[i].y > maxY) maxY = vertices[i].y;
		if (vertices[i].z < minZ) minZ = vertices[i].z;
		if (vertices[i].z > maxZ) maxZ = vertices[i].z;
	}
	objSize = glm::vec3(maxX - minX, maxY - minY, maxZ - minZ);
	center = glm::vec3((minX + maxX) / 2, (minY + maxY) / 2, (minZ + maxZ) / 2);
	transform = glm::translate(glm::mat4(1), center)* glm::scale(glm::mat4(1), objSize);

}
	
void GameObject::drawBBox(Matrix* matrix){

	if (!selected){

		return;
	}

	//Apply object's transformation matrix //
	glm::mat4 m; 
	if (useMatrix){ ///////////////////////////////////OBJ K JIH NAKLIKAMO
		m = this->matrix->modelMatrix * transform;
	}else{
		m = matrix->modelMatrix * transform;
	}


	//SEND TO SHADER CALCULATED PVM
	glm::mat4 P = matrix->projMatrix;
	glm::mat4 V = matrix->viewMatrix;
	glm::mat4 PVM = P * V * m;
	glUniformMatrix4fv(matrix->MatrixM_ID, 1, GL_FALSE, glm::value_ptr(m));
	glUniformMatrix4fv(matrix->MatrixPVM_ID, 1, GL_FALSE, glm::value_ptr(PVM));



	glBindBuffer(GL_ARRAY_BUFFER, vbo_bbox);
	glEnableVertexAttribArray(0);
	glVertexAttribPointer(
		0,  // attribute
		4,                  // number of elements per vertex, here (x,y,z,w)
		GL_FLOAT,           // the type of each element
		GL_FALSE,           // take our values as-is
		0,                  // no extra data between each position
		0                   // offset of first element
		);

	glLineWidth(3.0);
	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo_elements);
	glDrawElements(GL_LINE_LOOP, 4, GL_UNSIGNED_SHORT, 0);
	glDrawElements(GL_LINE_LOOP, 4, GL_UNSIGNED_SHORT, (GLvoid*)(4 * sizeof(GLushort)));
	glDrawElements(GL_LINES, 8, GL_UNSIGNED_SHORT, (GLvoid*)(8 * sizeof(GLushort)));

}

glm::vec3 GameObject::getPosition(){
	return glm::vec3(matrix->modelMatrix * glm::vec4(0.0f, 0.0f, 0.0f, 1.0f));
}

void GameObject::setMatrix(Matrix* matrix){

	this->matrix = matrix;
	useMatrix = true;
}

Matrix* GameObject::getMatrix(){
	return matrix;
}

GameObject::~GameObject(){
	glDeleteBuffers(1, &vbo_bbox);
	glDeleteBuffers(1, &ibo_elements);
}

glm::vec3 GameObject::getObjSize(){
	return objSize;
}