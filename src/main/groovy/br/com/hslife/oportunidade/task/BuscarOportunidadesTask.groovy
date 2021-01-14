package br.com.hslife.oportunidade.task

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

@Component
public class BuscarOportunidadesTask {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")

    @Scheduled(fixedRate = 60000l)
    public void buscarOportunidades() {
        System.out.println("Iniciando a busca pelas oportunidades... :: Hora local - " + dateTimeFormatter.format(LocalDateTime.now()))

        // Traz as variáveis de ambiente
        def envvars = System.getenv()



        System.out.println("Finalizado a busca pelas oportunidades... :: Hora local - " + dateTimeFormatter.format(LocalDateTime.now()))
    }
}
