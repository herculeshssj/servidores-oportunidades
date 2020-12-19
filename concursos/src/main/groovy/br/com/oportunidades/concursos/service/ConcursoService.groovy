package br.com.oportunidades.concursos.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import br.com.oportunidades.concursos.repository.ConcursoRepository
import br.com.oportunidades.concursos.model.Concurso


@Service
class ConcursoService {

    @Autowired
    ConcursoRepository concursoRepository

    Optional<Concurso> findById(String id) {
        return concursoRepository.findById(id);
    }

    List findByTituloOrDescricaoOrUf(String titulo, String descricao, String uf) {
        List<Concurso> listaConcurso = new ArrayList<>()

        if (uf.equalsIgnoreCase('Nacional'))
            uf = ''

        if (uf.equalsIgnoreCase('Todos')) {
            // A busca só inclui título e descricao

            if (titulo.isEmpty() && !descricao.isEmpty()) {
                listaConcurso = concursoRepository.findByDescricao(descricao)
            } else if (!titulo.isEmpty() && descricao.isEmpty()) {
                listaConcurso = concursoRepository.findByTitulo(titulo)
            } else {
                // TODO fazer buscar por titulo e descricao
                // TODO caso titulo e descricao sejam vazios, executar o findAll
                listaConcurso = concursoRepository.findByArquivado(false)
            }

        } else {

            if (titulo.isEmpty() && !descricao.isEmpty()) {
                listaConcurso = concursoRepository.findByDescricaoAndUf(descricao, uf)
            } else if (!titulo.isEmpty() && descricao.isEmpty()) {
                listaConcurso = concursoRepository.findByTituloAndUf(titulo, uf)
            } else {
                listaConcurso = concursoRepository.findByUf(uf)
            }
        }

        return listaConcurso
    }

    void arquivar(String concursoId) {
        Optional<Concurso> concursoOptional = concursoRepository.findById(concursoId)

        if (concursoOptional.isPresent()) {
            Concurso concurso = concursoOptional.get()
            concurso.arquivado = true
            concursoRepository.save(concurso)
        }
    }
}