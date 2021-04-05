#!/usr/bin/env bash

curl -kvvv -XGET "http://localhost:8083/api/v1/rest/persons/all?page=2&size=2&fullInfo=true" | jq .
