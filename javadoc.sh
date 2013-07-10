#!/bin/bash


package="guma/"
javadoc="./javadoc/"
docspackage="$javadoc$package"


javadoc guma -subpackages guma/arithmetic/:guma/core/:guma/net/:guma/ui/main/:guma/ui/simulator:guma/gui/:guma/simulator/ ;

mkdir -p $docspackage

mv -f {package-list,resources/,*.{html,css}} ./javadoc/

mv -f $package*.html $docspackage

for i in $(ls -l $package | grep d | awk '{print $9}') 
do
	
	echo "File: $i"
	
	file=$package$i/
	
	mkdir -p $docspackage$i/
	
	if [ $(ls -l $file | grep .html | wc -l) -gt 0 ] 
	then
		mv  -f $file/*.html $docspackage$i/
	fi
	
	if [ $(ls -l $file | grep d | wc -l) -gt 0 ] 
	then
	
		for j in $(ls -l $file | grep d | awk '{print $9}')
		do
				echo "*File: $javadoc$file$j"
		 	mkdir $javadoc$file$j/
			mv  -f $file$j/*.html $javadoc$file$j/;
		done
	fi
	
done





