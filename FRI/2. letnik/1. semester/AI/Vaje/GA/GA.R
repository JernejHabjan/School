# Uporabljali bomo knji�nico GA
# Prepricajte se, da je knjiznica nalozena (sicer jo nalozite z install.packages)
install.packages("GA")

library(GA)

#
#
# PRIMER 1: Iskanje maksimuma enodimenzionalne funkcije -- iskanje x v grafu
#
#

# Maksimizacija asimetri�ne funkcije "double claw" je te�avna zaradi velikega �tevila lokalnih ekstremov.
# Standardni pristopi, ki se zana�ajo na vrednosti odvodov, bi na�li maksimum, najbli�ji za�etni vrednosti.

f <- function(x) 
{
	y <- (0.46 * (dnorm(x, -1, 2/3) + dnorm(x, 1, 2/3)) +
	(1/300) * (dnorm(x, -0.5, 0.01) + dnorm(x, -1, 0.01) +
	dnorm(x, -1.5, 0.01)) +
	(7/300) * (dnorm(x, 0.5, 0.07) + dnorm(x, 1, 0.07) +
	dnorm(x, 1.5, 0.07)))
	
	y
}

# Izri�emo funkcijo "double claw"
curve(f, from = -3, to = 3, n = 1000) #naredili bomo genetski alg ki bo nasel max
#fitnes funkcija kandidata je sama vrednost funkcije ->boljsi kandidat ima visjo vrednost
#tiste ki imajo nizjo umrejo, visje pa kombiniramo


# Pri optimizaciji te funkcije lahko kot vrednost kriterijske funkcije (fitness function) uporabimo kar vrednost funkcije same.
GA <- ga(type = "real-valued", fitness = f, min = -3, max = 3)
#evo v tej-> real-valued -> x   ->1 komponenta
#po defaultu je naredu 100 generacij, sem pa tja mutirov.. itd... je že naredu vse


# Vrnjeni objekt lahko izri�emo
plot(GA) #zelena crte predstavlja najbolsi osebek
summary(GA) #propertyji

# Izris re�itve
curve(f, from = -3, to = 3, n = 1000)
points(GA@solution, f(GA@solution), col="red") #narisemo to tocko na grafu


# Evolucijo populacije in ustreznih vrednosti funkcije za vsako generacijo je mogo�e pridobiti
# z definiranjem ustrezne monitor funkcije (funkcija za izris trenutne populacije), ki jo nato 
# podamo v funkcijo ga() kot opcijski argument.

#VIZUALIZACIJA KAJ SE DOGAJA
#vsakic se klice funkcija k se generacija zamena
myMonitor <- function(obj)  #just use it - he said
{
	curve(f, obj@min, obj@max, n = 1000, main = paste("iteration =", obj@iter))
	points(obj@population, obj@fitness, pch = 20, col = 2)
	rug(obj@population, col = 2)
	Sys.sleep(1)
}

GA <- ga(type = "real-valued", fitness = f, min = -3, max = 3, monitor = myMonitor)
#tko poklicemo ta monitor



#
#
# PRIMER 2: Iskanje minimuma DVODIMENZIONALNE funkcije 
#
#

f2 <- function(x1, x2) #2d funkcija
{
	(x1^2 + x2 - 11)^2 + (x1 + x2^2 - 7)^2
}

# Izri�imo to funckijo

# Ustvarimo matriko z vrednostmi funkcije na intervalu [-4.0, 4.0]

x1 <- seq(-4.0, 4.0, by = 0.1)
x2 <- seq(-4.0, 4.0, by = 0.1)

arrayPoints <- matrix(nrow = length(x1), ncol = length(x2))
for (i in 1:length(x1))
	for (j in 1:length(x2))
		arrayPoints[i,j] <- f2(x1[i],x2[j])

#
# Do enakega rezultata lakho pridemo tudi z uporabo funkcije outer():
#
# arrayPoints <- outer(x1, x2, f2)
#

# Izrisimo sliko povrsine, ki jo definira ta funkcija
persp3D(x1, x2, arrayPoints, theta = 50, phi = 20) #narises graf

