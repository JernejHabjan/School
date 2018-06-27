%meritve
cas = 30;
meritev_sec = 10;
matrix_X = zeros(2, cas * meritev_sec);

%podatki o avtomobilu
sirina = 2.5; 
zacetna_oddaljenost = 10;
pospesek = 0.5; % m/s^2

%izraèun
for t = 1 : cas * meritev_sec
    oddaljenost_od_zacetka_vzorcenja = (1/2) * pospesek * (t/meritev_sec)^2;
    temp_sirina = sirina / (zacetna_oddaljenost +  oddaljenost_od_zacetka_vzorcenja);
    matrix_X(2,t) = temp_sirina;
    matrix_X(1,t) = t;
end

%narišemo
plot(matrix_X(1,:), 10 * matrix_X(2,:)) % pomnozili Y_axis z 10, da prikazemo v metrih
title('Velikost avtomobila skozi èas')
ylabel('Širina avtomobila')
xlabel('Število vzorca (Frame)')

clear;