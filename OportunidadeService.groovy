import java.security.MessageDigest
import groovy.util.XmlSlurper
import Oportunidade

class OportunidadeService {

    /*
        Acessa o endereço https://www.servidor.gov.br/assuntos/oportunidades/abertas
        e traz as oportunidades disponíveis. 
    */
    void buscarOportunidades() {

        List<Oportunidade> listaOportunidade = new ArrayList<Oportunidade>()

        def tagsoupParser = new org.ccil.cowan.tagsoup.Parser()

        def parser = new XmlSlurper(tagsoupParser)

        def html = parser.parse("https://www.servidor.gov.br/assuntos/oportunidades/abertas")

        Oportunidade op = null

        html.'**'.findAll { it.@class == 'tileContent' }.each {
            
            op = new Oportunidade()
            op.titulo = it.h2.a.text()
            op.descricao = it.p.span.text()
            op.link = it.h2.a.@href.text()
            op.hash = this.generateMD5(op.toString())
            listaOportunidade.add(op)

        }

        this.salvarOportunidades(listaOportunidade)
    }

    /*
        Cadastra as oportunidades encontradas na base
    */
    private void salvarOportunidades(List<Oportunidade> oportunidades) {

        OportunidadeRepository repository = new OportunidadeRepository()

        repository.salvarOportunidades(oportunidades)

    }

    /*
        Notifica aos usuários das novas oportunidades disponíveis no site
    */
    void notificarOportunidades() {
        List<Oportunidade> listaOportunidades = this.recuperarNovasOportunidades()

        // TODO seguir a partir daqui
    }

    /*
        Recupera da base as oportunidades cadastradas e que ainda não foram enviadas para os
        usuários.
    */
    private List<Oportunidade> recuperarNovasOportunidades() {
        OportunidadeRepository repository = new OportunidadeRepository()

        return repository.buscarOportunidadesNaoEnviadas()
    }

    private String generateMD5(String s) {
        MessageDigest digest = MessageDigest.getInstance("MD5")
        digest.update(s.bytes);
        return new BigInteger(1, digest.digest()).toString(16).padLeft(32, '0')
    } 
}