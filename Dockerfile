# Build
FROM eclipse-temurin:17-jdk-alpine AS build
COPY . /app/
RUN chmod a+x /app/mvnw
WORKDIR /app/
RUN ./mvnw clean package -Pproduction
RUN ls -la /app/target

#Run
FROM eclipse-temurin:17-jre-alpine
COPY --from=build /app/target/flip-layout-demo-1.0-SNAPSHOT.jar /app/
WORKDIR /app/
EXPOSE 8080
ENTRYPOINT java -Xmx300m -jar flip-layout-demo-1.0-SNAPSHOT.jar 8080
