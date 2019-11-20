import com.mongodb.MongoClient
import com.mongodb.DBCollection
import com.mongodb.DB
import com.mongodb.BasicDBObject

class MongoService {

    private MongoClient mongoClient

    def host = "mongodb-container"
    def port = 27017
    def databaseName = "oportunidade"
    def user = "root"
    def pass = "root"

    public MongoClient getClient() {
        mongoClient = mongoClient ?: new MongoClient(host, port)
        return mongoClient
    }

    public DBCollection collection(collectionName) {
        DB db = getClient().getDB(databaseName)
        
        boolean authenticated = db.auth(user, pass)
        if (authenticated) {
            println "Authenticated on DB"
        } else {
            println "Failed to authenticate on DB"
        }

        return db.getCollection(collectionName)
    }
}