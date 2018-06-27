function [manipulator] = manipulator_animate(world, manipulator, state, steps)
%MANIPULATOR_ANIMATE animates the transition of the manipulator to a new state.
%   arn = MANIPULATOR_ANIMATE(manipulator, state, steps)
%
%   world World structure
%   manipulator   Manipulator structure
%   state New state of the manipulator (1 x N joint states)
%   steps Number of steps in animation (or refresh rate in case of remote manipulator)
%
%   manipulator   Updated manipulator structure
%
%   This function animates the transition of an manipulator from the current state to
%   a new state. The animation is split in a given number of steps. This
%   function serves only as a visualization tool.
%
%   See also MANIPULATOR_UPDATE, MANIPULATOR_DRAW, WORLD_DRAW.

j = manipulator.dof;
n = steps;

if (length(state) < j)
	return;
end;

if isfield(manipulator, 'remote')

    manipulator = manipulator_move(manipulator, state, 'Blocking', false);
    j = manipulator.dof;

    P = zeros(4, j+1);
    P(:, 1) = [manipulator.origin 1];

    while true
        pause(0.5);
        [current_state, manipulator] = manipulator_retrieve(manipulator);
        for i = 1:j
            P(:, i+1) = manipulator_calculate(manipulator.param, i);
        end;
        world_draw(world, P);

        diff = abs(current_state(:) - state(:)) .* manipulator.types(:, 5);
        diff(manipulator.types(:, 1) == 0 & diff > pi) = diff(manipulator.types(:, 1) == 0 & diff > pi) - 2*pi;
        if all(diff < 0.1)
            break;
        end;

    end;

else

    parameters = reshape(repmat(manipulator.param, 1, steps), j, 4, steps);

    P = zeros(4, j+1, steps);
    P(:, 1, :) = repmat([manipulator.origin 1]', 1, steps) ;

    for i = 1:j
        state(i) = min(max(manipulator.types(i, 2), state(i)), manipulator.types(i, 3));
        if manipulator.types(i, 1) ~= 0 % Not a rotational joint
            parameters(i, 3, :) = linspace(manipulator.param(i, 3), state(i), steps);
        else
            if manipulator.param(i, 4) - state(i) < -pi
                parameters(i, 4, :) = linspace(manipulator.param(i, 4), state(i) - 2 * pi, steps);
            elseif manipulator.param(i, 4) - state(i) > pi
                parameters(i, 4, :) = linspace(manipulator.param(i, 4) - 2*pi, state(i), steps);
            else
                parameters(i, 4, :) = linspace(manipulator.param(i, 4), state(i), steps);
            end;
        end;
    end;

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

        P(:, i+1, :) = multiprod(S, [manipulator.origin 1]');
    end;

    for step = 1:steps
        world_draw(world, P(:, :, step));
        pause(0.2);
    end;

    manipulator = manipulator_move(manipulator, state);

end;
