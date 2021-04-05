#!/usr/bin/env bash

curl -kvvv -XGET http://localhost:8083/api/v1/rest/persons/email/vasja.ivanov@inbox.lv?fullInfo=true | jq .
