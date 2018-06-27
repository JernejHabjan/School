%C
if(false)
   
    
    % for point at (50, 90)
    x = [10, 30, 50, 80];
    y = [10, 60, 20, 90];
    bins_theta = 300; bins_rho = 300; % Resolution of the accumulator array
    figure;
    
    for idx = 1:numel(x)
        A = zeros(bins_rho, bins_theta);
        A = hough_partially(A, x(idx),y(idx), bins_rho, bins_theta, 100);
        subplot(2,2,idx);
        imagesc(A); % Display status of the accumulator
        title(strcat(strcat(strcat('x = ', int2str(x(idx))), ', y = '), int2str(y(idx))))
    end
end
%%
%D
if(false)
    
    I1 = rgb2gray(imread('oneline.png'));
    I2 = rgb2gray(imread('rectangle.png'));
    figure;
    
    %first image
    sizeI1 = size(I1);
    bins_theta = sizeI1(1); bins_rho = sizeI1(2); % Resolution of the accumulator array

    A = zeros(bins_rho, bins_theta);
    edges = edge(I1);
    
    [w,h] = size(I1);
    for idx = 1:w
        for idy = 1:h
            
            edge1 = edges(idx, idy); %coords so zdj idx pa idy
            if (edge1 > 0)
                A = A + hough_partially(A, idx, idy, bins_rho, bins_theta, 200);
            end
        end
    end
    subplot(1,2,1);
    imagesc(uint8(A));
    
    % other image
    sizeI2 = size(I2);
    bins_theta = sizeI2(1); bins_rho = sizeI2(2); % Resolution of the accumulator array

    A = zeros(bins_rho, bins_theta);
    edges = edge(I2, 'Sobel');
    
    [w,h] = size(I1);
    for idx = 1:w
        for idy = 1:h
            
            edge1 = edges(idx, idy); %coords so zdj idx pa idy
            if (edge1 > 0)
                A = A + hough_partially(A, idx, idy, bins_rho, bins_theta, 400);
            end
        end
    end
    subplot(1,2,2);
    imagesc(uint8(A));
    
end


%%
% E
if(false)
    I1 = double(rgb2gray(imread('pier.jpg')));
    I2 = double(rgb2gray(imread('bricks.jpg')));
    figure;
    
    %slika1
    subplot(1,2,1);
    threshold = 50;
    edges = edge(I1,'Sobel',threshold);
    
    %imshow(edges)

    [out_rho, out_theta] = hough_find_lines(edges, 'threshold', 1,'count',20, 'neighborhood', 20, 'thetabins', 200); 
    hough_draw_lines(uint8(I1), out_rho, out_theta)
    
    % SLIKA 2
    subplot(1,2,2);
    threshold = 50;
    edges = edge(I2,'Sobel',threshold);
    [out_rho, out_theta] = hough_find_lines(edges, 'threshold', 1, 'neighborhood', 15, 'thetabins', 70);
    hough_draw_lines(uint8(I2), out_rho, out_theta)
    
    hold off
    
end

%%
%F

%{
Vprašanje: Pogosto lahko obravnavamo problem iskanja kronic, ko imamo radij e
poznan. Kakšna je v tem primeru enaèba, ki jo ena toèka generira v parametriènem
prostoru?

na listu enaèba
%}


%%

%H
if(false)
    I1 = double(rgb2gray(imread('eclipse.jpg')));
    I2 = double(rgb2gray(imread('coins.jpg')));
    figure;
    
    threshold = 20;
    Ie1 = edge(I1,'Sobel',threshold);

    
    for r = 45:50
       [out_x, out_y, out_r] = hough_find_circles(Ie1, r, 'threshold', 5, 'count', 25, 'neighborhood', 2);
       
       subplot(1,2,1);
      
       hough_draw_circles(uint8(I1), out_x, out_y, out_r)
    end

    
    % SLIKA 2
    threshold = 60;
    Ie2 = edge(I2,'Sobel',threshold);
 
    for r = 85:85

       [out_x, out_y, out_r]= hough_find_circles(Ie2, r, 'threshold', 1, 'count', 20, 'neighborhood', 1);      
       subplot(1,2,2);
       hough_draw_circles(uint8(I2), out_x, out_y, out_r)
    end
    
end

%% CLEAR
    clear

%% FUNCTIONS

% A

function A = hough_partially(A,x,y, bins_rho, bins_theta, max_rho)

    val_theta = (linspace(-90, 90, bins_theta) / 180) * pi; % Values of theta are known
    val_rho = linspace(-max_rho, max_rho, bins_rho);
    rho = x * cos(val_theta) + y * sin(val_theta); % compute rho for all thetas
    bin_rho = round(((rho + max_rho) / (2 * max_rho)) * length(val_rho)); % Compute bins for rho
    for i = 1:bins_theta % Go over all the points
        if bin_rho(i) > 0 && bin_rho(i) <= bins_rho % Mandatory out-of-bounds check
            A(bin_rho(i), i) = A(bin_rho(i), i) + 1; % Increment the accumulator cells
        end
    end
end





