#!/bin/sh
#
# Shell script to launch GUMA on Unix systems.  Mostly borrowed from
# the Apache Ant project.
#
# The idea is that you can put a softlink in your "bin" directory back
# to this file in the GUMA install directory and this script will
# use the link to find the jars that it needs, e.g.:
#
# ln -s /usr/local/ArgoUML/guma.sh /usr/local/bin/argo
#
# That was lended from a start scrict of project argoUML
#
#Dimitrios Desyllas (pc_magas): pc_magas@yahoo.gr

PRG=$0
progname=`basename $0`

while [ -h "$PRG" ] ; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '.*/.*' > /dev/null; then
      PRG="$link"
  else
      PRG="`dirname $PRG`/$link"
  fi
done
java  -Xbootclasspath/a:./libs/commons-io-2.4/commons-io-2.4.jar:./libs/net/download-1.0.jar:. -jar `dirname $PRG`/guma-1.7-beta-5.jar "$@"
