import groovy.util.XmlSlurper
import Oportunidade

class OportunidadeService {

    /*
        Acessa o endereço https://www.servidor.gov.br/assuntos/oportunidades/abertas
        e traz as oportunidades disponíveis. 
    */
    void buscarOportunidades() {
        this.salvarOportunidades()
    }

    /*
        Cadastra as oportunidades encontradas na base
    */
    private void salvarOportunidades() {

        OportunidadeRepository repository = new OportunidadeRepository()

        Oportunidade oportunidade = new Oportunidade(
            titulo: 'Teste',
            descricao: 'Descricao de teste',
            link: 'https://teste.com',
            uf: 'RJ',
            periodoInscricao: 'até 31/12/2019'
        )

        repository.salvarOportunidade(oportunidade)

    }

    /*
        Notifica aos usuários das novas oportunidades disponíveis no site
    */
    void notificarOportunidades() {

    }

    /*
        Recupera da base as oportunidades cadastradas e que ainda não foram enviadas para os
        usuários.
    */
    private void recuperarNovasOportunidades() {

    }
/*
    def listaOportunidade = new ArrayList<Oportunidade>()

    /*
        Acessa o endereço https://www.servidor.gov.br/assuntos/oportunidades/abertas
        e traz as oportunidades cadastradas.
    *
    def buscarOportunidade() {

        def tagsoupParser = new org.ccil.cowan.tagsoup.Parser()

        def parser = new XmlSlurper(tagsoupParser)

        def html = parser.parse("https://www.servidor.gov.br/assuntos/oportunidades/abertas")

        Oportunidade op = null

        html.'**'.findAll { it.@class == 'tileContent' }.each {
            
            op = new Oportunidade()
            op.titulo = it.h2.a.text()
            op.descricao = it.p.span.text()
            op.link = it.h2.a.@href.text()
            listaOportunidade.add(op)

        }
    }

    def salvarOportunidades() {

        def mongoService = new MongoService()
        def foo = mongoService.collection('servidores')

        Oportunidade oportunidade = new Oportunidade(
            titulo: 'Teste',
            descricao: 'Descricao de teste',
            link: 'https://teste.com'
        )

        def json = JsonOutput.toJson(oportunidade).replace("{", "[").replace("}", "]")

        //def data = json.collect { it as BasicDBObject }
        def data = [
            [firstName: 'Jane', lastName: 'Doe'],
            [firstName: 'Elvis', lastName: 'Presley']
        ].collect { it as BasicDBObject }

        foo.insert(data)

        foo.insert(data)

        println "Oportunidades salvas na base!"
    }
    */
}