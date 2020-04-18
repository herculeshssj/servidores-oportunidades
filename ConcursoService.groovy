import java.security.MessageDigest
import groovy.util.XmlSlurper
import Concurso

class ConcursoService {

    /*
        Busca os concursos registrados no link passado
    */
    void buscarConcursos(String link) {

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
                    concurso.uf = value
                }
                if (value['@class'] == 'cd') {
                    concurso.vagasCargosSalarios = value
                }
                if (value['@class'] == 'ce') {
                    concurso.periodoInscricao = value
                }
            }

            concurso.hash = this.generateMD5(concurso.toString())  

            listaConcursos.add(concurso)
        }

        this.salvarConcursos(listaConcursos)
    }

    /*
        Cadastra os concursos encontrados na base
    */
    private void salvarConcursos(List<Concurso> concursos) {

        ConcursoRepository repository = new ConcursoRepository()

        repository.salvarConcursos(concursos)

    }

    private String generateMD5(String s) {
        MessageDigest digest = MessageDigest.getInstance("MD5")
        digest.update(s.bytes);
        return new BigInteger(1, digest.digest()).toString(16).padLeft(32, '0')
    } 
}