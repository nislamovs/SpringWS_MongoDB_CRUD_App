#!/usr/bin/env bash

curl -kvvv -XPOST --header "Content-Type: application/json" "http://localhost:8083/api/v1/rest/persons" -d @createPerson.json | jq .
