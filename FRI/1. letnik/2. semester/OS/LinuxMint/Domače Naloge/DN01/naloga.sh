#!/bin/bash

a="$1"
len=${#a}  

if [ "$len" -eq 0 ]; then	
	echo "Podajte argument"; exit 1
fi

if [ "$#" -ne 1 ]; then	                                #preveri ali je število vnesenih inputov 1
	echo "St. arg. mora biti 1"; exit $#
fi

if [ "$len" -eq 1 ]; then
	echo "Argument mora biti daljsi od le enega znaka"; exit 255
fi

for (( i=0; i<len; i++ )); do                           #check ali ima input presledek
	char=${a:$i:1}
	if [ "$char" == " " ] || [ $( printf "%d" "'${char}" ) -lt 97 ]; then
		echo "Vnesli ste niz s presledkom ali veliko črko."
		exit 1;
	fi
done

mid=$(($len/2))
i=0
while [ $i -lt $mid ]; do
    if [[ "${a:$i:1}" != "${a: -$(($i+1)):1}" ]]; then  #desni del od -1
      echo "$a NI palindrom"; exit 0
    fi
    (( i++ ))
done
echo "$a JE palindrom"; exit 0

