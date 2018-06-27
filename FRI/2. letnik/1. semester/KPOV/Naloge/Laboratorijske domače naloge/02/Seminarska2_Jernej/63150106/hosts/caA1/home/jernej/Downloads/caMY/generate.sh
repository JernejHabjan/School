#!/bin/bash

if [ "$1" = "help" ] || [ $# -gt 1 ]; then
	echo "usage: $0 [help]"
	exit 0;
fi

# Poisce direktorij certifikatne agencije
ca_dir="$(dirname "$(realpath "$0")")"

# Poisce absolutno pot do certifikata in privatnega kljuca
ca_crt="${ca_dir}$(sed -n -r -e 's/^certificate\s*=\s*\$dir(.*)/\1/p' "$ca_dir/openssl.cnf" | head -1)"
ca_key="${ca_dir}$(sed -n -r -e 's/^private_key\s*=\s*\$dir(.*)/\1/p' "$ca_dir/openssl.cnf" | head -1)"

if [ $# -eq 1 ]; then
	# Ce je podana pot (brez koncnice) se ta uporabi kot pot do kljuca
	# in zahteve za podpis certifikata
	csr_file="$(realpath "$1.csr")"
	key_file="$(realpath "$1.key")"
else
	# Ce ni, se za zahtevo za podpis uporabi pot certifikata (privzeto certs/cert.crt)
	# s koncnico .csr; za privatni kljuc pa kar pot, na kateri se ta pricakuje
	# (privzeto private/privkey.key)
	csr_file="${ca_crt%.*}.csr"
	key_file="$ca_key"
fi

# Na silo pobrise privatni kljuc, ce ze obstaja, saj je ta zelo verjetno
# shranjen samo za branje, kar sesuje OpenSSL, ko ga ta zeli prepisati
if [ -f "$key_file" ]; then
	rm -f "$key_file"
fi

# Premik v direktorij certifikatne agencije, vsi naslednji ukazi
# se bodo izvajali, kot da bi bila skripta pognana od tam
cd "$ca_dir"

# Ustvari nov par kljucev in zakljuci skripto ce gre karkoli narobe
openssl genrsa -out "$key_file" 4096 || exit 1
# Omeji dovoljenja na zasebnem kljucu
chmod 400 "$key_file"

# Izdela zahtevo za podpis certifikata
openssl req -config openssl.cnf -new -key "$key_file" -out "$csr_file" || exit 2
chmod 644 "$csr_file"

echo
echo "Generated private key $(realpath "$key_file")"
echo "Generated certificate sign request $(realpath "$csr_file")"

