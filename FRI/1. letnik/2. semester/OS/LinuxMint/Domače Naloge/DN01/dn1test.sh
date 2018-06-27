#!/bin/bash

if [ -z "$1" ]
 then
  echo "Kot argument tej skripti morate podati ime vase skripte, ki naj bi po navodilih bila poimenovana naloga.sh ;-)"
  exit 1
fi

if ! [ -x "$1" ]; then
  echo "Datoteka z imenom $1 ne obstaja ali pa ni izvršljiva"
  exit 2
fi

tocke=0

#v nadaljevanju so primeri testov, ne pa vsi testi, ki bodo uporabljeni pri preverjanju delovanja oddane rešitve

#testiranje nepravilnih vnosov >>>

#testiranje števila argumentov
izpis=$("./$1")
status=$?
if [ "$izpis" == "Podajte argument" ] && [ $status -eq 1 ]
then
	(( tocke++ ))
	echo "1. naloga: 1 | skupaj: $tocke"
fi

izpis=$(./$1 a b c d)
status=$?
if [ "$izpis" == "St. arg. mora biti 1" ] && [ $status -eq 4 ]
then 
	(( tocke=tocke+2 ))
	echo "2. naloga: 2 | skupaj: $tocke"
fi

#testiranje dolžine argumentov
izpis=$(./$1 a)
status=$?
if [ "$izpis" == "Argument mora biti daljsi od le enega znaka" ] && [ $status -eq 255 ]
then 
	(( tocke=tocke+2 ))
	echo "3. naloga: 2 | skupaj: $tocke"
fi

#testiranje pravilnih vnosov >>>

izpis=$(./$1 pericarezeracirep)
status=$?
if [ "$izpis" == "pericarezeracirep JE palindrom" ] && [ $status -eq 0 ]
then 
	(( tocke=tocke+5 ))
	echo "4. naloga: 5 | skupaj: $tocke"
fi

izpis=$(./$1 operacijskisistemi)
status=$?
if [ "$izpis" == "operacijskisistemi NI palindrom" ] && [ $status -eq 0 ]
then 
	(( tocke=tocke+5 ))
	echo "5. naloga: 5 | skupaj: $tocke"
fi

#izpis točk tega testa (x od 15) >>>
echo "Skupaj zbranih tock = $tocke od 15"

exit 0

