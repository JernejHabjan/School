function [A] = antropomorfic_manipulator(parametersIn)


    parameters = [0, pi/2, 3, parametersIn(1);
                  3,  0,   0, parametersIn(2);
                  3,  0,   0, parametersIn(3); ];


    A = zeros(4, 4, 3);
    
    I = eye(4);
  
    Sklep1 = I * dh_joint(parameters(1,:));
    Sklep2 = Sklep1 *  dh_joint(parameters(2,:));
    Sklep3 = Sklep2 *  dh_joint(parameters(3,:));
    
    A(:,:,1) = Sklep1;
    A(:,:,2) = Sklep2;
    A(:,:,3) = Sklep3;
    
    
end