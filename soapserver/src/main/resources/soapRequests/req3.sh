#!/usr/bin/env bash

SINGLE_LINE_RESPONSE=$( curl --header "content-type: text/xml" -d @request.xml http://localhost:8082/api/v1/ws/persons )

echo "$SINGLE_LINE_RESPONSE" | xmllint --format -  > response.xml

