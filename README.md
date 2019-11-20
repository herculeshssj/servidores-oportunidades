Running a Groovy script
=======================

```
docker run --rm -v "$PWD":/home/groovy/scripts -w /home/groovy/scripts groovy groovy <script> <script-args>
```

Running MongoDB Docker container
================================

```
docker run --restart=unless-stopped --name mongodb-container -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=root -v "$HOME"/MONGODBDATA:/data/db -d mongo
```

Running MongoDB Express web client
==================================

```
docker run --name mongodb-express -p 8081:8081 --link mongodb-container -e ME_CONFIG_MONGODB_ADMINUSERNAME=root -e ME_CONFIG_MONGODB_ADMINPASSWORD=root -e ME_CONFIG_MONGODB_SERVER=mongodb-container -d mongo-express
```

Configuring database
====================

Enter on MongDB container

```
docker exec -it mongodb-container mongo -u root -p 'root'
```

Create a new user

```
> use oportunidade
> db.createUser({
    user: 'oport',
    pwd: 's3cretp4assw0rd',
    roles: [{ role: 'readWrite', db:'oportunidade'}]
})
```

Create a empty collection

```
> db.createCollection("delete_me")
```

Exit from MongoDB console

```
> quit()
```

Running application
===================

```
docker run --rm --link mongodb-container -v "$PWD":/home/groovy/scripts -w /home/groovy/scripts groovy groovy Main.groovy
```