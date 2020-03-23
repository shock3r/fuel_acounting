![Java CI](https://github.com/alexanderignathick/aignathick-fuel-accounting/workflows/Java%20CI/badge.svg)

# Fuel accounting
main course project

## How build
Setup java 11 and Maven, see [enviroment_setup.md](enviroment_setup.md)

## Build project 
Goto Project folder and execute  
    
    mvn clean install
    
## Run jetty web server
    mvn jetty:run -pl fuel-accounting-web-app

## Connect to web application using localhost or 127.0.0.1 adress and 8081 port
    Applicatin was tested on browser google chrome 80.0.3987.132
    in path field use adress: "http://localhost:8081/".
    this adress will redirect you on fuels page: "http://localhost:8081/fuels"
    For date moment 2020_03_23 10:30 you can use http://localhost:8081/fuel" and
    "http://localhost:8081/fuels", "http://localhost:8081/transports" (cant resolve Fuels type column yet), + transport edit buttun that        will redirect to "http://localhost:8081/transport/transportId" page.
