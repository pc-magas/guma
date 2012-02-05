VERSION=1.0

guma/MainFrame.class: guma/MainFrame.java
	javac guma/*.java

run: guma/MainFrame.class
	java guma.MainFrame

jar: 
	jar cvfe ./guma-${VERSION}.jar guma.MainFrame guma/*

clean:
	rm -fr guma/*.class

javadoc:
	mkdir -p javadoc/guma && javadoc guma/* && mv ./*.html javadoc && mv guma/*.html javadoc/guma && mv *.css javadoc

build:
	make && make jar && rm -fr guma/*.class
