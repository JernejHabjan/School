function [manipulator] = manipulator_interactive(world, manipulator)
%MANIPULATOR_INTERACTIVE allows you to manipulate manipulator state in interactive manner.
%   manipulator = MANIPULATOR_INTERACTIVE(world, manipulator)
%
%   world World structure
%   manipulator   Manipulator structure
%
%   manipulator   Updated manipulator structure
%
%   This function allows you to manipulate manipulator state using keyboard. The
%   values of the first joint are changed using keys Q and A, for the
%   second one W and S are used and for the third joint E and D.
%   Press P to exit interactive mode.
%
%   See also MANIPULATOR_ANIMATE, MANIPULATOR_UPDATE.

run = 1;

manipulator_draw(world, manipulator);

state = manipulator_retrieve(manipulator);

while run

try
    k = waitforbuttonpress;

    if (k == 1)
        c = get(world.figid, 'CurrentCharacter');
        if c == 'p'
            run = 0;
            continue;
        elseif c == 'q'
            state(1) = state(1) - 0.1;
        elseif c == 'a'
            state(1) = state(1) + 0.1;
        elseif c == 'w'
            state(2) = state(2) - 0.1;
        elseif c == 's'
            state(2) = state(2) + 0.1;
        elseif c == 'e'
            state(3) = state(3) - 0.1;
        elseif c == 'd'
            state(3) = state(3) + 0.1;
        else
            continue
        end

        set(world.figid, 'CurrentCharacter', '?');

        manipulator = manipulator_move(manipulator, state);
        manipulator_draw(world, manipulator);

    end;

catch e
    display(e.message);
    run = 0;
end;

end;

