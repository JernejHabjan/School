
% A
if(false)
    I = double(rgb2gray(imread('museum.jpg')));
    [Imag, Idir] = gradient_magnitude(I, 2);
    
    
    figure;

    subplot(1,3,1); 
    title("Original");
    imagesc(uint8(I));


    subplot(1,3,2); 
    title("Imag");
    imagesc(uint8(Imag));


    subplot(1,3,3); 
    title("Idir");
    imagesc(uint8(Idir));
    
    colormap gray;
end

%%
%B

if(false)
    I = double(rgb2gray(imread('museum.jpg')));
    Thetas = [15, 40]; %napisi notri nekaj vrednosti
    figure;

    for idx = 1:numel(Thetas)
        Theta = Thetas(idx);
        Ie = edges_simple(I, Theta);
        
        subplot(1,numel(Thetas),idx);
        imagesc(uint8(Ie));
        title(strcat('treshold: ',int2str(Theta)));
    end
    colormap gray;
end

%%
%C
if (false)
    I = rgb2gray(imread('pier.jpg'));
    
    subplot(2,3,1);
    imagesc(edge(I));

    subplot(2,3,1);
    imshow(I);
    
    subplot(2,3,2);
    imshow(edge(I, 'Canny'));
    subplot(2,3,3);
    imshow(edge(I, 'Prewitt'));
    subplot(2,3,4);
    imshow(edge(I,'Canny', 100 / 255));
    subplot(2,3,5);
    imshow(edge(I,'Roberts'));
    subplot(2,3,6);
    imshow(edge(I,'Sobel'));
    
end


%%
%D
if(false)
    I = rgb2gray(imread('pier.jpg'));
    figure;
    subplot(1,2,1);
    cornerPositions = corner(I);
    imshow(I)
    title('Corner matlab algo');
    hold on;
    scatter(cornerPositions(:,1), cornerPositions(:,2))
    hold off;
    
    
    subplot(1,2,2);
    imshow(I);
    hold on;

    
    myCorners = harris(I);
    scatter(myCorners(:,1), myCorners(:,2)) % TODOOOOOOO  ---------- TODOoo
    hold off;
        
end

%% CLEAR

clear

%% FUNCTIONS
%A

function [Imag, Idir] = gradient_magnitude(I, sigma)
    [GradientX,GradientY] = gradient(double(I));
    Imag = sqrt(power(GradientX, 2) + power(GradientY, 2));
    Idir = atan2(GradientY,GradientX);
end

%B
function Ie = edges_simple(I, treshold)
    [Imag, Idir] = gradient_magnitude(I, 2);%%%%%%%%%%%%%% NPR 2
    Ie = Imag > treshold;
end

% D

function Corners = harris(I)
    I = double(I);
    %prefilter
    dx = [-1 0 1; -1 0 1; -1 0 1]; % image derivatives
    dy = dx';
    Ix = imfilter(I, dx);
    Iy = imfilter(I, dy);

    h = ones(5,5) / 25;
    I = imfilter(I, h, 'same');
    
    %matrika lokalne strukture %%%%%%%%%%%% TODOOOOOOOOOOOOOOOOOOOOOOOOOOOO
    g = fspecial('gaussian',9,2);
    Ix2 = imfilter(Ix.^2, g); 
    Iy2 = imfilter(Iy.^2, g);
    IxIy = imfilter(Ix.*Iy, g);

    %glajenje matrike %%%%%%%%%%%% TODOOOOOOOOOOOOOOOOOOOOOOOOOOOO
    A_ = nonmaxima_suppression(Ix2, 0.5);
    B_ = nonmaxima_suppression(Iy2, 0.5);
    C_ = nonmaxima_suppression(IxIy, 0.5);
    
    %diagonalizacija %%%%%%%%%%%% TODOOOOOOOOOOOOOOOOOOOOOOOOOOOO
    %lambda1 = (1/2) * (A_ + B_ + sqrt(power(A_, 2) - 2 * A_ * B_ + power(B_, 2) + 4 * power(C_, 2)));
    %lambda2 = (1/2) * (A_ + B_ - sqrt(power(A_, 2) - 2 * A_ * B_ + power(B_, 2) + 4 * power(C_, 2)));
    
    %funkcija odziva na kote - TODOOOOO
    
    
    % mera jakosti kota
    alpha = 0.2;
    Q = (A_ * B_ - C_^2) - alpha .* (A_ + B_) ^ 2;
    
    
    %doloci kote
    treshold = 10000;
    Q = Q > treshold;
    
    %dobimo kote %%% TODOOOOOOO
    Q = sort(Q);
    Corners = Q;
    
end

