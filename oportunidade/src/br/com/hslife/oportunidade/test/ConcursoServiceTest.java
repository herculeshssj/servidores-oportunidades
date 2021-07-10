package br.com.hslife.oportunidade.test;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ConcursoServiceTest {

    public static void main(String args[]) throws IOException{
        String link = "https://www.pciconcursos.com.br/concursos/norte/";

        Document doc = Jsoup.connect(link).get();

        Elements contents = doc.select(".ca");
        //System.out.println(contents.first());
        

        for (Iterator<Element> i = contents.iterator(); i.hasNext(); ) {
            Element content = i.next();

            System.out.println("--------------------------------------------------");
            System.out.println(content.select("a").first().text()); // Título
            System.out.println(content.select("a").first().attr("href")); // Link
            System.out.println(content.select(".cc").text()); // Estado
            System.out.println(content.select(".cd").text()); // Vagas, cargos e salários
            System.out.println(content.select(".ce").text()); // período de inscrição
        } 
    }
    
}
