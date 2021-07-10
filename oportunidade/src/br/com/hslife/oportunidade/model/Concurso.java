package br.com.hslife.oportunidade.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.openxava.model.Identifiable;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="concursos")
@Getter @Setter
public class Concurso extends Identifiable {
    
    @Column(columnDefinition = "text", nullable = false)
    String titulo;

    @Column(columnDefinition = "text", nullable = false)
    String descricao;

    @Column(columnDefinition = "text", nullable = false)
    String link;

    @Column(length = 2, nullable = false)
    String uf;

    @Column(nullable = false)
    String hash;

    @Column
    LocalDate dataCadastro;

    @Column(columnDefinition = "text")
    String cargos;

    @Column
    Double salario;

    @Column(columnDefinition = "text")
    String nivelEscolaridade;

    @Column
    Integer vagas;

    @Column(columnDefinition = "text")
    String vagasCargosSalarios;

    @Column
    String periodoInscricao;

    @Column
    LocalDate dataTerminoInscricao;

    @Column
    Boolean arquivado;

    public Concurso() {
        dataCadastro = LocalDate.now();
        arquivado = false;
    }

    public String toString() {
        return this.titulo + "\n\r" + this.descricao + "\n\r" + "UF: " + this.uf + "\n\r" + this.link + "\n\r" + this.vagasCargosSalarios + "\n\r" + this.periodoInscricao;
    }
}
