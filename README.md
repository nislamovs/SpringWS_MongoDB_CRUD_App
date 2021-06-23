# SOAP client/server crud app example

### Stack:
* Java8, Springboot, Lombok, Spring Webservices, SOAP/REST
* SpringData, MongoDB (embedded mongo), mongoTrek
* Docker-compose
* Mutual authentication

#### Build:
* Run command : `./buildAll.sh`

#### Starting app:
* Go to project root folder.
* Run command: `docker-compose up`

or

* Run command: `./totalRebuild.sh`

### Documentation:
* Visit http://localhost:8082/api/v1/ws/persons/persons.wsdl [deprecated]

* Visit https://localhost:8443/api/v1/ws/persons/persons.wsdl

Run request from browser:

`http://localhost:8083/api/v1/rest/persons/536f710fc55b2acc61000bd1`