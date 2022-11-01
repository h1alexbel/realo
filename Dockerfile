FROM openjdk:17.0.2
ADD target/RealEstateApp-0.0.1-SNAPSHOT.jar RealEstateApp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "RealEstateApp-0.0.1-SNAPSHOT.jar"]
EXPOSE 5689