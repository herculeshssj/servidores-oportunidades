@Grab(group = 'org.mongodb', module = 'mongodb-driver', version = '3.11.2')
@Grab(group = 'org.ccil.cowan.tagsoup', module = 'tagsoup', version = '1.2')

import OportunidadeService
import Oportunidade

public static void main(String... args) {

    // Obtém o token do Telegram Bot
    String telegramBotToken = null
    if (args.length != 0) {
        telegramBotToken = args[0]
    }

    // Verifica se o token do Telegram Bot foi informado
    if (telegramBotToken == null) {
        println "Token do Telegram Bot não informado. Saindo..."
    } else {

        // Inicializa o serviço
        OportunidadeService service = new OportunidadeService()

        // Busca as oportunidades
        service.buscarOportunidades()

        // Envia a notificação das novas oportunidades
        service.notificarOportunidades(telegramBotToken)

    }    
}