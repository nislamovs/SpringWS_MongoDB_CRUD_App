#!/usr/bin/env bash

curl -kvvv -XPUT --header "Content-Type: application/json" "https://localhost:8443/api/v1/rest/persons/6095a47871b4685609e04e2e" -d @updatePerson.json | jq .
