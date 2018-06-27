#!/bin/bash

esc='\033'
regular=$esc'[0m'
red=$esc'[91m'
green=$esc'[92m'
bold=$esc'[1m'
format="$bold"'  * %-45s'

assert_equal () {
	if [ "$2" = "$1" ]; then
		message="$3 
${md5print}OK"
	else
	expected=$1
	found=$2
		message="$3 
${md5print}NAPAKA (pričakovano: $expected, prebrano: $found)"
	fi
	print_message "$message"
}

assert_OK () {
	if [ "$2" = "$1" ]; then
		message="$3 
${md5print}OK"
	else
		expected=$(echo "$1" | tr "\n" "$")
		found=$(echo "$2" | tr "\n" "$")
		message="$3 
${md5print}NAPAKA (pričakovano: $expected, prebrano: $found)"
	fi
	print_message "$message"
}

print_message () {
	echo "$1" >> "$results"
	let test_counter++
	echo "Test $test_counter zaključen".
}

check_proc_usage () {
	approx_cpu_usage=`ps aux | awk {'sum+=$3;print sum'} | tail -n 1` 
	if [ $(echo "$approx_cpu_usage > 75" | bc) -eq 1 ]; then
		echo -e "$red${bold}OPOZORILO: Processor preobremenjen: $approx_cpu_usage %$regular" >&2
	fi
}

timeout_checker () {
	sleep 12
	echo -e "Program zaključil:\nNAPAKA (prekoračena časovna omejitev 12 s)" >> "$results"
	wrap_up
}

wrap_up () {
	points=0
	while read line; do
		if [ "$line" = "OK" ]; then
		  printf " [  $green$line$regular$bold  ]$regular\n"
		  let points+=1
		elif [ "${line:0:6}" = "NAPAKA" ]; then
		  printf "[ $red${line:0:6}$regular$bold ]$regular${line:6}\n"
		else
		  awk -v text="$line" -v format="$format" 'BEGIN {printf(format, text)}'
		fi  
	done < "$results"
	echo "Skupaj uspešnih testov: $points"
	
	cleanup_process_subtree
	kill -9 -$$ &> /dev/null
}



cleanup_process_subtree () {
	kill -9 -$pid &> /dev/null
}

prepare () {
	if [ ! -e "$dirtotest/sleeper.sh" ]; then
		printf '#!/bin/bash\nif [ $# -ge 1 ]; then\n  sleep $1\nfi' > "$dirtotest/sleeper.sh"; chmod +x "$dirtotest/sleeper.sh"
	fi
	
	if [ -e $results ]; then
		rm $results
	fi

	if [ -e $errlog ]; then
    	rm $errlog
		touch $errlog
	fi

	if [ $(pgrep -c "$(basename $1)") -gt 0 ]; then
		pkill -9 $(basename $1)
	fi

}

testedscript="$1" 
testerdir=$PWD
dirtotest=$(readlink -f $(dirname $testedscript))
scripttotest=$(basename $testedscript)
results="$testerdir/results"

errlog="$testerdir/err.log"


if [ -x "$testedscript" ]; then

	prepare "$testedscript"
	
	sleep 0.2
	check_proc_usage
	timeout_checker &
	
	# uncomment the following line to hide output to STDERR
	# exec 2> /dev/null
	
	cd "$dirtotest"
	
	export test_counter=0
	
	checkinterval=0.5
	pipename=inputpipe
	if [ ! -e "$pipename" ]; then
		mkfifo "$pipename"
	fi

	setsid "./$scripttotest" $checkinterval $pipename 2> "$errlog" & 
	pid=$!
	
	#setup for public tests
	sleep 10 & pid1=$!
    /bin/sleep 10 & pid2=$!
    ./sleeper.sh 10 & pid3=$! 
	sleep 11 & pid4=$!
	if [ -e  active.log ]; then
		printf "" > active.log
	fi
	
	sleepinterval=0.2
	
    #test
	echo "proc:1:$pid1" > "$pipename"
	sleep $sleepinterval
	echo "log" > "$pipename"
	sleep $sleepinterval
	assert_equal "/bin/sleep 10" "$(tail -n 2 active.log | head -n 1)" "Pravilna zagonska vrstica (1):"

    #test
    assert_equal "$pid1" "$(tail -n 1 active.log)" "Pravilno beleženje PIDa (1):"

	#test
	echo "proc:1:$pid3" > "$pipename"
	sleep $sleepinterval
	echo "log last" > "$pipename"
	sleep $sleepinterval
	assert_equal "/bin/bash ./sleeper.sh 10" "$(tail -n 2 active.log | head -n 1)" "Pravilna zagonska vrstica (2):"

	#test
	echo "proc:2:$pid1,$pid3" > "$pipename"
	sleep 0.3
	assert_equal "PID matching error: $pid1,$pid3" "$(tail -n 1 $errlog)" "Procesa se ne ujemata:"

	#test
	echo "proc:2:$pid1,$pid4" > "$pipename"
	sleep $sleepinterval
	assert_equal "PID matching error: $pid1,$pid4" "$(tail -n 1 $errlog)" "Argumenti se ne ujemajo:"
	sleep $sleepinterval
	
	#test
	echo "proc:2:" > "$pipename"
	sleep $sleepinterval
	assert_equal "PID matching error: $pid1,$pid4" "$(tail -n 1 $errlog)" "Ignoriraj prazen ukaz:"
	sleep $sleepinterval

	#test
	exec 2>/dev/null	
	kill -9 $pid3
	sleep $(echo  "$checkinterval + $sleepinterval" | bc)
	procs="$(pgrep -fx '/bin/bash ./sleeper.sh 10')"
	assert_OK 1 "$(echo $procs | wc -w)" "Proces pognan (1):"
	exec 2>&1

	#test
	echo "log last" > "$pipename"
	sleep $sleepinterval
	assert_equal "$procs" "$(tail -n 1 active.log)" "Pravilno beleženje novega PIDa:"


	#test
	echo "proc:1:$pid1" > "$pipename"
	sleep $sleepinterval
	assert_equal "Run configuration already exists." "$(tail -n 1 $errlog)" "Konfiguracija že obstaja:"
	
	#test
	echo "stop:/bin/sleep 10" > "$pipename"
	sleep $sleepinterval
	
	echo "proc:2:$pid1,$pid2" > "$pipename"
	sleep $sleepinterval
	echo "log last" > "$pipename"
	sleep $sleepinterval
	assert_equal "/bin/sleep 10" "$(tail -n 2 active.log | head -n 1)" "Pravilen stop in pravilna zagonska vrstica:"
	
	#test
	assert_OK 1 "$(tail -n 1 active.log | egrep -c "\<$pid1\>")" "Pravilno beleženje PIDa (2):"

	#test 
	assert_OK 2 "$(tail -n 1 active.log | wc -w)" "Pravilno stevilo PIDov:"
	pid11=$(tail -n 1 active.log | cut -d' ' -f 2)
	pid12=$(tail -n 1 active.log | cut -d' ' -f 1)
	
	#test
	echo "exit" > "$pipename"
	sleep $sleepinterval
	kill -0 $pid &>/dev/null;
	assert_OK 1 "$?" "Program se zaključi:"
	
	#test
	kill -0 $pid11 &>/dev/null
	assert_OK 1 "$?" "Proces ne obstaja po zaključku (1):"
	
	#test
	kill -0 $pid12 &>/dev/null
	assert_OK 1 "$?" "Proces ne obstaja po zaključku (2):"

	 wrap_up

else 
   echo "Could not run."
   exit 2
fi
