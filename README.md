Running a Groovy script
=======================

```
docker run --rm -v "$PWD":/home/groovy/scripts -w /home/groovy/scripts groovy groovy <script> <script-args>
```

Running MongoDB Docker container
================================

```
docker run --restart=unless-stopped --name mongodb-container -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=root -v "$HOME"/MONGODBDATA:/data/db -v "$HOME"/backup:/backup -d mongo
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
docker run --name servidores-oportunidades --rm --link mongodb-container -v "$PWD":/home/groovy/scripts -w /home/groovy/scripts groovy groovy Main.groovy <Telegram Bot token> <Telegram Channel ID> [-t]
```

The '-t' parameter (optional) indicates a running in thread, useful to run the application in background or inside a container.

If you want to keep the application running continuously, change the "--rm" parameter to "--restart=unless-stopped" in the Docker command. After the "-w" parameter, add a "-d" parameter for container run in background.

Backing the database
====================

```
docker exec -i mongodb-container mongodump -u oport -p 's3cretp4assw0rd' --db oportunidade --out /backup/oportunidade.dump
```

Restoring the database
======================

```
docker exec -t mongodb-container mongorestore -u oport -p 's3cretp4assw0rd' --db oportunidade --drop /backup/oportunidade.dump/oportunidade
```

Troubleshooting
===============

If you have problems with an SSL certificate, import the CA certificate using the Java class InstallCert.java.

## Compilation:


```
javac InstallCert.java
```

## Execution:

```
java InstallCert www.example.com
```

Include only the domain, the first part between the "https://" and the first slash.

After that, run the application using this command:

```
docker run --name servidores-oportunidades --rm --link mongodb-container -v "$PWD":/home/groovy/scripts -v $PWD/jssecacerts:/opt/java/openjdk/lib/security/jssecacerts -w /home/groovy/scripts groovy groovy Main.groovy <Telegram Bot token> <Telegram Channel ID> [-t]
```