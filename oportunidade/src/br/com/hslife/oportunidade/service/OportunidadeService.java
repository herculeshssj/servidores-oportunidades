package br.com.hslife.oportunidade.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hslife.oportunidade.model.Oportunidade;
import br.com.hslife.oportunidade.repository.OportunidadeRepository;

@Service
public class OportunidadeService {

    @Autowired
    OportunidadeRepository oportunidadeRepository;

    public void buscarOportunidades(String link) {

        try {

            List<Oportunidade> listaOportunidade = new ArrayList<>();

            Document doc = Jsoup.connect(link).get();

            Elements contents = doc.select(".tileContent");

            for (Iterator<Element> i = contents.iterator(); i.hasNext();) {
                Element content = i.next();

                Oportunidade op = Oportunidade.builder().titulo(content.select(".tileHeadline").text())
                        .descricao(content.select(".tileBody").text())
                        .link(content.select(".tileHeadline").select("a").first().attr("href")).build();

                // Trata os valores nulos e vazios
                op.setTitulo(op.getTitulo() == null || op.getTitulo().isEmpty() ? "-" : op.getTitulo());
                op.setDescricao(op.getDescricao() == null || op.getDescricao().isEmpty() ? "-" : op.getDescricao());
                op.setLink(op.getLink() == null || op.getLink().isEmpty() ? "-" : op.getLink());

                // Gera o hash a partir das informações inseridas
                op.setHash(this.SHA256(op.toString()));

                // Setando os demais atributos
                op.setDataCadastro(LocalDate.now());
                op.setArquivado(false);
                op.setEnviado(false);

                listaOportunidade.add(op);
            }

            // Salva todas as oportunidades
            oportunidadeRepository.salvarOportunidades(listaOportunidade);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Retorna o texto criptografado em SHA-256
     */
    private String SHA256(String texto) {
        String sen = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            BigInteger hash = new BigInteger(1, md.digest(texto.getBytes()));
            sen = hash.toString(16);
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return sen;
    }

    /*
     * Notifica aos usuários das novas oportunidades disponíveis no site
     */
    public void notificarOportunidades(String telegramBotToken, Long channelID) {
        List<Oportunidade> listaOportunidades = oportunidadeRepository.buscarOportunidadesNaoEnviadas();

        // Criação do objeto bot com as informações de acesso
        TelegramBot bot = new TelegramBot(telegramBotToken);

        listaOportunidades.forEach(oportunidade -> {

            // envio da mensagem para o canal
            SendResponse sendResponse = bot.execute(new SendMessage(channelID, oportunidade.toString()));

            // verificação de mensagem enviada com sucesso
            if (sendResponse.isOk()) {
                // Seta a oportunidade como enviada
                oportunidadeRepository.atualizarOportunidadeEnviada(oportunidade);
            }

            // Faz uma pausa de 5 segundos
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ie) {
                System.out.println("Erro no timer de pausa...");
                ie.printStackTrace();
            }    

        });

    }
}
