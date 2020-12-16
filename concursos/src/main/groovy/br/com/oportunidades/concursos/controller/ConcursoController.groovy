package br.com.oportunidades.concursos.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import br.com.oportunidades.concursos.service.ConcursoService
import br.com.oportunidades.concursos.model.Concurso

@RestController
@RequestMapping("/concurso")
class ConcursoController {

    @Autowired
    ConcursoService concursoService

    @GetMapping("")
    List findAll() {
        return concursoService.findAll()
    }

    @GetMapping("{id}")
    Optional<Concurso> findById(@PathVariable String id) {
        return concursoService.findById(id)
    }
}