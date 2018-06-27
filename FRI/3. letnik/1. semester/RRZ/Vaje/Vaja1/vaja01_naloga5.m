img =  double(rgb2gray(imread('regions.png'))); 
prag = 127;
img = img > prag;


imgDisplay = uint8(img * 255); %pomnozili z 255 da dobimi bele in crne objecte

% uporabi funkcijo bwlabel
L = bwlabel(img);

maxAreas = max(L(:));

%%

%c


if (false)
    %prikaz grafa
    figure;
    subplot(1,1,1);

    imshow(imgDisplay);
    title('Original Slika')

    % uporabi funkcijo bwlabel
    s = regionprops(L, {'Centroid','BoundingBox'});
    centroids = cat(1, s.Centroid);

    for i = 1:maxAreas
        text(centroids(i,1), centroids(i,2), sprintf('%d', i));
    end
end
%%

if (false)
    %d - funkcije na koncu
    %L==1 - prva regija npr
    R = L==1; % izbrali samo prvo regijo
    p = 1;
    q = 0;
    moment_out = moment(R, p, q)
    
    momentPQ = central_moment(R, p, q)
    % IS THIS CORRECT??????
end



%% 
%e
if (false)
    % izracunamo centroide za regije
    s = regionprops(L, {'Centroid','BoundingBox'});
    centroids = cat(1, s.Centroid);
    boundingBoxes = cat(1, s.BoundingBox);


    imshow(imgDisplay);
    hold on

    plot(centroids(:,1), centroids(:,2), '.')
    title('centroids and bBox')
    % set scale
    imgSize = size(img);
    xlim([1 imgSize(2)]) 
    ylim([1 imgSize(1)])


    linelen = 3; %data units now, not pixels
    paintcolor = [1 0 0];   %red

    bboxsize = size(boundingBoxes);
    for i = 1:bboxsize(1)
        rectangle('Position', boundingBoxes(i,:), 'EdgeColor', paintcolor);
    end
    hold off

end


%%
% b
if (false)
    img = double(rgb2gray(imread('regions.png'))); % todo change this to grayscale
    prag = 127;
    img = img > prag;

    L = bwlabel(img);

    %izpis st regij
    %max(L(:)

    maxAreas = max(L(:));
    for i = 1:maxAreas
        povrsina = moment(L==i, 0 ,0);
        %izpis povrsin
        %povrsina
    end
end


%% c

if (false)
    I = imread('regions_noise.png') > 127;
    se = ones(3, 3); % initialize a simple strucural element
    A1 = imdilate(I, se);
    A2 = imerode(A1, se);
    B1 = imerode(I, se);
    B2 = imdilate(B1, se);
    figure(1);
    subplot(2, 3, 1);
    imshow(I);
    subplot(2, 3, 2);
    imshow(A1);
    subplot(2, 3, 3);
    imshow(A2);
    subplot(2, 3, 5);
    imshow(B1);
    subplot(2, 3, 6);
    imshow(B2);
end

%%

if (false)
    %d opening / closing
    I = imread('regions_noise.png') > 127;
    se = strel('disk',5); % krogec

    figure(1);
    subplot(1, 3, 1);
    imshow(I);
    
    subplot(1, 3, 2);
    imshow(opening(I, se));%OPENING
    
    subplot(1, 3, 3);
    imshow(closing(I, se));  %CLOSING
end


%% 
% e
if (false)
    I = im2bw(imread('bird.jpg'), 0.2);
    se = strel('disk',6);
    I = imclose(I, se);
    imshow(I)
end

%% d1 functions

function [moment_out] = moment(R, p, q)

    R = double(R);
    sizeR = size(R);
    vsota = 0;
    for i=1:sizeR(1)
        for j=1:sizeR(2)
            vsota = vsota +  R(i,j) * i^q * j^p;
        end
    end
    moment_out = vsota;

end

function pqmoment = central_moment(R, p, q)
    R = double(R);
    m00 = moment(R, 0, 0); 
    mx = moment(R, 0, 1) / m00;
    my = moment(R, 1, 0) / m00;
        
    R = double(R);
    sizeR = size(R);
    vsota = 0;
    for i=1:sizeR(1)
        for j=1:sizeR(2)
            vsota = vsota +  R(i,j) * (i - my)^q * (j - mx)^q;
        end
    end
    pqmoment = vsota;
    
end


%% d2 functions


function I_out = opening(I, H)
    I_out = imdilate(imerode(I, H), H);
end

function I_out = closing(I, H)
    I_out = imerode(imdilate(I, H), H);

end






function I_out = opening_old(I, H)
  I_temp = I;
  sizeI = size(I);
  sizeH = size(H);
  middleH = [sizeH(1)/2 + 1 , sizeH(2)/2 + 1]
  
  for i=1:sizeI(1)
      for j=1:sizeI(2)
          matrikca = I(i:i+sizeH(1)-1, j:j+sizeH(2)-1) ;
          for k=1:sizeH(1)
              for l=1:sizeH(2)
                  try
                     I_temp(i, j) = (matrikca(k,l) == H(k,l));    
                  end
              end
          end 
      end
   end 
   I_out = I_temp;
   subplot(1, 2, 2);
   imshow(I_out);

end

function I_out = closing_old(I, H)
    I_temp = I;
  sizeI = size(I);
  sizeH = size(H);
  
  
  for i=1:sizeI(1)
      for j=1:sizeI(2)
          try
            matrikca = I(i:i+sizeH(1)-1, j:j+sizeH(2)-1) ;
          
            I_temp(i, j)= (matrikca == H);
            
          except
            I_temp(i, j)= 0;
          end
          
      end
   end 
   I_out = I_temp;
   subplot(1, 2, 2);
   imshow(I_out);
end


