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

## Dev
Run RabbitMQ:
```
docker run --rm --hostname rabbitmq --name rabbitmq -e RABBITMQ_DEFAULT_USER='michal' -e RABBITMQ_DEFAULT_PASS='strongpassword' rabbitmq:3.8.6-rc.1-management-alpine
```

Run MongoDB:
```
docker run --rm --hostname mongodb --name mongodn -e MONGO_INITDB_ROOT_USERNAME='michal' -e MONGO_INITDB_ROOT_PASSWORD='strongpassword' mongo:4.4.0
```

Build backend:
```
./backend/mvnw clean install
```

Run backend:
```
java -jar TODO
```

Build frontend:
```
cd frontend && yarn install
```

Run frontend:
```
cd frontend && yarn start
```
