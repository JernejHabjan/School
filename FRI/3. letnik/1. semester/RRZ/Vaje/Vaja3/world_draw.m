function [] = world_draw(world, P)
%WORLD_DRAW draws the world and an arm.
%   [] = WORLD_DRAW(world, positions)
%   
%   world     The world structure.
%   positions Positions of arm joints.
%
%   See also WORLD_SETUP.

initialize = ~ishandle(world.figid);

sfigure(world.figid);
if is_octave() || initialize
    clf;
    axis([-world.span, world.span, -world.span, world.span, 0, world.span]);   
else
    cla;
end;
grid on;
hold on;

plot3(P(1, :), P(2, :), P(3, :), 'color', [0,0,0], 'linewidth', 4);
scatter3(P(1, :), P(2, :), P(3, :), 40, [0.5 0.5 0.5]);

if ~isempty(world.points)
    scatter3(world.points(1, :), world.points(2, :), world.points(3, :), 15, [0.0 1 0.0]);
end;

show_system(eye(4));

hold off;

drawnow;
