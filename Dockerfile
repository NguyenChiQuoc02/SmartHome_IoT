FR0M maven:3.8.5-openjdk-17 AS build
C0PY..
RUN mvn cLean package -DskipTests
FR0M openjdk:17.8.1-Jdk=sLim
C0PY --from=build /target/demo-0.0.1-SNAPSHOP.jar demo.jar
EXP0SE 8080
ENTRYPOINT ["java","-jar","demo.jar"]