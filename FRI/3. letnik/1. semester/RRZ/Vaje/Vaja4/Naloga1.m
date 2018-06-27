%B
%preslikava homogenih koordinat
if(false)
    I = imread('camera1.jpg');
    A = load('camera1.txt');


    n = 10;
    X = linspace(0, 200, n);
    Y = linspace(0, 200, n);

    % pretvorba v homogene koordinate
    Z = ones(1,n);
    pS = vertcat(X,Y,Z);
    pH = A * pS;
    X = pH(1,:) ./  pH(3,:);
    Y = pH(2,:) ./  pH(3,:);

    % izris
    figure(1); imshow(I); % Draw the image
    hold on;
    for i = 1:n
    plot([X(i), X(i)], [Y(1), Y(end)], 'b');
    plot([X(1), X(end)], [Y(i), Y(i)], 'b');
    end
    hold off;
end

%%
%C
if(false)
    I = imread('camera1.jpg');
    ISize = size(I);
    A = load('camera1.txt');
    A = inv(A);
    figure(1); 
    subplot(1,2,1);
    imshow(I);
    
    n = 5;
    [X,Y] = ginput(n);
    Z = ones(1,n);
    
    figure(1); imshow(I); % Draw the image
    hold on;
    for i = 1:n
        plot(X, Y, '*');

    end
    hold off;
    
    subplot(1,2,2);
 
    xyz = vertcat(X',Y',Z);
    result = A * xyz;
    result(1,:)
    
    plot(result(1,:), result(2,:), '*'); %% TODOOOOOO SCALE TEGA GRAFA SE NE UJEMA - NEVEM WORLD KOORDINATE PLATE

    xlim([-ISize(2) ISize(2)])
    ylim([- ISize(1) ISize(1)])
    
    
end

%% 
clear