# Izrisimo toplotni graf (heat map) vrednosti nase funkcije
filled.contour(x1, x2, arrayPoints, color.palette = jet.colors) #tloris tega grafa

# Optimiziramo to funkcijo z opazovanjem prostora, ki ga GA preisce na vsakem korakom

# Funkcija ga() isce maksimalno vrednost kriterijske funkcije. Ker nas zanima minimum opazovane funkcije,
# bomo vrednost kriterijske funkcije definirali kot negativno vrednost opazovane funkcije.

#GA maksimizira fitness funkcijo -> mi pa iščemo minimum -> negiramo
myFitnes2 <- function(x)
{
	-f2(x[1],x[2]) #uporabnimo negativno vrednost funkcije -> ker iščemo minimum
		#dve komponenti -> x1 in x2
}

GA2 <- ga(type = "real-valued", fitness = myFitnes2, min = c(-4.0, -4.0), max = c(4.0, 4.0), crossover = gareal_blxCrossover, popSize = 50, maxiter = 100)
		#nasi osebki so stevila v floatu, fitness je naša funkcija, minimum damo za prvi param, min za 2. param, isto za max.
				#crossover -> za mesanje??? not sure
summary(GA2)

# Izrisimo resitev
filled.contour(x1, x2, arrayPoints, color.palette = jet.colors, plot.axes = { axis(1); axis(2); points(GA2@solution, pch = 19, col = 2)})
	#izrisuje

# Izris poteka genetskega algoritma
myMonitor2 <- function(obj) #monitor za 2d fun
{
	contour(x1, x2, arrayPoints, drawlabels = FALSE, col = gray(0.5))
	title(paste("iteration =", obj@iter), font.main = 1)
	points(obj@population, pch = 20, col = 2)
	Sys.sleep(1)
}

GA2 <- ga(type = "real-valued", fitness = myFitnes2, min = c(-4.0, -4.0), max = c(4.0, 4.0), crossover = gareal_blxCrossover, popSize = 50, maxiter = 100, monitor = myMonitor2)



#
#
# PRIMER 3: Prileganje krivulji (curve fitting) #model fitting
#
#

# Uporabljali bomo podatke o rasti dreves

# Starost drevesa ob meritvi
Age <- c(2.44, 12.44, 22.44, 32.44, 42.44, 52.44, 62.44, 72.44, 82.44, 92.44, 102.44, 112.44)

# Prostornina debla
Vol <- c(2.2, 20.0, 93.0, 262.0, 476.0, 705.0, 967.0, 1203.0, 1409.0, 1659.0, 1898.0, 2106.0)

plot(Age, Vol)

# Ekoloski model za velikost rastline (merjeno s prostornino) kot funkcije starosti je Richardsova krivulja
# f(x) = a*(1-exp(-b*x))^c, kjer so a, b in c parametri modela
			#tle je formula


# Poskusimo Richardsovo krivuljo prilagoditi nasim podatkom z uporabo genetskih algoritmov

# Najprej definiramo funkcijo f (argument theta je vektor parametrov modela a, b, and c) 
richards <- function(x, theta)
{
	theta[1] * (1 - exp(-theta[2] * x))^theta[3]
	#a                   b                 c
}

# Definiramo kriterijsko funkcijo kot vsoto kvadratov razlik med izracunanimi in dejanskimi vrednostmi
myFitness3 <- function(theta, x, y) 
{
	-sum((y - richards(x, theta))^2) ##fitness funkcijo damo neg, ker hočemo čim manjšo razliko enega napovedovanega rezultata od dejanskih
		#negativna vsota med pravimi in napovedanimi
		#kvadraticna seebolj kaznuje slabe
} 

# Kriterijsko funkcijo je treba maksimizirati s spreminjanjem parametrov v vektorju theta, pri podanih podatkih v x in y.
# Za izboljsanje iskanja cez prostor parametrov uporabljamo krizanje z uporabo zlivanja (blend crossover): pri 
# podanih star�ih x1 in x2 (naj bo x1 < x2), nakljucno izbere resitev v intervalu [x1 - k*(x2-x1), x2 + k*(x2-x1)], 
# kjer k predstavlja konstanto med 0 in 1.

