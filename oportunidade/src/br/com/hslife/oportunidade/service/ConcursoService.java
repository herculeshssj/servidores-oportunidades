package br.com.hslife.oportunidade.service;

import org.springframework.stereotype.Service;

@Service
public class ConcursoService {

    /*
    
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

    /*
        Busca os concursos registrados no link passado
    */
    public void buscarConcursos(String link) {
        System.out.println(link);
    }
    
    /*

        List<Concurso> listaConcursos = new ArrayList<Concurso>()

        def tagsoupParser = new org.ccil.cowan.tagsoup.Parser()

        def parser = new XmlSlurper(tagsoupParser)

        def html = parser.parse(link)

        def tagsContents = html.'**'.findAll { it.div.@class == 'ca'}

        tagsContents.each {

            Concurso concurso = new Concurso()

            concurso.titulo = it.div.a.text()
            concurso.descricao = it.div.a['@title']
            concurso.link = it.div.a['@href']

            it.div.div.each { value ->
                if (value['@class'] == 'cc') {
                    concurso.uf = value.text().trim()

                    if (concurso.uf.length() != 2) {
                        concurso.uf = ''
                    }
                }
                if (value['@class'] == 'cd') {
                    concurso.vagasCargosSalarios = value
                }
                if (value['@class'] == 'ce') {
                    concurso.periodoInscricao = value
                }
            }

            concurso.hash = this.generateMD5(concurso.toString())

            // Busca o concurso para saber se o mesmo já foi cadastrado
            Concurso c = concursoRepository.findByHash(concurso.hash)
            if (c == null) {
                listaConcursos.add(concurso)
            }
        }

        this.salvarConcursos(listaConcursos)
    }

    /*
        Cadastra os concursos encontrados na base
    *
    private void salvarConcursos(List<Concurso> concursos) {

        concursoRepository.saveAll(concursos)
        System.out.println("Concursos cadastrados: " + concursos.size())
    }

    private String generateMD5(String s) {
        MessageDigest digest = MessageDigest.getInstance("MD5")
        digest.update(s.bytes);
        return new BigInteger(1, digest.digest()).toString(16).padLeft(32, '0')
    }

    */
}
