
serverURL = 'roka7.fri1.uni-lj.si';
%serverURL = 'localhost:8080';


%init arm before calculating points
world = world_setup(1, 200);
manipulator = manipulator_create(serverURL);
manipulator_draw(world, manipulator);

%move arm to initial position
% return to initial position
state = manipulator_solve(manipulator, [100, 0, 200]);
state(6) = 0;
state(7) = 0;
state(1) = state(1) - pi/36;
manipulator = manipulator_animate(world, manipulator, state, 10);
   
%objects


camera = camera_create(serverURL); % Create camera handle

[Iorg, I, A] = getImage(camera);

% MODRA
IBlue = I(:,:,1) > 0.2 & I(:,:,1) < 0.9 & I(:,:,2) > 0.1 & I(:,:,3) > 0.4;
[centroidsBlue,orientationsBlue] = getCentroids(IBlue, Iorg);

% RDECA
IRed = I(:,:,1) > 0.0 & I(:,:,1) < 0.3 & I(:,:,2) > 0.4 & I(:,:,3) > 0.4;
[centroidsRed, orientationsRed] = getCentroids(IRed, Iorg);

%% MANIPULATOR


     
moveArmOnCentroids(world, manipulator, centroidsBlue, A, 1, orientationsBlue);
moveArmOnCentroids(world, manipulator, centroidsRed, A, 0, orientationsRed);





% return to initial position
state = manipulator_solve(manipulator, [100, 0, 200]);

state(6) = 0;
state(1) = state(1) - pi/36;
manipulator = manipulator_animate(world, manipulator, state, 10);
        


%%
%clear
clear

%%
%FUNCTIONS

function [Iorg, I, A] = getImage(camera)
    image = camera_image(camera); % Retrieve current image
    homography = camera_position(camera); % Retrieve current homoraphy
    A = homography; % homografska matrika

    figure(2); 
    subplot(2,3,1);
    imshow(image); 
    title('org slika s toèkami');


     % pretvorba v homogene koordinate
     
    X = [100, 100, 300, 300];
    Y = [-200, 200, 200, -200];

    Z = ones(1,4);
    pS = vertcat(X,Y,Z);
    pH = A * pS;

    X = pH(1,:) ./  pH(3,:);
    Y = pH(2,:) ./  pH(3,:);

    %drawing square
    hold on;

    plot([X(1), X(2)], [Y(1), Y(2)], 'b'); 
    plot([X(2), X(3)], [Y(2), Y(3)], 'b'); 
    plot([X(3), X(4)], [Y(3), Y(4)], 'b'); 
    plot([X(4), X(1)], [Y(4), Y(1)], 'b'); 
    hold off;   


    % B NALOGA SPODAJ
    I = image;
    Iorg = I;
    sizeI = size(I);


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

    

end


function returnOut = moveCubeToVector(manipulator, world, cubePosition, dropLocation, cubeOrientationState6Offset)
        % move to cube position
        state = manipulator_solve(manipulator, cubePosition);
        state(6) = cubeOrientationState6Offset;
        manipulator = manipulator_animate(world, manipulator, state, 10);
        
        % grip cube
        manipulator.param(7,4) = 0.75;
        state = manipulator_calculate(manipulator.param);
        state(6) = cubeOrientationState6Offset;
        manipulator = manipulator_animate(world, manipulator, state, 10);
        %pause gripping
        state = manipulator_solve(manipulator, cubePosition);
        state(6) = cubeOrientationState6Offset;
        manipulator = manipulator_animate(world, manipulator, state, 10);
            
        disp('moving to drop position')
        % move to drop position
        state = manipulator_solve(manipulator, dropLocation');
        state(6) = 0;
        manipulator = manipulator_animate(world, manipulator, state, 10);
        
        %ungrip cube
        manipulator.param(7,4) = 0;
     
        state = manipulator_calculate(manipulator.param);
         state(6) = 0;
        manipulator = manipulator_animate(world, manipulator, state, 10);
        %pause ungripping
       
        state = manipulator_solve(manipulator, dropLocation');
         state(6) = 0;
        manipulator = manipulator_animate(world, manipulator, state, 10);
        
        %return to initial
        state = manipulator_solve(manipulator, [100, 0, 200]);
        state(6) = 0;
        manipulator = manipulator_animate(world, manipulator, state, 10);


end

function returnOut = moveArmOnCentroids(world, manipulator, centroids, A, openArm, orientations)
    

    %pretvorba koordinatne sistema kamere v koord sistem delovne površine
    invA = inv(A);
    
    state = manipulator_solve(manipulator, [100, 0, 200]);
    state(6) = 0;
    manipulator = manipulator_animate(world, manipulator, state, 10);

        
    
    for i = 1: numel(centroids(:,1))
        orientation = orientations(i);
        
       
        
        
        x = centroids(i,1);
        y = centroids(i,2);
        z = [1];
        
        
        homogXYZ = invA * [x,y,z]';
        
        X = homogXYZ(1) /  homogXYZ(3); % WORLD X
        Y = homogXYZ(2) /  homogXYZ(3) - 10; % WORLD Y AND OFFSET FOR ROBOT
        Z = 20; %to be 3 cm away from cube
        WORLDXYZ = vertcat(X,Y,Z)';
        % MOVE ARM TO THIS POSITION
        cubeOrientationState6Offset = deg2rad(orientation) + state(1) ;
        
       
        
        %Open arm if cube is red
        if (openArm)
            disp('MOVING CUBE TO LEFT SIDE')
            
            if(WORLDXYZ(2) < 0)
                state = manipulator_solve(manipulator, WORLDXYZ);
                state(6) = cubeOrientationState6Offset;       
                manipulator = manipulator_animate(world, manipulator, state, 10);
                
                moveCubeToVector(manipulator,world, WORLDXYZ, [150,150,100]', cubeOrientationState6Offset); % LETS SAY THIS IS LEFT SIDE
            
            end
            
        else

            disp('MOVING CUBE TO RIGHT SIDE')
            if(WORLDXYZ(2) > 0)
                state = manipulator_solve(manipulator, WORLDXYZ);
                state(6) = cubeOrientationState6Offset;       
                manipulator = manipulator_animate(world, manipulator, state, 10);
                
                
                moveCubeToVector(manipulator, world, WORLDXYZ, [150,-150,100]', cubeOrientationState6Offset); % LETS SAY THIS IS RIGHT SIDE
            end
        end
        
    end
end
function [centroidsOut, orientationsOut] = getCentroids(I2, Iorg)
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
    s = regionprops(L, {'Centroid', 'Orientation'});


    centroids = cat(1, s.Centroid);
    orientations = cat(1, s.Orientation);
    
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
    
    centroidsOut = centroids;
    orientationsOut = orientations;
end


function I_out = opening(I, H)
    I_out = imdilate(imerode(I, H), H);
end

function I_out = closing(I, H)
    I_out = imerode(imdilate(I, H), H);

end
