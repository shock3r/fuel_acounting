![Java CI](https://github.com/alexanderignathick/aignathick-fuel-accounting/workflows/Java%20CI/badge.svg)

# Fuel accounting
main course project

## How build
Setup java 11 and Maven, see [enviroment_setup.md](enviroment_setup.md)

## Build project 
Goto Project folder and execute  
    
    mvn clean install

## Rest Server
### Start Rest using Maven Jetty plugin
```
mvn jetty:run -pl fuel-accounting-rest-app
```    
or
 ``` 
cd fuel-accounting-rest-app
mvn jetty:run
```

## Available Rest endpoints
### version
    curl --location --request GET 'localhost:8088/version'
### fuel dtos
```
curl --location --request GET 'localhost:8088/fuel_dtos'
```
Pretty print json:
```
curl --location --request GET 'localhost:8088/fuel_dtos' | json_pp       
```
### fuels

#### findAll
    curl --location --request GET 'localhost:8088/fuels' | json_pp

#### create
    curl --location --request POST 'localhost:8088/fuels/' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "fuelId": null,
        "fuelName": "PostmanFuel"
    }'
    
#### findById(fuelId)
    curl --location --request GET 'localhost:8088/fuel/1'| json_pp

#### update
    curl --location --request PUT 'localhost:8088/fuels/' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "fuelId": 1,
        "fuelName": "AI95"
    }'

#### delete
    curl --location --request DELETE 'localhost:8088/fuels/3'
    
### transports

#### findAll
    curl --location --request GET 'localhost:8088/transports' | json_pp
#### create
    curl --location --request POST 'localhost:8088/transports' \
    --header 'Content-Type: application/json' \
    --data-raw '{
            "transportId": null,
            "transportName": "Fiat Punto",
            "fuelId": 2,
            "transportTankCapasity": 47.0,
            "transportDate": "2020-04-10"
        }'

## Start monolite web application using Maven Jetty plugin
 ```
 mvn jetty:run -pl fuel-accounting-web-app
 ```    
 or
 ``` 
 cd fuel-accounting-web-app
 mvn jetty:run
 ```

## Connect to web application using localhost or 127.0.0.1 adress and 8081 port
    Application was tested on browser google chrome 80.0.3987.132
    in path field use adress: "http://localhost:8081/".
    this adress will redirect you on fuels page: "http://localhost:8081/fuels"
    For date moment 2020_04_03 23:23 monolite web application is ready

