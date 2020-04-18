@Grab(group = 'org.ccil.cowan.tagsoup', module = 'tagsoup', version = '1.2')

import groovy.util.XmlSlurper

class Concurso {
    Long id
    String titulo
    String descricao
    String link
    String uf
    String hash
    Date dataCadastro
    String cargos
    String salario
    String nivelEscolaridade
    Integer vagas
    String vagasCargosSalarios
    String periodoInscricao
    Date dataTerminoInscricao


public static void main(String... args) {
    
    def link = "https://www.pciconcursos.com.br/concursos/"
    //def link = "https://www.pciconcursos.com.br/concursos/nacional/"
    //def link = "https://www.pciconcursos.com.br/concursos/sudeste/"
    //def link = "https://www.pciconcursos.com.br/concursos/sul/"
    //def link = "https://www.pciconcursos.com.br/concursos/norte/"
    //def link = "https://www.pciconcursos.com.br/concursos/nordeste/"
    //def link = "https://www.pciconcursos.com.br/concursos/centrooeste/"

    def tagsoupParser = new org.ccil.cowan.tagsoup.Parser()

    def parser = new XmlSlurper(tagsoupParser)

    def html = parser.parse(link)

    def tagsContents = html.'**'.findAll { it.div.@class == 'ca'}

    tagsContents.each {
        System.out.println("Concurso: " + it.div.a.text())
        System.out.println("Descrição: " + it.div.a['@title'])
        System.out.println("Link: " + it.div.a['@href'])
        it.div.div.each { value ->
            if (value['@class'] == 'cc') {
                System.out.println("Estado: " + value)
            }
            if (value['@class'] == 'cd') {
                System.out.println("Vagas, cargos e salários: " + value)
            }
            if (value['@class'] == 'ce') {
                System.out.println("Inscrição até: " + value)
            }
        }

        System.out.print("\n")
    }


}

}