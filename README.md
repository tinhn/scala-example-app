
# Setup kafka with docker
`docker-compose -f kafka-docker-compose.yml up -d`

* kafka-ui lets us visualize our cluster state in the browser on port 8080
* create new topic: getting-started

# Build + Package Scala Project
run sbt command `sbt clean compile assembly`


# Run jar on windows
``` 
java -jar C:\Users\public\KafkaScalaDemo\target\scala-3.3.4\KafkaScalaDemo-assembly-0.1.0.jar
```
