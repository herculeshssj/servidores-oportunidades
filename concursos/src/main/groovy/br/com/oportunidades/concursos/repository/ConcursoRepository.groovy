package br.com.oportunidades.concursos.repository

import org.springframework.data.mongodb.repository.MongoRepository;
import br.com.oportunidades.concursos.model.Concurso
import org.springframework.data.mongodb.repository.Query

interface ConcursoRepository extends MongoRepository<Concurso, String> {

    @Query(value = '{$and: [{titulo: {$regex: ?0,$options: "i"}},{descricao: {$regex: ?1,$options: "i"}},{uf: ?2}]}', sort = '{titulo: 1}')
    List findByTituloAndDescricaoAndUf(String titulo, String descricao, String uf)

    @Query(value = '{$and: [{titulo: {$regex: ?0,$options: "i"}},{uf: ?1}]}', sort = '{titulo: 1}')
    List findByTituloAndUf(String titulo, String uf)

    @Query(value = '{$and: [{descricao: {$regex: ?0,$options: "i"}},{uf: ?1}]}', sort = '{titulo: 1}')
    List findByDescricaoAndUf(String descricao, String uf)

    @Query(value = '{$and: [{titulo: {$regex: ?0,$options: "i"}}]}', sort = '{titulo: 1}')
    List findByTitulo(String titulo)

    @Query(value = '{$and: [{descricao: {$regex: ?0,$options: "i"}}]}', sort = '{titulo: 1}')
    List findByDescricao(String descricao)

    @Query(value = '{uf: ?0}', sort = '{titulo: 1}')
    List findByUf(String uf)
}