function [manipulator] = manipulator_create(setup)
%MANIPULATOR_CREATE creates an manipulator structure from a setup structure.
%   manipulator = MANIPULATOR_CREATE(setup)
%
%   setup  The setup structure or hostname for remote manipulator
%
%   manipulator    The manipulator structure
%

if ischar(setup)

    url = sprintf('http://%s/api/manipulator', setup);

    data = parse_json(urlread(sprintf('%s/describe', url)));

    data = data{1};

    setup = struct();
    setup.origin = [0, 0, 0];

    for j = 1:length(data.joints)
        jd = data.joints{j};

        joint = struct('type', lower(jd.type), 'parameters', [jd.a, jd.alpha, jd.d, jd.theta], 'min', jd.min, 'max', jd.max);

        setup.(sprintf('j%d', j)) = joint;

    end;

    manipulator = manipulator_create(setup);

    manipulator.remote = struct('url', url, 'name', data.name, 'version', data.version);

    state = manipulator_retrieve(manipulator);

    if isfield(manipulator, 'remote')
        for i = 1:j
            if manipulator.types(i, 5)
                manipulator.param(i, manipulator.types(i, 4)) = state(i);
            end;
        end
    end

else

    for i = 1:20
	    if ~isfield(setup, sprintf('j%d', i))
		    break;
	    end
    end

    manipulator = struct();

    manipulator.dof = i - 1;
    manipulator.param = zeros(manipulator.dof, 4);
    manipulator.types = zeros(manipulator.dof, 5);

    manipulator.origin = setup.origin;

    for i = 1:manipulator.dof
        joint = setup.(sprintf('j%d', i));
        p = joint.parameters;
        switch lower(joint.type)
            case 'translation'
                low = get_field(joint, 1, 'min');
                high = get_field(joint, 5, 'max');
                manipulator.param(i, :) = p;
                manipulator.types(i, :) = [1, low, high, 3, 1];
            case 'rotation'
                low = get_field(joint, -pi/2, 'min');
                high = get_field(joint, pi/2, 'max');
                manipulator.param(i, :) = p;
                manipulator.types(i, :) = [0, low, high, 4, 1];
            case 'gripper'
                low = get_field(joint, 0, 'min');
                high = get_field(joint, 1, 'max');
                manipulator.param(i, :) = p;
                manipulator.types(i, :) = [2, low, high, 4, 1];
            case 'fixed'
                manipulator.param(i, :) = p;
                manipulator.types(i, :) = [3, 0, 0, 0, 0];
        end;
    end

end

end

function value = get_field(struct, default, varargin)
if (isfield(struct, varargin{:}))
    value = getfield(struct, varargin{:});
else
    value = default;
end
end


