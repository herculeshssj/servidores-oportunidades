@Grab(group = 'org.mongodb', module = 'mongodb-driver', version = '3.11.2')
@Grab(group = 'org.ccil.cowan.tagsoup', module = 'tagsoup', version = '1.2')

import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import OportunidadeService
import Oportunidade
import TelegramMessage

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

        String getResult = new URL('https://api.telegram.org/bot' + telegramBotToken + '/getUpdates').text

        def jsonSlurper = new JsonSlurper()
        def object = jsonSlurper.parseText(getResult)

        if (object.ok) {
            
            // Grava os chats IDs
            Set<Long> chatIds = new HashSet<Long>()
            for (it in object.result) {
                chatIds.add(it.message.chat.id)
            }
            
            // Itera os chat Ids para enviar a notificação das oportunidades para os inscritos
            String postResult
            for ( chatID in chatIds ) {

                TelegramMessage telegramMessage = new TelegramMessage()
                telegramMessage.chat_id = chatID
                telegramMessage.text = "Olá, bem vindo ao mundo! :)"

                def json = JsonOutput.toJson(telegramMessage)

                ((HttpURLConnection)new URL('https://api.telegram.org/bot' + telegramBotToken + '/sendMessage').openConnection()).with({
                    requestMethod = 'POST'
                    doOutput = true
                    setRequestProperty('Content-Type', 'application/json')
                    outputStream.withPrintWriter({ printWriter -> 
                        printWriter.write(json)
                    })
                    postResult = inputStream.text
                })

                println postResult
                
            }

        } else {
            println "Falha na requisição! Saindo..."
        }
    }

    // Inicializa o serviço
    //OportunidadeService service = new OportunidadeService()

    // Busca as oportunidades
    //service.buscarOportunidades()

    // Envia a notificação das novas oportunidades
    //service.notificarOportunidades()
}