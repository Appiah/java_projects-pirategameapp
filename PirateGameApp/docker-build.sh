./gradlew assemble
docker build . -t pirategameapp
docker run -p 8080:8080 pirategameapp