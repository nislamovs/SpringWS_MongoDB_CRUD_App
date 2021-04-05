#!/usr/bin/env bash

curl -kvvv -XGET "http://localhost:8083/api/v1/rest/persons?firstname=Vasja&lastname=Ivanov&fullInfo=true" | jq .
