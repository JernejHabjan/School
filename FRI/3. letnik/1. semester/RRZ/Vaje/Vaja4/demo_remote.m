% A demonstration program for the sample robot manipulator environment

disp('This is a demo of stohastic IK solver for a remote manipulator.');

% Setup world: world structure defines the bounds of the world and
% points in the world used for navigation
world = world_setup(1, 200);

% We will use two points in this sample.
world.points = [100, -180, 100; 100, 180, 100]';

manipulator = manipulator_create('localhost');

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
