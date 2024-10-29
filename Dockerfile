FROM maven:3.8.5-openjdk-17
VOLUME /tmp
ADD target/demoqpj.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Xmx2g","-Xms2g","-Xss512k","-XX:MaxMetaspaceSize=256m","-XX:+UseG1GC","-XX:MaxGCPauseMillis=150","-XX:ParallelGCThreads=4","-XX:+PrintGCDetails","-Xlog:gc:/var/logs/demo/gc.log","-XX:G1ReservePercent=40","/app.jar","--spring.profiles.active=pro"]