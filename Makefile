VERSION=1.6.1
SOURCE=guma

guma: core simulator gui ${SOURCE}/Main.java
	javac ${SOURCE}/*.java

arithmetic:
	javac ${SOURCE}/arithmetic/*.java

core: arithmetic
	javac ${SOURCE}/core/*.java

ui:
	javac ${SOURCE}/ui/*.java

gui: ui
	javac ${SOURCE}/gui/*.java

simulator:
	javac ${SOURCE}/simulator/*.java

run: guma guma/Main.class
	java guma.Main

jar: guma
	jar cvfe ./src/guma-${VERSION}.jar guma.Main guma/*

clean: clean-unessesery
	rm -fr guma/*.class && rm -fr guma/*/*.class && rm -fr guma/*/*/*.class

clean-unessesery:
	rm -fr guma/*~ && rm -fr guma/*/*~ && rm -fr guma/*/*/*~

javadoc:
	mkdir -p javadoc/guma && javadoc guma/* && mv ./*.html javadoc && mv guma/*.html javadoc/guma && mv *.css javadoc

build: jar clean

tar: build run.bat run.sh Licence.txt CHANGES.TXT
	tar -cvzf guma-${VERSION}.tar.gz guma-${VERSION}.jar run.sh run.bat Licence.txt CHANGES.TXT 
