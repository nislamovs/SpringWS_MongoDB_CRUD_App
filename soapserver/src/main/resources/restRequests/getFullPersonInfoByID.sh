#!/usr/bin/env bash

curl -kvvv -XGET https://localhost:8443/api/v1/rest/persons/536f710fc55b2acc61000bd1?fullInfo=true | jq .

#curl -kvvv -XGET https://localhost:8443/api/v1/rest/persons/536f710fc55b2acc61000bd1?fullInfo=true | jq .
