#!/usr/bin/env bash

curl -kvvv -XPUT --header "Content-Type: application/json" "https://localhost:8443/api/v1/rest/persons/60c4f87c651aa618618195cf" -d @updatePerson2.json | jq .

