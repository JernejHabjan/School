#!/bin/bash

usage() {
	echo "usage: $0 [type]"
	echo -e "\ttype:\tcertificate authority type"
	echo
	echo "type:"
	echo -e "\tc, client\t= end user"
	echo -e "\ti, intermediate\t= intermediate ca"
	echo -e "\tr, root\t= root ca"

	exit 0
}

if [ "$#" -eq 0 ] || [ "$#" -gt 2 ]; then
	usage
fi

if [ "$#" -eq 2 ] && [ "$2" != "+dh" ]; then
	usage
fi

case $1 in
	c|client) ca_type="client";;
	i|intermediate) ca_type="intermediate";;
	r|root) ca_type="root";;
	*) usage;;
esac

# Premakne uporabnika v direktorij s certifikatno agencijo
ca_dir="$(dirname "$(realpath "$0")")"
cd "$ca_dir"

echo "Configuring certificate authority for $ca_type..."
echo

# Uporabniki v resnici uporabljajo namestitev za vmesne
# certifikatne agencije, a ne morejo podpisovati kljucev
if [ $ca_type = "client" ]; then
	ca_type="intermediate"
fi

# Kopiranje predloge za konfiguracijo, ce ta se ne obstaja
if [ ! -f "openssl.cnf" ]; then
	echo "Creating configuration..."
	cp openssl.orig.cnf openssl.cnf
fi

echo "Patching configuration..."
# Popravi direktorij s certifikatno agencijo
sed -i -r "s#^(dir\s*=)\s*(.*)#\1 $ca_dir#g" openssl.cnf || exit 1
# Popravi vrsto certifikatne agencije
sed -i -r "s/^(default_ca\s*=)\s*(.*)/\1 ${ca_type}_ca/" openssl.cnf || exit 1

# Ustvarjanje direktorijev potrebnih za delovanje certifikatne agencije
# poti do teh so navedene v konfiguraciji, zato jih ne spreminjat

if [ ! -d "client-certs" ]; then
	echo "Creating client certificate directory..."
	mkdir client-certs
fi

if [ ! -d "private" ]; then
	echo "Creating private key directory..."
	mkdir private
fi

if [ ! -d "certs" ]; then
	echo "Creating certificate directory..."
	mkdir certs
fi

# Interni direktorij je namenjen shranjevanju datotek,
# ki jih OpenSSL vzdrzuje samostojno
if [ ! -d "internal" ]; then
	echo "Creating internal ca directory..."
	mkdir internal
fi

# database.txt vsebuje seznam podpisanih certifikatov
if [ ! -f "internal/database.txt" ]; then
	echo "Creating database..."
	touch internal/database.txt
fi

# serial je datoteka z naslednjo zaporedno stevilko certifikata
if [ ! -f "internal/serial" ] || [ ! -s "internal/serial" ]; then
	echo "Initializing serial sequence..."
	echo 1000 > internal/serial
fi

# Struktura za preklicno listo, ki se trenutno ne uporablja

if [ ! -f "internal/crl-number" ] || [ ! -s "internal/crl-number" ]; then
	echo "Initializing revocation list sequence..."
	echo 1000 > internal/crl-number
fi

# Ustvari Diffie-Hellman parametre, ki jih potrebuje OpenVPN
# A le ce ti se ne obstajajo, saj to straja kar nekaj casa
if [ "$2" = "+dh" ] && [ ! -f "private/dhparams.key" ]; then
	echo "Generating Diffie-Hellman parameters, this might take a while..."
	echo
	openssl dhparam -out private/dhparams.key 4096
fi

echo
echo "Certificate authority successfully configured!"

