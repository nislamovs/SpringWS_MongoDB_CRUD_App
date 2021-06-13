#!/usr/bin/env bash

curl -kvvv -XGET "https://localhost:8443/api/v1/rest/persons/info?email=peteris.peteresons@yahoo.com" | jq .
