mvn clean package
#java -jar target/albums-1.0-SNAPSHOT.jar
docker build -t cleptes/albums .
docker stop albums
docker rm albums
docker run -d --name albums -p 8080:8080 cleptes/albums