@Grab(group = 'org.mongodb', module = 'mongodb-driver', version = '3.12.7')
@Grab(group = 'org.ccil.cowan.tagsoup', module = 'tagsoup', version = '1.2.1')

import OportunidadeService
import ConcursoService

public static void main(String... args) {

    // Inicializa as variáveis
    String telegramBotToken = null
    Long channelID = null
    boolean rodarEmThread = false

    // Obtém os parâmetros passados via linha de comando
    if (args != null) {
        switch(args.length) {
            case 1: 
                telegramBotToken = args[0];
                break;
            case 2:
                telegramBotToken = args[0];
                channelID = Long.parseLong(args[1]);
                break;
            case 3:
                telegramBotToken = args[0];
                channelID = Long.parseLong(args[1]);
                rodarEmThread = true;
                break;
            default:
                println "Nenhum parâmetro informado! Saindo..."
                System.exit(-1)
        }
    } else {
        println "Nenhum parâmetro informado! Saindo..."
        System.exit(-1)
    }

    // Inicia o loop que monitora as oportunidades
    do {
        
        // Inicializa os serviços
        OportunidadeService service = new OportunidadeService()
        ConcursoService serviceConcurso = new ConcursoService()

        List<String> linkOportunidades = new ArrayList<>()
        //linkOportunidades.add("https://www.gov.br/servidor/pt-br/centrais-de-conteudo/oportunidades/divulgacao/cessao-1/cessao")
        //linkOportunidades.add("https://www.gov.br/servidor/pt-br/centrais-de-conteudo/oportunidades/divulgacao/movimentacao/movimentacao")
        //linkOportunidades.add("https://www.gov.br/servidor/pt-br/centrais-de-conteudo/oportunidades/divulgacao/requisicao/requisicao")
        linkOportunidades.add("https://www.gov.br/servidor/pt-br/centrais-de-conteudo/oportunidades/divulgacao/ex-territorios/ex-territorios")

        // Busca as oportunidades
        for (String link : linkOportunidades) {
            service.buscarOportunidades(link)
        }        

        // Envia a notificação das novas oportunidades
        service.notificarOportunidades(telegramBotToken, channelID)

        // Atualiza o cadastro de concursos
        List<String> linkConcursos = new ArrayList<>()
        //linkConcursos.add("https://www.pciconcursos.com.br/concursos/")
        linkConcursos.add("https://www.pciconcursos.com.br/concursos/nacional/")
        //linkConcursos.add("https://www.pciconcursos.com.br/concursos/sudeste/")
        //linkConcursos.add("https://www.pciconcursos.com.br/concursos/sul/")
        //linkConcursos.add("https://www.pciconcursos.com.br/concursos/norte/")
        //linkConcursos.add("https://www.pciconcursos.com.br/concursos/nordeste/")
        //linkConcursos.add("https://www.pciconcursos.com.br/concursos/centrooeste/")

        for (String link : linkConcursos) {
            serviceConcurso.buscarConcursos(link)
        }

        System.sleep(3600000)
    } while (rodarEmThread)
     
}