function [] = manipulator_draw(world, manipulator)
%MANIPULATOR_DRAW draws the manipulator in a world.
%   MANIPULATOR_ANIMATE(world, manipulator)
%
%   world World structure
%   manipulator   Manipulator structure
%
%   This function draws the world and the manipulator within it.
%
%   See also MANIPULATOR_ANIMATE, WORLD_DRAW.

j = manipulator.dof;

P = zeros(4, j+1);
P(:, 1) = [manipulator.origin 1];

for i = 1:j
	P(:, i+1) = manipulator_calculate(manipulator.param, i);
end;

world_draw(world, P);

