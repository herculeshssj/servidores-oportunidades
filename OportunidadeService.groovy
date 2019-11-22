import java.security.MessageDigest
import groovy.util.XmlSlurper
import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import Oportunidade
import TelegramMessage

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
    void notificarOportunidades(String telegramBotToken) {
        List<Oportunidade> listaOportunidades = this.recuperarNovasOportunidades()
        
        OportunidadeRepository repository = new OportunidadeRepository()

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

            for (Oportunidade op : listaOportunidades) {
                
                for ( chatID in chatIds ) {
                    
                    TelegramMessage telegramMessage = new TelegramMessage()
                    telegramMessage.chat_id = chatID
                    telegramMessage.text = op.toString()

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

                    def objectResult = jsonSlurper.parseText(postResult)
                }
                
                repository.atualizarOportunidade(op)

                // Pausa de 5 segundo entre as requisições
                sleep(5000)
            }

        } else {
            println "Falha na requisição! Saindo..."
        }
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