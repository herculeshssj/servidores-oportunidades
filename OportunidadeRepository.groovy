import com.mongodb.DBCollection
import com.mongodb.DBCursor
import com.mongodb.DBObject
import com.mongodb.BasicDBObject
import com.mongodb.BasicDBObjectBuilder

import MongoService

class OportunidadeRepository {
    
    void salvarOportunidades(List<Oportunidade> oportunidades) {

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

    List<Oportunidade> buscarOportunidadesNaoEnviadas() {

        MongoService mongoService = new MongoService()

        DBCollection servidoresCollection = mongoService.getDB().getCollection("servidores")

        DBObject query = new BasicDBObject("enviado", false)
        BasicDBObject fields = new BasicDBObject("titulo", 1).append("link", 1)

        DBCursor cursor = servidoresCollection.find(query, fields)

        List<Oportunidade> listaResultado = new ArrayList<Oportunidade>()

        try {
            while (cursor.hasNext()) {
                
                BasicDBObject dbobj = cursor.next()
                
                Oportunidade op = new Oportunidade()
                op.titulo = dbobj.titulo
                op.descricao = dbobj.descricao
                op.periodoInscricao = dbobj.periodoInscricao
                op.uf = dbobj.uf
                op.link = dbobj.link

                listaResultado.add(op)

            }
        } finally {
            cursor.close()
        }
	
        mongoService.close()

        return listaResultado
    }
}