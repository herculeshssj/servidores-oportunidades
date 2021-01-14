package br.com.hslife.oportunidade.repository

import br.com.hslife.oportunidade.model.Oportunidade
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface OportunidadeRepository extends MongoRepository<Oportunidade, String>{

    Oportunidade findByHash(String hash)

    @Query(value = '{enviado: false}')
    List<Oportunidade> findAllNaoEnviados()
}