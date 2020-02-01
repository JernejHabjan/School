#include "Grid.hpp"


Grid::Grid(){}

void Grid::initSq(Matrix* matrix){
	makePath();
	makePathFlying();

	matrix->pushMatrix(matrix->modelMatrix);

	for (float i = 0; i < GRID_H; i += SQUARE_SIZE) {
		for (float j = 0; j < GRID_W; j += SQUARE_SIZE) {
			
		
			float sqX = j;
			float sqZ = i;

			float x_Middle = sqX + SQUARE_SIZE / 2.0f;
			float z_Middle = sqZ + SQUARE_SIZE / 2.0f;
			bool isPath = pathNode(sqX, sqZ);
			bool isPathFlying = pathNodeFlying(sqX, sqZ);

			squareArray.push_back(Square(sqX, sqZ, x_Middle, z_Middle, *matrix, isPath, isPathFlying));
			matrix->translateX(matrix->modelMatrix, SQUARE_SIZE);
		}
		matrix->translateZ(matrix->modelMatrix, -SQUARE_SIZE);
		matrix->translateX(matrix->modelMatrix, -GRID_W);
	}
	matrix->popMatrix(matrix->modelMatrix);

}




bool Grid::pathNode(float &x, float &z){
	bool isPath = false;
	for (glm::vec3 vec : path){

		if (vec.x == x && vec.z == z){
			isPath = true;

		}
	}
	return isPath;
}


bool Grid::pathNodeFlying(float &x, float &z){
	bool isPath = false;
	for (glm::vec3 vec : pathFlying){

		if (vec.x == x && vec.z == z){
			isPath = true;
		}
	}
	return isPath;
}


void Grid::makePath(){
	
	path = {
	glm::vec3(0, 0, 64),
	glm::vec3(8, 0, 64),
	glm::vec3(16, 0, 64),
	glm::vec3(16, 0, 72),
	glm::vec3(16, 0, 80),
	glm::vec3(24, 0, 80),
	glm::vec3(32, 0, 80),
	glm::vec3(40, 0, 80),

	glm::vec3(40, 0, 80),
	glm::vec3(40, 0, 72),
	glm::vec3(40, 0, 64),
	glm::vec3(40, 0, 56),
	glm::vec3(40, 0, 48),
	glm::vec3(40, 0, 40),
	glm::vec3(40, 0, 32),
	glm::vec3(40, 0, 24),
	glm::vec3(40, 0, 16),
	glm::vec3(48, 0, 16),
	glm::vec3(48, 0, 16),
	glm::vec3(56, 0, 16),
	glm::vec3(64, 0, 16),
	glm::vec3(64, 0, 24),
	glm::vec3(64, 0, 32),
	glm::vec3(72, 0, 32),
	glm::vec3(80, 0, 32),
	glm::vec3(80, 0, 24),
	glm::vec3(80, 0, 16),
	glm::vec3(88, 0, 16),
	glm::vec3(96, 0, 16),
	glm::vec3(96, 0, 24),
	glm::vec3(96, 0, 32),
	glm::vec3(96, 0, 40),
	glm::vec3(96, 0, 48),
	glm::vec3(96, 0, 56),
	glm::vec3(96, 0, 64),
	glm::vec3(96, 0, 64),
	glm::vec3(104, 0, 64),
	glm::vec3(112, 0, 64),
	glm::vec3(120, 0, 64)
	};
	
}

void Grid::makePathFlying(){

	pathFlying = {
		glm::vec3(0, 0, 64),
		glm::vec3(8, 0, 64),
		glm::vec3(16, 0, 64),
		glm::vec3(24, 0, 64),
		glm::vec3(32, 0, 64),
		glm::vec3(40, 0, 64),
		glm::vec3(48, 0, 64),
		glm::vec3(56, 0, 64),
		glm::vec3(64, 0, 64),
		glm::vec3(72, 0, 64),
		glm::vec3(80, 0, 64),
		glm::vec3(88, 0, 64),
		glm::vec3(96, 0, 64),
		glm::vec3(104, 0, 64),
		glm::vec3(112, 0, 64),
		glm::vec3(120, 0, 64)
	};

}


