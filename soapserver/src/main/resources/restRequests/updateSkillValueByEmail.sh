#!/usr/bin/env bash

#!/usr/bin/env bash

curl -kvvv -XPUT --header "Content-Type: application/json" "https://localhost:8443/api/v1/rest/persons/skillSet?email=vasja.ivanov@inbox.lv&skillName=lockpicking&skillValue=100" | jq .