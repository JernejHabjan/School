
I1 = imread('trucks.jpg');
%%
if (false)
    figure(1);
    hold on;
    colormap gray;
    subplot(2, 4, 1);
    imshow(I1);
    % Draw RGB channels as separate subplots
    subplot(2, 4, 2);
    imshow(I1(:,:,1));
    %rdeè kanal

    subplot(2, 4, 3);
    imshow(I1(:,:,2));
    %zelen kanal

    subplot(2, 4, 4);
    imshow(I1(:,:,3));
    % model kanal

    I2 = rgb2hsv(I1);
    % Draw HSV channels as separate subplots

    subplot(2, 4, 6);
    imshow(I2(:,:,1));
    %hue - krog - vrednosti od 0-360

    subplot(2, 4, 7);
    imshow(I2(:,:,2));
    %saturation - nasièenost - bolj žive barve so na ven

    subplot(2, 4, 8);
    imshow(I2(:,:,3));
    %value - intenziteta barv - èrna je podaj, zgoraj so pa vedno bolj "svetle"

    hold off;
end




%%

% b upragovanje po modrem kanalu z mejo 200

if (false)
    figure(1);
    hold on;
    subplot(1, 2, 1);
    imshow(I1(:,:,3));

    Iupragovana = I1(:,:,3) > 200;
    subplot(1, 2, 2);
    imshow(Iupragovana);
    hold off;

    % Ni kvalitetno, saj se sivinski toni tudi štejejo kot modra barva 
    % npr bela, je oznaèena kot -najbolj modra, ki ima sicer blue channel 255
end

%%
%c 
if (false)
    % tako doloèimo regije R, G, B

    figure(1);
    I1 = double(I1);
    Inormalizirana = I1(:,:,3) ./ sum(I1,3); % ELEMENT-WISE DIVISION !!!!!!!!!
    Iupragovana = Inormalizirana > 0.45;
    imshow(uint8(Iupragovana * 255));

end

%%

% d

if (false)
    I = ones(20, 255, 3);
    I(:, :, 1) = ones(20, 1) * linspace(0, 1, 255);

    I = hsv2rgb(I);
    %I2 = I > 0.45 & I < 0.75;
    image([0, 1], [0, 1], I);
    % modra - 0.45 - 7.5
    %rumena 0.05 - 0.15

    I1 = imread('frank.jpg');
    I2 = rgb2hsv(I1);
    I2(:,:,1) = I2(:,:,1) > 0.00 & I2(:,:,1) < 0.2; % barva kože
    imshow(I2(:,:,1)); %h channel
end


%%

% e 
if (false)
    I1 = imread('trucks.jpg');
    I2 = rgb2hsv(I1);
    figure(1);
    hold on;
    subplot(1, 2, 1);
    imshow(I1);

    I2(:,:,1) = I2(:,:,1) > 0.05 & I2(:,:,1) < 0.15  & I2(:,:,2) > 0.3 & I2(:,:,3) > 0.47;
    subplot(1, 2, 2);
    imshow(I2(:,:,1)); %h channel

    % dodali saturation pogoj in value pogoj - oba sta zmanjšala obseg in šum
end

%%

% f
if (false)
    I1 = imread('trucks.jpg');
    I2 = rgb2hsv(I1);
    figure(1);
    hold on;
    subplot(1, 2, 1);
    imshow(I1);

    I2(:,:,1) = I2(:,:,1) > 0.05 & I2(:,:,1) < 0.15  & I2(:,:,2) > 0.3 & I2(:,:,3) > 0.47;
    subplot(1, 2, 2);
    imshow(I2(:,:,1)); 
    Igray = I2(:,:,1);

    sclose = strel('disk',5);
    Igray = imclose(Igray, sclose);
    se = strel('disk',4);
    Igray = imopen(Igray, se);
    imshow(Igray)


    L = bwlabel(Igray);
    maxAreas = max(L(:));

    moments = zeros(maxAreas, 4);

    for i= 1:maxAreas
        moments(i,1) = i;
        moments(i,2) = moment(L==i, 0,0);
        moments(i,3) = moment(L==i, 1,0);
        moments(i,4) = moment(L==i, 0,1);
    end
    %izpis momentov
    moments

end


%%

% g 

if (false)
    I = ones(20, 255, 3);
    I(:, :, 1) = ones(20, 1) * linspace(0, 1, 255);

    I = hsv2rgb(I);
    %I2 = I > 0.45 & I < 0.75;
    image([0, 1], [0, 1], I);
    % modra - 0.45 - 7.5
    %rumena 0.05 - 0.15

    I1 = imread('trucks.jpg');
    I2 = rgb2hsv(I1);
    I2(:,:,1) = I2(:,:,1) > 0.5 & I2(:,:,1) < 0.7 & I2(:,:,2) > 0.6; % modra


    %ELEMENTWISE POMNOŽIMO Z MASKO
    Imodra = double(I1) .* double(I2(:,:,1));
    imshow(uint8(Imodra)); %h channel

end


%%
%moment function

function [moment_out] = moment(R, p, q)

    R = double(R);
    sizeR = size(R);
    vsota = 0;
    for i=1:sizeR(1)
        for j=1:sizeR(2)
            vsota = vsota +  R(i,j) * i^p * j^q;
        end
    end
    moment_out = vsota;

end