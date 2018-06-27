function [positions] = manipulator_calculate(parameters, varargin)
%MANIPULATOR_CALCULATE calculates forward kinematic for multiple DH parameter sets.
%   positions = MANIPULATOR_CALCULATE(parameters)
%   positions = MANIPULATOR_CALCULATE(parameters, end_joint)
%
%   parameters New state of the manipulator (no.joints x 4 x N)
%   end_joint  If set, defines the end joint (instead of the size of
%              parameters matrix.
%
%   positions  Calcuated positions in the end joint
%              Size is 4 x N, but the last row is just ones.
%
%   See also MANIPULATOR_SOLVE.

[j, p, n] = size(parameters);

% a alpha d theta

if nargin > 1
	j = max(1, min(j, varargin{1}));
end

S = reshape(repmat(eye(4), 1, n), 4, 4, n);

V = zeros(4, 4, n);
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
end

positions = multiprod(S, [0 0 0 1]');