#in pozenemo:
GA3 <- ga(type = "real-valued", fitness = myFitness3, x = Age, y = Vol, min = c(3000, 0, 2), max = c(4000, 1, 4),
 popSize = 500, crossover = gareal_blxCrossover, maxiter = 5000, run = 200, names = c("a", "b", "c"))
 #vrednost a je iz predznanja med 3-4k... isto vemo za b 0-1, c 2-4
 #run = 200 -> ce v 200 zaporednih generacijah ne spremeni best rezultat -> ustavimo

summary(GA3)

# Izrisimo naso resitev

plot(Age, Vol)
lines(Age, richards(Age, GA3@solution)) #graf


#
#
# PRIMER 4: Izbira podmnozice atributov #uporabimo binarno reprezentacijo
# osebki so binarni vektorji
# ce imamo n atributov imamo n bitov dolzino osebka
# ko kombiniramo -> ga nekje razrezemo in pol vzamemo neki od enga, neki od druzga
# mutacija -> zamenjamo 1 bita
# fitness funkcija -> za vsak osebek mormo zgradit model-evaluiramo in točnost modela je kvaliteta modela
#
#

# Binarni GA lahko uporabimo za izbiro podmnozice atributov
 
# Nalozimo ucno mnozico
train.data <- read.table("AlgaeLearn.txt", header = T)

# Nalozimo testno mnozico
test.data <- read.table("AlgaeTest.txt", header = T)

# Zgradimo regresijsko drevo
library(rpart)
rt <- rpart(a1 ~ ., data = train.data)
plot(rt); text(rt, pretty = 0)

# Ocenimo model z uporabo srednje kvadratne napake
mean((test.data$a1 - predict(rt, test.data))^2) ##z mean square ror

# Problem izbire podmnozice atributov je moc prilagoditi za GA z uporabo binarnega niza, kjer vrednost i-tega bita 
# oznacuje, ali je i-ti atribut izbran v podmnozico ali ne. Podmnozico atributov lahko ocenimo z enim izmed 
# kriterijev za izbiro modela. V tem primeru bomo uporabili povprecno kvadratno napako.

# Podatke razdelimo v dve mnozici: eno s ciljnimi vrednostmi in drugo z vrednostmi atributov. 
# (drop = FALSE uporabimo, da preprecimo transformacijo podatkovnega okvirja v vektor, ce je izbran zgolj en stolpec)
Y <- train.data[, "a1", drop = FALSE] #drop = false - ne konvertaj ampak pusti kot matriko -> sconvertal bi ker je samo 1 stolpec
X <- train.data[, which(names(train.data) != "a1")]

#celotno ucno mnozico narbijemo na 2 matriki ->
	#Y- ima sam vredost cilne sprmenljivke (a1)
	#in navadne atribute v X -> klor, season,.....


fitness4 <- function(string) #dobi en vzorec nicel in enk 
{
	# izbran mora biti vsaj en atribut
	if (sum(string) == 0) #model je shit
		return(0)

	# kateri atributi so izbrani
	inc <- which(string == 1) #pogledam kateri imajo enke
	
      # zgradimo regresijsko drevo z uporabo podatkovne mnozice, zgrajene iz ciljnih vrednosti in izbranih atributov
      # (drop = FALSE uporabimo, da preprecimo spremembo podatkovnega okvirja v vektor, ce je izbran zgolj en stolpec)
	rt <- rpart(a1 ~., data = cbind(Y, X[,inc, drop = FALSE])) #dodamo not se ciljno spremenlivko v Y
		#zgradimo drevo v rt
	
	# ocenimo kvaliteto modela
	-mean((test.data$a1 - predict(rt, test.data))^2) #minus, da mi najde NAJMANJSO napako
}

GA4 <- ga("binary", fitness = fitness4, nBits = ncol(X), names = colnames(X), monitor = plot)

summary(GA4)

