#!/usr/bin/env bash

curl -kvvv -XPOST --header "Content-Type: application/json" "https://localhost:8443/api/v1/rest/persons" -d @createPerson.json | jq .
