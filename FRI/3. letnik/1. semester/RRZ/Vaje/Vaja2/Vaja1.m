% B
if(false)
    
    formatSpec = '%f';
    fileID1 = fopen('signal.txt','r');
    fileID2 = fopen('kernel.txt','r');
    I = fscanf(fileID1,formatSpec);
    g = fscanf(fileID2,formatSpec);
    Ig = simple_convolution(I,g);
    Ig_conv=conv(I,g, 'same');
    figure;
    subplot(1,1,1);
    plot(I);
    hold on
    plot(g);
    plot(Ig);
    plot(Ig_conv);
    hold off

    fclose(fileID1);
    fclose(fileID2);
end

%{
Vprašanje: Ali prepoznate obliko jedra ?? Kakšna je vsota vseh elementov jedra?

Za jedro je vzet gausov filter (razpotegnjen)

Vsota vseh elementov jedra znaša 1

%}


%%
%C

%{
Vprašanje: V dokumentaciji funkcije si poglejte kaj pomeni parameter same. V
èem se rezultat razlikuje od simple_convolution(I, g)? Kaj je vzrok za to?


Parameter same vrne samo centralni del konvolucije, ki je enake velikosti I
Rezultat se razlikuje na zaèetku, ko rezultat za prvo mesto ni izraèunan.
Ni izraèunano zato, ker bi filter moral iti v minus, kar bi program
crashalo.
%}

%%
%D
if(false)
    Sigmas = [0.5, 1, 2, 3, 4];
    figure;
    for idx = 1:numel(Sigmas)
        subplot(1,numel(Sigmas),idx);
        sigma = Sigmas(idx);
        [g,x] = gauss(sigma); 
        
        plot(x,g)
    end
end


%%
%F

if(false)
    figure;
    
    formatSpec = '%f';
    fileID1 = fopen('signal.txt','r');
    s = fscanf(fileID1,formatSpec);
    k1 = gauss(2);
    k2 = [0.1, 0.6, 0.4];
     
    subplot(1,4,1);
    plot(s);
    title('S');
    
    subplot(1,4,2);
    plot(conv(conv(s,k1),k2));
    title('(s * k1) * k2');
    
    subplot(1,4,3);
    plot(conv(conv(s,k2),k1));
    title('(s * k2) * k1');
    
    subplot(1,4,4);
    plot(conv(s,conv(k1, k2)));
    title('s * (k1 * k2)');

    fclose(fileID1);   
end
%{
Glavna prednost konvolucije napram korelaciji je asociativnost operacije. To nam
omogoèa, da veè jeder najprej konvoluiramo med seboj, šele nato pa s sliko. Preverite
to lastnost tako, da preberete signal iz datoteke signal.txt ter ga nato
konvoluirate z dvema jedroma, najprej z Gaussovim jedrom ?1 s parametrom ? = 2,
nato pa z jedrom k2 = [0.1, 0.6, 0.4]. V drugem poskusu zamenjajte vrstni red jeder,
nato pa preizkusite še najprej izraèunati konvolucijo obeh jeder ?1 * ?2, rezultat pa
konvoluirajte s signalom. Izrišite vse tri rezultate in jih primerjajte.
%}


%%

%G

if(false)
    A = rgb2gray(imread('lena.png'));
    Icg = imnoise(A,'gaussian', 0, 0.01); % Gaussian noise
    figure;
    subplot(2,2,1); imshow(Icg); colormap gray;
    axis equal; axis tight; title('Gaussian noise');
    Ics = imnoise(A,'salt & pepper', 0.1); % Salt & pepper noise
    subplot(2,2,2) ; imshow(uint8(Ics)); colormap gray;
    axis equal; axis tight; title('Salt and pepper');
    Icg_b = gaussfilter(double(Icg), 1);
    Ics_b = gaussfilter(double(Ics), 1);
    subplot(2,2,3) ; imshow(uint8(Icg_b)); colormap gray;
    axis equal; axis tight; title('Filtered') ;
    subplot(2,2,4) ; imshow(uint8(Ics_b)); colormap gray;
    axis equal; axis tight; title('Filtered');

end


%%

%H
if(false)
    k = [0,0,0;0,2,0;0,0,0] - (1/9) * [1,1,1;1,1,1;1,1,1];
    A = double(rgb2gray(imread('museum.jpg')));
    figure
    subplot(1,2,1);
    imshow(uint8(A));
     for idx = 1:4
        A = conv2(A, k); 
         
     end
     subplot(1,2,2);
     imshow(uint8(A));
    
end

%%
%I

% implementacija medianinega filtra

