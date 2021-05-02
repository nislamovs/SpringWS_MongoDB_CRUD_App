#!/usr/bin/env bash

curl -kvvv -XDELETE "https://localhost:8443/api/v1/rest/persons/536f710fc55b2acc61000bd1" | jq .
