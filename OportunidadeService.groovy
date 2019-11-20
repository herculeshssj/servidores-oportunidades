import groovy.util.XmlSlurper
import java.util.ArrayList
import Oportunidade

class OportunidadeService {

    def listaOportunidade = new ArrayList<Oportunidade>()

    /*
        Acessa o endereço https://www.servidor.gov.br/assuntos/oportunidades/abertas
        e traz as oportunidades cadastradas.
    */
    def buscarOportunidade() {

        @Grab(group='org.ccil.cowan.tagsoup', module='tagsoup', version='1.2')
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
}