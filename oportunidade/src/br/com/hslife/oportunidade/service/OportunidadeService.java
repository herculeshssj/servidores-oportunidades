package br.com.hslife.oportunidade.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    
            for (Iterator<Element> i = contents.iterator(); i.hasNext(); ) {
                Element content = i.next();
    
                Oportunidade op = Oportunidade.builder()
                    .titulo(content.select(".tileHeadline").text())
                    .descricao(content.select(".tileBody").text())
                    .link(content.select(".tileHeadline").select("a").first().attr("href"))
                    .build();

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

    public void notificarOportunidades(String telegramBotToken, Long channelID) {
        System.out.println("Notificação enviada!");
    }
    /*
        Notifica aos usuários das novas oportunidades disponíveis no site
    *
    void notificarOportunidades(String telegramBotToken, Long channelID) {
        List<Oportunidade> listaOportunidades = this.recuperarNovasOportunidades()

        String getResult = new URL('https://api.telegram.org/bot' + telegramBotToken + '/getUpdates').text

        def jsonSlurper = new JsonSlurper()
        def object = jsonSlurper.parseText(getResult)

        if (object.ok) {
            
            // Grava os chats IDs
            Set<Long> chatIds = new HashSet<Long>()
            for (it in object.result) {
                if (it.message != null) {
                    chatIds.add(it.message.chat.id)
                } else if (it.channel_post != null) {
                    chatIds.add(it.channel_post.chat.id)
                }
            }

            // Inclui o Chat ID do canal caso tenha sido informado
            if (channelID != null)
                chatIds.add(channelID)

            // Itera os chat Ids para enviar a notificação das oportunidades para os inscritos
            String postResult

            for (Oportunidade op : listaOportunidades) {
                
                // Lista de chats enviados
                Set<Long> chatsEnviados = new HashSet<Long>()

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

                    if (objectResult.ok) {
                        chatsEnviados.add(chatID)
                    }
                }
                
                if (chatsEnviados.size() != 0 && chatsEnviados.size() == chatIds.size()) {
                    this.atualizarOportunidade(op)
                }
                
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
    *
    private List<Oportunidade> recuperarNovasOportunidades() {
        return oportunidadeRepository.findAllNaoEnviados()
    }

    private String generateMD5(String s) {
        MessageDigest digest = MessageDigest.getInstance("MD5")
        digest.update(s.bytes);
        return new BigInteger(1, digest.digest()).toString(16).padLeft(32, '0')
    }

    private void atualizarOportunidade(Oportunidade oportunidade) {
        oportunidade.enviado = true
        oportunidadeRepository.save(oportunidade)
    }

    */
}
