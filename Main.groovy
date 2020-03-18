@Grab(group = 'org.mongodb', module = 'mongodb-driver', version = '3.11.2')
@Grab(group = 'org.ccil.cowan.tagsoup', module = 'tagsoup', version = '1.2')

import OportunidadeService
import Oportunidade

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

        // Inicializa o serviço
        OportunidadeService service = new OportunidadeService()

        List<String> linkOportunidades = new ArrayList<>()
        linkOportunidades.add("https://www.servidor.gov.br/assuntos/oportunidades/divulgacao/cessao-1/cessao")
        linkOportunidades.add("https://www.servidor.gov.br/assuntos/oportunidades/divulgacao/movimentacao/movimentacao")
        linkOportunidades.add("https://www.servidor.gov.br/assuntos/oportunidades/divulgacao/requisicao/requisicao")
        linkOportunidades.add("https://www.servidor.gov.br/assuntos/oportunidades/divulgacao/ex-territorios/ex-territorios")

        // Busca as oportunidades
        for (String link : linkOportunidades) {
            service.buscarOportunidades(link)
        }        

        // Envia a notificação das novas oportunidades
        service.notificarOportunidades(telegramBotToken, channelID)

    }    
}