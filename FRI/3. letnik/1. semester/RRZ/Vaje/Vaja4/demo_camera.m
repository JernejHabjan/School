camera = camera_create('localhost:8080'); % Create camera handle
image = camera_image(camera); % Retrieve current image
homography = camera_position(camera); % Retrieve current homoraphy

figure(1);
imshow(image); % Display image
hold on;
% TODO: draw points here
hold off;



