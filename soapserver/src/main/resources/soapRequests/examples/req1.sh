#!/usr/bin/env bash

# Use data from file
curl --header "content-type: text/xml" -d @request.xml http://localhost:8080/api/v1/ws/persons