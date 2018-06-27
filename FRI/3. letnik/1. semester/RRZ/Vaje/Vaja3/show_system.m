function [] = show_system(M)
%SHOW_SYSTEM plots a coordinate system of a transformation on the current
%structure.

hold on;
plot3([M(1, 4), M(1, 4) + M(1, 1)], [M(2, 4), M(2, 4) + M(2, 1)], [M(3, 4), M(3, 4) + M(3, 1)], 'color', [1 0 0]);
plot3([M(1, 4), M(1, 4) + M(1, 2)], [M(2, 4), M(2, 4) + M(2, 2)], [M(3, 4), M(3, 4) + M(3, 2)], 'color', [0 1 0]);
plot3([M(1, 4), M(1, 4) + M(1, 3)], [M(2, 4), M(2, 4) + M(2, 3)], [M(3, 4), M(3, 4) + M(3, 3)], 'color', [0 0 1]);
hold off;




