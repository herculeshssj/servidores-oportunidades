package br.com.oportunidades.concursos.controller

import br.com.oportunidades.concursos.util.Util
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
        model.addAttribute("title", "Concursos")
        model.addAttribute("concursosEncontrados", 0)
        model.addAttribute("estados", Util.estados())
        return "index";
    }

    @GetMapping(value = "/{concursoId}")
    public String findById(Model model, @PathVariable String concursoId) {
        Concurso concurso = new Concurso()
        String errorMessage = ''

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
        return "visualizar"
    }

    @PostMapping("/arquivar")
    public String arquivar(Model model, @ModelAttribute("concursoId") String concursoId) {
        concursoService.arquivar(concursoId)
        return this.findById(model, concursoId)
    }

    @PostMapping("/buscar")
    public String buscar(Model model,
                         @ModelAttribute("buscaTitulo") String buscaTitulo,
                         @ModelAttribute("buscaDescricao") String buscaDescricao,
                         @ModelAttribute("buscaUf") String buscaUf) {

        List<Concurso> listConcurso = concursoService.findByTituloOrDescricaoOrUf(buscaTitulo, buscaDescricao, buscaUf)

        model.addAttribute("title", "Concursos")
        model.addAttribute("concursosEncontrados", listConcurso.size())
        model.addAttribute("listConcurso", listConcurso);
        model.addAttribute("estados", Util.estados())

        return "index"
    }
}