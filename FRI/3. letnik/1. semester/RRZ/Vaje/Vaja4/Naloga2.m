I = imread('camera2.jpg');

Iorg = I;
sizeI = size(I);

figure(1); 
subplot(2,3,1);
imshow(I); 
title('org slika s toèkami');


[X,Y] = ginput(4);
Mask = poly2mask(X,Y, sizeI(1), sizeI(2));

hold on;
for i = 1:4
    plot(X, Y, '*');

end
hold off;

subplot(2,3,2);

imshow(Mask * 255);
title('Mask');
IMasked = double(I) .* double(Mask);
subplot(2,3,3);
imshow(uint8(IMasked));
title('Image cutout');


I = rgb2hsv(IMasked);



% handlane meje hue,
% handlane meje saturationa
% handlane meje value
% minimum saturation 0.4 - zaradi bele podlage
I2 = I(:,:,1) > 0.2 & I(:,:,1) < 0.8 & I(:,:,2) > 0.4 & I(:,:,3) > 0.4;

subplot(2, 3, 4);
title('HSV tresholded');
imshow(I2);


%binarize
Igray = I2;
%Igray = 1- im2bw(Igray, 0.86);

%morfološki

se = strel('disk',5);
Igray = closing(Igray, se);

sclose = strel('disk',5);
Igray = opening(Igray, sclose);


subplot(2, 3, 5);
title('Morfološki');
imshow(Igray);

% oznaci areas in centroide

L = bwlabel(Igray);
maxAreas = max(L(:));
s = regionprops(L, {'Centroid'});


centroids = cat(1, s.Centroid);
subplot(2, 3, 6);
title('Centroidi');
imshow(Iorg);
hold on
if (size(centroids))
    plot(centroids(:,1), centroids(:,2), '*')
    text(50, 50, sprintf('%d',numel(centroids(:,1))));
else
    text(50, 50, sprintf('%d',0));
end

    
    
%%
%clear
clear


%%
%FUNCTIONS
function I_out = opening(I, H)
    I_out = imdilate(imerode(I, H), H);
end

function I_out = closing(I, H)
    I_out = imerode(imdilate(I, H), H);

end
