
addresRoka = 'localhost:8080';
%addresRoka = 'roka2.fri1.uni-lj.si';
%%
%B
if(false)
    
    disp('Interpolacija med 4 toèkami');
    world = world_setup(1, 200);
    world.points = [100,100,100; 150,100,100; 150,150,100;100,150,100]';
    manipulator = manipulator_create(addresRoka);
    manipulator_draw(world, manipulator);
    for i = 1:1000 % num of iterations
        for idx = 1:4
            state = manipulator_solve(manipulator, world.points(:, idx)');
            manipulator = manipulator_animate(world, manipulator, state, 10);
        end
    end
    
end

%%
%C
if(false)
    disp('Risanje èrke A z direktno kinematiko');

    world = world_setup(1, 200);
    manipulator = manipulator_create(addresRoka);
    manipulator_draw(world, manipulator);
  
    Koti = [
         0,   pi/2, -pi/2, 0,     pi/2, 0, 0; %initial
         0.5, pi/2, -pi/2, 0,     pi/2, 0, 0; %obrni za prvo crto
         0.5, pi/2, -pi/2, -pi/2, pi/2, 0, 0; %narisi eno crto
         0.5, pi/2, -pi/2, 0,     pi/2, 0, 0; %nazaj
        -0.5, pi/2, -pi/2, 0,     pi/2, 0, 0; %obrni za drugo crto
        -0.5, pi/2, -pi/2, -pi/2, pi/2, 0, 0; %narisi za drugo crto
        -0.5, pi/2, -pi/2, 0,     pi/2, 0, 0; %nazaj
        -0.5, 0.8,  -pi/2, 0,     pi/2, 0, 0; %postavi se za precko
         0.3, 0.8,  -pi/2, 0,     pi/2, 0, 0; %naredi crto
         0,   1.5,  -pi/2, 0,     pi/2, 0, 0; %initial
    ];
     SizeKoti = size(Koti);
     
    for i = 1:2 % num of ite
        
        for idx = 1:SizeKoti(1)
            state = Koti(idx,:);
            
            manipulator = manipulator_animate(world, manipulator, state, 10);
        end
    end
end

%%
%D

if(true)
    disp('Risanje èrke A z inverzno kinematiko');
    flumastrHeight = 100;
    safeAbove = flumastrHeight + 5;
    dolPress = 2;
    
    world = world_setup(1, 200);
    world.points = [
        100,0,safeAbove;   %initial
        100,-30,flumastrHeight + 2; %dol za zacet risat 
        150,0,flumastrHeight - dolPress; %potegn crto do sredine špice
        100,30,flumastrHeight; %potegn crto do druzga konca spodnjega A
        100,30,safeAbove; %udzign
        130,25,safeAbove; %prestav na point kjer se zaène desnkonc èrte
        130,25,flumastrHeight; %postav dol
        130,-25,flumastrHeight - dolPress; %potegn èrto
        130,-25,safeAbove; %udzign
        100,0,safeAbove; %prestav na initial
        ]';
    manipulator = manipulator_create(addresRoka);
    manipulator_draw(world, manipulator);
    SizeWPoints = size(world.points);
    %UNGRIP
        manipulator.param(7,4) = 0.0;
        state = manipulator_calculate(manipulator.param);
        manipulator = manipulator_animate(world, manipulator, state, 10);
    for i = 1:1 % num of iterations
        for idx = 1:SizeWPoints(2)
            if(idx == 2)
                state = manipulator_solve(manipulator, world.points(:, idx)');
                manipulator = manipulator_animate(world, manipulator, state, 10);
                disp('caka te za flumastr')
                pause
                %GRIP
                manipulator.param(7,4) = 0.7;
                state = manipulator_calculate(manipulator.param);
                manipulator = manipulator_animate(world, manipulator, state, 10);
                %pause anim
                state = manipulator_solve(manipulator, world.points(:, idx)');
                manipulator = manipulator_animate(world, manipulator, state, 10);
            end
            state = manipulator_solve(manipulator, world.points(:, idx)');
            manipulator = manipulator_animate(world, manipulator, state, 10);
            
        end
    end
    %UNGRIP
    manipulator.param(7,4) = 0.0;
    state = manipulator_calculate(manipulator.param);
    manipulator = manipulator_animate(world, manipulator, state, 10);
    
end

%%
%E
%RISANJE S SVINÈNIKOM

%%
%F

if(false)
    world = world_setup(1, 200);
    manipulator = manipulator_create(addresRoka);
    manipulator_draw(world, manipulator);
    
    cubeHeight = 23;
    odlagalisceSafe = [150,50, 150];
    odlagalisce = [150, 50, 30];
    
    
    safeAboveCube = [150,-50, 150];
    vzemalisce = [150,-50, 25];
    for i = 1:5 % pet kock
         %UNGRIP
         
         newState = manipulator.param
         %newState(6,4) = 0;
        %newState(7,4) = 0;

        
        %manipulator = manipulator_animate(world, manipulator, newState, 10);
        
    
        
        state = manipulator_solve(manipulator, safeAboveCube); %safe position above cube
        manipulator = manipulator_animate(world, manipulator, state, 10);
       
        
        
         % here we placed cube now we can order arm to pick it up
        
        state = manipulator_solve(manipulator, vzemalisce); %move to cube
        manipulator = manipulator_animate(world, manipulator, state, 10);
       
        
        pause
        %GRIP
        manipulator.param(7,4) = 0.8;
        state = manipulator_calculate(manipulator.param);
        manipulator = manipulator_animate(world, manipulator, state, 10);
        % PAUSE GRIP ANIMATION FROM MOVING IT AWAY
        state = manipulator_solve(manipulator, vzemalisce); %move to cube
        manipulator = manipulator_animate(world, manipulator, state, 10);
       
        %move to safe 
        state = manipulator_solve(manipulator, safeAboveCube); 
        manipulator = manipulator_animate(world, manipulator, state, 10);
        
        %move to odlagalisce
        
        
        
        state = manipulator_solve(manipulator, odlagalisce); %move to odlagalisce
        manipulator = manipulator_animate(world, manipulator, state, 10);
       
        %UNGRIP
        manipulator.param(7,4) = 0;
        state = manipulator_calculate(manipulator.param);
        manipulator = manipulator_animate(world, manipulator, state, 10);
        %pause unfrip animation
        state = manipulator_solve(manipulator, odlagalisce); 
        manipulator = manipulator_animate(world, manipulator, state, 10);
       
        
        %move up arm
        state = manipulator_solve(manipulator, odlagalisceSafe);
        manipulator = manipulator_animate(world, manipulator, state, 10);
        
        odlagalisce(3) =  odlagalisce(3) + 20 % increase cube stack height
        
    end
end

