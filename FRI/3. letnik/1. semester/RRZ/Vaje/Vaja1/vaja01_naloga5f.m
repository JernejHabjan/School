I = imread('candy.jpg');
Iorg = I;
sizeI = size(I);


figure(1); clf;
imshow(I);
hold on
[x,y] = ginput(1);
x = uint8(x);
y = uint8(y);

I = rgb2hsv(I);



hsv = I(y,x,:);
h = hsv(1);
s = hsv(2);
v = hsv(3);

% handlane meje hue,
% handlane meje saturationa
% handlane meje value
% minimum saturation 0.4 - zaradi bele podlage
I2 = I(:,:,1) > h - 0.02 & I(:,:,1) < h + 0.02 & I(:,:,2) > s - 0.1 & I(:,:,2) < s + 0.1 & I(:,:,3) > v - 0.5 & I(:,:,3) < v + 0.1 & I(:,:,2) > 0.4;

subplot(2, 2, 1);
imshow(I2);

%binarize
Igray = I2;
%Igray = 1- im2bw(Igray, 0.86);

%morfološki

se = strel('disk',5);
Igray = closing(Igray, se);

sclose = strel('disk',5);
Igray = opening(Igray, sclose);


subplot(2, 2, 2);
imshow(Igray);

% oznaci areas in centroide

L = bwlabel(Igray);
maxAreas = max(L(:));
s = regionprops(L, {'Centroid'});


centroids = cat(1, s.Centroid);
subplot(2, 2, 3);
imshow(Iorg);
hold on
if (size(centroids))
    plot(centroids(:,1), centroids(:,2), 'o')
    text(50, 50, sprintf('%d',numel(centroids(:,1))));
else
    text(50, 50, sprintf('%d',0));
end


function I_out = opening(I, H)
    I_out = imdilate(imerode(I, H), H);
end

function I_out = closing(I, H)
    I_out = imerode(imdilate(I, H), H);

end





























