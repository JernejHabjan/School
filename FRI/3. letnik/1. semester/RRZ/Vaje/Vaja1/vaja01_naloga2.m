
A = imread('umbrellas.jpg'); % Image A is in 8-bit format (uint8)
%figure(1); clf; imagesc(A); % open figure window, clear, draw
%figure(2); clf; imshow(A);

%%
% izpis velikosti slike
[h, w, d] = size(A);

%%
% povpreèenje matrike
Ad = double(A);
Mean_Ad = mean(Ad, 3);
A_gray = (Ad(:,:,1) + Ad(:,:,2) + Ad(:,:,3)) / 3.0;
%figure; imshow(uint8(A_gray));
%%
if(false)
    %mapping
    imagesc(uint8(A_gray)) %// display matrix as image
    colormap([1 0 0; 0 1 0; 0 0 1]) %// apply colormap  - lahko tudi winter
    colorbar %// show color bar
end

%%
% izrez kvadrata

if (false)
    A1 = A;
    A1(130:260,240:450,3) = 0 ;
    figure;
    subplot(1,2,1);
    imshow(A1);
    A2 = A(130:260,240:450, 1);
    subplot(1,2,2);
imshow(A2);
end

%%
%negacija barve
if (false)
    A3 = uint8(A_gray);
    A3(130:260,240:450) = 255 - A3(130:260,240:450) ;
    figure;
    imshow(A3);
end

%%
%upragovana binarna slika
if (false)
    A5 = uint8(A_gray);
    A5 = A5 > 150;  % all elements with value > 150 are changed to 1, others are changed to 0
    figure(1); imagesc(A5);
end

%%
%kamera
if (false)
    try
       cam = webcam(1);

        for idx = 1:50
            img = snapshot(cam);
            Ad = double(img);
            Mean_Ad = mean(Ad, 3);
            A_gray = (Ad(:,:,1) + Ad(:,:,2) + Ad(:,:,3)) / 3.0;
            imshow(uint8(A_gray)> 150);
        end
        %preview(cam)


        clear cam
    catch exception
        disp('Webcam not avaliable!')
    end
end