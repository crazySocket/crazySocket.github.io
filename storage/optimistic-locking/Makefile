
all: run

increase.o: increase.asm
	nasm -f elf64 increase.asm

Test_NativeCounter.o: Test_NativeCounter.c
	javah Test.NativeCounter
	gcc -I/usr/lib/jvm/default/include/{.,linux} -c -fPIC $^

libNativeCounter.so: increase.o Test_NativeCounter.o
	gcc -shared -fPIC -o $@ $^

Test.class: Test.java
	javac Test.java

.PHONY: run clean

run: Test.class libNativeCounter.so
	java -Djava.library.path=. Test

clean:
	-rm *.o
	-rm *.class
	-rm *.h
	-rm *.so
