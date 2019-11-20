import com.mongodb.DBCollection
import com.mongodb.DBObject;
import com.mongodb.BasicDBObjectBuilder;

import MongoService

class OportunidadeRepository {
    
    void salvarOportunidade(Oportunidade oportunidade) {

        MongoService mongoService = new MongoService()

        DBCollection servidoresCollection = mongoService.getDB().getCollection("servidores");

        servidoresCollection.insert(this.createMongoObject(oportunidade))

        mongoService.close()

        println "Oportunidade salva na base!"
    }

    private DBObject createMongoObject(Oportunidade oportunidade) {

        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start()

        docBuilder.append("_id", oportunidade.id)
        docBuilder.append("titulo", oportunidade.titulo)
        docBuilder.append("descricao", oportunidade.descricao)
        docBuilder.append("uf", oportunidade.uf)
        docBuilder.append("periodoInscricao", oportunidade.periodoInscricao)
        docBuilder.append("link", oportunidade.link)
        docBuilder.append("enviado", oportunidade.enviado)

		return docBuilder.get();
    }
}