
function Ig = gaussfilter(I, sigma)
    
    g = gauss(sigma);
    Ib = conv2(I, g, 'same');
    
    
    g = transpose(g);
    Ig = conv2(Ib, g, 'same');

end