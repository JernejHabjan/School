function [A] = dh_joint(parameters)
    % INPUT: DH parameters for joint (a, alpha, d, theta)
    % OUTPUT: 4x4 homogeneous transformation matrix
    A = zeros(4, 4);
    A(1, 1) = cos(parameters(4));
    A(2, 1) = sin(parameters(4));
    A(1, 2) = -sin(parameters(4)) * cos(parameters(2));
    A(2, 2) = cos(parameters(4)) * cos(parameters(2));
    A(3, 2) = sin(parameters(2));
    A(1, 3) = sin(parameters(4)) * sin(parameters(2));
    A(2, 3) = -cos(parameters(4)) * sin(parameters(2));
    A(3, 3) = cos(parameters(2));
    A(1, 4) = parameters(1) * cos(parameters(4));
    A(2, 4) = parameters(1) * sin(parameters(4));
    A(3, 4) = parameters(3);
    A(4, 4) = 1;
end