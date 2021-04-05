#!/usr/bin/env bash

curl -kvvv -XPUT --header "Content-Type: application/json" "http://localhost:8083/api/v1/rest/persons" -d @updatePerson.json | jq .
