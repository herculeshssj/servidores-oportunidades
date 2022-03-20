package br.com.hslife.oportunidade.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.hslife.oportunidade.service.ConcursoService;
import br.com.hslife.oportunidade.service.OportunidadeService;

@Component
public class BuscarOportunidadesTask {

    @Autowired
    OportunidadeService oportunidadeService;

    @Autowired
    ConcursoService concursoService;

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    
    public void buscarOportunidades() {
        System.out.println("Iniciando a busca pelas oportunidades... :: Hora local - " + dateTimeFormatter.format(LocalDateTime.now()));

        // Traz as variáveis de ambiente
        //Map<String, String> envvars = System.getenv();

        // O CANAL NO TELEGRAM FOI ENCERRADO!

        //String telegramBotToken = envvars.get("app.telegram.token");
        //String channelID = envvars.get("app.telegram.channel");

        // TODAS AS OPORTUNIDADES DIVULGADAS PELO GOVERNO FEDERAL AGORA ESTÃO NO APLICATIVO SOUGOV.BR

        /*
        // Atualiza o cadastro de oportunidades
        List<String> linkOportunidades = new ArrayList<>();
        linkOportunidades.add("https://www.gov.br/servidor/pt-br/centrais-de-conteudo/oportunidades/divulgacao/cessao-1/cessao");
        linkOportunidades.add("https://www.gov.br/servidor/pt-br/centrais-de-conteudo/oportunidades/divulgacao/movimentacao/movimentacao");
        linkOportunidades.add("https://www.gov.br/servidor/pt-br/centrais-de-conteudo/oportunidades/divulgacao/requisicao/requisicao");
        linkOportunidades.add("https://www.gov.br/servidor/pt-br/centrais-de-conteudo/oportunidades/divulgacao/ex-territorios/ex-territorios");

        // Busca as oportunidades
        for (String link : linkOportunidades) {
            oportunidadeService.buscarOportunidades(link);
        }

        // Envia a notificação das novas oportunidades
        //oportunidadeService.notificarOportunidades(telegramBotToken, Long.parseLong((channelID == null ? "0" : channelID)));
        */

        // Atualiza o cadastro de concursos
        List<String> linkConcursos = new ArrayList<>();
        linkConcursos.add("https://www.pciconcursos.com.br/concursos/");
        linkConcursos.add("https://www.pciconcursos.com.br/concursos/nacional/");
        linkConcursos.add("https://www.pciconcursos.com.br/concursos/sudeste/");
        linkConcursos.add("https://www.pciconcursos.com.br/concursos/sul/");
        linkConcursos.add("https://www.pciconcursos.com.br/concursos/norte/");
        linkConcursos.add("https://www.pciconcursos.com.br/concursos/nordeste/");
        linkConcursos.add("https://www.pciconcursos.com.br/concursos/centrooeste/");

        for (String link : linkConcursos) {
            concursoService.buscarConcursos(link);
        }

        // Arquivar oportunidades e concursos antigos
        //oportunidadeService.arquivarAntigos();
        concursoService.arquivarAntigos();

        System.out.println("Finalizado a busca pelas oportunidades... :: Hora local - " + dateTimeFormatter.format(LocalDateTime.now()));
    }
}