std::vector<Square>& Grid::getSquareArray(){ return squareArray; }
std::vector<glm::vec3>& Grid::getPath(){ return path; }
std::vector<glm::vec3>& Grid::getPathFlying(){ return pathFlying; }



/*
void Grid::start(){

	double x = StdDraw.mouseX();
	double y = StdDraw.mouseY();

	if (StdDraw.mousePressed()){

		for (Square k : squareArray){//SO  SLOWWWWWWW
			int squareSize = HEIGHT / SQ_NUM;
			if (k.isfree == true){
				if (((x - squareSize)<k.x&(k.x<(x + squareSize))) & ((y - squareSize)<k.y&(k.y<(y + squareSize)))){
					k.setColor(10, 255, 150);
					k.isfree = false;  //state ma zaseden
					k.isStart = true;
					startSq = k;
					k.draw();
					startNotSet = false;
				}
			}
		}
	}
}

void Grid::end(){
	double x = StdDraw.mouseX();
	double y = StdDraw.mouseY();

	if (StdDraw.mousePressed()){

		for (Square k : squareArray){
			int squareSize = HEIGHT / SQ_NUM;
			if (k.isfree == true){
				if (((x - squareSize)<k.x&(k.x<(x + squareSize))) & ((y - squareSize)<k.y&(k.y<(y + squareSize)))){
					k.setColor(255, 255, 150);
					k.isEnd = true;
					endSq = k;
					k.draw();
					endNotSet = false;
				}
			}
		}
	}
}

void Grid::initRandomStartEnd(){
	Random rnd = new Random();
	Boolean startCreated = false;
	int i = 0;
	while (i<squareArray.size()){ //da se ne zacikla
		int randStart = rnd.nextInt(squareArray.size()); //rectangleArray.size() ni vkljuèen
		Square sq = squareArray.get(randStart); //later lah zbrišeš Kvadrat kv pa daš sam rectangleArray.get(i) za hitrejš delovanje
		if (startCreated == false && sq.isfree){ //createStart
			sq.isStart = true;
			sq.isfree = false;
			sq.setColor(10, 255, 150);
			startCreated = true;
			startSq = sq;
			sq.draw();
		}if (startCreated == true && sq.isfree){ //createEnd
			sq.isEnd = true;
			//kv.isfree=false; ->konc more bit frej d ga lah poišèe
			sq.setColor(255, 255, 150);
			endSq = sq;
			sq.draw();
			break;
		}i++;
	}
}

void Grid::initFixedStartEnd(){
	int start = SQ_NUM + 1;
	Square sq = squareArray.get(start);
	sq.isStart = true;
	sq.isfree = false;
	sq.setColor(10, 255, 150);
	startSq = sq;
	sq.draw();


	//end
	int end = SQ_NUM*SQ_NUM - SQ_NUM - 2;
	Square sqEnd = squareArray.get(end);
	sqEnd.isEnd = true;
	//kv.isfree=false; ->konc more bit frej d ga lah poišèe
	sqEnd.setColor(255, 255, 150);
	endSq = sqEnd;
	sqEnd.draw();


}

double Grid::heuristic(Square node){ //more here: http://theory.stanford.edu/~amitp/GameProgramming/Heuristics.html
	double euclid = Math.sqrt(Math.pow((node.x - endSq.x), 2) + Math.pow((node.y - endSq.y), 2));






	int D = 1;
	double D2 = Math.sqrt(2); //this is called the octile distance.
	double dx = Math.abs(node.x - endSq.x);
	double dy = Math.abs(node.y - endSq.y);

	//return D * (dx + dy) + (D2 - 2 * D) * Math.min(dx, dy); // vrne Manhattansko razdaljo od noda do cilja

	return euclid;





}

void Grid::setHeuristic(){


	StdDraw.setPenColor(StdDraw.BLACK);
	for (Square sq : squareArray){
		if (sq.isfree || sq.isStart){
			sq.H = heuristic(sq); //da lepo prikaže 10, 20, 2*heig... je velikost kvadratka


			if (squareArray.size()<256){
				Miscellaneous.screenText(sq.x + squareSize / 8, sq.y + squareSize / 3, squareSize / 8, "H:" + (int)sq.H);
			}
		}
	}
}

*/