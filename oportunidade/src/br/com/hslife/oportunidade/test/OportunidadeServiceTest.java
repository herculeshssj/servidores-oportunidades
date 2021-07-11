package br.com.hslife.oportunidade.test;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class OportunidadeServiceTest {

    public static void main(String args[]) throws IOException {
        String link = "https://www.gov.br/servidor/pt-br/centrais-de-conteudo/oportunidades/divulgacao/cessao-1/cessao";

        Document doc = Jsoup.connect(link).get();

        Elements contents = doc.select(".tileContent");

        for (Iterator<Element> i = contents.iterator(); i.hasNext(); ) {
            Element content = i.next();

            System.out.println("--------------------------------------------------");
            System.out.println("Título: " + content.select(".tileHeadline").text());
            System.out.println("Descrição: " + content.select(".tileBody").text());
            System.out.println("Link: " + content.select(".tileHeadline").select("a").first().attr("href"));
        } 

        //System.out.println(contents.first().select(".tileHeadline").text());
        //System.out.println(contents.first().select(".tileBody").text());
        //System.out.println(contents.first().select(".tileHeadline").select("a").first().attr("href"));

        /*
        List<Oportunidade> listaOportunidade = new ArrayList<Oportunidade>()

        def tagsoupParser = new org.ccil.cowan.tagsoup.Parser()

        def parser = new XmlSlurper(tagsoupParser)

        def html = parser.parse(link)

        Oportunidade op = null

        html.'**'.findAll { it.@class == 'tileContent' }.each {
            
            op = new Oportunidade()
            op.titulo = it.h2.a.text()
            op.descricao = it.p.span.text()
            op.link = it.h2.a.@href.text()
            op.hash = this.generateMD5(op.toString())

            // Verifica se a oportunidade já foi cadastrada
            Oportunidade o = oportunidadeRepository.findByHash(op.hash)
            if (o == null) {
                listaOportunidade.add(op)
            }

        }

        this.salvarOportunidades(listaOportunidade)
        */
    }
    
}
