CC	= gcc
CFLAGS	= -std=c99 -pedantic -Wall
LIBS	= -lm

$(ID)	: $(ID).c
	@echo "=== COMPILING: ==="
	$(CC) $(CFLAGS) -o $@ $< $(LIBS)

test	: $(ID)
	@echo "=== TESTING: ==="
	ulimit -t 3 ; ./$(ID) < input-1
	if diff -w -B output-1 svet.txt ; then echo "1 - correct" ; fi
	ulimit -t 3 ; ./$(ID) < input-2
	if diff -w -B output-2 ChessBoard.txt ; then echo "2 - correct" ; fi
	ulimit -t 3 ; ./$(ID) < input-3
	if diff -w -B output-3 Mongoose.txt ; then echo "3 - correct" ; fi

clean	:
	'rm' -fr $(ID) *~

