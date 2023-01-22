FROM maven:3-jdk-11 as builder

COPY . /data

WORKDIR /data

RUN mvn package

FROM openjdk:11

COPY --from=builder /data/target/abwehr.jar /

ENTRYPOINT []
CMD ["java", "-jar", "abwehr.jar"]