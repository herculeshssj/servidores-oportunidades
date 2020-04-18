import com.mongodb.DBCollection
import com.mongodb.DBCursor
import com.mongodb.DBObject
import com.mongodb.BasicDBObject
import com.mongodb.BasicDBObjectBuilder
import com.mongodb.WriteResult

import MongoService

class ConcursoRepository {

    void salvarConcursos(List<Concurso> concursos) {

        int concursoCadastrados = 0

        MongoService mongoService = new MongoService()

        DBCollection concursosCollection = mongoService.getDB().getCollection("concursos");

        for (Concurso concurso : concursos) {

            DBObject query = new BasicDBObject("hash", concurso.hash)

            // Cadastra somente os novos concursos
            if (concursosCollection.findOne(query) == null) {
                concursosCollection.insert(this.createMongoObject(concurso))
                concursoCadastrados++
            } 
        }

        mongoService.close()

        System.out.println("Quantidade de concursos cadastrados: " + concursoCadastrados)
    }

    private DBObject createMongoObject(Concurso concurso) {

        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start()
        docBuilder.append("_id", concurso.id)
        docBuilder.append("titulo", concurso.titulo)
        docBuilder.append("descricao", concurso.descricao)
        docBuilder.append("link", concurso.link)
        docBuilder.append("uf", concurso.uf)
        docBuilder.append("hash", concurso.hash)
        docBuilder.append("dataCadastro", concurso.dataCadastro)
        docBuilder.append("cargos", concurso.cargos)
        docBuilder.append("salario", concurso.salario)
        docBuilder.append("nivelEscolaridade", concurso.nivelEscolaridade)
        docBuilder.append("vagas", concurso.vagas)
        docBuilder.append("vagasCargosSalarios", concurso.vagasCargosSalarios)
        docBuilder.append("periodoInscricao", concurso.periodoInscricao)
        docBuilder.append("dataTerminoInscricao", concurso.dataTerminoInscricao)

		return docBuilder.get();
    }
}