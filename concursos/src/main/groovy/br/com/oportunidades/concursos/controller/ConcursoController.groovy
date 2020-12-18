package br.com.oportunidades.concursos.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import br.com.oportunidades.concursos.service.ConcursoService
import br.com.oportunidades.concursos.model.Concurso

@Controller
class ConcursoController {

    private List<String> estados() {
        List<String> lista = new ArrayList<>()
        lista.add("Todos")
        lista.add("Nacional")
        lista.add("AC")
        lista.add("AL")
        lista.add("AM")
        lista.add("AP")
        lista.add("BA")
        lista.add("CE")
        lista.add("DF")
        lista.add("ES")
        lista.add("GO")
        lista.add("MA")
        lista.add("MG")
        lista.add("MS")
        lista.add("MT")
        lista.add("PA")
        lista.add("PB")
        lista.add("PE")
        lista.add("PI")
        lista.add("PR")
        lista.add("RJ")
        lista.add("RN")
        lista.add("RO")
        lista.add("RR")
        lista.add("RS")
        lista.add("SC")
        lista.add("SE")
        lista.add("SP")
        lista.add("TO")
        return lista
    }

    @Autowired
    ConcursoService concursoService

    @GetMapping("/")
    public String index(Model model) {
        List listConcurso = concursoService.findAll()
        model.addAttribute("title", "Concursos")
        model.addAttribute("concursosEncontrados", listConcurso.size())
        model.addAttribute("listConcurso", listConcurso)
        model.addAttribute("estados", this.estados())
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
        return "visualizar"
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
        model.addAttribute("estados", this.estados())

        return "index"
    }
}