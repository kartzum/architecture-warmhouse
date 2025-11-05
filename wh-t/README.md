# wh-t - temperature-api

## Описание
Приложение создано на базе Spring Boot 4. С использованием Java 25 и maven.

## Запуск через docker compose
Для запуска необходимо собрать приложение ```maven clean install```
Выполнить ```docker compose up --build```

## Запуск через spring-boot-docker-compose
Собрать приложение ```maven clean install```
Выполнить ```spring-boot:run -Pdocker-compose -Dspring-boot.run.profiles=docker-compose -f pom.xml```

## API

### temperature

```
curl http://localhost:8081/temperature?location=42
```

### thing

```
-- Thing - createThing

curl -X POST \
  -H 'Content-Type: application/json' \
  -d '{"id": "9963ca3b-dd05-4630-89fd-a0df391ec3eb", "name": "temperature-sensor", "thingType": "mqtt-temperature-sensor", "thingStatus": "CONNECTED"}' \
  http://localhost:8081/thing

{"id":"9963ca3b-dd05-4630-89fd-a0df391ec3eb","name":"temperature-sensor","thingType":"","thingStatus":"CONNECTED"}

-- getThingById 

curl -I -X GET http://localhost:8081/thing/9963ca3b-dd05-4630-89fd-a0df391ec3eb

{"id":"9963ca3b-dd05-4630-89fd-a0df391ec3eb","name":"temperature-sensor","thingType":"","thingStatus":"CONNECTED"

-- updateThingStatus

curl -X POST \
  -H 'Content-Type: application/json' \
  -d '{"name": "DISCONNECTED"}' \
  http://localhost:8081/thing/9963ca3b-dd05-4630-89fd-a0df391ec3eb/updateThingStatus

DISCONNECTED

-- getThingStatus

curl -X GET http://localhost:8081/thing/9963ca3b-dd05-4630-89fd-a0df391ec3eb/getThingStatus

DISCONNECTED

-- sendThingCommand

curl -X POST \
  -H 'Content-Type: application/json' \
  -d '{"payload": ""}' \
  http://localhost:8081/thing/9963ca3b-dd05-4630-89fd-a0df391ec3eb/sendThingCommand

-- deleteThingById

curl -X DELETE http://localhost:8081/thing/9963ca3b-dd05-4630-89fd-a0df391ec3eb

-- 

```

## Описание файлов

application.yaml - свойства приложения

application-docker-compose.yml - необходим для spring-boot-docker-compose

docker-compose.yml - основной файл для запуска docker-compose

Dockerfile - Dockerfile приложения

.env - переменные окружения

init-scripts/init.sql - скрипт для инициализации БД
