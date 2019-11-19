import groovy.util.XmlSlurper

class Oportunidades {

    /*
        Acessa o endereço https://www.servidor.gov.br/assuntos/oportunidades/abertas
        e traz as oportunidades cadastradas.
    */
    def buscarOportunidades() {

        @Grab(group='org.ccil.cowan.tagsoup', module='tagsoup', version='1.2')
        def tagsoupParser = new org.ccil.cowan.tagsoup.Parser()

        def parser = new XmlSlurper(tagsoupParser)

        def html = parser.parse("https://www.servidor.gov.br/assuntos/oportunidades/abertas")

        html.'**'.findAll { it.@class == 'tileContent' }.each {
            //println it
            println it.h2.a.text()
            println it.p.span.text()
            println it.h2.a.@href.text()
            println ""
        }

        println "Oportunidades carregadas!"
    }
}