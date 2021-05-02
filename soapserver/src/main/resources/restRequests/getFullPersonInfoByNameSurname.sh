#!/usr/bin/env bash

curl -kvvv -XGET "https://localhost:8443/api/v1/rest/persons?firstname=Vasja&lastname=Ivanov&fullInfo=true" | jq .
