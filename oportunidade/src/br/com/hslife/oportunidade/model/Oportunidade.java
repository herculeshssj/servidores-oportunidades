package br.com.hslife.oportunidade.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.openxava.model.Identifiable;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="selecoes")
@Getter @Setter
public class Oportunidade extends Identifiable {
    
    @Column(columnDefinition = "text", nullable = false)
    String titulo;

    @Column(columnDefinition = "text", nullable = false)
    String descricao;

    @Column
    String uf;

    @Column
    String periodoInscricao;

    @Column(columnDefinition = "text")
    String link;

    @Column
    boolean enviado;

    @Column(nullable = false)
    String hash;

    @Column
    LocalDate dataCadastro;

    @Column
    boolean arquivado;

    public Oportunidade() {
        enviado = false;
        dataCadastro = LocalDate.now();
        arquivado = false;
    }

    public String toString() {
        return this.titulo + "\n\r" + this.descricao + "\n\r" + this.link;
    }
}
