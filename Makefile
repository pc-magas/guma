VERSION=1.7
SOURCE=guma
LIBS= ./html2image-0.9.jar

guma: core simulator gui ${SOURCE}/Main.java
	javac ${SOURCE}/*.java

arithmetic:
	javac ${SOURCE}/arithmetic/*.java

core: arithmetic
	javac ${SOURCE}/core/*.java

ui:
	javac ${SOURCE}/ui/*/*.java

gui: ui
	javac -cp ${LIBS}:. ${SOURCE}/gui/*.java

simulator:
	javac ${SOURCE}/simulator/*.java

run: guma guma/Main.class
	java -classpath ${LIBS}:. guma.Main

jar: guma
	jar cvfe ./guma-${VERSION}.jar guma.Main guma/*

clean: clean-unessesery
	rm -fr guma/*.class && rm -fr guma/*/*.class && rm -fr guma/*/*/*.class

clean-unessesery:
	rm -fr guma/*~ && rm -fr guma/*/*~ && rm -fr guma/*/*/*~

javadoc:
	mkdir -p javadoc/guma && javadoc guma/* && mv ./*.html javadoc && mv guma/*.html javadoc/guma && mv *.css javadoc

build: jar clean

tar: build run.bat run.sh Licence.txt CHANGES.TXT
	tar -cvzf guma-${VERSION}.tar.gz guma-${VERSION}.jar run.sh run.bat Licence.txt CHANGES.TXT 
