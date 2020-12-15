#!/usr/bin/env bash

# Use inline XML data
curl <<-EOF -fsSL -H "content-type: text/xml" -d @- http://localhost:8080/api/v1/ws/persons > response.xml && xmllint --format response.xml

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:gs="http://localhost:8080/persons">
   <soapenv:Header/>

   <soapenv:Body>
      <gs:getCountryRequest>
         <gs:name>Spain</gs:name>
      </gs:getCountryRequest>
   </soapenv:Body>

</soapenv:Envelope>

EOF