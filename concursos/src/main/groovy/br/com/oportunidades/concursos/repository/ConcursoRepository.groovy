package br.com.oportunidades.concursos.repository

import org.springframework.data.mongodb.repository.MongoRepository;
import br.com.oportunidades.concursos.model.Concurso

interface ConcursoRepository extends MongoRepository<Concurso, String> {

    List findByTituloOrDescricaoOrUf(String titulo, String descricao, String uf)

    List findByUf(String uf)
}