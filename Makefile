VERSION=1.7-beta-6
SOURCE=guma
CLASSFOLDER=./libs
JAR1=${CLASSFOLDER}/commons-io-2.4/commons-io-2.4.jar
JAR2=${CLASSFOLDER}/net/download-1.0.1.jar
CLASSPATH= ${JAR1}:${JAR2}:.

guma: core simulator gui net ${SOURCE}/Main.java
	javac ${SOURCE}/*.java

arithmetic:
	javac ${SOURCE}/arithmetic/*.java

core: arithmetic
	javac ${SOURCE}/core/*.java

ui:
	javac ${SOURCE}/ui/*/*.java

gui: ui net
	javac  ${SOURCE}/gui/*.java

simulator:
	javac ${SOURCE}/simulator/*.java

run: guma guma/Main.class
	java -cp ${CLASSPATH} guma.Main

net: 
	javac -cp ${CLASSPATH} ${SOURCE}/net/*.java
	
jar: guma
	jar cvfe ./guma-${VERSION}.jar guma.Main guma/*

clean: clean-unessesery
	rm -fr guma/*.class && rm -fr guma/*/*.class && rm -fr guma/*/*/*.class

clean-unessesery:
	rm -fr guma/*~ && rm -fr guma/*/*~ && rm -fr guma/*/*/*~

javadoc:
	./javadoc.sh
	
tar: jar run.bat run.sh Licence.txt CHANGES.TXT
	tar -cvzf guma-${VERSION}.tar.gz guma-${VERSION}.jar run.sh run.bat Licence.txt CHANGES.TXT libs
