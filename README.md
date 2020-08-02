# Simple application to demonstrate migration to Google Cloud
![Architecture](on_premise_architecture.png)


## Docker Compose
Build:
```
docker-compose build
```

Run:
```
docker-compose up
```

Open:  
http://localhost:81/

## Dev
Run RabbitMQ and MongoDB:
```
docker-compose up mongo rabbit
```

Build and run backend:
```
cd backend &&
./backend/mvnw clean install &&
java -jar TODO
```

Build and run frontend:
```
cd frontend &&
yarn install &&
yarn start
```

Open:  
http://localhost:4200/
