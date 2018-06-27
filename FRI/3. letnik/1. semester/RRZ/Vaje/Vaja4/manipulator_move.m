function [manipulator] = manipulator_move(manipulator, state, varargin)
%MANIPULATOR_MOVE moves the state of the manipulator considering the boundaries of joints.
%   manipulator = MANIPULATOR_MOVE(manipulator, state)
%
%   manipulator   Manipulator structure
%   state New state of the manipulator (1 x N joint states)
%
%   manipulator   Updated manipulator structure
%
%   This function moves the joint status of the manipulator.
%
%   See also MANIPULATOR_ANIMATE, MANIPULATOR_RETRIEVE.

j = manipulator.dof;

if (length(state) < j)
	return;
end;

blocking = true;

for i = 1:2:length(varargin)
    switch lower(varargin{i})
        case 'blocking'
            blocking = varargin{i+1};
        otherwise
            error(['Unknown switch ', varargin{i},'!']) ;
    end
end

for i = 1:j
    if manipulator.types(i, 5)
        manipulator.param(i, manipulator.types(i, 4)) = min(max(manipulator.types(i, 2), state(i)), manipulator.types(i, 3));
    end;
end;

if isfield(manipulator, 'remote')
    vals = zeros(j, 2);
    vals(:, 1) = 1:j;
    for i = 1:j
        if manipulator.types(i, 5)
            vals(i, 2) = manipulator.param(i, manipulator.types(i, 4));
        end
    end
    vals = vals(logical(manipulator.types(:, 5)), :);
    positions = sprintf('&j%d=%.5f', vals');
    if blocking
        urlread(sprintf('%s/move?speed=1&blocking=1%s', manipulator.remote.url, positions));
    else
        urlread(sprintf('%s/move?speed=1%s', manipulator.remote.url, positions));
    end;
    disp('Done');
end

