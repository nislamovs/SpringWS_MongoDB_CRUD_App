#!/usr/bin/env bash

#Set exit on failure flag
set -e

#Build project
./gradlew clean build docker ;