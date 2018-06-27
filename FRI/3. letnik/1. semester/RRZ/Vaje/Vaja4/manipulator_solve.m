function [state] = manipulator_solve(manipulator, position, varargin)
%MANIPULATOR_SOLVE a simple inverse kinematic solver.
%   state = MANIPULATOR_SOLVE(manipulator, position)
%   state = MANIPULATOR_SOLVE(manipulator, position, iterations)
%   state = MANIPULATOR_SOLVE(manipulator, position, iterations, distance)
%
%   manipulator Manipulator structure
%   position   Target position of the final joint
%   iterations Number of iterations of optimization (default 20)
%   distance   Maximum distance from the position to be considered as a
%              solution (default 1)
%
%   state      State of the manipulator (1 x N joint states)
%
%   This function tries to solve IK problem using coortindate descend.
%
%   See also MANIPULATOR_CALCULATE, MANIPULATOR_INTERACTIVE.

iterations = 40;
distance = 1;

if nargin > 2
   iterations = varargin{1};
end;

if nargin > 3
   distance = varargin{2};
end;

state = zeros(1, manipulator.dof);

for i = 1:manipulator.dof
    if manipulator.types(i, 5)
        state(i) = manipulator.param(i, manipulator.types(i, 4));
    end;
end;

for k = 1:iterations

    for i = 1:manipulator.dof

        state = ik_optimize_joint(manipulator, position, state, i, 600);

    end

    parameters = manipulator.param;

	for i = 1:manipulator.dof
        if manipulator.types(i, 5)
            parameters(i, manipulator.types(i, 4), :) = state(i);
        end;
	end;

    current = squeeze(calculate_positions(parameters, manipulator.origin));
    proximity = sqrt(sum( (current - position') .^ 2));

    if proximity < distance
       break;
    end
end

end

function [state] = ik_optimize_joint(manipulator, position, start, joint, N)

    state = start;

    if manipulator.types(joint, 1) > 1
        return;
    end

    nj = manipulator.dof;

    parameters = reshape(repmat(manipulator.param, 1, N), nj, 4, N);

    samples = linspace(manipulator.types(joint, 2), manipulator.types(joint, 3), N);

	for i = 1:nj
        if manipulator.types(i, 5)
            parameters(i, manipulator.types(i, 4), :) = start(i);
        end
	end;

    parameters(joint, manipulator.types(joint, 4), :) = samples;

    positions = calculate_positions(parameters, manipulator.origin);

    proximity = sqrt(sum( (bsxfun(@minus, squeeze(positions), position')) .^ 2));

    [best_v, best_i] = min(proximity);

    state(joint) = samples(best_i);

end

function [positions] = calculate_positions(parameters, origin)

	[j, a, vn] = size(parameters);

	S = reshape(repmat(eye(4), 1, vn), 4, 4, vn);

	V = zeros(4, 4, vn);
	V(4, 4, :) = 1;
	for i = 1:j

		V(1, 1, :) = cos(parameters(i, 4, :));
		V(2, 1, :) = sin(parameters(i, 4, :));

		V(1, 2, :) = -sin(parameters(i, 4, :)) .* cos(parameters(i, 2, :));
		V(2, 2, :) = cos(parameters(i, 4, :)) .* cos(parameters(i, 2, :));
		V(3, 2, :) = sin(parameters(i, 2, :));

		V(1, 3, :) = sin(parameters(i, 4, :)) .* sin(parameters(i, 2, :));
		V(2, 3, :) = -cos(parameters(i, 4, :)) .* sin(parameters(i, 2, :));
		V(3, 3, :) = cos(parameters(i, 2, :));

		V(1, 4, :) = parameters(i, 1, :) .* cos(parameters(i, 4, :));
		V(2, 4, :) = parameters(i, 1, :) .* sin(parameters(i, 4, :));
		V(3, 4, :) = parameters(i, 3, :);

		S = multiprod(S, V);
	end;

	positions = multiprod(S, [origin 1]');

    positions = positions(1:3, :, :);

end
