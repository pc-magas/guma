GUMA a Free Software Educational Program for elementary school students.

What is GUMA?
Guma is a ree Software Educational Program for elementary school students, that helps thet to practice in basic arithmetic operations of multiplication,adding, substraction and division.

So how does help them?
Is helps them by challenging them to solve random arithmetic praxis with random numbers. 
Also you can selext the number of the random of arithmetic praxis that you want to slove, the maximum value of a number 
that you want to participate in arithmetic praxis, and the type of arithmetic praxis that you want to practice.
You can select to simulate the arithmetic operation.

How can I compile:

a)Go where is ther folder guma dy using comand line environment (terminal in Unix - like systems): 
In UNIX like systems (eg Linux FreeBSD) you can do it with teminal command cd.

b)If not (located together) bring them together under the same folder.

c)Into the uma you need 2 libraries:
	*)commons-io by apache
	*)net library https://github.com/pc-magas/net (type make jar to make it into jar)

d)Then type in terminal:
	*) make to compile
	*) make jar to make the excecutable jar
	*) make run in order to excecute the compiled .class files
	*) make clear to remove any .class files into file guma
	*) make build to compile .java files, make the jar file and remove any .class files into folder guma.

Also you can compile manually by typing in comand line environment (terminal in Unix-like systems):
	*)javac -cp <path-to-commons-io>:<path-to-net-ibrary>:. guma/*/*.java && javac guma/*.java
	(For windoes replace the : with ;)
	
And making the jar file with:
	*)	jar cvfe ./guma-<version>.jar guma.Main guma/*

Both Source code and binaries are inside the guma-1.0.jar file in the folder guma.
Sourcecode have .java ending and binaries are these that have .class ending in their name.

WARNING:
In order to compile the files you need the latest Java jdk environment.
http://www.oracle.com/technetwork/java/javase/downloads/index.html

(Perhaps you need to configure the CLASSPATH environment variable in MS Windows systems.)

If instructions are not clear or have some errors email me at: ddesilas@yahoo.gr
