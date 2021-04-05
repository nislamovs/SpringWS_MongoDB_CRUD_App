#!/usr/bin/env bash

curl -kvvv -XDELETE "http://localhost:8083/api/v1/rest/persons/536f710fc55b2acc61000bd1" | jq .
