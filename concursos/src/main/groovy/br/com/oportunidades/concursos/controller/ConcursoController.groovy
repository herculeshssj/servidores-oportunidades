package br.com.oportunidades.concursos.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import br.com.oportunidades.concursos.service.ConcursoService
import br.com.oportunidades.concursos.model.Concurso

@Controller
class ConcursoController {

    @Autowired
    ConcursoService concursoService

    @GetMapping("/")
    public String index(Model model) {
        List listConcurso = concursoService.findAll()
        model.addAttribute("title", "Concursos")
        model.addAttribute("concursosEncontrados", listConcurso.size())
        model.addAttribute("listConcurso", listConcurso);
        return "index";
    }

    @GetMapping(value = "/{concursoId}")
    public String findById(Model model, @PathVariable String concursoId) {
        Concurso concurso = null
        String errorMessage = null

        try {
            Optional<Concurso> optConcurso = concursoService.findById(concursoId)
            if (optConcurso.isPresent()) {
                concurso = optConcurso.get()
            } else {
                errorMessage = "Concurso não encontrado"
            }
        } catch (Exception ex) {
            ex.printStackTrace()
        }
    
        model.addAttribute("concurso", concurso)
        model.addAttribute("errorMessage", errorMessage)
        return "visualizar";
    }
}