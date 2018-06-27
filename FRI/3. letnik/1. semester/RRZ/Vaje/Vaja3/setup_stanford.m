% sets up Stanford manipulator
% TODO
setup.origin = [0, 0, 0];
setup.j1.type = 'rotation';
setup.j1.parameters = [0 pi/2 50 0];
setup.j1.min = 0;
setup.j1.max = 2 * pi;

setup.j2.type = 'rotation';
setup.j2.parameters = [0 pi/2 100 0];
setup.j2.min = 1;
setup.j2.max = 5;

setup.j3.type = 'translation';
setup.j3.parameters = [0 0 30 0];
setup.j3.min = 20;
setup.j3.max = 150;

manipulator = manipulator_create(setup);
