%%

I = double(rgb2gray(imread('umbrellas.jpg')));
P = I(:); % A handy way to turn 2D matrix into a vector of numbers
%%
if (false)
    figure(1); clf;
    bins = 10 ;
    H = hist(P, bins);
    subplot(1,3,1); bar(H, 'b');
    bins = 20 ;
    H = hist(P, bins);
    subplot(1,3,2); bar(H, 'b');
    bins = 40 ;
    H = hist(P, bins);
    subplot(1,3,3); bar(H, 'b');
end

%%
%c 
if (false)
    I  = double(imread('phone.jpg'));

    figure(1); clf;
    subplot(2,2,1);
    imshow(uint8(I));
    P = I(:);
    bins = 1:256 ; % PAZI DA JE OD 1 - 256

    H = hist(P, bins);

    subplot(2,2,2); bar(H, 'b');


    % stretch
    S = histstretch(I);

    subplot(2,2,3);

    imshow(uint8(S));
    P = S(:);

    H = hist(P, bins);
    subplot(2,2,4); bar(H, 'b');

end



%%
% d) upragovanje:
if (false)
    BW = im2bw(imread('umbrellas.jpg'));
    imshow(BW)
end

%%

function S = histstretch(I)
    v_max = max( I(:) );
    v_min = min( I(:) );
    
    %normaliziramo med min in max in pomnozimo z 255 -> max uint8
     S = ((I - v_min) / (v_max - v_min)) * 255; 
   
    % 1. determine the minimum and maximum value of I
    % 2. the minimum and maximum of the output image S are known
    % 3. use the stretch formula to compute new value for each pixel

end






