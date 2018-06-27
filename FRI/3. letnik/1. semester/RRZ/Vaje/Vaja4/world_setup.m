function [world] = world_setup(figid, span)
%WORLD_SETUP sets up a world structure.
%   world = WORLD_SETUP(figid, span)
%   
%   figid The id of a figure used to draw the world
%   span  Size of the world view.
%
%   world A world structure
%
%   See also WORLD_DRAW.

world = struct();
world.figid = figid;
world.points = [];
world.span = span;

sfigure(world.figid);
clf;
axis([-world.span, world.span, -world.span, world.span, 0, world.span]);
grid on;