# Ce najdemo vec kot eno resitev, izberemo tisto, ki ima najmanj atributov
sel <- GA4@solution[which.min(apply(GA4@solution, 1, sum)),]
sel

# Ustvari model z uporabo izbrane podmnozice atributov
rt1 <- rpart(a1 ~., data = cbind(Y, X[, which(sel == 1)]))
mean((test.data$a1 - predict(rt1, test.data))^2) #dobimo 188
plot(rt1); text(rt1, pretty = 0) #narisemo drevo


#
#
# PRIMER 5: Optimizacija z omejitvami
#
#

# Problem 0-1 nahrbtnika je definiran tako: ob podani mnozici predmetov, ki so opisani s tezo in vrednostjo,
# poisci podmnozico teh predmetov, katerih skupna teza ne presega neke podane meje, in je njihova vrednost najvecja.

#izbira bo spet binarna
#skupen volumen mora biti manjši ali enak kapaciteti nahrbtnika
#fitness funkcija -> cim vecje stevilo izbranih predmetov
#če gre čez volumen -> mu lahko povemo koliko ni bila dobra -> vrednsot fitnes funkcije je razlika med volumnom predmetom in max vol.

# vektor vrednosti predmetov
values <- c(5, 8, 3, 4, 6, 5, 4, 3, 2) #seznam vrednosti predmetov

# vektor tez predmetov
weights <- c(1, 3, 2, 4, 2, 1, 3, 4, 5) #teže -> isto kt volume

# najvecja skupna dovoljena teza
Capacity <- 10

# Za resitev tega problema lahko uporabimo binarni GA. Resitev je binarni niz, katerega dolzina je enaka stevilu 
# predmetov v mnozici. Vrednost i-tega bita oznacuje, ali je i-ti predmet izbran (vrednost 1) ali ne (vrednost 0).
# Kriterijska funkcija mora kaznovati resitve, katerih teza presega najvisjo dovoljeno.

knapsack <- function(x) 
{
	f <- sum(x * values) #mnozimo vrednosti predmetov
	w <- sum(x * weights)

	if (w > Capacity)
		f <- Capacity - w #ce je vec pa odstejemo -> dobi mav minusa

	f
}

GA5 <- ga(type = "binary", fitness = knapsack, nBits = length(weights), maxiter = 1000, run = 200, popSize = 100)
#type = binary!!
summary(GA5)



#
#
# PRIMER 6: Problem trgovskega potnika
#
#

# Podan je seznam mest in razdalj med njimi (za vsak par mesto - mesto). Kaksna je najkrajsa pot, ki obisce 
# vsa mesta v seznamu natanko enkrat in se zakljuci v izhodiscnem mestu?

data("eurodist", package = "datasets")
D <- as.matrix(eurodist)
D

# Mesta ostevilcimo (dolocimo jim identifikacijske stevilke). Posamezen obhod je predstavljen kot permutacija 
# teh identifikacijskih stevilk. Njihov vrstni red doloca, v katerem vrstnem redu bomo obiskovali mesta.

#ena permutacija definira pot
#fitnes funkcija je pot od mesta do mesta in pol še do prvega mesta nazaj -> rabimo minimalno in bo to naša fitness fun.

# Izracun dolzine obhoda.
tourLength <- function(tour) 
{
	tour <- c(tour, tour[1]) #dodamo še prvo mesto šeenkrat
	dist <- 0
	for (i in 2:length(tour)) 
		dist <- dist + D[tour[i],tour[i-1]]

	dist
}

# Kriterijska funkcija je obratno sorazmerna dolzini obhoda.
tspFitness <- function(tour) 
{
	1/tourLength(tour) 
}

GA6 <- ga(type = "permutation", fitness = tspFitness, min = 1, max = ncol(D), popSize = 50, maxiter = 5000, run = 500, pmutation = 0.2)
#type = permutatiton

summary(GA6)

# Iz permutacije zgradimo resitev.
tour <- GA6@solution[1, ]
tour <- c(tour, tour[1])

tourLength(tour)
colnames(D)[tour]

#dobimo 2 rešitve ki sta zrcalne ena drugo


