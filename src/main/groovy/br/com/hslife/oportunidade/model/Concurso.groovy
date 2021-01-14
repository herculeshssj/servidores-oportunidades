package br.com.hslife.oportunidade.model

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="concursos")
class Concurso {

    @Id
    String id
    String titulo
    String descricao
    String link
    String uf
    String hash
    Date dataCadastro
    String cargos
    Double salario
    String nivelEscolaridade
    Integer vagas
    String vagasCargosSalarios
    String periodoInscricao
    Date dataTerminoInscricao
    Boolean arquivado

    public Concurso() {
        dataCadastro = new Date()
        arquivado = false
    }

    String toString() {
        return this.titulo + "\n\r" + this.descricao + "\n\r" + "UF: " + this.uf + "\n\r" + this.link + "\n\r" + this.vagasCargosSalarios + "\n\r" + this.periodoInscricao
    }
}