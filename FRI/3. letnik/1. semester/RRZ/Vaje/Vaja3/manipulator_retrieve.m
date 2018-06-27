function [state, manipulator] = manipulator_retrieve(manipulator)
%MANIPULATOR_RETRIEVE returns the state of the manipulator.
%   state = MANIPULATOR_RETRIEVE(manipulator)
%
%   manipulator   Manipulator structure
%
%   state The state of the manipulator (1 x N joint states)
%
%   This function returns the joint status of the manipulator as a vector.
%
%   See also MANIPULATOR_ANIMATE, MANIPULATOR_UPDATE.
j = manipulator.dof;

if isfield(manipulator, 'remote')

    data = parse_json(urlread(sprintf('%s/state', manipulator.remote.url)));
    data = data{1};

    for i = 1:j
        if manipulator.types(i, 5)
            manipulator.param(i, manipulator.types(i, 4)) = data.joints{i}.position;
        end
    end
end

state = zeros(j, 1);

for i = 1:j
    if manipulator.types(i, 5)
        state(i) = manipulator.param(i, manipulator.types(i, 4));
    end
end;


