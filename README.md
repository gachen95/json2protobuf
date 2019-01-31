# json2protobuf

## Prerequisites
1. I tested it in ubuntu 18.0.4 LTS
2. java version
  ```sh
  $ java --version
  openjdk 10.0.2 2018-07-17
  OpenJDK Runtime Environment (build 10.0.2+13-Ubuntu-1ubuntu0.18.04.4)
  OpenJDK 64-Bit Server VM (build 10.0.2+13-Ubuntu-1ubuntu0.18.04.4, mixed mode)
  ```
3. maven version
  ```sh
  $ mvn --version
  Apache Maven 3.5.2
  Maven home: /usr/share/maven
  ```

## Run
```sh
$ git clone https://github.com/gachen95/json2protobuf.git
$ cd json2protobuf
$ mvn clean install
$ cd target
$ java -jar json2protobuf-1.0-SNAPSHOT.jar
```

## Test

### Test with postman

### Test with curl 

```
curl http://localhost:8080/person/save \
  -H "Content-Type: application/json; charset=UTF-8" \
  -d '{"name":"xyz","id":"123"}' 

curl http://localhost:8080/person/save \
  -H "Content-Type: application/json; charset=UTF-8" \
  -d '{"name":"xyz","id":"123"}' \
  --cookie "JSESSIONID=node01ke3hbp9borsc38476qf51ee122;"
```