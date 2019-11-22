import com.mongodb.DBCollection
import com.mongodb.DBCursor
import com.mongodb.DBObject
import com.mongodb.BasicDBObject
import com.mongodb.BasicDBObjectBuilder
import com.mongodb.WriteResult

import MongoService

class OportunidadeRepository {
    
    void salvarOportunidades(List<Oportunidade> oportunidades) {

        MongoService mongoService = new MongoService()

        DBCollection servidoresCollection = mongoService.getDB().getCollection("selecoes");

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

    List<Oportunidade> buscarOportunidadesNaoEnviadas() {

        MongoService mongoService = new MongoService()

        DBCollection servidoresCollection = mongoService.getDB().getCollection("selecoes")

        DBObject query = new BasicDBObject("enviado", false)

        DBCursor cursor = servidoresCollection.find(query)

        List<Oportunidade> listaResultado = new ArrayList<Oportunidade>()

        try {
            while (cursor.hasNext()) {
                
                DBObject dbobj = cursor.next()

                Oportunidade op = new Oportunidade()
                op.titulo = dbobj.titulo
                op.descricao = dbobj.descricao
                op.periodoInscricao = dbobj.periodoInscricao
                op.uf = dbobj.uf
                op.link = dbobj.link
                op.hash = dbobj.hash

                listaResultado.add(op)

            }
        } finally {
            cursor.close()
        }
	
        mongoService.close()

        return listaResultado
    }

    void atualizarOportunidades(List<Oportunidade> oportunidades) {

        MongoService mongoService = new MongoService()

        DBCollection servidoresCollection = mongoService.getDB().getCollection("selecoes");

        for (Oportunidade oportunidade : oportunidades) {

            DBObject query = new BasicDBObject("hash", oportunidade.hash)
            DBObject update = new BasicDBObject()
            update.put('$set', new BasicDBObject("enviado", true))
            WriteResult result = servidoresCollection.update(query, update)

        }

        mongoService.close()
    }
}