import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.DB

/*


import com.mongodb.BasicDBObject

*/
class MongoService {

    private MongoClient mongoClient
    private DB db

    private String host = "mongodb-container"
    private int port = 27017
    private String databaseName = "oportunidade"
    private String user = "oport"
    private String password = "s3cretp4assw0rd"

    public DB getDB() {

        final MongoCredential credencial = MongoCredential.createCredential(user, databaseName, password.toCharArray())

        ServerAddress serverAddress = new ServerAddress(host, port)

        this.mongoClient = new MongoClient(serverAddress, new ArrayList<MongoCredential>(){
            { 
                add(credencial)
            }
        })

        this.db = this.mongoClient.getDB(databaseName)

        return this.db
    }

    public void close() {
        this.mongoClient.close()
    }
}