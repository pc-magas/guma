#!/bin/bash
unzip guma-source.zip
java ./guma/*.java
jar cvfe ./guma-1.0.jar  guma.Main guma/*

