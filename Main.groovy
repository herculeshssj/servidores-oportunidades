@Grab(group = 'org.mongodb', module = 'mongodb-driver', version = '3.11.2')
@Grab(group = 'org.ccil.cowan.tagsoup', module = 'tagsoup', version = '1.2')

import OportunidadeService
import ConcursoService

public static void main(String... args) {

    // Obtém o token do Telegram Bot
    String telegramBotToken = null
    Long channelID = null
    if (args.length != 0) {
        telegramBotToken = args[0]

        if (args.length == 2) {
            channelID = Long.parseLong(args[1])
        }
    }

    // Verifica se o token do Telegram Bot foi informado
    if (telegramBotToken == null) {
        println "Token do Telegram Bot não informado. Saindo..."
    } else {

        // Inicializa os serviços
        OportunidadeService service = new OportunidadeService()
        ConcursoService serviceConcurso = new ConcursoService()

        List<String> linkOportunidades = new ArrayList<>()
        linkOportunidades.add("https://www.gov.br/servidor/pt-br/centrais-de-conteudo/oportunidades/divulgacao/cessao-1/cessao")
        linkOportunidades.add("https://www.gov.br/servidor/pt-br/centrais-de-conteudo/oportunidades/divulgacao/movimentacao/movimentacao")
        linkOportunidades.add("https://www.gov.br/servidor/pt-br/centrais-de-conteudo/oportunidades/divulgacao/requisicao/requisicao")
        linkOportunidades.add("https://www.gov.br/servidor/pt-br/centrais-de-conteudo/oportunidades/divulgacao/ex-territorios/ex-territorios")

        // Busca as oportunidades
        for (String link : linkOportunidades) {
            service.buscarOportunidades(link)
        }        

        // Envia a notificação das novas oportunidades
        service.notificarOportunidades(telegramBotToken, channelID)

        // Atualiza o cadastro de concursos
        List<String> linkConcursos = new ArrayList<>()
        linkConcursos.add("https://www.pciconcursos.com.br/concursos/")
        linkConcursos.add("https://www.pciconcursos.com.br/concursos/nacional/")
        linkConcursos.add("https://www.pciconcursos.com.br/concursos/sudeste/")
        linkConcursos.add("https://www.pciconcursos.com.br/concursos/sul/")
        linkConcursos.add("https://www.pciconcursos.com.br/concursos/norte/")
        linkConcursos.add("https://www.pciconcursos.com.br/concursos/nordeste/")
        linkConcursos.add("https://www.pciconcursos.com.br/concursos/centrooeste/")

        for (String link : linkConcursos) {
            serviceConcurso.buscarConcursos(link)
        }
    }    
}