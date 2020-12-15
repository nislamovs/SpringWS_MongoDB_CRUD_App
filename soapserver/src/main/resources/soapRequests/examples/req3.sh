#!/usr/bin/env bash

SINGLE_LINE_RESPONSE=$( curl --header "content-type: text/xml" -d @request.xml http://localhost:8080/api/v1/ws/persons )

echo "$SINGLE_LINE_RESPONSE" | xmllint --format -  > response2.xml

