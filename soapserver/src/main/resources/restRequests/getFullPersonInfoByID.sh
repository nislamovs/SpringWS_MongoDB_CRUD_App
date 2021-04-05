#!/usr/bin/env bash

curl -kvvv -XGET http://localhost:8083/api/v1/rest/persons/536f710fc55b2acc61000bd1?fullInfo=true | jq .
