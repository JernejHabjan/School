%A
%{
Najprej izraèunajte matrike za dano stanje manipulatorja,
nato izhodišèno toèko (0, 0, 0) pomnožite z matriko vsakega sklepa posebej.
Tako dobite položaje posameznih sklepov posebej. V nadaljevanju prikažite
koordinatne sisteme za vse tri sklepe s pomoèjo funkcije show_system
%}
if(false)
    %SPREMENLJIVKE
    % SIGMAi èe je iti sklep ROTACIJSKI
    % Di èe je iti sklep TRANSLACIJSKI
    
    %https://i.imgur.com/073U2Qu.png
    
    % A, alpha, d, THETA --- ZAPOREDJE PARAMETROTV
    
    coordPoint = [0, 0, 0, 1]';
    
    % HARDCODED STUFF
 
    theta1 = 0;
    theta2 = 0;
    translation3 = 0;
    parameters = [theta1, theta2, translation3];
    
    A = stanford_manipulator(parameters);
    
   
    theta1 = 0;
    theta2 = 0;
    theta3 = 0;
    parameters = [theta1, theta2, theta3];
    
    B = antropomorfic_manipulator(parameters);
    
    sizeParam = size(parameters);
    
    
    M = zeros(1, 4, sizeParam(2));
    for ind = 1:sizeParam(2) 
        M(:,:,ind) = (A(:,:,ind) * coordPoint); % poležemo toèke da so vodoravno v matrik
        
    end
     
    X = [0, M(:,1,1),M(:,1,2),M(:,1,3) ];
    Y = [0, M(:,2,1),M(:,2,2),M(:,2,3) ];
    Z = [0, M(:,3,1),M(:,3,2),M(:,3,3) ];
    figure;
    subplot(1,2,1);
    plot3(X, Y, Z, 'color', [0,0,0], 'linewidth', 4);
    hold on;
    scatter3(X,Y,Z, 20, [0.5 0.5 0.5]);
    hold on;
    
    show_system(A(:,:,1)); 
    show_system(A(:,:,2)); 
    show_system(A(:,:,3)); 
    hold off;
    
    % ANTROPOMORFNI
    M = zeros(1, 4, sizeParam(2));
    for ind = 1:sizeParam(2) 
        M(:,:,ind) = (B(:,:,ind) * coordPoint); % poležemo toèke da so vodoravno v matrik
        
    end
     
    X = [0, M(:,1,1),M(:,1,2),M(:,1,3) ];
    Y = [0, M(:,2,1),M(:,2,2),M(:,2,3) ];
    Z = [0, M(:,3,1),M(:,3,2),M(:,3,3) ];
    
    subplot(1,2,2);
    plot3(X, Y, Z, 'color', [0,0,0], 'linewidth', 4);
    hold on;
    scatter3(X,Y,Z, 20, [0.5 0.5 0.5]);
    hold on;
    
    show_system(B(:,:,1)); 
    show_system(B(:,:,2)); 
    show_system(B(:,:,3)); 
    hold off;
end

%%
%B
if(false)
    point = [3, 3, 4]';
    startingPoint = [0, 0, 0, 1]';
    B = antropomorfic_manipulator([0.2, 0.1, 0.3]);
   
    PolozajZadnjegaSklepa = B(:,:,3) * startingPoint;
    razdalja = sqrt((PolozajZadnjegaSklepa(1) - point(1))^2 +  (PolozajZadnjegaSklepa(2) - point(2))^2 +(PolozajZadnjegaSklepa(3) - point(3))^2);
    razdalja
    %expected result = 3.257
end
%%
%C
%VIZUALIZACIJA
if(true)
    SavingMatrix = zeros(90,90);

    point = [3, 3, 4]';
    startingPoint = [0, 0, 0, 1]';
    
    for i = 1:5:360
       for j = 1:5:360
            B = antropomorfic_manipulator([deg2rad(i), 0.1, deg2rad(j)]);
            
            PolozajZadnjegaSklepa = B(:,:,3) * startingPoint;
            
            razdalja = sqrt((PolozajZadnjegaSklepa(1) - point(1))^2 +  (PolozajZadnjegaSklepa(2) - point(2))^2 +(PolozajZadnjegaSklepa(3) - point(3))^2);
            SavingMatrix(i,j) = razdalja;
          
       end
    end
    figure;
    imagesc(SavingMatrix)
    
end
%%
clear

