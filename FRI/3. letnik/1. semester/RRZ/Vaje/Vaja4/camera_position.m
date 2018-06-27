function [homography, rotation, translation] = camera_position(camera)
%CAMERA_POSITION retrieves the camera position information.
%   [homography, rotation, translation] = CAMERA_POSITION(camera)
%   
%   camera    The camera structure
%
%   homography    The working plane homography matrix
%   rotation      Camera rotation matrix
%   translation   Camera translation vector
%

data = parse_json(urlread(sprintf('%s/position', camera.url)));

data = data{1};

rotation = [data.rotation{1}{1}, data.rotation{1}{2}, data.rotation{1}{3}; ...
    data.rotation{2}{1}, data.rotation{2}{2}, data.rotation{2}{3}; ...
    data.rotation{3}{1}, data.rotation{3}{2}, data.rotation{3}{3}];

translation = [data.translation{1}{1}; data.translation{2}{1}; data.translation{3}{1}];

transform = [rotation, translation];

projective = camera.intrinsics * transform;
homography = projective(:, [1, 2, 4]);
homography = homography ./ homography (3, 3);


