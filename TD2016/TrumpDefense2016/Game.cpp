#include "Game.hpp"


Game::Game(){

}


void Game::nextLevel(){
	std::cout << "NEXT LEVEL LOADED!" << std::endl;

	level++;

	switch (level){
	case 2:
		enemyArray.push_back("Builder");
		enemyArray.push_back("MexicanBalooner");
		break;

	case 3:
		enemyArray.push_back("MexicanMafia");
		enemyArray.push_back("MexicanBaloon");
		break;
	}

}

void Game::init(){
	//HIDE CONSOLE:
	//FreeConsole();

	SDL_DisplayMode current;

	SDL_Init(SDL_INIT_VIDEO);

	// Get current display mode of all displays.
	for (int i = 0; i < SDL_GetNumVideoDisplays(); ++i) {

		int should_be_zero = SDL_GetCurrentDisplayMode(i, &current);

		if (should_be_zero != 0)
			// In case of error...
			SDL_Log("Could not get display mode for video display #%d: %s", i, SDL_GetError());

		else {
			// On success, print the current display mode.
			SDL_Log("Display #%d: current display mode is %dx%dpx @ %dhz.", i, current.w, current.h, current.refresh_rate);
			//and set current SCREEN_W and SCREEN_H
			SCREEN_W = current.w;
			SCREEN_H = current.h;
		}
	}



	screen = SDL_CreateWindow("Trump Defense 2016", 0, 0, SCREEN_W, SCREEN_H, SDL_WINDOW_OPENGL);
	assert(screen != nullptr && "SDL_Window not created!");
	enemyArray.push_back("MexicanBanjo");

}

void Game::play(){



	Shader shader;
	Sound sound;
	Matrix matrix;
	Grid grid;





	assert(screen != nullptr && "SDL_Window not created!");
	Camera cam(screen);
	SDL_GLContext context = SDL_GL_CreateContext(screen);
	assert(context != nullptr && "SDL_Window not created!");

	glewExperimental = true; // Needed for core profile
	GLenum glewinit = glewInit();
	assert(!glewinit && "Glew not okay!");
	assert(GLEW_VERSION_2_1 && "Glew version lesser than 2.1 -error ! ");


	GLenum error = glGetError();
	assert(error == GL_NO_ERROR && "GL ERROR");

	std::cout << "***********************************" << std::endl;

	std::cout << "GPU Vendor: " << glGetString(GL_VENDOR) << std::endl;
	std::cout << "OpenGL Version: " << glGetString(GL_VERSION) << std::endl;
	std::cout << "Renderer: " << glGetString(GL_RENDERER) << std::endl;

	int max; glGetIntegerv(GL_MAX_TEXTURE_IMAGE_UNITS, &max);
	std::cout << "Max Texture Units: " << max << std::endl;

	glGetIntegerv(GL_MAX_UNIFORM_LOCATIONS, &max);
	std::cout << "Max Uniforms: " << max << std::endl;

	glGetIntegerv(GL_MAX_VERTEX_ATTRIBS, &max);
	std::cout << "Max Vertex Attributes: " << max << std::endl;
	std::cout << "***********************************" << std::endl;


	running = true;
	Uint32 startTime;
	Uint32 gameStartTime;
	SDL_Event event;

	Resources resources(SCREEN_W, SCREEN_H, &sound, &cam, &matrix, enemyArray);
	resources.R_initStart(screen, grid, level);

	gameStartTime = SDL_GetTicks();
	while (running){


		startTime = SDL_GetTicks();
		while (SDL_PollEvent(&event)){
			switch (event.type){
			case SDL_QUIT:
				running = false;
				break;

			case SDL_KEYDOWN:
				InputManager::manageEvent(&event.key, &sound, running, victory, resources, grid, level, gameStartTime);
				break;

			case SDL_MOUSEBUTTONDOWN:
				InputManager::manageMousePressEvent(event.button, SCREEN_W, SCREEN_H, &cam, matrix, grid, &sound);
				break;
			case SDL_MOUSEWHEEL:
				InputManager::onMouseWheelScroll(event, &cam);
				break;
			}
		}


		resources.R_draw(grid, level, gameStartTime);//tuki se klièe

		if (resources.life <= 0){
			for (int i = 0; i < 50; i++){//zbrišeš vse druge sfx
				if (i != 21)
					sound.fadeOut(i, 0);
			}
			sound.playSFX(21, 21, 0); //DEFEAT
			SDL_Delay(5000);
			defeated = true;
			defeatedTimes++;
			return;
			std::cout << "--ENDGAME--" << std::endl;
		}
		if (victory){
			for (int i = 0; i < 50; i++){ //zbrišeš vse druge sfx
				if( i!=20)
					sound.fadeOut(i, 0);
			}
			sound.playSFX(20, 20, 0); //CHEER
			SDL_Delay(5000);
			return;
		}


	

		SDL_GL_SwapWindow(screen); //show everything we draw 
		if (1000 / 60 > (SDL_GetTicks() - startTime))
			SDL_Delay(1000 / 60 - (SDL_GetTicks() - startTime));

	}


	sound.free();

	SDL_GL_DeleteContext(context);
	SDL_DestroyWindow(screen);
	SDL_Quit();
}

Game::~Game()
{
}
