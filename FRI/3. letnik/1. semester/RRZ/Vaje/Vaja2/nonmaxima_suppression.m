function A = nonmaxima_suppression(A, n)

    valid = ((ordfilt2(A, (n * 2 + 1) ^ 2, true(n * 2 + 1))) == A);
    A(~valid) = 0;
    
