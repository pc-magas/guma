VERSION=1.0

guma/Main.class: guma/Main.java
	javac guma/*.java

run: guma/Main.class
	java guma/Main

jar: 
	jar cvfe ./guma-${VERSION}.jar guma.Main guma/*

clean:
	rm -fr guma/*.class
