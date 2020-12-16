package br.com.oportunidades.concursos.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import br.com.oportunidades.concursos.repository.ConcursoRepository
import br.com.oportunidades.concursos.model.Concurso


@Service
class ConcursoService {

    @Autowired
    ConcursoRepository concursoRepository

    List findAll() {
        return concursoRepository.findAll();
    }

    Optional<Concurso> findById(String id) {
        return concursoRepository.findById(id);
    }
}