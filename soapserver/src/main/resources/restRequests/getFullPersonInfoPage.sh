#!/usr/bin/env bash

curl -kvvv -XGET "https://localhost:8443/api/v1/rest/persons/all?page=2&size=2&fullInfo=false" | jq .
