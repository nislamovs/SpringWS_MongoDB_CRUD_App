#!/usr/bin/env bash

#Set exit on failure flag
set -e


#Copy all report templates to sql scripts folder
cd ./src/main/resources/maintenanceScripts/ ;
./docker_total_cleanup.sh ;
cd - ;

#Build project
./gradlew clean build docker ;
