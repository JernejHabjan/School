#include "Game.hpp"

#undef main
int main(int argc, char **argv){
	
	std::string file_path = argv[0];




	Game game;
	game.init();
	do{
		game.defeated = false;
		game.play();
		if (game.victory){ //èe je zmaga naloadi next level
			game.nextLevel();
			game.victory = false;
		}

	} while (game.running && game.level < 4);
	return 0;
}

