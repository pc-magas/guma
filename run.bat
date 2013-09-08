@echo off
java -Xbootclasspath/a:.\libs\commons-io-2.4\commons-io-2.4.jar;.\libs\net\download-1.0.jar;. -jar guma-1.7-beta-6.jar %1 %2 %3 %4 %5 %6 %7 %8

if errorlevel 1 pause
