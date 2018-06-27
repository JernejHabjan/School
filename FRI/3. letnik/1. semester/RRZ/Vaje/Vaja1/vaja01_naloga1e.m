path = 'C:\Users\jerne\Google Drive\School\FRI\3. letnik\1. semester\Robotika in Raèunalniško zaznavanje\Vaje\Vaja1\vaja01_naloga1e snapshots\';
file = 'C:\Users\jerne\Google Drive\School\FRI\3. letnik\1. semester\Robotika in Raèunalniško zaznavanje\Vaje\Vaja1\vaja01_naloga1e snapshots\*.jpg';

myfile=dir(file); % the folder inw hich our images exists
for i = 1 : length(myfile)
    filename = strcat(path, myfile(i).name);
    %imshow(imread(filename))
    %input('')
end
%{
MERITVE:
    slika_30cm -> 126 to 215 = 
    slika_45cm -> 133 to 193 = 
    slika_60cm -> 138 to 181 = 
    slika_75cm -> 141 to 175 = 
    slika_90cm -> 141 to 170 = 
    slika_105cm -> 142 to 162 = 
%}
slika1_oddaljenost = 30;
slika1_numPixels = 215 - 126; %89

slika2_oddaljenost = 45;
slika2_numPixels = 193 - 133; %60

slika3_oddaljenost = 60;
slika3_numPixels = 181 - 138;

slika4_oddaljenost = 75;
slika4_numPixels = 175 - 141;

slika5_oddaljenost = 90;
slika5_numPixels = 170 - 141;

slika6_oddaljenost = 105;
slika6_numPixels = 162 - 142;

rubiks_cube_height = 5.7; %5.7 cm

% calculate f
f = (slika1_oddaljenost * slika1_numPixels) / rubiks_cube_height;

% calculate other pixel sizes:
y2 = f * (rubiks_cube_height/ slika2_oddaljenost);
y3 = f * (rubiks_cube_height/ slika3_oddaljenost);
y4 = f * (rubiks_cube_height/ slika4_oddaljenost);
y5 = f * (rubiks_cube_height/ slika5_oddaljenost);
y6 = f * (rubiks_cube_height/ slika6_oddaljenost);


%izracunaj napake:
napaka = abs(y2 - slika2_numPixels) + abs(y3 - slika3_numPixels) + abs(y4 - slika4_numPixels) + abs(y5 - slika5_numPixels) + abs(y6 - slika6_numPixels);
napaka
% skupna napaka znaša 10px








