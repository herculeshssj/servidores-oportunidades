package br.com.hslife.oportunidade.repository

import br.com.hslife.oportunidade.model.Concurso
import ch.qos.logback.core.pattern.color.BoldBlueCompositeConverter
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query

interface ConcursoRepository extends MongoRepository<Concurso, String> {

    @Query(value = '{$and: [{titulo: {$regex: ?0,$options: "i"}},{descricao: {$regex: ?1,$options: "i"}},{uf: ?2}, {arquivado: false}]}', sort = '{titulo: 1}')
    List<Concurso> findByTituloAndDescricaoAndUf(String titulo, String descricao, String uf)

    @Query(value = '{$and: [{titulo: {$regex: ?0,$options: "i"}},{uf: ?1}, {arquivado: false}]}', sort = '{titulo: 1}')
    List<Concurso> findByTituloAndUf(String titulo, String uf)

    @Query(value = '{$and: [{descricao: {$regex: ?0,$options: "i"}},{uf: ?1}, {arquivado: false}]}', sort = '{titulo: 1}')
    List<Concurso> findByDescricaoAndUf(String descricao, String uf)

    @Query(value = '{$and: [{titulo: {$regex: ?0,$options: "i"}}, {arquivado: false}]}', sort = '{titulo: 1}')
    List<Concurso> findByTitulo(String titulo)

    @Query(value = '{$and: [{descricao: {$regex: ?0,$options: "i"}}, {arquivado: false}]}', sort = '{titulo: 1}')
    List<Concurso> findByDescricao(String descricao)

    @Query(value = '{$and: [{uf: ?0}, {arquivado: false}]}', sort = '{titulo: 1}')
    List<Concurso> findByUf(String uf)

    List<Concurso> findByArquivado(Boolean arquivado)

    Concurso findByHash(String hash)
}