%%
%J
if(false)
    x = [zeros(1, 14), ones(1, 11), zeros(1, 15)]; % Input signal
    xc = x; xc(11) = 5; xc(18) = 5; % Corrupted signal
    figure;
    subplot(1, 4, 1); plot(x); axis([1, 40, 0, 7]); title('Input');
    subplot(1, 4, 2); plot(xc); axis([1, 40, 0, 7]); title('Corrupted');
    g = gauss(1);
    x_g = conv(xc, g, 'same');
    x_m = simple_median(xc, 5);
    subplot(1, 4, 3); plot(x_g); axis([1, 40, 0, 7]); title('Gauss');
    subplot(1, 4, 4); plot(x_m); axis([1, 40, 0, 7]); title('Median');
end
% bolje se obnese medianin, saj naredi srednjo vrednost petih èlenov, pri
% katerih velike špice ne vplivajo (velika odstopanja)


%%
%K
if(false)
    A = rgb2gray(imread('lena.png'));
    Icg = imnoise(A,'gaussian', 0, 0.01); % Gaussian noise
    figure;
    subplot(2,3,1); imshow(Icg); colormap gray;
    axis equal; axis tight; title('Gaussian noise');
    Ics = imnoise(A,'salt & pepper', 0.1); % Salt & pepper noise
    subplot(2,3,4) ; imshow(uint8(Ics)); colormap gray;
    axis equal; axis tight; title('Salt and pepper');
    
    
    Icg_b = gaussfilter(double(Icg), 1);
    Ics_b = gaussfilter(double(Ics), 1);
    subplot(2,3,2) ; imshow(uint8(Icg_b)); colormap gray;
    axis equal; axis tight; title('Gauss Filtered') ;
    subplot(2,3,5) ; imshow(uint8(Ics_b)); colormap gray;
    axis equal; axis tight; title('Gauss Filtered');
    
    Icg_b = medianfilter(double(Icg), 1);
    Ics_b = medianfilter(double(Ics), 1);
    subplot(2,3,3) ; imshow(uint8(Icg_b)); colormap gray;
    axis equal; axis tight; title('Median Filtered') ;
    subplot(2,3,6) ; imshow(uint8(Ics_b)); colormap gray;
    axis equal; axis tight; title('Median Filtered');
    
    
end

%%
%L
% IMPLEMETACIJA gaussdx funckcije


%%
%M
if(false)
    I = zeros(25,25) ; I(13,13) = 255 ;
    
    sigma = 6;
    G = gauss(sigma);
    D = gaussdx(sigma);
    
    figure;

    subplot(2,3,1); 
    title("Impulse");
    imagesc(I);

    subplot(2,3,2); 
    title("G,Dt");
    imagesc(conv2(conv2(I,G),D'));

    subplot(2,3,3); 
    title("D,Gt");
    imagesc(conv2(conv2(I,D),G'));


    subplot(2,3,4); 
    title("G,Gt");
    imagesc(conv2(conv2(I,G),G'));


    subplot(2,3,5); 
    title("Gt,G");
    imagesc(conv2(conv2(I,G'),D));

    subplot(2,3,6);
    title("Dt,G");
    imagesc(conv2(conv2(I, D'),G));
    colormap(gray(256))
end

%%
%N
if(false)
    I = rgb2gray(imread('pier.jpg'));
    figure;

    subplot(1,3,1); 
    title("Original");
    imagesc(I);
    colormap(gray(256))

    sigma = 6;
    [Ix, Iy] = image_derivatives(I, sigma);
    
    subplot(1,3,2); 
    title("lx");
    imagesc(Ix);

    subplot(1,3,3); 
    title("ly");
    imagesc(Iy);
end

%% CLEAR

clear

%% FUNCTIONS

%D
% JE V DATOTEKI


% I

function Ig = simple_median(I, W)
    N = ceil((W-1)/2) ;
    Ig = zeros(1, length(I)) ;
    for i = N+1 : length(I)-N
        i_left = max([1, i-N]) ;
        i_right = min([length(I), i+N]) ;
        
        
        Ig_sorted = sort(I(i_left:i_right));        
        Ig(i) = Ig_sorted(ceil(W/2));

    end
    
end

%K
function Ig = medianfilter(I, W)
    Ig = im2double(I);
    [m, n] = size(I);
    %Modified filter 
    for i = 2 : m - W
        for j = 2 : n  - W
                M = I(i - W : i + W, j - W : j  + W);
                Ig(i, j) = median(M(:)); 
        end
    end 

end
% L

function result = gaussdx(sigma)

    x = -round(3.0*sigma):round(3.0*sigma);
 
    g = - (1 / (sqrt(2 * pi) * sigma ^ 3)) * x .*  exp(-x .^ 2 / (2 * sigma ^ 2)); 
    g = g / ( 0.5 * sum(abs(g))) ; % normalisation
  
    result = g;
    
end

% N
function [Ix, Iy] = image_derivatives(I, sigma)
    G = gauss(sigma);
    D = gaussdx(sigma);

    Iy = conv2(conv2(I, G), D');
    Ix = conv2(conv2(I, D), G');
    
end