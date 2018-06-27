#!/bin/bash

usage() {
	echo "usage: $0 [type] [csr]"
	echo -e "\ttype:\tcertificate type"
	echo -e "\tcsr:\tpath to .csr file"
	echo
	echo "type:"
	echo -e "\tc, client\t\t= end user certificate"
	echo -e "\ts, server\t\t= service certificate"
	echo -e "\ti, intermediate\t\t= intermediate ca certificate"

	exit 0
}

if [ $# -ne 2 ]; then
	usage
fi

case $1 in
	c|client) extension="client_cert";;
	s|server) extension="server_cert";;
	i|intermediate) extension="intermediate_cert";;
	*) usage;;
esac

# Poisce direktorij certifikatne agencije
ca_dir="$(dirname "$(realpath "$0")")"

# Poisce absolutno pot do podane zahteve za podpis
csr_file="$(realpath "$2")"

# Spremeni koncnico na .crt za podpisani certifikat
# Streznik dobi 2 certifikata, enega za povezavo na korenski
# server, drugega za svoj server
if [ "$extension" = "server_cert" ]; then
	client_crt_file="${csr_file%.*}.client.crt"
	server_crt_file="${csr_file%.*}.server.crt"
else
	crt_file="${csr_file%.*}.crt"
fi

# Poisce absolutno pot do certifikata in privatnega kljuca
ca_crt="${ca_dir}$(sed -n -r -e 's/^certificate\s*=\s*\$dir(.*)/\1/p' "$ca_dir/openssl.cnf" | head -1)"
ca_key="${ca_dir}$(sed -n -r -e 's/^private_key\s*=\s*\$dir(.*)/\1/p' "$ca_dir/openssl.cnf" | head -1)"

# Za podpisovanje sta potrebna in certifikat in privatni kljuc
# Ca enega izmed njih ni mogoce najti se izpise opozorilo, ki pove,
# kje naj bi se te dve datoteki nahajali
if [ ! -f $ca_crt ] || [ ! -f $ca_key ]; then
	echo "Can not sign certificate without a certificate authority key"
	echo
	echo "One of these files is missing:"
	echo -e "\t$ca_crt"
	echo -e "\t$ca_key"
	
	exit 1
fi

# Premik v direktorij certifikatne agencije, vsi naslednji ukazi
# se bodo izvajali, kot da bi bila skripta pognana od tam
cd "$ca_dir"

# Podpise podano zahtevo za certifikat
if [ "$extension" = "server_cert" ]; then
	# Za streznik se ustvari in strezniski in uporabniski certifikat
	openssl ca -config openssl.cnf -extensions client_cert -notext -in "$csr_file" -out "$client_crt_file" || exit 1
	openssl ca -config openssl.cnf -extensions server_cert -notext -in "$csr_file" -out "$server_crt_file" || exit 1

	# Na konec certifikata doda celotno verigo (vse nadrejene certifikate)
	# Ta veriga se nato uporablja na strani OpenVPN za preverjanje verodostojnosti
	cat "$ca_crt" >> "$client_crt_file"
	cat "$ca_crt" >> "$server_crt_file"

	# Omeji dovoljenja na zasebnem kljucu
	chmod 644 "$client_crt_file"
	chmod 644 "$server_crt_file"

	echo
	echo "Generated client certificate $(realpath "$client_crt_file")"
	echo "Generated server certificate $(realpath "$server_crt_file")"
else
	# Za vse ostale se ustvari samo en certifikat
	openssl ca -config openssl.cnf -extensions "$extension"  -notext -in "$csr_file" -out "$crt_file" || exit 1

	# Na konec certifikata doda celotno verigo (vse nadrejene certifikate)
	# Ta veriga se nato uporablja na strani OpenVPN za preverjanje verodostojnosti
	cat "$ca_crt" >> "$crt_file"

	# Omeji dovoljenja na zasebnem kljucu
	chmod 644 "$crt_file"

	echo
	echo "Generated certificate $(realpath "$crt_file")"
fi


