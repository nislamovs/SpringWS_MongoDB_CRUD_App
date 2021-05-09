#!/usr/bin/env bash

curl -kvvv -XDELETE --header "Content-Type: application/json" "https://localhost:8443/api/v1/rest/persons/536f710fc55b2acc61000bd2" -d @updatePerson.json | jq .
