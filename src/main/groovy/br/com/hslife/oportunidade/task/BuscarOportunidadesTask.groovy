package br.com.hslife.oportunidade.task

import br.com.hslife.oportunidade.service.OportunidadeService
import br.com.hslife.oportunidade.service.ConcursoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
public class BuscarOportunidadesTask {

    @Autowired
    ConcursoService concursoService

    @Autowired
    OportunidadeService oportunidadeService

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")

    @Scheduled(fixedRate = 360000l)
    public void buscarOportunidades() {
        System.out.println("Iniciando a busca pelas oportunidades... :: Hora local - " + dateTimeFormatter.format(LocalDateTime.now()))

        // Traz as variáveis de ambiente
        def envvars = System.getenv()

        def telegramBotToken = envvars.get("app.telegram.token")
        def channelID = envvars.get("app.telegram.channel")

        // Atualiza o cadastro de oportunidades
        List<String> linkOportunidades = new ArrayList<>()
        linkOportunidades.add("https://www.gov.br/servidor/pt-br/centrais-de-conteudo/oportunidades/divulgacao/cessao-1/cessao")
        linkOportunidades.add("https://www.gov.br/servidor/pt-br/centrais-de-conteudo/oportunidades/divulgacao/movimentacao/movimentacao")
        linkOportunidades.add("https://www.gov.br/servidor/pt-br/centrais-de-conteudo/oportunidades/divulgacao/requisicao/requisicao")
        linkOportunidades.add("https://www.gov.br/servidor/pt-br/centrais-de-conteudo/oportunidades/divulgacao/ex-territorios/ex-territorios")

        // Busca as oportunidades
        for (String link : linkOportunidades) {
            oportunidadeService.buscarOportunidades(link)
        }

        // Envia a notificação das novas oportunidades
        oportunidadeService.notificarOportunidades(telegramBotToken, Long.parseLong(channelID))

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
            concursoService.buscarConcursos(link)
        }

        System.out.println("Finalizado a busca pelas oportunidades... :: Hora local - " + dateTimeFormatter.format(LocalDateTime.now()))
    }
}
