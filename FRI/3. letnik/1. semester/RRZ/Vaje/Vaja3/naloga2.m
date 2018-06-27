%A

if (false)
    % DELA SAM FUL POÈAS
    point = [3, 3, 4]';
    startingPoint = [0, 0, 0, 1]';
    
    najmanjsaRazdalja = 9999999;
    najmanjsiI = 0;
    najmanjsiJ = 0;
    najmanjsiK = 0;
    for i = 1:90
       for j = 1:90
           for k = 1:90
                B = antropomorfic_manipulator([i, j, k]);

                PolozajZadnjegaSklepa = B(:,:,3) * startingPoint;
                razdalja = sqrt((PolozajZadnjegaSklepa(1) - point(1))^2 +  (PolozajZadnjegaSklepa(2) - point(2))^2 +(PolozajZadnjegaSklepa(3) - point(3))^2);
                if (razdalja < najmanjsaRazdalja)
                    najmanjsaRazdalja = razdalja;
                    najmanjsiI = i;
                    najmanjsiJ = j;
                    najmanjsiK = k;
                end
           end
            
       end
    end 
    optimalParams = [najmanjsiI, najmanjsiJ, najmanjsiK];
    optimalParams
    
end

%%
%B

if(false)
    
    disp('This is a demo of stohastic IK solver for antropomorphic manipulator.');

    % Setup world: world structure defines the bounds of the world and
    % points in the world used for navigation
    world = world_setup(1, 200);

    % We will use two points in this sample.
    world.points = [pi/2, pi/2 + 0.5, 1; 2, 2 ,4]';

    % Setup script for antropomorphic manipulator.
    setup_antropomorphic;
    %setup_stanford;

    manipulator = manipulator_create('localhost:8080');
    manipulator_draw(world, manipulator);
    
    
    
    
    % Draw world
    manipulator_draw(world, manipulator);

    % Solve the IK problem for a checkpoint
    % state is a vector of joint states that bring the point of
    % the manipulator to the desired position
    state = manipulator_solve(manipulator, world.points(:, 1)');
    % Transition to the new manipulator state
    manipulator = manipulator_animate(world, manipulator, state, 10);

    % Now do the same for the second point
    state = manipulator_solve(manipulator, world.points(:, 2)');
    % Transition to the new manipulator state
    manipulator = manipulator_animate(world, manipulator, state, 10);

    
    
    %{
    manipulator_solve parameters
    manipulator, position, varargin
    iterations = 40;
    distance = 1;
    %}
    %result = manipulator_solve(B);
    %manipulator_animate(result);
end
    

%%
%C
%HANOI TOWER
if(false)
    
    disp('This is a demo of stohastic IK solver for antropomorphic manipulator.');

    % Setup world: world structure defines the bounds of the world and
    % points in the world used for navigation
    world = world_setup(1, 200);

    % We will use two points in this sample.
    world.points = [
        100,-100,100; 
        100, 0, 100; 
        100, 100, 100; 
        100, 0, 150]';
    
    
    % Setup script for antropomorphic manipulator.
    %setup_antropomorphic;
    %setup_stanford;

    manipulator = manipulator_create('localhost:8080');
    manipulator_draw(world, manipulator);
    
    
    % Draw world
    manipulator_draw(world, manipulator);
    
     A =world.points(:,1);
     B = world.points(:,2);
     C = world.points(:,3);
     MIDDLE = world.points(:,4);
     
     
     %animate to moddle point
      state = manipulator_solve(manipulator, MIDDLE');
      manipulator = manipulator_animate(world, manipulator, state, 10);

     
    towers_of_hanoi(5,A,C,B,MIDDLE, world, manipulator)

    
end
%%
%D
% razširjen C

%%
clear

%%
% FUNCTIONS

%C

function towers_of_hanoi(n,A,C,B,MIDDLE, world, manipulator)
  if (n~=0)
      towers_of_hanoi(n-1,A,B,C, MIDDLE,world, manipulator);
      
      disp(sprintf('Move disk %d from tower [%d %d %d] to tower [%d %d %d]',[n A(1) A(2) A(3) C(1) C(2) C(3)]));
      state = manipulator_solve(manipulator, A');
      manipulator = manipulator_animate(world, manipulator, state, 10);
      state = manipulator_solve(manipulator, C');
      manipulator = manipulator_animate(world, manipulator, state, 10);
      %animate to middle point
      state = manipulator_solve(manipulator, MIDDLE');
      manipulator = manipulator_animate(world, manipulator, state, 10);
  
      
      towers_of_hanoi(n-1,B,C,A,MIDDLE, world, manipulator);
  end
end

