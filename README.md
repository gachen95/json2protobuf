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
4. docker and docker-compose

## design
1. class JsonProtoBufServer: embedded jetty server
2. class SavePerson: receive json data format {“name:” “<name>”, “id”: <number>} and save into file in protobuf format. 

File name format is protobuf_session id.txt, so all the json data from the same session will be saved to the same file.

## populate jar file
```sh
$ git clone https://github.com/gachen95/json2protobuf.git
$ cd json2protobuf
$ mvn clean install
```
After that, there is ./target/json2protobuf-1.0-SNAPSHOT.jar

## Run

two options to run it
### 1. run it with docker-compse

```sh
$ docker-compose up -d
```

### 2. run it without docker/docker-compose
```sh
$ java -jar ./target/json2protobuf-1.0-SNAPSHOT.jar
```

## Test

### Test with postman
![postman](2019-01-30_14-22-35.png)

### Test with curl 

1. The 1st curl command to create the session
```sh
$ curl http://localhost:8080/person/save \
  -H "Content-Type: application/json; charset=UTF-8" \
  -d '{"name":"xyz","id":"123"}' 
```

2. To use the session created in step 1, use --cookie "JSESSIONID=<session id>;"  
   You can find session id from disk file name which is in format of protobuf_<session id>.txt, like protobuf_node0108p2if0otxusdw2secos8pr1.txt

```
curl http://localhost:8080/person/save \
  -H "Content-Type: application/json; charset=UTF-8" \
  -d '{"name":"xyz","id":"123"}' \
  --cookie "JSESSIONID=node01ke3hbp9borsc38476qf51ee122;"
```
