import com.mongodb.DBCollection
import com.mongodb.DBObject
import com.mongodb.BasicDBObject
import com.mongodb.BasicDBObjectBuilder

import MongoService

/*
    Artigo de referência: https://www.journaldev.com/3963/mongodb-java-crud-example-tutorial
*/

class OportunidadeRepository {
    
    void salvarOportunidade(List<Oportunidade> oportunidades) {

        MongoService mongoService = new MongoService()

        DBCollection servidoresCollection = mongoService.getDB().getCollection("servidores");

        for (Oportunidade oportunidade : oportunidades) {

            DBObject query = new BasicDBObject("hash", oportunidade.hash)

            // Cadastra somente as oportunidades novas
            if (servidoresCollection.findOne(query) == null) {
                servidoresCollection.insert(this.createMongoObject(oportunidade))
            } 
        }

        mongoService.close()
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
        docBuilder.append("hash", oportunidade.hash)

		return docBuilder.get();
    }
}