function image = camera_image(camera)
%CAMERA_IMAGE retrieves the current image from the camera.
%   image = CAMERA_IMAGE(camera)
%   
%   camera    The camera structure
%
%   image     The color image matrix
%

image = imread(sprintf('%s/image', camera.url));