#!/usr/bin/env bash

#!/usr/bin/env bash

curl -kvvv -XPUT --header "Content-Type: application/json" "https://localhost:8443/api/v1/rest/persons/stats?email=vasja.ivanov@inbox.lv&statName=intellect&statValue=100" | jq .